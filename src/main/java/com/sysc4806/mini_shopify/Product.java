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

    private double price;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Product(){}

    public Product(Store store,String name, String description, String image, int inventoryNumber, double price) {
        this.store = store;
        this.name = name;
        this.description = description;
        this.image = image;
        this.inventoryNumber = inventoryNumber;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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