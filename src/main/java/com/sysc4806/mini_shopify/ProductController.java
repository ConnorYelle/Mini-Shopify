package com.sysc4806.mini_shopify;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    public ProductController(ProductRepository productRepository, StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    // --- Create a product for a given store ---
    @PostMapping("/{storeId}/create")
    public ResponseEntity<?> createProduct(@PathVariable Long storeId, @RequestBody Product product) {
        try {
            Store store = storeRepository.findById(storeId)
                    .orElseThrow(() -> new RuntimeException("Store not found"));

            // Link product to store before saving
            product.setStore(store);

            Product savedProduct = productRepository.save(product);

            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // --- Return HTML view for product creation page ---
    @GetMapping("/{storeId}/create")
    public String getProductView(@PathVariable Long storeId, Model model) {
        model.addAttribute("storeId", storeId);
        return "products"; // name of the HTML file (Thymeleaf or other template)
    }

    // --- Get a single product by ID ---
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- Get all products for a store ---
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Product>> getProductsByStore(@PathVariable Long storeId) {
        return ResponseEntity.ok(productRepository.findByStoreId(storeId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok((List<Product>) productRepository.findAll());
    }

    // Get inventory count for a store
    @GetMapping("/store/{storeId}/inventory")
    public ResponseEntity<?> getInventoryCount(@PathVariable Long storeId) {
        try {
            // Ensure store exists
            boolean exists = storeRepository.existsById(storeId);
            if (!exists) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found");
            }

            int count = productRepository.findByStoreId(storeId).size();

            return ResponseEntity.ok().body(new InventoryResponse(count));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting inventory");
        }
    }
    static class InventoryResponse {
        public int count;

        public InventoryResponse(int count) {
            this.count = count;
        }
    }

}
