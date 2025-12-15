package com.example.supermarket.entity;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Employee")
@Getter
@Setter
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
    private String role;

    private Boolean isActive = true;
}
