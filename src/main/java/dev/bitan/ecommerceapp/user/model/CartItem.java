package dev.bitan.ecommerceapp.user.model;


import lombok.Data;

@Data
public class CartItem {
    private String productId;
    private int quantity;

    public CartItem(String productId) {
        this.productId = productId;
        this.quantity = 1;
    }
}


