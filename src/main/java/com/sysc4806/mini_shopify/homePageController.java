package com.sysc4806.mini_shopify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class homePageController {

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

    // deletes a store
    @DeleteMapping("/stores/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
