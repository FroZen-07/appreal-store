package dev.bitan.ecommerceapp.user.model;

import dev.bitan.ecommerceapp.user.model.CartItem;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String roles;
    private List<CartItem> cartItems;

    public User() {
        this.cartItems = new ArrayList<>();
    }

    public User(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }


    public void addToCart(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public void removeFromCart(CartItem cartItem) {
        cartItems.remove(cartItem);
    }
}
