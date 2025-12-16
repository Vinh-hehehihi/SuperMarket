package com.example.supermarket.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class PurchaseItemDTO {
    private Integer productID;
    private Integer quantity;
    private BigDecimal unitCost;

    public PurchaseItemDTO() {
    }

    public PurchaseItemDTO(Integer productID, Integer quantity, BigDecimal unitCost) {
        this.productID = productID;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }
}