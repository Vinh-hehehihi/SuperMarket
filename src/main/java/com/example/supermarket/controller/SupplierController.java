package com.example.supermarket.controller;

import com.example.supermarket.dto.SupplierRequestDTO;
import com.example.supermarket.dto.SupplierResponseDTO;
import com.example.supermarket.service.SupplierService;
import com.example.supermarket.validator.SupplierValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private SupplierValidator supplierValidator;

    // 1. READ/LIST & SEARCH (GET /suppliers)
    @GetMapping
    public String listSuppliers(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<SupplierResponseDTO> suppliers = supplierService.findAllOrSearch(keyword);
        model.addAttribute("suppliers", suppliers); // Danh sách DTO
        model.addAttribute("keyword", keyword);
        return "supplier/list"; // Trả về supplier/list.html
    }

    // 2. HIỂN THỊ FORM TẠO MỚI (GET /suppliers/new)
    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("supplierRequest", new SupplierRequestDTO());
        model.addAttribute("isEdit", false);
        model.addAttribute("supplierId", null);
        return "supplier/new_edit";
    }

    // 3. XỬ LÝ LƯU (CREATE)
    @PostMapping("/save")
    public String saveSupplier(@Valid @ModelAttribute("supplierRequest") SupplierRequestDTO requestDTO,
                               BindingResult bindingResult, // Chứa kết quả validate
                               Model model) {

        // Gọi custom validator
        supplierValidator.validate(requestDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            // Nếu có lỗi, trả về trang form kèm thông báo lỗi
            model.addAttribute("isEdit", false);
            return "supplier/new_edit"; // (Hoặc supplier/form nếu bạn đã đổi tên)
        }

        supplierService.save(requestDTO, null);
        return "redirect:/suppliers";
    }

    // 4. HIỂN THỊ FORM CHỈNH SỬA
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        SupplierRequestDTO requestDTO = supplierService.getRequestDTOById(id);

        // QUAN TRỌNG: Phải set ID vào DTO để khi submit form, ID này được gửi đi
        requestDTO.setSupplierID(id);

        model.addAttribute("supplierRequest", requestDTO);
        model.addAttribute("isEdit", true);
        model.addAttribute("supplierId", id);
        return "supplier/new_edit";
    }

    // 5. XỬ LÝ CẬP NHẬT (UPDATE)
    @PostMapping("/update/{id}")
    public String updateSupplier(@PathVariable("id") Integer id,
                                 @Valid @ModelAttribute("supplierRequest") SupplierRequestDTO requestDTO,
                                 BindingResult bindingResult,
                                 Model model) {

        // Gán ID từ URL vào DTO để Validator biết đây là ai
        requestDTO.setSupplierID(id);

        // Gọi custom validator
        supplierValidator.validate(requestDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", true);
            model.addAttribute("supplierId", id); // Cần truyền lại ID để form action đúng
            return "supplier/new_edit";
        }

        supplierService.save(requestDTO, id);
        return "redirect:/suppliers";
    }

    // 6. XÓA (GET /suppliers/delete/{id})
    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable("id") Integer id) {
        supplierService.delete(id);
        return "redirect:/suppliers";
    }


    // 7. XEM CHI TIẾT (GET /suppliers/detail/{id})
    @GetMapping("/detail/{id}")
    public String viewSupplierDetail(@PathVariable("id") Integer id, Model model) {
        // Tận dụng hàm getRequestDTOById vì nó lấy đủ hết thông tin (Email, Contact, Code...)
        SupplierRequestDTO supplier = supplierService.getRequestDTOById(id);

        // Gán ID thủ công vì hàm getRequestDTOById có thể chưa set ID vào DTO (tùy logic mapper cũ)
        supplier.setSupplierID(id);

        model.addAttribute("supplier", supplier);
        return "supplier/detail"; // Trả về file detail.html
    }
}