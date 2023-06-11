package dev.bitan.ecommerceapp.controller;
import dev.bitan.ecommerceapp.model.Product;
import dev.bitan.ecommerceapp.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/user")
public class UserController {
    private final ProductRepository productRepository;
    private List<Product> cart = new ArrayList<>();

    public UserController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public String viewProducts(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, 10));
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        return "user/products";
    }


    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id"));
        cart.add(product);
        return "redirect:/user/cart";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable("id") String id) {
        cart.removeIf(product -> product.getId().equals(id));
        return "redirect:/user/cart";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", cart);
        return "user/cart";
    }
}