package com.example.supermarket.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
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
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role{
        ADMIN,
        INVENTORY,
        SELLER
    }

    private Boolean isActive = true;
}
