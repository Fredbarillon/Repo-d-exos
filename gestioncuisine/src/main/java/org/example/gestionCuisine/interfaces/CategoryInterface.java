package org.example.gestionCuisine.interfaces;

import org.example.gestionCuisine.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryInterface {
    Category getCategoryById(UUID id);
    Category getCategoryByName(String name);
    Category addCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(UUID id);
    List<Category> getAllCategories();
}
