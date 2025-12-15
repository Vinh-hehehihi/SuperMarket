package com.example.supermarket.service;

import com.example.supermarket.dto.EmployeeDTO;
import com.example.supermarket.dto.EmployeeUpdateDTO;
import com.example.supermarket.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmployeeService {
    Page<Employee> search(String keyword, String role, Pageable pageable);
    Employee createEmployee(EmployeeDTO dto);
    Employee updateEmployee(EmployeeUpdateDTO dto);
    Employee deleteEmployee(Integer id); // Soft delete
    EmployeeDTO getEmployeeDetail(Integer id);
}
