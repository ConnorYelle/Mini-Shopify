package com.sysc4806.mini_shopify;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
We must host multiple vendors so we need to persist stores into database.
Customers should be able to find the store either by searching the name, and/or
if it falls under certain tags/categories.
 */
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String owner;
    private String description;

    // Optional single main category
    private String category;

    // Optional list of tags/subcategories
    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    public Store() {}

    public Store(String name, String owner, String category, String description) {
        this.name = name;
        this.owner = owner;
        this.category = category;
        this.description = description;
    }

    public Store(String name, String owner, List<String> tags) {
        this.name = name;
        this.owner = owner;
        this.tags = tags;
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    /*
    Return the products of the store
     */
    public List<Product> getProducts() {
        return products;
    }

    /*
    Set products of the store
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /*
    Add product to the store
     */
    public void addProduct(Product product) {
        this.products.add(product);
        product.setStore(this);
    }

    /*
    return the tags/categories of the store
     */
    public List<String> getTags() {
        return tags;
    }

    /*
    set tag/category of the store
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /*
    add tag to the store
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }
    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                '}';
    }
}
