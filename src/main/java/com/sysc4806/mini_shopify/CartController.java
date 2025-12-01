package com.sysc4806.mini_shopify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

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

    @PostMapping("/remove/{productId}")
    public Cart removeFromCart(@PathVariable Long productId,
                               @RequestParam(defaultValue = "1") int quantity) {

        Cart cart = getCart();

        CartItem existingItem = cart.getItems().stream()
                .filter(ci -> ci.getProduct().getId().equals(productId))
                .findFirst().orElse(null);

        if (existingItem != null) {
            int newQty = existingItem.getQuantity() - quantity;

            if (newQty > 0) {
                existingItem.setQuantity(newQty);
            } else {
                // quantity dropped to 0 → remove item from cart completely
                cart.getItems().remove(existingItem);
            }
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
        private String shippingAddress;
        private String shippingMethod;
        private String billingAddress;
        private String paymentMethod;

        public String getEmail() {
            return email;
        }
        public String getPaymentMethod() {
            return paymentMethod;
        }
        public String getShippingAddress() {
            return shippingAddress;
        }
        public String getShippingMethod() {
            return shippingMethod;
        }
        public String getBillingAddress() {
            return billingAddress;
        }
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody PurchaseRequest req) {
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

         Order order = new Order();
        order.setShippingAddress(req.getShippingAddress());
        order.setShippingMethod(req.getShippingMethod());
        order.setBillingAddress(req.getBillingAddress());
        order.setPaymentMethod(req.getPaymentMethod());
        order.setEmail(req.getEmail());

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem ci : cart.getItems()) {
            OrderItem oi = new OrderItem(ci.getProduct(), ci.getQuantity(), order);
            orderItems.add(oi);
        }
        order.setItems(orderItems);

        orderRepository.save(order);

        cart.clearCart();
        cartRepository.save(cart);

        return ResponseEntity.ok(Map.of("orderId", order.getId()));
    }

    @GetMapping("/checkout")
    public String checkoutPage() {
        //model.addAttribute("cart", 1L);
        return "checkout";
    }
}
