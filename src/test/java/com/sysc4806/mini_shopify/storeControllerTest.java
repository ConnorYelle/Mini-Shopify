package com.sysc4806.mini_shopify;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StoreController.class)
class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private storeRepository storeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateStore() throws Exception {
        Store store = new Store("TechStore", "Alice", null);
        store.setId(1L);

        when(storeRepository.save(any(Store.class))).thenReturn(store);

        mockMvc.perform(post("/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(store)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TechStore"))
                .andExpect(jsonPath("$.owner").value("Alice"));

        verify(storeRepository, times(1)).save(any(Store.class));
    }

    @Test
    void testGetStore() throws Exception {
        Store store = new Store("TechStore", "Alice", null);
        store.setId(1L);
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
        mockMvc.perform(get("/store/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TechStore"))
                .andExpect(jsonPath("$.owner").value("Alice"));
        verify(storeRepository, times(1)).findById(1L);
    }
}
