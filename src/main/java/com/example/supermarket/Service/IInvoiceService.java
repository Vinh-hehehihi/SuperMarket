package com.example.supermarket.service;

import com.example.supermarket.dto.InvoiceDTO;
import com.example.supermarket.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IInvoiceService {

    Invoice createInvoice(InvoiceDTO invoiceDTO);
    List<Invoice> getAllInvoices();
    Invoice getById(int id);
    Page<Invoice> searchInvoices(
            Integer memberId,
            Integer employeeId,
            Date startDate,
            Date endDate,
            Pageable pageable
    );
}
