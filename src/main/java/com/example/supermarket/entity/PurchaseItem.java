package com.example.supermarket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PurchaseItem")
@Getter
@Setter
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
