package com.sysc4806.mini_shopify;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Product(){}
}