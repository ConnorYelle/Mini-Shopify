package com.sysc4806.mini_shopify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class homePageController {

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Mini-Shopify");
        model.addAttribute("stores", storeRepository.findAll());
        return "index";
    }

    @GetMapping("/stores")
    public String viewStores(Model model) {
        model.addAttribute("stores", storeRepository.findAll());
        model.addAttribute("store", new Store());
        return "stores";
    }

    @PostMapping("/stores")
    public String createStore(@ModelAttribute Store store, Model model) {
        storeRepository.save(store);
        model.addAttribute("message", "Store created.");
        model.addAttribute("stores", storeRepository.findAll());
        model.addAttribute("store", new Store());
        return "stores";
    }

    @GetMapping("/store/{id}")
    public String getStore(@PathVariable Long id, Model model) {
        return storeRepository.findById(id)
                .map(store -> {
                    model.addAttribute("store", store);
                    return "store-details";
                })
                .orElse("error");
    }


}