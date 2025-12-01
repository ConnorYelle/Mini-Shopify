package com.sysc4806.mini_shopify;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    @Test
    void setQuantityUpdatesQuantity() {
        Product p = new Product();
        p.setId(10L);
        p.setPrice(7.5);
        p.setDescription("Sample");

        CartItem ci = new CartItem();
        ci.setProduct(p);
        ci.setQuantity(1);

        assertEquals(1, ci.getQuantity(), "Initial quantity should be 1");

        ci.setQuantity(4);
        assertEquals(4, ci.getQuantity(), "Quantity should update to 4 after setQuantity");

        assertEquals(p.getPrice(), ci.getProduct().getPrice(), 0.001, "Item unit price should match product price");
    }
}