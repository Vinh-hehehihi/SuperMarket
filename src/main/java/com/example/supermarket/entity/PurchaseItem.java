package com.example.supermarket.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PurchaseItem")
public class PurchaseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer purchaseItemID;

    private Integer quantity;
    private Double unitCost;

    @ManyToOne
    @JoinColumn(name = "poid")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;
}
