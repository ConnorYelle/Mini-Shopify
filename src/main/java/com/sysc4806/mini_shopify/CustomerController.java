package com.sysc4806.mini_shopify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Shopify");
        return "index";
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("/customer/{id}")
    public Optional<Customer> getCustomer(@PathVariable Long id) {
        return customerRepository.findById(id);
    }
}
