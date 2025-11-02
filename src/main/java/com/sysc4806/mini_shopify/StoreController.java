package com.sysc4806.mini_shopify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class StoreController {
    
    @Autowired
    private storeRepository storeRepository;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Shopify");
        return "index";
    }
    
    @PostMapping
    public Store createStore(@RequestBody Store store) {
        return storeRepository.save(store);
    }
    
    @GetMapping("/store/{id}")
    public Optional<Store> getStore(@PathVariable Long id) {
        return storeRepository.findById(id);
    }
}