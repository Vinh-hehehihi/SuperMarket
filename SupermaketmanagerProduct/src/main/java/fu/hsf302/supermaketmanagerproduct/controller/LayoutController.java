package fu.hsf302.supermaketmanagerproduct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LayoutController {

    @GetMapping("/")
    public String layout() {
        return "layout/layout";
    }
}