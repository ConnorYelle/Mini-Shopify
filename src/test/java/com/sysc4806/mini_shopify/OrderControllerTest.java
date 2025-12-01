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
class OrderControllerTest {
    private MockMvc mockMvc;
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderController orderController;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreateOrder() throws Exception {
        Order order = new Order("462 Wilson Farm Rd", "Express",
                "2165 Carling Ave", "Credit Card", "test@test.com");
        order.setId(1L);
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        mockMvc.perform(post("/orders").contentType("application/json").content(objectMapper.writeValueAsString(order))).andExpect(status().isOk())
                .andExpect(jsonPath("$.shippingAddress").value("462 Wilson Farm Rd"))
                .andExpect(jsonPath("$.shippingMethod").value("Express"))
                .andExpect(jsonPath("$.billingAddress").value("2165 Carling Ave"))
                .andExpect(jsonPath("$.paymentMethod").value("Credit Card"))
                .andExpect(jsonPath("$.email").value("test@test.com"));
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testGetOrder() throws Exception {
        Order order = new Order("462 Wilson Farm Rd", "Express",
                "2165 Carling Ave", "Credit Card", "carleton@uni.com");
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        mockMvc.perform(get("/orders/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.shippingAddress").value("462 Wilson Farm Rd"))
                .andExpect(jsonPath("$.shippingMethod").value("Express"))
                .andExpect(jsonPath("$.billingAddress").value("2165 Carling Ave"))
                .andExpect(jsonPath("$.paymentMethod").value("Credit Card"))
                .andExpect(jsonPath("$.email").value("carleton@uni.com"));
        verify(orderRepository, times(1)).findById(1L);
    }
}