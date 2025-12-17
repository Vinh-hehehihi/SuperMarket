package com.example.supermarket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "Stall")
@Getter
@Setter
public class Stall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stallID;

    private String stallCode;
    @Nationalized
    private String stallName;
    private String locationNote;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;
}
