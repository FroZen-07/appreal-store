package dev.bitan.ecommerceapp.controller;

import dev.bitan.ecommerceapp.model.Product;
import dev.bitan.ecommerceapp.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;
    private static final int PAGE_SIZE = 10;


    public AdminController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping("/products")
//    public String viewProducts(@RequestParam(defaultValue = "0") int page, Model model) {
//        int pageSize = 10;
//        Page<Product> productPage = productService.findAllProducts(page, pageSize);
//        model.addAttribute("products", productPage.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("pageSize", pageSize);
//        return "admin/products";
//    }

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
        return "admin/products";
    }

    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/add-product";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable("id") String productId, Model model) {
        Product product = productService.findProductById(productId);
        if (product == null) {
            return "redirect:/admin/products";
        }
        model.addAttribute("product", product);
        return "admin/edit-product";
    }

    @PostMapping("/products/edit/{id}")
    public String editProduct(@PathVariable("id") String productId, @ModelAttribute("product") Product updatedProduct) {
        Product product = productService.findProductById(productId);
        if (product != null) {
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());
            productService.saveProduct(product);
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") String productId) {
        productService.deleteProduct(productId);
        return "redirect:/admin/products";
    }
}
