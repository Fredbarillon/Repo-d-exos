package org.example.gestionCuisine.controller;

import org.example.gestionCuisine.interfaces.CategoryInterface;
import org.example.gestionCuisine.interfaces.RecipeInterface;
import org.example.gestionCuisine.model.Recipe;
import org.example.gestionCuisine.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final CategoryInterface categoryService;
    private final RecipeInterface recipeService;

    public RecipeController(RecipeInterface recipeService, CategoryInterface categoryService) {
        this.categoryService = categoryService;
        this.recipeService = recipeService;
    }

    @GetMapping
    public String home(){
        return "home";
    }

    @GetMapping("/list")
    public String getAllRecipes(Model model){
        List<Recipe> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "recipe/recipeList";
    }

    @GetMapping("/detail/{id}")
    public String getRecipeDetails(@PathVariable("id") UUID id, Model model){
        Recipe recipe = recipeService.getRecipeById(id);
        if (recipe != null && recipe.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(recipe.getCategoryId());
            model.addAttribute("category", category);
        }
        model.addAttribute("recipe", recipe);
        return "recipe/recipeDetails";
    }

    @GetMapping("/add")
    public String addRecipe(Model model){
        Recipe recipe = new Recipe();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("recipe", recipe);
        model.addAttribute("categories", categories);
        return "recipe/recipeForm";
    }

    @PostMapping("/add")
    public String addRecipe(@ModelAttribute Recipe recipe, @RequestParam("categoryId") String categoryIdStr){
        if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
            try {
                UUID categoryId = UUID.fromString(categoryIdStr);
                recipe.setCategoryId(categoryId);
            } catch (IllegalArgumentException e) {
            }
        }
        recipeService.addRecipe(recipe);
        return "redirect:/recipe/list";
    }

    @GetMapping("/search")
    public String searchRecipe(@RequestParam(value = "name", required = false) String name, Model model){
        if (name != null && !name.trim().isEmpty()) {
            Recipe recipe = recipeService.getRecipeByName(name);
            List<Recipe> recipes = new ArrayList<>();
            if (recipe != null) {
                recipes.add(recipe);
            }
            model.addAttribute("recipes", recipes);
            model.addAttribute("searchTerm", name);
        }
        return "recipe/searchResults";
    }

    @GetMapping("/update/{id}")
    public String updateRecipe(@PathVariable("id") UUID id, Model model){
        Recipe recipe = recipeService.getRecipeById(id);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("recipe", recipe);
        model.addAttribute("categories", categories);
        return "recipe/recipeUpdateForm";
    }

    @PostMapping("/update")
    public String updateRecipe(@ModelAttribute Recipe recipe, @RequestParam("categoryId") String categoryIdStr){
        if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
            try {
                UUID categoryId = UUID.fromString(categoryIdStr);
                recipe.setCategoryId(categoryId);
            } catch (IllegalArgumentException e) {}
        }
        recipeService.updateRecipe(recipe);
        return "redirect:/recipe/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable("id") UUID id){
        recipeService.deleteRecipe(id);
        return "redirect:/recipe/list";
    }
}