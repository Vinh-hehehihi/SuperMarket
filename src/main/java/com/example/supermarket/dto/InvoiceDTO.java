package com.example.supermarket.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class InvoiceDTO {
    private String invoiceNumber;
    private Date invoiceDate;
    private String paymentMethod;
    private String notes;

    private Integer employeeID;
    private Integer memberID;

    private List<InvoiceLineDTO> invoiceLines = new ArrayList<>();
}
