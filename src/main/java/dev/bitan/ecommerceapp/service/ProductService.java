package dev.bitan.ecommerceapp.service;


import dev.bitan.ecommerceapp.model.Product;
import dev.bitan.ecommerceapp.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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


}

