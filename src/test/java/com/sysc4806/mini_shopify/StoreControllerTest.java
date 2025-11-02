package com.sysc4806.mini_shopify;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class StoreControllerTest {

    private MockMvc mockMvc;

    @Mock
    private storeRepository storeRepository;

    @InjectMocks
    private StoreController storeController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreateStore() throws Exception {
        Store store = new Store("TechStore", "Alice", null);
        store.setId(1L);

        when(storeRepository.save(any(Store.class))).thenReturn(store);

        mockMvc = MockMvcBuilders.standaloneSetup(storeController).build();

        mockMvc.perform(post("/").contentType("application/json").content(objectMapper.writeValueAsString(store))).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TechStore"))
                .andExpect(jsonPath("$.owner").value("Alice"));
        verify(storeRepository, times(1)).save(any(Store.class));
    }

    @Test
    void testGetStore() throws Exception {
        Store store = new Store("TechStore", "Alice", null);
        store.setId(1L);
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
        mockMvc = MockMvcBuilders.standaloneSetup(storeController).build();
        mockMvc.perform(get("/store/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TechStore"))
                .andExpect(jsonPath("$.owner").value("Alice"));

        verify(storeRepository, times(1)).findById(1L);
    }
}