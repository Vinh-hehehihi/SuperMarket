package com.example.supermarket.service;

import com.example.supermarket.dto.PurchaseOrderRequestDTO;
import com.example.supermarket.entity.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {
    void createImportInvoice(PurchaseOrderRequestDTO request);
    List<PurchaseOrder> getAllOrders();
    PurchaseOrder getOrderById(Integer id);
}