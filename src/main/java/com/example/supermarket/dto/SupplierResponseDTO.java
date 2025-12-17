package com.example.supermarket.dto;

public class SupplierResponseDTO {
    private Integer supplierID;
    private String supplierName;
    private String phone;
    private String address;

    public SupplierResponseDTO() {
    }

    public SupplierResponseDTO(Integer supplierID, String supplierName, String phone, String address) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.phone = phone;
        this.address = address;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
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
}
