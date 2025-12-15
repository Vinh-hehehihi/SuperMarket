package com.example.supermarket.controller;

import com.example.supermarket.dto.EmployeeDTO;
import com.example.supermarket.dto.EmployeeUpdateDTO;
import com.example.supermarket.entity.Employee;
import com.example.supermarket.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/employees")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping
    public String listEmployees(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Page<Employee> employeePage = employeeService.search(keyword, null, PageRequest.of(page, 5));

        model.addAttribute("employees", employeePage);
        model.addAttribute("keyword", keyword);
        return "employee/list";
    }
    @GetMapping("/detail/{id}")
    public String viewEmployeeDetail(@PathVariable Integer id, Model model) {
        EmployeeDTO dto = employeeService.getEmployeeDetail(id);
        model.addAttribute("employee", dto);
        return "employee/detail";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employeeDTO", new EmployeeDTO());
        return "employee/form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute EmployeeDTO dto) {
        employeeService.createEmployee(dto);
        return "redirect:/admin/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        EmployeeDTO dto = employeeService.getEmployeeDetail(id);
        model.addAttribute("employeeDTO", dto);
        return "employee/form";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute EmployeeUpdateDTO dto) {
        employeeService.updateEmployee(dto);
        return "redirect:/admin/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return "redirect:/admin/employees";
    }
}