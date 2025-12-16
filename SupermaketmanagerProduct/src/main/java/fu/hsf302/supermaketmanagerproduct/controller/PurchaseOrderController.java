package fu.hsf302.supermaketmanagerproduct.controller;

import fu.hsf302.supermaketmanagerproduct.entity.PurchaseOrder;
import fu.hsf302.supermaketmanagerproduct.entity.PurchaseItem;
import fu.hsf302.supermaketmanagerproduct.entity.Product;
import fu.hsf302.supermaketmanagerproduct.repository.PurchaseOrderRepository;
import fu.hsf302.supermaketmanagerproduct.repository.PurchaseItemRepository;
import fu.hsf302.supermaketmanagerproduct.repository.ProductRepository;
import fu.hsf302.supermaketmanagerproduct.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/staff/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepo;

    @Autowired
    private PurchaseItemRepository purchaseItemRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private SupplierRepository supplierRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("purchaseOrders", purchaseOrderRepo.findAll());
        return "staff/purchase-orders";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("purchaseOrder", new PurchaseOrder());
        model.addAttribute("suppliers", supplierRepo.findAll());
        return "staff/purchase-order-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute PurchaseOrder purchaseOrder) {
        purchaseOrder.setOrderDate(new Date());
        purchaseOrderRepo.save(purchaseOrder);
        return "redirect:/staff/purchase-orders";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        PurchaseOrder purchaseOrder = purchaseOrderRepo.findById(id).orElse(null);
        model.addAttribute("purchaseOrder", purchaseOrder);
        model.addAttribute("products", productRepo.findAll());
        model.addAttribute("purchaseItem", new PurchaseItem());
        return "staff/purchase-order-details";
    }

    @PostMapping("/{id}/add-item")
    public String addItem(@PathVariable Integer id, @ModelAttribute PurchaseItem purchaseItem) {
        PurchaseOrder purchaseOrder = purchaseOrderRepo.findById(id).orElse(null);
        if (purchaseOrder != null) {
            purchaseItem.setPurchaseOrder(purchaseOrder);
            purchaseItemRepo.save(purchaseItem);
        }
        return "redirect:/staff/purchase-orders/" + id;
    }
}