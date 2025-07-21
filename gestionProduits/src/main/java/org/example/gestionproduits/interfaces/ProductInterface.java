package org.example.gestionproduits.interfaces;

import org.example.gestionproduits.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductInterface {
   Product getProductById(UUID id);
   List<Product> getAllProducts();
   List<Product> getProductsByParams(String category, double maxPrice);
}
