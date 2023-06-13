package dev.bitan.ecommerceapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "products")
@Data
public class Product {

    @Id
    private String id;
    private String SKU;
    private String name;
    private String shortDescription;
    private String description;
    private double price;
    private List<String> categories;
    private List<String> images;
    private List<String> size;
    private List<String> color;
    private int quantity;


}
