package com.sysc4806.mini_shopify;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private productRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    // DTO for receiving JSON
    public static class ProductCreateRequest {
        public String name;
        public String description;
        public String image;
        public int inventoryNumber;
    }

    @PostMapping("/create/{storeId}")
    public ResponseEntity<?> getProductView(@PathVariable Long storeId, @RequestBody ProductCreateRequest req, HttpSession session) {

        Store store = storeRepository.findById(storeId).orElse(null);
        if (store == null) {
            return ResponseEntity.badRequest().body("Store not found.");
        }

        Product p = new Product(store, req.name, req.description, req.image, req.inventoryNumber);

        store.addProduct(p);
        Product saved = productRepository.save(p);

        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        return productRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{storeId}/create")
    public String getProductView(@PathVariable Long storeId, Model model) {
        model.addAttribute("storeId", storeId);
        return "products.html"; // name of the HTML file
    }
}
