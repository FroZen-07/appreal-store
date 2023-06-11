package dev.bitan.ecommerceapp.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String role;
    private List<Product> cart;

    public User() {
        this.cart = new ArrayList<>();
    }

    // Getters and setters

    public List<Product> getCart() {
        return cart;
    }

    public void addToCart(Product product) {
        cart.add(product);
    }

    public void removeFromCart(Product product) {
        cart.remove(product);
    }
}
