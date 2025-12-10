package com.example.supermarket.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Stall")
public class Stall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stallID;

    private String stallCode;
    private String stallName;
    private String locationNote;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;
}
