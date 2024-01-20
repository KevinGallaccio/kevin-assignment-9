package com.coderscampus.kevinassignment9.service;

import com.coderscampus.kevinassignment9.domain.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> ingestRecipeData();
    List<Recipe> getGlutenFreeRecipes();
    List<Recipe> getVeganRecipes();
    List<Recipe> getVeganAndGlutenFreeRecipes();
    List<Recipe> getVegetarianRecipes();

}
