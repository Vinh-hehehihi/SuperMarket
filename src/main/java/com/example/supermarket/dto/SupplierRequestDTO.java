package com.example.supermarket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SupplierRequestDTO {

    private Integer supplierID;

    @Size(max = 20, message = "Mã NCC không được quá 20 ký tự")
    private String supplierCode;

    @NotBlank(message = "Tên nhà cung cấp không được để trống")
    private String supplierName;


    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^\\d{10,11}$", message = "Số điện thoại phải gồm 10-11 chữ số")
    private String phone;

    private String address;
    private String contactName;

    @Pattern(regexp = "^$|^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email không đúng định dạng")
    private String email;

    public SupplierRequestDTO() {
    }

    public SupplierRequestDTO(String supplierCode, String supplierName, String phone, String address, String contactName, String email) {
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
        this.phone = phone;
        this.address = address;
        this.contactName = contactName;
        this.email = email;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}