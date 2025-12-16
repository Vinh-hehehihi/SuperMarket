package com.example.supermarket.controller;

import com.example.supermarket.dto.ProductRequestDTO;
import com.example.supermarket.service.ProductService;
import com.example.supermarket.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private SupplierService supplierService;

    private void addSuppliersToModel(Model model) {
    }

    // 1. DANH SÁCH
    @GetMapping
    public String list(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("products", productService.findAllOrSearch(keyword));
        model.addAttribute("keyword", keyword);
        return "product/list";
    }

    // 2. FORM MỚI
    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("productRequest", new ProductRequestDTO());
        model.addAttribute("isEdit", false);
        // Load danh sách NCC cho dropdown
        model.addAttribute("listSuppliers", supplierService.getAllSuppliers());
        return "product/new-edit";
    }

    // 3. LƯU
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("productRequest") ProductRequestDTO dto,
                       BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            model.addAttribute("listSuppliers", supplierService.getAllSuppliers()); // Load lại nếu lỗi
            return "product/new-edit";
        }
        productService.save(dto);
        return "redirect:/products";
    }

    // 4. FORM SỬA
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("productRequest", productService.getById(id));
        model.addAttribute("isEdit", true);
        model.addAttribute("listSuppliers", supplierService.getAllSuppliers());
        return "product/new-edit";
    }

    // 5. CẬP NHẬT
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("productRequest") ProductRequestDTO dto,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            model.addAttribute("listSuppliers", supplierService.getAllSuppliers());
            return "new-edit";
        }
        productService.save(dto);
        return "redirect:/products";
    }

    // 6. XÓA
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/products";
    }
}