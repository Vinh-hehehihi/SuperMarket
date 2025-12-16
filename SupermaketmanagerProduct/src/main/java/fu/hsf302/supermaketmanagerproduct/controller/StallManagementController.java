package fu.hsf302.supermaketmanagerproduct.controller;

import fu.hsf302.supermaketmanagerproduct.entity.Category;
import fu.hsf302.supermaketmanagerproduct.entity.Stall;
import fu.hsf302.supermaketmanagerproduct.repository.CategoryRepository;
import fu.hsf302.supermaketmanagerproduct.repository.StallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff/manage-stalls")
public class StallManagementController {

    @Autowired
    private StallRepository stallRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("stalls", stallRepo.findAll());
        Stall newStall = new Stall();
        newStall.setCategory(new Category()); // Initialize for the form
        model.addAttribute("stall", newStall);
        model.addAttribute("categories", categoryRepo.findAll());
        return "staff/manage-stalls";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("stall") Stall stall) {
        // Manually fetch and set the full Category object
        Category category = categoryRepo.findById(stall.getCategory().getCategoryID()).orElse(null);
        stall.setCategory(category);
        stallRepo.save(stall);
        return "redirect:/staff/manage-stalls";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Stall stall = stallRepo.findById(id).orElse(new Stall());
        if (stall.getCategory() == null) {
            stall.setCategory(new Category());
        }
        model.addAttribute("stall", stall);
        model.addAttribute("stalls", stallRepo.findAll());
        model.addAttribute("categories", categoryRepo.findAll());
        return "staff/manage-stalls";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        stallRepo.deleteById(id);
        return "redirect:/staff/manage-stalls";
    }
}