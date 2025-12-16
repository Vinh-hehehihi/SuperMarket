package fu.hsf302.supermaketmanagerproduct.controller;

import fu.hsf302.supermaketmanagerproduct.repository.EmployeeRepository;
import fu.hsf302.supermaketmanagerproduct.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("employees", employeeRepo.findAll());
        model.addAttribute("purchaseOrders", purchaseOrderRepo.findAll());
        return "staff/employees";
    }
}