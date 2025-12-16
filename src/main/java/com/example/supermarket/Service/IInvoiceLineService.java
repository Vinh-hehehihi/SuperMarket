package com.example.supermarket.service;

import com.example.supermarket.entity.Invoice;
import com.example.supermarket.entity.InvoiceLine;

import java.util.List;

public interface IInvoiceLineService {
    List<InvoiceLine> getAllByInvoice(Invoice invoice);
    InvoiceLine save(InvoiceLine invoiceLine);
    void delete(Integer id);
}
