package com.sysc4806.mini_shopify;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<CartItem> items = new ArrayList<>();

    private double totalPrice;

    public Cart() {}

    public Long getId() {
        return id;
    }

    public List<CartItem> getItems() {
        return items;
    }
    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this);
    }

    public void removeItem(CartItem item) {
        if (item == null) return;
        items.remove(item);
        item.setCart(null);
    }

    public void removeItemById(Long itemId) {
        items.removeIf(ci -> {
            boolean match = ci != null && ci.getId() != null && ci.getId().equals(itemId);
            if (match) ci.setCart(null);
            return match;
        });
    }

    public void clearCart() {
        items.clear();
    }

    public void calculateTotal() {
        double total = 0.0;
        if (this.items != null) {
            for (CartItem ci : this.items) {
                if (ci == null) continue;
                int qty = Math.max(0, ci.getQuantity());
                double price = 0.0;
                if (ci.getProduct() != null) {
                    price = ci.getProduct().getPrice();
                }
                total += price * qty;
            }
        }
        this.totalPrice = total;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }
}
