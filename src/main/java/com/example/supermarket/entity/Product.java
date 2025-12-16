package com.example.supermarket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.Date;

@Entity
@Table(name = "Product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productID;

    @Column(unique = true)
    private String productCode;
    @Nationalized
    private String productName;
    @Nationalized
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
