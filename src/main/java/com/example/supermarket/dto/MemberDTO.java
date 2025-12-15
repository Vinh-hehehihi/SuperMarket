package com.example.supermarket.dto;

import com.example.supermarket.entity.MemberCard;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
public class MemberDTO {
    private Integer memberID;
    private String memberCode;
    private String fullName;
    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    private Integer loyaltyPoints;
    private Boolean isActive;
    private MemberCard memberCard;
}