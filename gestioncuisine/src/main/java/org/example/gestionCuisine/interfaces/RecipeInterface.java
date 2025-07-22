package org.example.gestionCuisine.interfaces;

import org.example.gestionCuisine.model.Recipe;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface RecipeInterface {
    Recipe getRecipeById(UUID id);
    Recipe getRecipeByName(String name);
    List<Recipe> getAllRecipes();
    Recipe addRecipe(Recipe recipe);
    Recipe updateRecipe(Recipe recipe);
    void deleteRecipe(UUID id);
}
