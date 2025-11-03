package com.sysc4806.mini_shopify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    private Store store;

    @BeforeEach
    void setUp() {
        List<String> initialTags = new ArrayList<>(Arrays.asList("electronics", "gadgets"));
        store = new Store("TechStore", "Alice", initialTags);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("TechStore", store.getName());
        assertEquals("Alice", store.getOwner());
        List<String> tags = store.getTags();
        assertEquals(2, tags.size());
        assertTrue(tags.contains("electronics"));
        assertTrue(tags.contains("gadgets"));
        assertNull(store.getDescription());
        assertNull(store.getCategory());
        List<Product> products = store.getProducts();
        assertNotNull(products);
        assertTrue(products.isEmpty());
    }

    @Test
    void testSetters() {
        store.setName("NewTechStore");
        store.setOwner("Bob");
        store.setDescription("Best store for electronics");
        store.setCategory("Electronics");

        assertEquals("NewTechStore", store.getName());
        assertEquals("Bob", store.getOwner());
        assertEquals("Best store for electronics", store.getDescription());
        assertEquals("Electronics", store.getCategory());

        List<String> newTags = new ArrayList<>(Arrays.asList("home", "appliances"));
        store.setTags(newTags);
        assertEquals(newTags, store.getTags());
    }

    @Test
    void testAddTag() {
        store.addTag("newTag");
        assertTrue(store.getTags().contains("newTag"));
        assertEquals(3, store.getTags().size());
    }

    @Test
    void testProductsManagement() {
        Product product1 = new Product();
        Product product2 = new Product();
        store.setProducts(new ArrayList<>());
        store.addProduct(product1);
        store.addProduct(product2);
        List<Product> products = store.getProducts();
        assertEquals(2, products.size());
        assertTrue(products.contains(product1));
        assertTrue(products.contains(product2));
    }
}