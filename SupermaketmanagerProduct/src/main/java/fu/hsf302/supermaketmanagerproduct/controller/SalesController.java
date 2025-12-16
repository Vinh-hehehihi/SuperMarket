package fu.hsf302.supermaketmanagerproduct.controller;

import fu.hsf302.supermaketmanagerproduct.entity.Category;
import fu.hsf302.supermaketmanagerproduct.entity.Product;
import fu.hsf302.supermaketmanagerproduct.entity.Stall;
import fu.hsf302.supermaketmanagerproduct.entity.Supplier;
import fu.hsf302.supermaketmanagerproduct.repository.CategoryRepository;
import fu.hsf302.supermaketmanagerproduct.repository.ProductRepository;
import fu.hsf302.supermaketmanagerproduct.repository.StallRepository;
import fu.hsf302.supermaketmanagerproduct.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff/sales")
public class SalesController {

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private StallRepository stallRepo;
    @Autowired
    private SupplierRepository supplierRepo;

    @GetMapping
    public String sellForm(Model model) {
        Product product = new Product();
        product.setCategory(new Category());
        product.setStall(new Stall());
        product.setSupplier(new Supplier());
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("stalls", stallRepo.findAll());
        model.addAttribute("suppliers", supplierRepo.findAll());
        return "staff/sales";
    }

    @PostMapping("/sell")
    public String sell(@ModelAttribute Product product) {
        // Manually fetch and set the related entities
        Category category = categoryRepo.findById(product.getCategory().getCategoryID()).orElse(null);
        Stall stall = stallRepo.findById(product.getStall().getStallID()).orElse(null);
        Supplier supplier = supplierRepo.findById(product.getSupplier().getSupplierID()).orElse(null);

        product.setCategory(category);
        product.setStall(stall);
        product.setSupplier(supplier);

        productRepo.save(product);
        return "redirect:/staff/products";
    }
}