package com.sysc4806.mini_shopify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreControllerTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreController storeController;

    private Model model;

    @BeforeEach
    void setUp() {
        model = new BindingAwareModelMap();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(storeController).build();
    }

    @Test
    void testCreateStore() {
        Store store = new Store("TechStore", "Alice", null);
        store.setId(1L);
        when(storeRepository.save(any(Store.class))).thenReturn(store);
        String viewName = storeController.createStore(store, model);
        verify(storeRepository, times(1)).save(store);
        assertEquals("stores", viewName);
        assertEquals("Store created.", model.getAttribute("message"));
        assertEquals(storeRepository.findAll(), model.getAttribute("stores"));
    }

    @Test
    void testGetStoreFound() {
        Store store = new Store("TechStore", "Alice", null);
        store.setId(1L);
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
        String viewName = storeController.getStore(1L, model);
        verify(storeRepository, times(1)).findById(1L);
        assertEquals("store-details", viewName);
        assertEquals(store, model.getAttribute("store"));
    }

    @Test
    void testGetStoreNotFound() {
        when(storeRepository.findById(1L)).thenReturn(Optional.empty());
        String viewName = storeController.getStore(1L, model);
        verify(storeRepository, times(1)).findById(1L);
        assertEquals("error", viewName);
    }
}