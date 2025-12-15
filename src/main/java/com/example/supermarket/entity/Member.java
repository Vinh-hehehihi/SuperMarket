package com.example.supermarket.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberID;

    private String memberCode;
    private String fullName;
    private String gender;
    private Date birthDate;
    private Integer loyaltyPoints;
    private Boolean isActive = true;

    @OneToOne(mappedBy = "member")
    private MemberCard memberCard;
}
