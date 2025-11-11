package com.sysc4806.mini_shopify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomePageController {

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/")
    public String index() {
        return "storeHomePage";
    }

    //return JSON make sure ur stuff returns JSON
    @GetMapping("/stores")
    @ResponseBody
    public Iterable<Store> getAllStores() {
        return storeRepository.findAll();
    }

    //create a new store and returns JSON
    @PostMapping("/stores")
    @ResponseBody
    public ResponseEntity<Store> createStore(@RequestBody Store store) {
        Store saved = storeRepository.save(store);
        return ResponseEntity.ok(saved);
    }

    // get a single store by ID and returns JSON
    @GetMapping("/stores/{id}")
    @ResponseBody
    public ResponseEntity<Store> getStore(@PathVariable Long id) {
        return storeRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // update a store and returns JSON
    @PutMapping("/stores/{id}")
    @ResponseBody
    public ResponseEntity<Store> updateStore(@PathVariable Long id, @RequestBody Store updatedStore) {
        return storeRepository.findById(id)
                .map(store -> {
                    store.setName(updatedStore.getName() != null ? updatedStore.getName() : store.getName());
                    store.setDescription(updatedStore.getDescription());
                    store.setCategory(updatedStore.getCategory() != null ? updatedStore.getCategory() : store.getCategory());
                    Store saved = storeRepository.save(store);
                    return ResponseEntity.ok(saved);
                }).orElse(ResponseEntity.notFound().build());
    }

    // search stores by name and return JSON
    // in format /stores/search?query=__________
    @GetMapping("/api/stores/search")
    @ResponseBody
    public ResponseEntity<Iterable<Store>> searchStores(@RequestParam String query) {
        Iterable<Store> results = storeRepository.findByNameContainingIgnoreCase(query);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/stores/search")
    public String showSearchPage(){
        return "search";
    }

}
