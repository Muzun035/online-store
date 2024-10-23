package com.pluralsight;

public class Product {
    private String sku;
    private String name;
    private double price;


    public Product(String sku, String name, double price) {
        this.sku = sku;
        this.name = name;
        this.price = price;

    }

    // Getters and Setters
    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("SKU: %s | Name: %s | Price: $%.2f", sku, name, price);
    }
}
