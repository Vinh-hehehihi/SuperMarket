package fu.hsf302.supermaketmanagerproduct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff/promotions")
public class PromotionController {


    @GetMapping
    public String promotion() {
        return "staff/promotions";
    }
}