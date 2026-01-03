package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    // Singleton instance
    private static Cart instance;

    // List to hold cart items
    private final List<CartItem> items;

    private Cart() {
        items = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void clearCart() {
        items.clear();
    }

    // Inner class to store individual cart item
    public static class CartItem {
        public String name;
        public int price;

        public CartItem(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }
}
