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
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private StallRepository stallRepo;
    @Autowired
    private SupplierRepository supplierRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productRepo.findAllByOrderByProductNameAsc());
        model.addAttribute("categories", categoryRepo.findAll());
        return "staff/products";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productRepo.findById(id).orElse(null));
        return "staff/product-details";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        Product product = new Product();
        product.setCategory(new Category());
        product.setStall(new Stall());
        product.setSupplier(new Supplier());
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("stalls", stallRepo.findAll());
        model.addAttribute("suppliers", supplierRepo.findAll());
        return "staff/product-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product product) {
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

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Product product = productRepo.findById(id).orElse(new Product());
        if (product.getCategory() == null) {
            product.setCategory(new Category());
        }
        if (product.getStall() == null) {
            product.setStall(new Stall());
        }
        if (product.getSupplier() == null) {
            product.setSupplier(new Supplier());
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("stalls", stallRepo.findAll());
        model.addAttribute("suppliers", supplierRepo.findAll());
        return "staff/product-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productRepo.deleteById(id);
        return "redirect:/staff/products";
    }
}