package fu.hsf302.supermaketmanagerproduct.controller;

import fu.hsf302.supermaketmanagerproduct.entity.Stall;
import fu.hsf302.supermaketmanagerproduct.entity.Category;
import fu.hsf302.supermaketmanagerproduct.entity.Product;
import fu.hsf302.supermaketmanagerproduct.repository.StallRepository;
import fu.hsf302.supermaketmanagerproduct.repository.CategoryRepository;
import fu.hsf302.supermaketmanagerproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/shop")
public class ShopNavigationController {

    @Autowired
    private StallRepository stallRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ProductRepository productRepo;

    // 1. Display all stalls
    @GetMapping
    public String showStalls(Model model) {
        model.addAttribute("stalls", stallRepo.findAll());
        return "shop/stalls-list";
    }

    // 2. Display categories for a selected stall
    @GetMapping("/{stallId}/categories")
    public String showCategoriesByStall(@PathVariable Integer stallId, Model model) {
        Stall stall = stallRepo.findById(stallId).orElse(null);
        if (stall == null) {
            return "redirect:/shop"; // Redirect if stall not found
        }

        // Get all products for this stall
        List<Product> productsInStall = productRepo.findByStall_StallIDOrderByProductNameAsc(stallId);

        // Extract unique categories from these products
        Set<Category> categoriesInStall = productsInStall.stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());

        model.addAttribute("stall", stall);
        model.addAttribute("categories", categoriesInStall);
        return "shop/categories-list";
    }

    // 3. Display products for a selected category within a specific stall
    @GetMapping("/{stallId}/categories/{categoryId}/products")
    public String showProductsByCategoryAndStall(@PathVariable Integer stallId,
                                                 @PathVariable Integer categoryId,
                                                 Model model) {
        Stall stall = stallRepo.findById(stallId).orElse(null);
        Category category = categoryRepo.findById(categoryId).orElse(null);

        if (stall == null || category == null) {
            return "redirect:/shop"; // Redirect if stall or category not found
        }

        List<Product> products = productRepo.findByStall_StallIDAndCategory_CategoryIDOrderByProductNameAsc(stallId, categoryId);

        model.addAttribute("stall", stall);
        model.addAttribute("category", category);
        model.addAttribute("products", products);
        return "shop/products-list";
    }
}