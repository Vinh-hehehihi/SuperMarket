package com.example.supermarket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "Supplier")
@Getter
@Setter
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplierID;
    private String supplierCode;
    @Nationalized
    private String supplierName;
    @Nationalized
    private String contactName;
    private String phone;
    private String email;
    @Nationalized
    private String address;
}
