package com.sysc4806.mini_shopify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MerchantPortalTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMerchantHomePageEndpoint() throws Exception {
        mockMvc.perform(get("/merchant"))
                .andExpect(status().isOk())
                .andExpect(view().name("merchantHomePage"));
    }

    @Test
    void testStoreCreateEndpoint() throws Exception {
        mockMvc.perform(get("/store/create"))
                .andExpect(status().isOk());
    }

    @Test
    void testProductCreateEndpoint() throws Exception {
        mockMvc.perform(get("/products/select-store"))
                .andExpect(status().isOk());
    }

    @Test
    void testReturnHomePageEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }






}
