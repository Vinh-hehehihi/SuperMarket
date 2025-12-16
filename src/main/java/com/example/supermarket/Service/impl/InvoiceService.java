package com.example.supermarket.service.impl;

import com.example.supermarket.dto.InvoiceDTO;
import com.example.supermarket.dto.InvoiceLineDTO;
import com.example.supermarket.entity.*;
import com.example.supermarket.repository.EmployeeRepository;
import com.example.supermarket.repository.InvoiceRepository;
import com.example.supermarket.repository.MemberRepository;
import com.example.supermarket.repository.ProductRepository;
import com.example.supermarket.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceService implements IInvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Invoice createInvoice(InvoiceDTO invoiceDTO) {

        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(invoiceDTO.getInvoiceNumber());
        invoice.setInvoiceDate(new Date());
        invoice.setPaymentMethod(invoiceDTO.getPaymentMethod());
        invoice.setNotes(invoiceDTO.getNotes());

        Employee employee =employeeRepository.findById(invoiceDTO.getEmployeeID())
                .orElseThrow(()-> new RuntimeException("Nhân viên không có"));
        invoice.setEmployee(employee);

        if (invoiceDTO.getMemberID() != null){
            Member member = memberRepository.findById(invoiceDTO.getMemberID())
                    .orElseThrow(()->new RuntimeException("Không tìm thấy member"));
            invoice.setMember(member);
        }

        List<InvoiceLine> lines = new ArrayList<>();
        double totalAmount = 0;

        for (InvoiceLineDTO lineDTO : invoiceDTO.getInvoiceLines()){
            Product product = productRepository.findById(lineDTO.getProductID())
                    .orElseThrow(()->new RuntimeException("Sản phẩm không tồn tại"));

            InvoiceLine invoiceLine = new InvoiceLine();
            invoiceLine.setInvoice(invoice);
            invoiceLine.setProduct(product);
            invoiceLine.setQuantity(lineDTO.getQuantity());
            invoiceLine.setUnitPrice(lineDTO.getUnitPrice());

            totalAmount += lineDTO.getQuantity() * lineDTO.getUnitPrice();

            lines.add(invoiceLine);

            product.setStockQuantity(product.getStockQuantity() - lineDTO.getQuantity());
            productRepository.save(product);
        }
        invoice.setInvoiceLines(lines);
        invoice.setTotalAmount(totalAmount);
        return invoiceRepository.save(invoice);
    }
}
