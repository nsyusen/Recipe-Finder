package com.example.cs65_final_project.spoonacular;

import com.example.cs65_final_project.Ingredient;
import com.example.cs65_final_project.Recipe;
import com.example.cs65_final_project.exceptions.SpoonacularException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Controller acting as a gateway to the Spoonacular client.
 */
public class SpoonacularGatewayController {

    private final SpoonacularClient mClient;

    public SpoonacularGatewayController() {
        this.mClient = new SpoonacularClient();
    }

    public List<Recipe> getRecipes(List<String> ingredients,
                                   int numOfResults) throws SpoonacularException {
        return toRecipes(mClient.getRecipes(ingredients, numOfResults));
    }

    public List<Ingredient> getIngredients(String query,
                                           int numOfResults) throws SpoonacularException {
        return toIngredients(mClient.getIngredients(query, numOfResults));
    }

    private List<Ingredient> toIngredients(List<SpoonacularIngredient> ingredients) {
        return ingredients.stream().map(this::toIngredient).collect(Collectors.toList());
    }

    private List<Recipe> toRecipes(List<SpoonacularRecipe> spoonacularRecipes) {
        return spoonacularRecipes.stream().map(this::toRecipe).collect(Collectors.toList());
    }

    private Recipe toRecipe(SpoonacularRecipe spoonacularRecipe) {
        return new Recipe(
                spoonacularRecipe.getTitle(),
                Stream.concat(spoonacularRecipe.getUsedIngredients().stream(),
                        spoonacularRecipe.getMissedIngredients().stream())
                        .map(this::toIngredient)
                        .collect(Collectors.toList()),
                30);
    }

    // TODO: Time?
    private Ingredient toIngredient(SpoonacularRecipeIngredient ingredient) {
        return new Ingredient(ingredient.getOriginal(), 1.0f);
    }

    // TODO: Time?
    private Ingredient toIngredient(SpoonacularIngredient ingredient) {
        return new Ingredient(ingredient.getName(), 1.0f);
    }
}