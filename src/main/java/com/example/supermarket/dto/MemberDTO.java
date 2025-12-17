package com.example.supermarket.dto;

import com.example.supermarket.entity.MemberCard;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
public class MemberDTO {

    private Integer memberID;

    @NotBlank(message = "Mã thành viên không được để trống")
    @Size(min = 3, max = 20, message = "Mã thành viên phải từ 3 đến 20 ký tự")
    private String memberCode;

    @NotBlank(message = "Họ tên không được để trống")
    @Size(min = 2, max = 100, message = "Họ tên phải từ 2 đến 100 ký tự")
    private String fullName;

    @NotBlank(message = "Giới tính không được để trống")
    private String gender;

    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh phải là một ngày trong quá khứ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Min(value = 0, message = "Điểm tích lũy không được nhỏ hơn 0")
    private Integer loyaltyPoints;

    @NotNull(message = "Trạng thái hoạt động không được để trống")
    private Boolean isActive;


    @Valid
    private MemberCard memberCard;
}