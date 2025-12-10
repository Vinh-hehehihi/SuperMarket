package com.example.supermarket.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
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
