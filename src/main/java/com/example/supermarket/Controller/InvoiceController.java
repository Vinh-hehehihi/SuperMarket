package com.example.supermarket.controller;

import com.example.supermarket.dto.InvoiceDTO;
import com.example.supermarket.dto.InvoiceLineDTO;
import com.example.supermarket.entity.Invoice;
import com.example.supermarket.service.impl.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        try {
            Invoice invoice = invoiceService.createInvoice(invoiceDTO);
            model.addAttribute("invoice", invoice);
            return "invoice/detail";
        } catch (RuntimeException e) {
            model.addAttribute("invoiceDTO", invoiceDTO);
            model.addAttribute("errorMessage", e.getMessage());
            return "invoice/create";
        }
    }

    @GetMapping
    public String listInvoices(@RequestParam(required = false) Integer memberId,
                               @RequestParam(required = false) Integer employeeId,
                               @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                               @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Invoice> invoicePage =
                invoiceService.searchInvoices(memberId, employeeId, startDate, endDate, pageable);

        model.addAttribute("invoicePage", invoicePage);
        model.addAttribute("invoices", invoicePage.getContent());

        // để dùng lại giá trị search trên form
        model.addAttribute("memberId", memberId);
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        // tạo list số trang để render nút phân trang
        int totalPages = invoicePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.range(0, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "invoice/list";
    }

    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable Integer id, Model model) {
        Invoice invoice = invoiceService.getById(id);
        model.addAttribute("invoice", invoice);
        return "invoice/detail";
    }
}
