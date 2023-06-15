package dev.bitan.ecommerceapp.user.model;

import dev.bitan.ecommerceapp.user.model.CartItem;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "users")
@Data
public class User implements UserDetails {
    @Id
    private String id;
    private String username;
    private String password;
    private List<String> roles;
    private List<CartItem> cartItems;

    public User() {
        this.cartItems = new ArrayList<>();
    }

    public User(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.cartItems = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addToCart(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public void removeFromCart(CartItem cartItem) {
        cartItems.remove(cartItem);
    }
}
