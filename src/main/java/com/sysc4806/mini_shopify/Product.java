package com.sysc4806.mini_shopify;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private String description;

    private String image;

    private String name;

    private int inventoryNumber;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Product(){}

    public Product(Store store,String name, String description, String image, int inventoryNumber) {
        this.store = store;
        this.name = name;
        this.description = description;
        this.image = image;
        this.inventoryNumber = inventoryNumber;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Store getStore() {
        return store;
    }
    public void setStore(Store store) {
        this.store = store;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(int inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public void decreaseInventoryNumber(){
        this.inventoryNumber--;
    }

    public void increaseInventoryNumber(){
        this.inventoryNumber++;
    }
}