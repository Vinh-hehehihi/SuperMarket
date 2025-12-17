package com.example.supermarket.controller;

import com.example.supermarket.dto.PurchaseItemDTO; // Import class DTO con của bạn
import com.example.supermarket.dto.PurchaseOrderRequestDTO;
import com.example.supermarket.service.ProductService;
import com.example.supermarket.service.PurchaseOrderService;
import com.example.supermarket.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService poService;
    private final SupplierService supplierService;
    private final ProductService productService;

    public PurchaseOrderController(PurchaseOrderService poService, SupplierService supplierService, ProductService productService) {
        this.poService = poService;
        this.supplierService = supplierService;
        this.productService = productService;
    }

    private void loadCommonData(Model model) {
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        model.addAttribute("products", productService.findAllOrSearch(null));
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", poService.getAllOrders());
        return "purchase_order/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        PurchaseOrderRequestDTO poRequest = new PurchaseOrderRequestDTO();
        poRequest.setItems(new ArrayList<>());
        poRequest.getItems().add(new PurchaseItemDTO());

        model.addAttribute("poRequest", poRequest);
        loadCommonData(model);
        return "purchase_order/create";
    }

    @PostMapping("/save")
    public String saveOrder(@ModelAttribute("poRequest") PurchaseOrderRequestDTO dto,
                            @RequestParam(value = "addRow", required = false) String addRow,
                            @RequestParam(value = "removeRow", required = false) Integer removeRow,
                            Model model,
                            RedirectAttributes redirectAttributes) {

        if (addRow != null) {
            if (dto.getItems() == null) dto.setItems(new ArrayList<>());
            dto.getItems().add(new PurchaseItemDTO());

            loadCommonData(model);
            return "purchase_order/create";
        }


        if (removeRow != null) {
            if (dto.getItems() != null && removeRow >= 0 && removeRow < dto.getItems().size()) {
                dto.getItems().remove(removeRow.intValue());
            }

            loadCommonData(model);
            return "purchase_order/create";
        }

        try {
            if (dto.getItems() != null) {
                dto.getItems().removeIf(item -> item.getProductID() == null || item.getQuantity() == null);
            }

            poService.createImportInvoice(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Nhập hàng thành công!");
            return "redirect:/purchase-orders";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Lỗi: " + e.getMessage());
            loadCommonData(model);
            return "purchase_order/create";
        }
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("order", poService.getOrderById(id));
        return "purchase_order/detail";
    }

    @GetMapping("/inventory")
    public String inventoryReport(Model model) {
        model.addAttribute("products", productService.findAllOrSearch(null));
        return "purchase_order/inventory";
    }
}