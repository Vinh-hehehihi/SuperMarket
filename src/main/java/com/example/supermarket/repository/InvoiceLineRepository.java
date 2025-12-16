package com.example.supermarket.repository;

import com.example.supermarket.entity.Invoice;
import com.example.supermarket.entity.InvoiceLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceLineRepository extends JpaRepository<InvoiceLine, Integer> {
    List<InvoiceLine> findByInvoice(Invoice invoice);
}
