package com.example.supermarket.Service;

import com.example.supermarket.Entity.Employee;
import jakarta.servlet.http.HttpSession;

public interface IEmployeeService {
    Employee findByUsernameOrEmail(String input);
    Employee loginEmployee (String usernameOrEmail, String password);
    boolean authenticateEmployee(String usernameOrEmail, String password, HttpSession session);
}
