package com.example.supermarket.dto;

import java.util.ArrayList;
import java.util.List;


public class PurchaseOrderRequestDTO {
    private Integer supplierID;
    private String notes;
    private List<PurchaseItemDTO> items = new ArrayList<>();

    public PurchaseOrderRequestDTO() {
    }

    public PurchaseOrderRequestDTO(Integer supplierID, String notes, List<PurchaseItemDTO> items) {
        this.supplierID = supplierID;
        this.notes = notes;
        this.items = items;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<PurchaseItemDTO> getItems() {
        return items;
    }

    public void setItems(List<PurchaseItemDTO> items) {
        this.items = items;
    }
}
