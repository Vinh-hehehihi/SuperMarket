package com.example.supermarket.service.impl;

import com.example.supermarket.entity.Invoice;
import com.example.supermarket.entity.InvoiceLine;
import com.example.supermarket.repository.InvoiceLineRepository;
import com.example.supermarket.service.IInvoiceLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceLineService implements IInvoiceLineService {

    @Autowired
    private InvoiceLineRepository invoiceLineRepository;


    @Override
    public List<InvoiceLine> getAllByInvoice(Invoice invoice) {
        return invoiceLineRepository.findByInvoice(invoice);
    }

    @Override
    public InvoiceLine save(InvoiceLine invoiceLine) {
        return invoiceLineRepository.save(invoiceLine);
    }

    @Override
    public void delete(Integer id) {
        invoiceLineRepository.deleteById(id);
    }
}
