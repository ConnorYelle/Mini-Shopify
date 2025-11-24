package com.sysc4806.mini_shopify;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    // --------- PAGE ENDPOINTS ---------

    // Home page
    @GetMapping("/create")
    public String index() {
        return "createStore";
    }

    // Search page
    @GetMapping("/search")
    public String showSearchPage() {
        return "search";
    }

    // Individual store page
    @GetMapping("/{id}/page")
    public String showStorePage(@PathVariable Long id, Model model) {
        model.addAttribute("storeId", id);
        return "store";
    }

    // Owner dashboard page
    @GetMapping("/{id}/owner")
    public String getOwnerDashboard(@PathVariable Long id, Model model) {
        Optional<Store> store = storeRepository.findById(id);
        if (store.isPresent()) {
            model.addAttribute("store", store.get());
            return "ownerDashboard";
        }
        return "redirect:/store/";
    }

    // --------- JSON ENDPOINTS ---------

    // Get all stores
    @GetMapping
    @ResponseBody
    public Iterable<Store> getAllStores() {
        return storeRepository.findAll();
    }

    // Create a new store
    @PostMapping({"", "/"})
    @ResponseBody
    public Store createStore(@RequestBody Store store) {
        return storeRepository.save(store);
    }

    // Get store by ID
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Store> getStore(@PathVariable Long id) {
        Optional<Store> store = storeRepository.findById(id);
        return store.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update store
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Store> updateStore(@PathVariable Long id, @RequestBody Store updatedStore) {
        return storeRepository.findById(id)
                .map(store -> {
                    store.setName(updatedStore.getName() != null ? updatedStore.getName() : store.getName());
                    store.setDescription(updatedStore.getDescription());
                    store.setCategory(updatedStore.getCategory() != null ? updatedStore.getCategory() : store.getCategory());
                    store.setOwner(updatedStore.getOwner() != null ? updatedStore.getOwner() : store.getOwner());

                    Store saved = storeRepository.save(store);
                    return ResponseEntity.ok(saved);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete store
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Search stores by name (JSON)
    // Example: /store/search?query=shoe
    @GetMapping("/api/search")
    @ResponseBody
    public ResponseEntity<Iterable<Store>> searchStores(@RequestParam String query) {
        Iterable<Store> results = storeRepository.findByNameContainingIgnoreCase(query);
        return ResponseEntity.ok(results);
    }
}
