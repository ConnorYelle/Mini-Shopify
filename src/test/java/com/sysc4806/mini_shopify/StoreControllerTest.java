package com.sysc4806.mini_shopify;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class HomePageControllerTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private homePageController homePageController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(homePageController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllStores() throws Exception {
        Store store1 = new Store("TechStore", "Alice", null);
        store1.setId(1L);
        Store store2 = new Store("BookShop", "Bob", null);
        store2.setId(2L);
        List<Store> stores = Arrays.asList(store1, store2);
        when(storeRepository.findAll()).thenReturn(stores);
        mockMvc.perform(get("/stores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("TechStore"))
                .andExpect(jsonPath("$[1].name").value("BookShop"));

        verify(storeRepository, times(1)).findAll();
    }

    @Test
    void testCreateStore() throws Exception {
        Store store = new Store("TechStore", "Alice", null);
        store.setId(1L);
        when(storeRepository.save(any(Store.class))).thenReturn(store);

        mockMvc.perform(post("/stores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(store)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("TechStore"))
                .andExpect(jsonPath("$.owner").value("Alice"));

        verify(storeRepository, times(1)).save(any(Store.class));
    }

    @Test
    void testGetStoreFound() throws Exception {
        Store store = new Store("TechStore", "Alice", null);
        store.setId(1L);
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));

        mockMvc.perform(get("/stores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("TechStore"));

        verify(storeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetStoreNotFound() throws Exception {
        when(storeRepository.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/stores/99"))
                .andExpect(status().isNotFound());

        verify(storeRepository, times(1)).findById(99L);
    }

    @Test
    void testUpdateStore() throws Exception {
        Store existingStore = new Store("OldName", "Alice", "Tech","Old");
        existingStore.setId(1L);

        Store updatedStore = new Store("NewName", "Alice", "Shoes","New");

        when(storeRepository.findById(1L)).thenReturn(Optional.of(existingStore));
        when(storeRepository.save(any(Store.class))).thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(put("/stores/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedStore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("New"))
                .andExpect(jsonPath("$.name").value("NewName"));

        verify(storeRepository, times(1)).save(any(Store.class));
    }

    @Test
    void testUpdateStoreNotFound() throws Exception {
        Store updatedStore = new Store("NewName", "Alice", "New Tag","New description");
        when(storeRepository.findById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(put("/stores/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedStore)))
                .andExpect(status().isNotFound());
        verify(storeRepository, times(1)).findById(1L);
        verify(storeRepository, never()).save(any(Store.class));
    }

    @Test
    void testDeleteStore() throws Exception {
        when(storeRepository.existsById(1L)).thenReturn(true);
        mockMvc.perform(delete("/stores/1"))
                .andExpect(status().isNoContent());
        verify(storeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteStoreNotFound() throws Exception {
        when(storeRepository.existsById(999L)).thenReturn(false);
        mockMvc.perform(delete("/stores/999"))
                .andExpect(status().isNotFound());
        verify(storeRepository, never()).deleteById(999L);
    }
}
