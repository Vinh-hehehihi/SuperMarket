package com.example.supermarket.Service.impl;

import com.example.supermarket.Entity.Employee;
import com.example.supermarket.Repository.EmployeeRepository;
import com.example.supermarket.Service.IEmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
