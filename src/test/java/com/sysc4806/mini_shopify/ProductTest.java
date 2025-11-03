package com.sysc4806.mini_shopify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    private Product product;

    private Store store;

    @BeforeEach
    public void setup() {

        List<String> initialTags = new ArrayList<>(Arrays.asList("electronics", "gadgets"));
        store = new Store("TechStore", "Alice", initialTags);

        product = new Product(store, "Product 1", "Image1", 3);

    }

    @Test
    void testConstructorAndGettersAll() {

        assertNotNull(product);
        assertNotNull(store);
        assertEquals("Product 1", product.getDescription());
        assertEquals("Image1", product.getImage());
        assertEquals(3, product.getInventoryNumber());

    }

    @Test
    void testConstructorAndSetters() {

        assertNotNull(product);
        assertNotNull(store);

        product.setDescription("New Description");
        product.setImage("New Image");
        product.setInventoryNumber(1);

        assertEquals("New Description", product.getDescription());
        assertEquals("New Image", product.getImage());
        assertEquals(1, product.getInventoryNumber());

    }

    @Test
    void testIncrementInventoryNumber() {
        product.decreaseInventoryNumber();
        assertEquals(2,product.getInventoryNumber());

        product.increaseInventoryNumber();
        assertEquals(3,product.getInventoryNumber());
    }

}
