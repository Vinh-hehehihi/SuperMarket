package com.example.supermarket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "Employee")
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeID;

    private String employeeCode;
    @Nationalized
    private String fullName;
    private String phone;
    private String email;

    private String username;
    private String passwordHash;
    private String role;

    private Boolean isActive = true;
}
