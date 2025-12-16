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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

        if (invoiceRepository.existsByInvoiceNumber(invoiceDTO.getInvoiceNumber())) {
            throw new RuntimeException("Số hóa đơn đã tồn tại, vui lòng nhập số khác");
        }

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

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getById(int id) {
        return invoiceRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Hoa don khong ton tai"));
    }

    @Override
    public Page<Invoice> searchInvoices(Integer memberId, Integer employeeId, Date startDate, Date endDate, Pageable pageable) {
        if (startDate != null && endDate != null) {
            return invoiceRepository.findByInvoiceDateBetween(startDate, endDate, pageable);
        }

        if (memberId != null && employeeId != null) {
            return invoiceRepository
                    .findByMember_MemberIDAndEmployee_EmployeeID(memberId, employeeId, pageable);
        }

        if (memberId != null) {
            return invoiceRepository.findByMember_MemberID(memberId, pageable);
        }

        if (employeeId != null) {
            return invoiceRepository.findByEmployee_EmployeeID(employeeId, pageable);
        }

        return invoiceRepository.findAll(pageable);
    }


}
