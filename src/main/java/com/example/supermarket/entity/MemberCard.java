package com.example.supermarket.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "MemberCard")
public class MemberCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardID;

    private String cardNumber;
    private Date issuedAt;
    private Date expiryAt;
    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "memberID", unique = true)
    private Member member;
}
