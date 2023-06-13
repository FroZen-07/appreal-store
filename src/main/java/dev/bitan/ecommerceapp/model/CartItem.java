package dev.bitan.ecommerceapp.model;

import dev.bitan.ecommerceapp.service.ProductService;
import lombok.Data;

public class CartItem {
    private String productId;
    private int quantity;

    public CartItem(String productId) {
        this.productId = productId;
        this.quantity = 1;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

