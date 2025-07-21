package org.example.gestionproduits.services;

import org.example.gestionproduits.interfaces.ProductInterface;
import org.example.gestionproduits.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements ProductInterface {
    private final List<Product> productList = new ArrayList<>();

    public ProductService() {
        productList.add(new Product(UUID.randomUUID(), "PC gamer", "Informatique", 1299.99));
        productList.add(new Product(UUID.randomUUID(), "Tablette Android", "Informatique", 329.99));
        productList.add(new Product(UUID.randomUUID(), "Cafetière", "Électroménager", 89.90));
        productList.add(new Product(UUID.randomUUID(), "Grille-pain", "Électroménager", 34.99));
        productList.add(new Product(UUID.randomUUID(), "Téléviseur OLED", "TV", 799.00));
        productList.add(new Product(UUID.randomUUID(), "Smartphone", "Téléphonie", 679.99));
        productList.add(new Product(UUID.randomUUID(), "VTT", "Sport", 499.00));
        productList.add(new Product(UUID.randomUUID(), "Casque Bluetooth", "Audio", 119.00));
        productList.add(new Product(UUID.randomUUID(), "Montre connectée", "Wearable", 149.99));
        productList.add(new Product(UUID.randomUUID(), "Aspirateur robot", "Électroménager", 299.99));
    }
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public List<Product> getProductsByParams(String category, double maxPrice) {
        return productList.stream()
                .filter(product -> product.getCategory().equals(category) && product.getPrice() <= maxPrice)
                .toList();
    }

    public Product getProductById(UUID id) {
        return productList.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
