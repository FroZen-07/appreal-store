package dev.bitan.ecommerceapp.user.controller;
import dev.bitan.ecommerceapp.user.model.CartItem;
import dev.bitan.ecommerceapp.product.model.Product;
import dev.bitan.ecommerceapp.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/user")
public class UserController {
    private final ProductService productService;

    private final List<CartItem> cart;

    public UserController(ProductService productService) {
        this.productService = productService;
        this.cart = new ArrayList<>();
    }

    @GetMapping("/products")
    public String viewProducts(@RequestParam(defaultValue = "0") int page, Model model) {
        int PAGE_SIZE = 10;
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
            CartItem cartItem = findCartItemById(productId);
            if (cartItem != null) {
                // Product already exists in cart, increase quantity
                cartItem.setQuantity(cartItem.getQuantity() + 1);
            } else {
                // Product does not exist in cart, add new item
                cart.add(new CartItem(productId));
            }
        }
        return "redirect:/user/cart";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable("id") String productId) {
        CartItem cartItem = findCartItemById(productId);
        if (cartItem != null) {
            if (cartItem.getQuantity() > 1) {
                // Decrease quantity if greater than 1
                cartItem.setQuantity(cartItem.getQuantity() - 1);
            } else {
                // Remove item if quantity is 1
                cart.remove(cartItem);
            }
        }
        return "redirect:/user/cart";
    }

    private CartItem findCartItemById(String productId) {
        return cart.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }


    @GetMapping("/cart")
    public String viewCart(Model model) {
        List<Product> products = productService.getProductsInCart(cart);
        double totalPrice = productService.calculateTotalPrice(products);

        model.addAttribute("cart", products);
        model.addAttribute("totalPrice", totalPrice);

        return "user/cart";
    }
}
