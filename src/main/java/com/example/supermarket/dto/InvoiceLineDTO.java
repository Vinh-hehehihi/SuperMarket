package com.example.supermarket.dto;

import lombok.Data;

@Data
public class InvoiceLineDTO {
    private Integer productID;
    private Integer quantity;
    private Double unitPrice;
}
