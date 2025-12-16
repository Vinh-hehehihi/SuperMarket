package com.example.supermarket.controller;

import com.example.supermarket.dto.InvoiceDTO;
import com.example.supermarket.dto.InvoiceLineDTO;
import com.example.supermarket.entity.Invoice;
import com.example.supermarket.service.impl.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/create")
    public String showCreateForm(Model model){
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.getInvoiceLines().add(new InvoiceLineDTO());
        model.addAttribute("invoiceDTO", invoiceDTO);
        return "invoice/create";
    }

    @PostMapping("/create")
    public String createInvocie(@ModelAttribute("invoiceDTO") InvoiceDTO invoiceDTO, Model model){
        Invoice invoice = invoiceService.createInvoice(invoiceDTO);
        model.addAttribute("invoice", invoice);
        return "invoice/detail";
    }
}
