package com.sysc4806.mini_shopify;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    // create a new store and return JSON
    @PostMapping
    @ResponseBody
    public Store createStore(@RequestBody Store store) {
        return storeRepository.save(store);
    }

    // get a single store by ID and return JSON
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Store> getStore(@PathVariable Long id) {
        Optional<Store> store = storeRepository.findById(id);
        return store.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/page")
    public String showStorePage(@PathVariable Long id, Model model) {
        model.addAttribute("storeId", id);
        return "store";
    }

    // get the owner dashboard page for a store
    @GetMapping("/{id}/owner")
    public String getOwnerDashboard(@PathVariable Long id, Model model) {
        Optional<Store> store = storeRepository.findById(id);
        if (store.isPresent()) {
            model.addAttribute("store", store.get());
            return "ownerDashboard";
        }
        return "redirect:/";
    }

    // deletes a store
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
