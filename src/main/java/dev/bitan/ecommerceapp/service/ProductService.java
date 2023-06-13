package dev.bitan.ecommerceapp.service;


import dev.bitan.ecommerceapp.model.CartItem;
import dev.bitan.ecommerceapp.model.Product;
import dev.bitan.ecommerceapp.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAllProducts(int page, int pageSize) {
        return productRepository.findAll(PageRequest.of(page, pageSize));
    }

    public Product findProductById(String productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }


    public List<Product> getProductsInCart(List<CartItem> cart) {
        List<Product> products = new ArrayList<>();

        for (CartItem cartItem : cart) {
            Product product = findProductById(cartItem.getProductId());
            if (product != null) {
                product.setQuantity(cartItem.getQuantity());
                products.add(product);
            }
        }

        return products;
    }

    public double calculateTotalPrice(List<Product> products) {
        double totalPrice = 0.0;

        for (Product product : products) {
            double itemTotalPrice = product.getPrice() * product.getQuantity();
            totalPrice += itemTotalPrice;
        }

        return totalPrice;
    }
}

