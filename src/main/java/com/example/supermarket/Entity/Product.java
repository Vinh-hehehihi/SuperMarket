package com.example.supermarket.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productID;

    private String productCode;
    private String productName;
    private String unit;
    private Double unitPrice;
    private Double costPrice;
    private Integer stockQuantity;
    private Date expiryDate;
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplierID")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "stallID")
    private Stall stall;
}
