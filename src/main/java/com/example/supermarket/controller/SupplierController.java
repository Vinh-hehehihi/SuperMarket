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

    @GetMapping
    public String listSuppliers(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<SupplierResponseDTO> suppliers = supplierService.findAllOrSearch(keyword);
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("keyword", keyword);
        return "supplier/list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("supplierRequest", new SupplierRequestDTO());
        model.addAttribute("isEdit", false);
        model.addAttribute("supplierId", null);
        return "supplier/new_edit";
    }

    @PostMapping("/save")
    public String saveSupplier(@Valid @ModelAttribute("supplierRequest") SupplierRequestDTO requestDTO,
                               BindingResult bindingResult,
                               Model model) {

        supplierValidator.validate(requestDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "supplier/new_edit";
        }

        supplierService.save(requestDTO, null);
        return "redirect:/suppliers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        SupplierRequestDTO requestDTO = supplierService.getRequestDTOById(id);
        requestDTO.setSupplierID(id);
        model.addAttribute("supplierRequest", requestDTO);
        model.addAttribute("isEdit", true);
        model.addAttribute("supplierId", id);
        return "supplier/new_edit";
    }

    @PostMapping("/update/{id}")
    public String updateSupplier(@PathVariable("id") Integer id,
                                 @Valid @ModelAttribute("supplierRequest") SupplierRequestDTO requestDTO,
                                 BindingResult bindingResult,
                                 Model model) {
        requestDTO.setSupplierID(id);
        supplierValidator.validate(requestDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", true);
            model.addAttribute("supplierId", id);
            return "supplier/new_edit";
        }

        supplierService.save(requestDTO, id);
        return "redirect:/suppliers";
    }

    //XÓA
    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable("id") Integer id) {
        supplierService.delete(id);
        return "redirect:/suppliers";
    }


    // XEM CHI TIẾT
    @GetMapping("/detail/{id}")
    public String viewSupplierDetail(@PathVariable("id") Integer id, Model model) {
        SupplierRequestDTO supplier = supplierService.getRequestDTOById(id);
        supplier.setSupplierID(id);

        model.addAttribute("supplier", supplier);
        return "supplier/detail";
    }
}