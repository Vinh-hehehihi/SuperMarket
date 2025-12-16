package com.example.supermarket.dto;

import lombok.Data;
import java.util.Date;

@Data
public class MemberCardDTO {
    private Integer cardID;
    private String cardNumber;
    private Date issuedAt;
    private Date expiryAt;
    private Boolean isActive;
    private Integer memberID;
}