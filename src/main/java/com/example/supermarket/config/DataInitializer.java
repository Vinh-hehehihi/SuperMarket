package com.example.supermarket.config;

import com.example.supermarket.entity.Employee;
import com.example.supermarket.repository.EmployeeRepository;
import com.example.supermarket.service.impl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        createDefaultAdmin();
    }

    private void createDefaultAdmin(){
        String adminEmail = "admin@gmail.com";
        Optional<Employee> admin = employeeRepository.findByEmail(adminEmail);
        if (admin.isEmpty()) {
            admin = Optional.of(new Employee());
            admin.get().setUsername("admin");
            admin.get().setFullName("admin");
            admin.get().setEmployeeCode("admin");
            admin.get().setEmail(adminEmail);
            admin.get().setPassword(passwordEncoder.encode("admin"));
            admin.get().setIsActive(true);
            admin.get().setPhone("0987654321");
            employeeService.createOrUpdate(admin.orElse(null));
            System.out.println("Admin create succesfully");
        }
    }
}
