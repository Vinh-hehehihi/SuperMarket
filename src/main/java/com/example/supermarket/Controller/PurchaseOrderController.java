package com.example.supermarket.controller;

import com.example.supermarket.dto.PurchaseOrderRequestDTO;
import com.example.supermarket.service.ProductService;
import com.example.supermarket.service.PurchaseOrderService;
import com.example.supermarket.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService poService;
    private final SupplierService supplierService;
    private final ProductService productService;

    // Constructor Injection
    public PurchaseOrderController(PurchaseOrderService poService, SupplierService supplierService, ProductService productService) {
        this.poService = poService;
        this.supplierService = supplierService;
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", poService.getAllOrders());
        return "purchase_order/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("poRequest", new PurchaseOrderRequestDTO());
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        // Giả sử hàm này lấy list products
        model.addAttribute("products", productService.findAllOrSearch(null));
        return "purchase_order/create";
    }

    // ... các imports

    @PostMapping("/save")
    public String saveOrder(@ModelAttribute("poRequest") PurchaseOrderRequestDTO dto,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        try {
            poService.createImportInvoice(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Nhập hàng thành công!");
            return "redirect:/purchase-orders";
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console để debug
            // Nếu lỗi, load lại dữ liệu cần thiết và trả về trang tạo mới
            model.addAttribute("errorMessage", "Lỗi: " + e.getMessage());

            // Load lại danh sách để dropdown không bị rỗng
            model.addAttribute("suppliers", supplierService.getAllSuppliers());
            model.addAttribute("products", productService.findAllOrSearch(null));

            // Trả về lại form để người dùng sửa
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