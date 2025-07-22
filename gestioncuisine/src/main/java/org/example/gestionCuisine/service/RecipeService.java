package org.example.gestionCuisine.service;

import org.example.gestionCuisine.interfaces.RecipeInterface;
import org.example.gestionCuisine.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService implements RecipeInterface {
    private final Map<UUID, Recipe> recipes;
    private final UUID chineseCategoryId = UUID.fromString("550e8400-e29b-41d4-a716-446655440003");
    private final UUID frenchCategoryId = UUID.fromString("550e8400-e29b-41d4-a716-446655440001");
    private final UUID americanCategoryId = UUID.fromString("550e8400-e29b-41d4-a716-446655440004");
    private final UUID italianCategoryId = UUID.fromString("550e8400-e29b-41d4-a716-446655440002");

    public RecipeService() {
        recipes = new HashMap<>();

        Recipe omeletteRecipe = new Recipe();
        omeletteRecipe.setId(UUID.randomUUID());
        omeletteRecipe.setName("omelette chinoise");
        omeletteRecipe.setIngredients(Arrays.asList("3 oeufs", "2 cuilleres d'huile de sesame", "1 oignon vert", "sauce soja"));
        Map<Integer, String> omeletteInstructions = new HashMap<>();
        omeletteInstructions.put(1, "battre les oeufs avec sauce soja");
        omeletteInstructions.put(2, "chauffer l'huile dans le wok");
        omeletteInstructions.put(3, "verser les oeufs et melanger rapidement");
        omeletteInstructions.put(4, "garnir avec oignon vert hache");
        omeletteRecipe.setInstructions(omeletteInstructions);
        omeletteRecipe.setCategoryId(chineseCategoryId);

        Recipe croqueRecipe = new Recipe();
        croqueRecipe.setId(UUID.randomUUID());
        croqueRecipe.setName("croque monsieur");
        croqueRecipe.setIngredients(Arrays.asList("1 monsieur", "sel", "poivre"));
        Map<Integer, String> croqueInstructions = new HashMap<>();
        croqueInstructions.put(1, "trouver un monsieur");
        croqueInstructions.put(2, "le croquer");
        croqueRecipe.setInstructions(croqueInstructions);
        croqueRecipe.setCategoryId(frenchCategoryId);

        Recipe hotdogRecipe = new Recipe();
        hotdogRecipe.setId(UUID.randomUUID());
        hotdogRecipe.setName("hot dog americain");
        hotdogRecipe.setIngredients(Arrays.asList("1 chien", "bois sec", "allumettes"));
        Map<Integer, String> hotdogInstructions = new HashMap<>();
        hotdogInstructions.put(1, "attraper un chien");
        hotdogInstructions.put(2, "allumer un feu");
        hotdogInstructions.put(3, "faire le chauffer");
        hotdogRecipe.setInstructions(hotdogInstructions);
        hotdogRecipe.setCategoryId(americanCategoryId);

        Recipe pastaRecipe = new Recipe();
        pastaRecipe.setId(UUID.randomUUID());
        pastaRecipe.setName("pates a la bolognaise");
        pastaRecipe.setIngredients(Arrays.asList("500g pates", "400g viande hachee", "tomates", "oignons", "ail"));
        Map<Integer, String> pastaInstructions = new HashMap<>();
        pastaInstructions.put(1, "faire revenir oignons et viande hachee");
        pastaInstructions.put(2, "ajouter tomates et laisser mijoter longtemps");
        pastaInstructions.put(3, "cuire pates et servir avec sauce");
        pastaRecipe.setInstructions(pastaInstructions);
        pastaRecipe.setCategoryId(italianCategoryId);

        recipes.put(omeletteRecipe.getId(), omeletteRecipe);
        recipes.put(croqueRecipe.getId(), croqueRecipe);
        recipes.put(hotdogRecipe.getId(), hotdogRecipe);
        recipes.put(pastaRecipe.getId(), pastaRecipe);
    }

    @Override
    public Recipe getRecipeById(UUID id) {
        return recipes.get(id);
    }

    @Override
    public Recipe getRecipeByName(String name) {
        String lowerName = name.toLowerCase();
        return recipes.values().stream()
                .filter(recipe -> recipe.getName().toLowerCase().contains(lowerName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipes.values());
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        UUID id = UUID.randomUUID();
        recipe.setId(id);
        recipes.put(id, recipe);
        return recipe;
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        recipes.put(recipe.getId(), recipe);
        return recipe;
    }

    @Override
    public void deleteRecipe(UUID id) {
        recipes.remove(id);
    }


}