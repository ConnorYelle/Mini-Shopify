package com.sysc4806.mini_shopify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final Long DEMO_CART_ID = 1L;

    @GetMapping
    @ResponseBody
    public Cart getCart() {
        return cartRepository.findById(DEMO_CART_ID)
                .orElseGet(() -> cartRepository.save(new Cart()));
    }

    @PostMapping("/add/{productId}")
    public Cart addToCart(@PathVariable Long productId,
                          @RequestParam(defaultValue = "1") int quantity) {

        Cart cart = getCart();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem existingItem = cart.getItems().stream()
                .filter(ci -> ci.getProduct().getId().equals(productId))
                .findFirst().orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            cart.addItem(new CartItem(product, quantity));
        }

        return cartRepository.save(cart);
    }

    @PostMapping("/clear")
    public Cart clearCart() {
        Cart cart = getCart();
        cart.clearCart();
        return cartRepository.save(cart);
    }

    public static class PurchaseRequest {
        private String email;
        private String paymentMethod;

        public String getEmail() {
            return email;
        }
        public String getPaymentMethod() {
            return paymentMethod;
        }
    }

    @PostMapping("/purchase")
    public ResponseEntity<String> purchase(@RequestBody PurchaseRequest req) {
        Cart cart = getCart();

        if (cart.getItems().isEmpty()) {
            return ResponseEntity.badRequest().body("Cart is empty");
        }

        for (CartItem item : cart.getItems()) {
            Product p = item.getProduct();
            if (p.getInventoryNumber() < item.getQuantity()) {
                return ResponseEntity.badRequest().body("Not enough inventory for: " + p.getName()
                );
            }
        }

        for (CartItem item : cart.getItems()) {
            Product p = item.getProduct();
            int updatedInventory = p.getInventoryNumber() - item.getQuantity();
            p.setInventoryNumber(updatedInventory);
            productRepository.save(p);
        }

        cart.clearCart();
        cartRepository.save(cart);

        return ResponseEntity.ok("Purchase successful");
    }

    @GetMapping("/checkout")
    public String checkoutPage() {
        //model.addAttribute("cart", 1L);
        return "checkout";
    }
}
