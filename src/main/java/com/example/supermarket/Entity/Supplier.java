package com.example.supermarket.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplierID;

    private String supplierCode;
    private String supplierName;
    private String contactName;
    private String phone;
    private String email;
    private String address;
}
