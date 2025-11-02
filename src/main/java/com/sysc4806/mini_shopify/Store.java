
package com.sysc4806.mini_shopify;
import jakarta.persistence.*;
import java.util.List;
/*
We must host multiple vendors so we need to persist stores into database
Customers should be able to find the store either by searching the name, and
or if it falls under certain tags/categories
 */
@Entity
public class Store {
    @Id
    private Long id;
    private String name;
    private String owner;
    private String description;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;

    @ElementCollection
    private List<String> tags;

    /*
    Just need this to create new instance from reflection by JPA
     */
    public Store() {}

    /*
    Create new store with name, owner, and categories relevant to the shop
     */
    public Store(String name, String owner, List<String> categories) {
        this.name = name;
        this.owner = owner;
        this.tags = categories;
    }

    /*
     returns Id of the shop
     */
    public Long getId() {
        return id;
    }

    /*
    set store id, mainly for testing
     */
    public void setId(Long id) {
        this.id = id;
    }

    /*
    returns the Name of the shop
     */
    public String getName() {
        return name;
    }

    /*
    sets the name of the shop
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
    return the owner of the shop
     */
    public String getOwner() {
        return owner;
    }

    /*
    set the owner of the shop
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /*
    return the description of the store
     */
    public String getDescription() {
        return description;
    }

    /*
    set the description of the store
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /*
    return the products of the store
     */
    public List<Product> getProducts() {
        return products;
    }

    /*
    set products of the store
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /*
    add product to the store
     */
    public void addProduct(Product product) {
        this.products.add(product);
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
}
