package fu.hsf302.supermaketmanagerproduct.controller;

import fu.hsf302.supermaketmanagerproduct.entity.Category;
import fu.hsf302.supermaketmanagerproduct.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("category", new Category());
        return "staff/categories";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Category category) {
        categoryRepo.save(category);
        return "redirect:/staff/categories";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("category", categoryRepo.findById(id).orElse(new Category()));
        model.addAttribute("categories", categoryRepo.findAll());
        return "staff/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        categoryRepo.deleteById(id);
        return "redirect:/staff/categories";
    }
}