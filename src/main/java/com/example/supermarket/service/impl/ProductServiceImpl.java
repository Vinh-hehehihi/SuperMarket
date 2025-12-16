package com.example.supermarket.service.impl;

import com.example.supermarket.dto.ProductRequestDTO;
import com.example.supermarket.dto.ProductResponseDTO;
import com.example.supermarket.entity.Product;
import com.example.supermarket.entity.Supplier;
import com.example.supermarket.repository.ProductRepository;
import com.example.supermarket.repository.SupplierRepository;
import com.example.supermarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // Đánh dấu đây là Bean Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    // --- Hàm phụ: Chuyển đổi Entity -> ResponseDTO ---
    private ProductResponseDTO mapToResponse(Product p) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductID(p.getProductID());
        dto.setProductCode(p.getProductCode());
        dto.setProductName(p.getProductName());
        dto.setUnit(p.getUnit());
        dto.setUnitPrice(p.getUnitPrice());
        dto.setStockQuantity(p.getStockQuantity());
        dto.setCostPrice(p.getCostPrice());


        //Lấy tên NCC để hiển thị
        if (p.getSupplier() != null) {
            dto.setSupplierName(p.getSupplier().getSupplierName());
        }
        return dto;
    }

    // --- Triển khai các hàm từ Interface ---

    @Override
    public List<ProductResponseDTO> findAllOrSearch(String keyword) {
        List<Product> list;
        if (keyword != null && !keyword.trim().isEmpty()) {
            // Gọi Repository tìm kiếm
            list = productRepository.findByProductNameContainingIgnoreCaseOrProductCodeContainingIgnoreCase(keyword.trim(), keyword.trim());
        } else {
            // Lấy tất cả
            list = productRepository.findAll();
        }
        // Stream convert sang DTO
        return list.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public ProductRequestDTO getById(Integer id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + id));

        ProductRequestDTO dto = new ProductRequestDTO();
        dto.setProductID(p.getProductID());
        dto.setProductCode(p.getProductCode());
        dto.setProductName(p.getProductName());
        dto.setUnit(p.getUnit());
        dto.setUnitPrice(p.getUnitPrice());
        dto.setCostPrice(p.getCostPrice());

        // Gán ID Supplier để Dropdown tự chọn đúng
        if (p.getSupplier() != null) {
            dto.setSupplierID(p.getSupplier().getSupplierID());
        }
        return dto;
    }

    @Override
    public void save(ProductRequestDTO dto) {
        Product p;

        // Kiểm tra Update hay Create
        if (dto.getProductID() != null) {
            p = productRepository.findById(dto.getProductID())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy SP để sửa"));
        } else {
            p = new Product();
            p.setStockQuantity(0); // Mặc định tồn kho = 0 khi tạo mới
        }

        // Map dữ liệu từ DTO sang Entity
        p.setProductCode(dto.getProductCode());
        p.setProductName(dto.getProductName());
        p.setUnit(dto.getUnit());
        p.setUnitPrice(dto.getUnitPrice());
        p.setCostPrice(dto.getCostPrice());

        // Xử lý liên kết Nhà Cung Cấp
        if (dto.getSupplierID() != null) {
            Supplier s = supplierRepository.findById(dto.getSupplierID())
                    .orElseThrow(() -> new RuntimeException("Nhà cung cấp không tồn tại"));
            p.setSupplier(s);
        }

        productRepository.save(p);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);

    }
}
