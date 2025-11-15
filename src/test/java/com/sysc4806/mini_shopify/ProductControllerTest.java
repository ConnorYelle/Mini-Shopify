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

    @Mock StoreRepository storeRepository;

    @InjectMocks
    private ProductController productController;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProduct() throws Exception {
        Store store = new Store("TechStore", "Alice", null);
        store.setId(1L);

        when(storeRepository.findById(anyLong())).thenReturn(Optional.of(store));

        Product product = new Product(store, "best product","Product 1", "Image1", 3);
        product.setId(1L);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductController.ProductCreateRequest productCreateRequest = new ProductController.ProductCreateRequest();
        productCreateRequest.name = "best product";
        productCreateRequest.description = "Product 1";
        productCreateRequest.image = "Image1";
        productCreateRequest.inventoryNumber = 3;

        //when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        //when(productRepository.save(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products/create/{storeId}", 1L)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(productCreateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

    }

    @Test
    void testGetProductById() throws Exception {
        Store store = new Store("TechStore", "Alice", null);
        Product product = new Product(store, "best product","Product 1", "Image1", 3);
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}
