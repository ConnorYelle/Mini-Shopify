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
public class CustomerPortalTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCustomerHomePage() throws Exception {
        mockMvc.perform(get("/customer")).andExpect(status()
                .isOk()).andExpect(view()
                .name("customerHomePage"));
    }
    @Test
    public void testStoreSearchEndpoint() throws Exception {
        mockMvc.perform(get("/store/search")).andExpect(status().isOk());
    }

    @Test
    public void testCartEndpoint() throws Exception {
        mockMvc.perform(get("/cart")).andExpect(status().isOk());
    }

    @Test
    public void testReturnHomePageEndpoint() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }
}
