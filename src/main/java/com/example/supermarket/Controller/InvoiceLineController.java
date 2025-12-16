package com.example.supermarket.controller;

import com.example.supermarket.entity.Invoice;
import com.example.supermarket.entity.InvoiceLine;
import com.example.supermarket.repository.InvoiceRepository;
import com.example.supermarket.service.IInvoiceLineService;
import com.example.supermarket.service.impl.InvoiceLineService;
import com.example.supermarket.service.impl.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/invoiceLine")
public class InvoiceLineController {
    @Autowired
    private InvoiceLineService invoiceLineService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    // Thêm 1 dòng cho invoice
    @PostMapping("/add/{invoiceId}")
    public String addInvoiceLine(@PathVariable("invoiceId") Integer invoiceId,
                                 @ModelAttribute InvoiceLine invoiceLine) {
        Invoice invoice = invoiceRepository.getById(invoiceId);
        invoiceLine.setInvoice(invoice);
        invoiceLineService.save(invoiceLine);
        return "redirect:/invoice/detail/" + invoiceId;
    }

    // Xóa 1 dòng
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        InvoiceLine invoiceLine = invoiceLineService.getAllByInvoice(null) // tạm thời cần get invoice từ line
                .stream().filter(line -> line.getInvoiceLineID().equals(id)).findFirst().orElse(null);
        if (invoiceLine != null) {
            Integer invoiceId = invoiceLine.getInvoice().getInvoiceID();
            invoiceLineService.delete(id);
            return "redirect:/invoice/detail/" + invoiceId;
        }
        return "redirect:/invoice/list";
    }
}
