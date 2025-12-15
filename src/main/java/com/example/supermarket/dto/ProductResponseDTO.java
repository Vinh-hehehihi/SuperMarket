package com.example.supermarket.dto;

public class ProductResponseDTO {
    private Integer productID;
    private String productCode;
    private String productName;
    private String unit;
    private Double unitPrice;
    private Integer stockQuantity;
    private String supplierName;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Integer productID, String productCode, String productName, String unit, Double unitPrice, Integer stockQuantity, String supplierName) {
        this.productID = productID;
        this.productCode = productCode;
        this.productName = productName;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.supplierName = supplierName;
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

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
