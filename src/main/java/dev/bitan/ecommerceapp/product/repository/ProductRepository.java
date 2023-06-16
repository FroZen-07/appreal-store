package dev.bitan.ecommerceapp.product.repository;

import dev.bitan.ecommerceapp.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    List<Product> findByIdIn(List<String> productIds);
}
