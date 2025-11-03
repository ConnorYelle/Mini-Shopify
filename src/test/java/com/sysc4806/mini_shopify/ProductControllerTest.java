package com.sysc4806.mini_shopify;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private productRepository productRepository;

    @InjectMocks
    private ProductController productController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProduct() throws Exception {
        Store store = new Store("TechStore", "Alice", null);
        Product product = new Product(store, "Product 1", "Image1", 3);
        product.setId(1L);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/").contentType("application/json").content(objectMapper.writeValueAsString(product))).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
        verify(productRepository, times(1)).save(any(Product.class));

    }

    @Test
    void testGetProductById() throws Exception {
        Store store = new Store("TechStore", "Alice", null);
        Product product = new Product(store, "Product 1", "Image1", 3);
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        mockMvc =  MockMvcBuilders.standaloneSetup(productController).build();
        mockMvc.perform(get("/product/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(productRepository, times(1)).findById(1L);
    }
}
