package com.example.supermarket.controller;

import com.example.supermarket.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Inject các Repository đã có sẵn để đếm số lượng
    @Autowired private ProductRepository productRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private SupplierRepository supplierRepository;
    @Autowired private InvoiceRepository invoiceRepository;
    @Autowired private PurchaseOrderRepository purchaseOrderRepository;

    @GetMapping("/")
    public String index() {
        return "redirect:/home"; // Chuyển hướng về trang chủ quản lý
    }

    @GetMapping("/home")
    public String home(Model model) {
        // Sử dụng hàm .count() có sẵn mặc định của JpaRepository
        // Không cần viết thêm dòng code nào trong Repository của bạn
        model.addAttribute("countProducts", productRepository.count());
        model.addAttribute("countMembers", memberRepository.count());
        model.addAttribute("countEmployees", employeeRepository.count());
        model.addAttribute("countSuppliers", supplierRepository.count());
        model.addAttribute("countInvoices", invoiceRepository.count());
        model.addAttribute("countPOs", purchaseOrderRepository.count());

        return "home"; // Trả về file home.html
    }
}