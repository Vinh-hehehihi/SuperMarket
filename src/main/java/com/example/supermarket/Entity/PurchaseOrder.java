package com.example.supermarket.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PurchaseOrder")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer poid;

    private String poNumber;
    private Date poDate;
    private Double totalAmount;
    private String status;

    @ManyToOne
    @JoinColumn(name = "supplierID")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "createdBy")
    private Employee employee;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<PurchaseItem> items;
}
