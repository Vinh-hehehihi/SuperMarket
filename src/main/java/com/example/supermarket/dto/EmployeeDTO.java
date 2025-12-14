package com.example.supermarket.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Integer employeeID;
    private String employeeCode;
    private String fullName;
    private String phone;
    private String email;
    private String username;
    private String password;
    private String role;
    private Boolean isActive;
}