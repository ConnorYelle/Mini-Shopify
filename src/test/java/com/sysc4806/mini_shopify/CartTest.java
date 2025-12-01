package com.sysc4806.mini_shopify;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void addRemoveClearAndCalculateTotal() {
        // create products
        Product p1 = new Product();
        p1.setId(1L);
        p1.setPrice(10.0);
        p1.setDescription("Product 1");

        Product p2 = new Product();
        p2.setId(2L);
        p2.setPrice(5.0);
        p2.setDescription("Product 2");

        // create cart and items
        Cart cart = new Cart();
        CartItem item1 = new CartItem();
        item1.setProduct(p1);
        item1.setQuantity(2);

        CartItem item2 = new CartItem();
        item2.setProduct(p2);
        item2.setQuantity(3);

        // add items
        cart.addItem(item1);
        cart.addItem(item2);

        // calculate total (use product.price * quantity)
        cart.calculateTotal();
        double expected = 2 * p1.getPrice() + 3 * p2.getPrice(); // 35.0
        assertEquals(expected, cart.getTotalPrice(), 0.001, "Cart total should match sum of product prices * quantities");

        // remove an item
        cart.removeItem(item1);
        cart.calculateTotal();
        expected = 3 * p2.getPrice(); // 15.0
        assertEquals(expected, cart.getTotalPrice(), 0.001, "Cart total should update after removing an item");

        // clear cart
        cart.clearCart();
        cart.calculateTotal();
        assertEquals(0.0, cart.getTotalPrice(), 0.001, "Cart total should be zero after clearing");
        assertTrue(cart.getItems() == null || cart.getItems().isEmpty(), "Cart items should be empty after clear");
    }
}