package com.example.supermarket.service;

import com.example.supermarket.dto.InvoiceDTO;
import com.example.supermarket.entity.Invoice;

public interface IInvoiceService {

    Invoice createInvoice(InvoiceDTO invoiceDTO);
}
