package com.example.supermarket.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmployeeUpdateDTO {

    @NotNull(message = "ID nhân viên không được để trống")
    private Integer employeeID;

    @NotBlank(message = "Họ tên không được để trống")
    @Size(min = 2, max = 100, message = "Họ tên phải từ 2 đến 100 ký tự")
    private String fullName;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại phải gồm 10 chữ số và bắt đầu bằng số 0")
    private String phone;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Định dạng email không hợp lệ")
    private String email;

    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 4, max = 50, message = "Tên đăng nhập phải từ 4 đến 50 ký tự")
    private String username;


    @Size(min = 6, message = "Nếu thay đổi mật khẩu, độ dài phải từ 6 ký tự trở lên")
    private String password;

    @NotBlank(message = "Chức vụ không được để trống")
    private String role;
}