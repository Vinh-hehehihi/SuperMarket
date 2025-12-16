package com.example.supermarket.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequestDTO {
        private Integer productID;

        @NotBlank(message = "Mã sản phẩm không được để trống")
        private String productCode;

        @NotBlank(message = "Tên sản phẩm không được để trống")
        private String productName;

        private String unit;

        @NotNull(message = "Giá bán không được để trống")
        @Min(value = 0, message = "Giá bán phải lớn hơn 0")
        private Double unitPrice;

        private Double costPrice;

        @NotNull(message = "Vui lòng chọn Nhà cung cấp")
        private Integer supplierID;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(Integer productID, String productCode, String productName, String unit, Double unitPrice, Double costPrice, Integer supplierID) {
        this.productID = productID;
        this.productCode = productCode;
        this.productName = productName;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.costPrice = costPrice;
        this.supplierID = supplierID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }
}
