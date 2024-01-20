package com.coderscampus.kevinassignment9.service;

import com.coderscampus.kevinassignment9.domain.Recipe;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    public List<Recipe> ingestRecipeData() {
        List<Recipe> recipes = new ArrayList<>();

        try (FileReader fileReader = new FileReader("recipes.txt");
             CSVParser csvParser = CSVFormat.DEFAULT
                     .withHeader()
                     .withIgnoreSurroundingSpaces()
                     .withEscape('\\')
                     .withQuoteMode(QuoteMode.MINIMAL).parse(fileReader)) {

            for (CSVRecord csvRecord : csvParser) {
                Recipe recipe = createRecipeFromCsvRecord(csvRecord);
                recipes.add(recipe);
            }

        } catch (IOException e) {
            System.out.println("file not found");
        }
        return recipes;
    }

    @Override
    public List<Recipe> getGlutenFreeRecipes() {
        return ingestRecipeData().stream().filter(Recipe::getGlutenFree).collect(Collectors.toList());
    }

    @Override
    public List<Recipe> getVeganRecipes() {
        return ingestRecipeData().stream().filter(Recipe::getVegan).collect(Collectors.toList());
    }

    @Override
    public List<Recipe> getVeganAndGlutenFreeRecipes() {
        return ingestRecipeData().stream().filter(recipe -> recipe.getVegan() && recipe.getGlutenFree()).collect(Collectors.toList());
    }

    @Override
    public List<Recipe> getVegetarianRecipes() {
        return ingestRecipeData().stream().filter(Recipe::getVegetarian).collect(Collectors.toList());
    }

    private Recipe createRecipeFromCsvRecord(CSVRecord csvRecord) {
        Recipe recipe = new Recipe();
        recipe.setCookingMinutes(Integer.parseInt(csvRecord.get("Cooking Minutes")));
        recipe.setDairyFree(Boolean.parseBoolean(csvRecord.get("Dairy Free")));
        recipe.setGlutenFree(Boolean.parseBoolean(csvRecord.get("Gluten Free")));
        recipe.setInstructions(csvRecord.get("Instructions"));
        recipe.setPreparationMinutes(Double.parseDouble(csvRecord.get("Preparation Minutes")));
        recipe.setPricePerServing(Double.parseDouble(csvRecord.get("Price Per Serving")));
        recipe.setReadyInMinutes(Integer.parseInt(csvRecord.get("Ready In Minutes")));
        recipe.setServings(Integer.parseInt(csvRecord.get("Servings")));
        recipe.setSpoonacularScore(Double.parseDouble(csvRecord.get("Spoonacular Score")));
        recipe.setTitle(csvRecord.get("Title"));
        recipe.setVegan(Boolean.parseBoolean(csvRecord.get("Vegan")));
        recipe.setVegetarian(Boolean.parseBoolean(csvRecord.get("Vegetarian")));
        return recipe;
    }

}
