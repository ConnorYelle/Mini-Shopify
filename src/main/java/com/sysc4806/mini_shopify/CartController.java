package com.sysc4806.mini_shopify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final Long DEMO_CART_ID = 1L;

    @GetMapping
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

        for (int i = 0; i < quantity; i++) {
            cart.addItem(new CartItem(product, 1));
        }

        return cartRepository.save(cart);
    }

    @PostMapping("/clear")
    public Cart clearCart() {
        Cart cart = getCart();
        cart.clearCart();
        return cartRepository.save(cart);
    }
}
