package org.example.gestionproduits.controller;

import org.example.gestionproduits.interfaces.ProductInterface;
import org.example.gestionproduits.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/products")
@ResponseBody
public class ProductController {

    private final ProductInterface productService;

    public ProductController(ProductInterface productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable UUID id) {
        return productService.getProductById(id);
    }


    @GetMapping("/filter")
    public List<Product> filterProducts(
            @RequestParam String category,
            @RequestParam double maxPrice) {
        return productService.getProductsByParams(category, maxPrice);
    }
}

