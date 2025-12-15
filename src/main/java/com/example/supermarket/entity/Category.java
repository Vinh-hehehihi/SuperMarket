package com.example.supermarket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Entity
@Table(name = "Category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryID;

    @Column(nullable = false, unique = true)
    private String categoryCode;

    @Column(nullable = false)
    @Nationalized
    private String categoryName;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Stall> stalls;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
