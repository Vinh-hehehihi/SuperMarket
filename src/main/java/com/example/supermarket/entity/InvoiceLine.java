package com.example.supermarket.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="InvoiceLine")
@Data
public class InvoiceLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoiceLineID;

    private Integer quantity;
    private Double unitPrice;

    @ManyToOne
    @JoinColumn(name = "invoiceID")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;
}
