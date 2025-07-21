package org.example.gestionproduits.controller;

import org.example.gestionproduits.interfaces.ProductInterface;
import org.example.gestionproduits.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductInterface productService;

    public ProductController(ProductInterface productService) {
        this.productService = productService;
    }

    @GetMapping({"/filters", "/all"})
    public String getProductsPage(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double maxPrice,
            Model model) {
        List<Product> products;
        if (category != null && maxPrice != null) {
            products = productService.getProductsByParams(category, maxPrice);
            model.addAttribute("title", "filteredList");
        } else {
            products = productService.getAllProducts();
            model.addAttribute("title", "rawList");
        }
        model.addAttribute("products", products);
        return "products";
    }


    @GetMapping("/{id}")
    public String getProductById(@PathVariable UUID id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "productsId";
    }
}
