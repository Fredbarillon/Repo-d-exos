package org.example.gestionCuisine.service;

import org.example.gestionCuisine.interfaces.CategoryInterface;
import org.example.gestionCuisine.model.Category;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService implements CategoryInterface {
    private final Map<UUID, Category> categories;

    public CategoryService() {
        categories = new HashMap<>();
        Category category1 = new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"), "Thai", "Spicy but yummy");
        Category category2 = new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440001"), "French", "Full of cheese!");
        Category category3 = new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440002"), "Italian", "Pasta at every turn");
        Category category4 = new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440003"), "Chinese", "Sweet and sour");
        Category category5 = new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440004"), "American", "Fat and greasy");

        categories.put(category1.getId(), category1);
        categories.put(category2.getId(), category2);
        categories.put(category3.getId(), category3);
        categories.put(category4.getId(), category4);
        categories.put(category5.getId(), category5);

    }

    @Override
    public Category getCategoryById(UUID id) {
        return categories.get(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        String lowerName = name.toLowerCase();
        return categories.values().stream()
                .filter(category -> category.getName().toLowerCase().contains(lowerName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Category addCategory(Category category) {
        UUID id = UUID.randomUUID();
        category.setId(id);
        categories.put(id, category);
        return category;
    }

    @Override
    public Category updateCategory(Category category) {
        categories.put(category.getId(), category);
        return category;
    }

    @Override
    public void deleteCategory(UUID id) {
        categories.remove(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }
}