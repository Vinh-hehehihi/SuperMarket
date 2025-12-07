package com.example.supermarket.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryID;

    @Column(nullable = false, unique = true)
    private String categoryCode;

    @Column(nullable = false)
    private String categoryName;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Stall> stalls;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
