package com.example.supermarket.service.impl;

import com.example.supermarket.dto.PurchaseItemDTO;
import com.example.supermarket.dto.PurchaseOrderRequestDTO;
import com.example.supermarket.entity.Product;
import com.example.supermarket.entity.PurchaseItem;
import com.example.supermarket.entity.PurchaseOrder;
import com.example.supermarket.entity.Supplier;
import com.example.supermarket.repository.ProductRepository;
import com.example.supermarket.repository.PurchaseOrderRepository;
import com.example.supermarket.repository.SupplierRepository;
import com.example.supermarket.service.PurchaseOrderService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date; // Dùng java.util.Date theo Entity
import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository poRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository poRepository,
                                    ProductRepository productRepository,
                                    SupplierRepository supplierRepository) {
        this.poRepository = poRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createImportInvoice(PurchaseOrderRequestDTO request) {
        //Validate
        if (request.getSupplierID() == null) throw new IllegalArgumentException("Vui lòng chọn Nhà cung cấp!");
        if (request.getItems() == null || request.getItems().isEmpty()) throw new IllegalArgumentException("Danh sách trống!");

        Supplier supplier = supplierRepository.findById(request.getSupplierID())
                .orElseThrow(() -> new IllegalArgumentException("NCC không tồn tại"));

        PurchaseOrder po = new PurchaseOrder();
        po.setPoNumber("PN-" + System.currentTimeMillis());
        po.setPoDate(new Date());
        po.setStatus("COMPLETED");
        po.setSupplier(supplier);

        List<PurchaseItem> itemList = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        // 3. Duyệt Items
        for (PurchaseItemDTO itemDTO : request.getItems()) {
            if (itemDTO.getProductID() == null || itemDTO.getQuantity() == null || itemDTO.getQuantity() <= 0) continue;

            // Xử lý giá an toàn
            BigDecimal importCostBigDec = (itemDTO.getUnitCost() != null) ? itemDTO.getUnitCost() : BigDecimal.ZERO;
            Double importCostDouble = importCostBigDec.doubleValue();

            Product product = productRepository.findById(itemDTO.getProductID())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy SP ID: " + itemDTO.getProductID()));

            // Cập nhật kho & giá vốn
            int currentStock = (product.getStockQuantity() == null) ? 0 : product.getStockQuantity();
            product.setStockQuantity(currentStock + itemDTO.getQuantity());
            product.setCostPrice(importCostDouble);
            productRepository.save(product);

            // Tạo Item
            PurchaseItem item = new PurchaseItem();
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setUnitCost(importCostDouble);
            item.setPurchaseOrder(po);
            itemList.add(item);
            totalAmount = totalAmount.add(importCostBigDec.multiply(BigDecimal.valueOf(itemDTO.getQuantity())));
        }

        if (itemList.isEmpty()) throw new IllegalArgumentException("Không có sản phẩm hợp lệ!");
        po.setItems(itemList);
        po.setTotalAmount(totalAmount.doubleValue());
        poRepository.save(po);
    }

    @Override
    public List<PurchaseOrder> getAllOrders() {
        return poRepository.findAll(Sort.by(Sort.Direction.DESC, "poDate"));
    }

    @Override
    public PurchaseOrder getOrderById(Integer id) {
        return poRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phiếu nhập ID: " + id));
    }
}