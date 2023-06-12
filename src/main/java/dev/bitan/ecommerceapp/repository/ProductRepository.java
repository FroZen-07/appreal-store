package dev.bitan.ecommerceapp.repository;

import dev.bitan.ecommerceapp.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}