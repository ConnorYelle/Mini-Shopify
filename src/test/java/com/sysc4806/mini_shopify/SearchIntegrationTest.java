package com.sysc4806.mini_shopify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SearchIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StoreRepository storeRepository;

    @BeforeEach
    void setup() {
        storeRepository.deleteAll();

        storeRepository.save(new Store("Best Buy", "Connor", List.of("Electronics")));
        storeRepository.save(new Store("BuyMore", "Connor", List.of("Value")));
        storeRepository.save(new Store("Canadian Tire", "Connor", List.of("Outdoor")));
    }

    @Test
    void searchStores_integration_returnsRealResults() throws Exception {
        mockMvc.perform(
                        get("/api/stores/search")
                                .param("query", "buy")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Best Buy"))
                .andExpect(jsonPath("$[1].name").value("BuyMore"));
    }
}
