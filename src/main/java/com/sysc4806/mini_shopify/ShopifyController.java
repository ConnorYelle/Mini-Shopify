package com.sysc4806.mini_shopify;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopifyController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Shopify");
        return "index";
    }
}