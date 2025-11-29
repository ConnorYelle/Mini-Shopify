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
    private final CartItemRepository cartItemRepository;

    public ProductController(ProductRepository productRepository,
                             StoreRepository storeRepository,
                             CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @PostMapping("/{storeId}/create")
    public ResponseEntity<?> createProduct(@PathVariable Long storeId, @RequestBody Product product) {
        try {
            Store store = storeRepository.findById(storeId)
                    .orElseThrow(() -> new RuntimeException("Store not found"));
            product.setStore(store);
            Product savedProduct = productRepository.save(product);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{storeId}/create")
    public String getProductView(@PathVariable Long storeId, Model model) {
        model.addAttribute("storeId", storeId);
        return "products";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Product>> getProductsByStore(@PathVariable Long storeId) {
        return ResponseEntity.ok(productRepository.findByStoreId(storeId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok((List<Product>) productRepository.findAll());
    }

    @GetMapping("/select-store")
    public String selectStorePage(Model model) {
        model.addAttribute("stores", storeRepository.findAll());
        return "selectStoreForProduct";
    }
    // Get inventory count for a store
    @GetMapping("/store/{storeId}/inventory")
    public ResponseEntity<?> getInventoryCount(@PathVariable Long storeId) {
        try {
            if (!storeRepository.existsById(storeId)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found");
            }
            int count = productRepository.findByStoreId(storeId).size();
            return ResponseEntity.ok(new InventoryResponse(count));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting inventory");
        }
    }

    static class InventoryResponse {
        public int count;
        public InventoryResponse(int count) { this.count = count; }
    }

    @DeleteMapping("/{storeId}/{productId}/delete")
    public ResponseEntity<String> deleteProduct(@PathVariable Long storeId, @PathVariable Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null || !product.getStore().getId().equals(storeId)) {
            return ResponseEntity.badRequest().body("Product not found in this store.");
        }
        boolean existsInCart = cartItemRepository.existsByProductId(productId);
        if (existsInCart) {
            return ResponseEntity.badRequest().body(
                    "Can't remove product because a customer has it in their cart."
            );
        }
        productRepository.delete(product);
        return ResponseEntity.ok("Product deleted.");
    }
}
