package dev.bitan.ecommerceapp.controller;
import dev.bitan.ecommerceapp.model.Product;
import dev.bitan.ecommerceapp.service.ProductService;
import org.springframework.data.domain.Page;
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
    private final ProductService productService;
    private static final int PAGE_SIZE = 10;

    private List<Product> cart;

    public UserController(ProductService productService) {
        this.productService = productService;
        this.cart = new ArrayList<>();
    }

    @GetMapping("/products")
    public String viewProducts(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Product> productPage = productService.findAllProducts(page, PAGE_SIZE);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", PAGE_SIZE);
        int totalPages = productPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("isFirstPage", page == 0);
        model.addAttribute("isLastPage", page == totalPages - 1);
        return "user/products";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") String productId) {
        Product product = productService.findProductById(productId);
        if (product != null) {
            cart.add(product);
        }
        return "redirect:/user/cart";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable("id") String productId) {
        cart.removeIf(product -> product.getId().equals(productId));
        return "redirect:/user/cart";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", cart);
        return "user/cart";
    }
}