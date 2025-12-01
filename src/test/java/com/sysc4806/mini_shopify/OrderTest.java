package com.sysc4806.mini_shopify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order1;
    private Order order2;

    @BeforeEach
    void setUp() {
        order1 = new Order();
        order2 = new Order("462 Wilson Farm Rd", "Express",
                "2165 Carling Ave", "Credit Card", "test@test.com");
    }

    @Test
    void testConstructor() {
        assertNotNull(order1);
        assertNotNull(order2);
    }

    @Test
    void testGetters() {
        assertNull(order1.getShippingAddress());
        assertNull(order1.getShippingMethod());
        assertNull(order1.getBillingAddress());
        assertNull(order1.getPaymentMethod());

        assertEquals("462 Wilson Farm Rd", order2.getShippingAddress());
        assertEquals("Express", order2.getShippingMethod());
        assertEquals("2165 Carling Ave", order2.getBillingAddress());
        assertEquals("Credit Card", order2.getPaymentMethod());
        assertEquals("test@test.com", order2.getEmail());
    }

    @Test
    void testSetters() {
        order1.setShippingAddress("317 Dundas St W");
        assertEquals("317 Dundas St W", order1.getShippingAddress());
        order1.setShippingMethod("Regular");
        assertEquals("Regular", order1.getShippingMethod());
        order1.setBillingAddress("290 Bremner Blvd");
        assertEquals("290 Bremner Blvd", order1.getBillingAddress());
        order1.setPaymentMethod("Apple Pay");
        assertEquals("Apple Pay", order1.getPaymentMethod());
        order1.setEmail("test@test.com");
        assertEquals("test@test.com", order1.getEmail());
    }
}