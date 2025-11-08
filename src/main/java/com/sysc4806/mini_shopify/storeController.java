package com.sysc4806.mini_shopify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class storeController {

    @Autowired
    private StoreRepository storeRepository;

    @PostMapping
    public Store createStore(@RequestBody Store store) {
        return storeRepository.save(store);
    }

    @GetMapping("/store/{id}")
    public Optional<Store> getStore(@PathVariable Long id) {
        return storeRepository.findById(id);
    }


}
