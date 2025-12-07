package com.example.supermarket.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeID;

    private String employeeCode;
    private String fullName;
    private String phone;
    private String email;

    private String username;
    private String passwordHash;
    private String role;

    private Boolean isActive = true;
}
