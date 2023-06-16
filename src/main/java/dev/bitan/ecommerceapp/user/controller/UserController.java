package dev.bitan.ecommerceapp.user.controller;

import dev.bitan.ecommerceapp.user.model.CartItem;
import dev.bitan.ecommerceapp.product.model.Product;
import dev.bitan.ecommerceapp.product.service.ProductService;
import dev.bitan.ecommerceapp.user.model.User;
import dev.bitan.ecommerceapp.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/user")
public class UserController {
    private final ProductService productService;
    private final UserRepository userRepository;

    public UserController(ProductService productService, UserRepository userRepository) {
        this.productService = productService;
        this.userRepository = userRepository;
    }

    @GetMapping("/products")
    public String getAllProducts(Model model, @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(required = false) String searchName) {
        int pageSize = 10;
        Page<Product> productPage;
        List<Product> products;

        if (searchName != null && !searchName.isEmpty()) {
            // Perform search based on product name
            productPage = productService.searchProductsByName(searchName, page, pageSize);
            products = productPage.getContent();
        } else {
            // Retrieve paginated list of products
            productPage = productService.findAllProducts(page, pageSize);
            products = productPage.getContent();
        }

        int totalPages = productPage.getTotalPages();
        boolean isLastPage = page == totalPages - 1;

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("isLastPage", isLastPage);
        model.addAttribute("quantity", 0);

        if (searchName != null && !searchName.isEmpty()) {
            model.addAttribute("searchResults", products);
        } else {
            model.addAttribute("searchResults", null);
        }

        return "user/products";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") String productId, @RequestParam("quantity") int quantity, Principal principal) {
        Product product = productService.findProductById(productId);
        if (product != null) {
            CartItem cartItem = new CartItem(productId);
            cartItem.setQuantity(quantity);

            User user = userRepository.findByUsername(principal.getName())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // Check if the product already exists in the cart
            CartItem existingCartItem = user.getCartItems()
                    .stream()
                    .filter(item -> item.getProductId().equals(productId))
                    .findFirst()
                    .orElse(null);

            if (existingCartItem != null) {
                // Product already exists in cart, update the quantity
                existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            } else {
                // Product does not exist in cart, add new item
                user.addToCart(cartItem);
            }

            userRepository.save(user);
        }

        return "redirect:/user/cart";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable("id") String productId, Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Check if the product exists in the cart
        CartItem existingCartItem = user.getCartItems()
                .stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            if (existingCartItem.getQuantity() > 1) {
                // Decrease the quantity by 1 if greater than one
                existingCartItem.setQuantity(existingCartItem.getQuantity() - 1);
            } else {
                // Remove the item from the cart if the quantity is one
                user.removeFromCart(existingCartItem);
            }
        }

        userRepository.save(user);
        return "redirect:/user/cart";
    }

    @GetMapping("/cart")
    public String viewCart(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Product> products = productService.getProductsInCart(user.getCartItems());
        double totalPrice = productService.calculateTotalPrice(products);
        model.addAttribute("cart", products);
        model.addAttribute("totalPrice", totalPrice);
        return "user/cart";
    }
}
