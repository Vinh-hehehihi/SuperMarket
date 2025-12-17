package com.example.supermarket.service.impl;

import com.example.supermarket.entity.Employee;
import com.example.supermarket.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String emailOrUserName) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(emailOrUserName)
                .or(()-> employeeRepository.findByEmail(emailOrUserName))
                .orElseThrow(()-> new UsernameNotFoundException("Tài khoản không tồn tại"));
        return new EmployeeUserDetails(employee);
    }
}
