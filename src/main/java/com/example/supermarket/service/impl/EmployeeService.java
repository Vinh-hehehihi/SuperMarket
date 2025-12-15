package com.example.supermarket.service.impl;

import com.example.supermarket.dto.EmployeeDTO;
import com.example.supermarket.dto.EmployeeUpdateDTO;
import com.example.supermarket.entity.Employee;
import com.example.supermarket.repository.EmployeeRepository;
import com.example.supermarket.service.IEmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> search(String keyword, String role, Pageable pageable) {
        return employeeRepository.searchEmployee(keyword, role, pageable);
    }

    @Override
    public Employee createEmployee(EmployeeDTO dto) {

        if (employeeRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã được sử dụng");
        }
        if (employeeRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username đã được sử dụng");
        }
        if (employeeRepository.findByEmployeeCode(dto.getEmployeeCode()).isPresent()) {
            throw new RuntimeException("Mã nhân viên đã tồn tại");
        }

        Employee employee = new Employee();

        employee.setEmployeeCode(dto.getEmployeeCode().trim().replaceAll("\\s{2,}", " ").toUpperCase());
        employee.setFullName(dto.getFullName().trim().replaceAll("\\s{2,}", " "));
        employee.setPhone(dto.getPhone().trim());
        employee.setEmail(dto.getEmail().trim().replaceAll("\\s{2,}", " "));

        employee.setUsername(dto.getUsername().trim());
        employee.setPassword(dto.getPassword());
//        employee.setPasswordHash(encoder.encode(dto.getPassword()));
        employee.setRole(dto.getRole());

        employee.setIsActive(true);

        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(EmployeeUpdateDTO dto) {
        Employee existingEmployee = employeeRepository.findById(dto.getEmployeeID())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên này"));


        existingEmployee.setFullName(dto.getFullName().trim().replaceAll("\\s{2,}", " "));
        existingEmployee.setPhone(dto.getPhone().trim());
        existingEmployee.setEmail(dto.getEmail().trim().replaceAll("\\s{2,}", " "));
        existingEmployee.setUsername(dto.getUsername().trim());
        existingEmployee.setRole(dto.getRole());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            existingEmployee.setPassword(dto.getPassword());
        }

//        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
//            existingEmployee.setPasswordHash(encoder.encode(dto.getPassword().trim()));
//        }

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public Employee deleteEmployee(Integer id) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên này"));

        existingEmployee.setIsActive(false);
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeDetail(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với ID: " + id));

        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeID(employee.getEmployeeID());
        dto.setEmployeeCode(employee.getEmployeeCode());
        dto.setFullName(employee.getFullName());
        dto.setPhone(employee.getPhone());
        dto.setEmail(employee.getEmail());
        dto.setUsername(employee.getUsername());
        dto.setRole(employee.getRole());
        dto.setIsActive(employee.getIsActive());

        return dto;
    }

    @Override
    public Employee findByUsernameOrEmail(String input) {
        return employeeRepository.findByUsername(input)
                .or(()-> employeeRepository.findByEmail(input))
                .orElse(null);

    }

    @Override
    public Employee loginEmployee(String usernameOrEmail, String password) {
        Employee employee = findByUsernameOrEmail(usernameOrEmail);

        if(employee == null){
            return null;
        }

        if(!employee.getIsActive()){
            return null;
        }

        if (!passwordEncoder.matches(password, employee.getPassword())){
            return null;
        }

        return employee;

    }

    @Override
    public boolean authenticateEmployee(String usernameOrEmail, String password, HttpSession session) {
        Employee employee = loginEmployee(usernameOrEmail,password);
        if(employee == null || !employee.getIsActive()){
            return false;
        }

        EmployeeUserDetails userDetails = new EmployeeUserDetails(employee);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        return true;

    }
}