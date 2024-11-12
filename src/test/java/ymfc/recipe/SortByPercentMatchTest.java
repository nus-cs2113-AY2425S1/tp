//@@author 3CCLY
package ymfc.recipe;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import ymfc.ingredient.Ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortByPercentMatchTest {

    @Test
    public void compare_validRecommendedRecipes_returnCorrectDifference() {
        // Initialise dummy recipe object for testing
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<String> steps = new ArrayList<>();
        Recipe recipe = new Recipe("ABC Soup", ingredients, steps);

        // Initialise two RecommendedRecipe objects with different percentMatch values
        int percentMatch1 = 100;
        int percentMatch2 = 50;
        ArrayList<Ingredient> missingIngredients = new ArrayList<>();
        RecommendedRecipe recommendedRecipe1 = new RecommendedRecipe(recipe, percentMatch1, missingIngredients);
        RecommendedRecipe recommendedRecipe2 = new RecommendedRecipe(recipe, percentMatch2, missingIngredients);

        SortByPercentMatch sorter = new SortByPercentMatch();

        assertEquals(percentMatch2 - percentMatch1, sorter.compare(recommendedRecipe1, recommendedRecipe2));
    }

    @Test
    public void sort_validRecommendedRecipes_returnDescendingOrder() {
        // Initialise 2 dummy recipe object for testing
        ArrayList<Ingredient> ingredients1 = new ArrayList<>();
        ingredients1.add(new Ingredient("ABC"));
        ArrayList<String> steps = new ArrayList<>();
        Recipe recipe1 = new Recipe("ABC Soup", ingredients1, steps);

        ArrayList<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(new Ingredient("ABC"));
        ingredients2.add(new Ingredient("Water"));
        Recipe recipe2 = new Recipe("ABC Soup", ingredients2, steps);

        // Initialise two RecommendedRecipe objects with different percentMatch values and different recipes
        int percentMatch1 = 100;
        int percentMatch2 = 50;
        ArrayList<Ingredient> missingIngredients = new ArrayList<>();
        RecommendedRecipe recommendedRecipe1 = new RecommendedRecipe(recipe1, percentMatch1, missingIngredients);
        RecommendedRecipe recommendedRecipe2 = new RecommendedRecipe(recipe2, percentMatch2, missingIngredients);

        // Add RecommendedRecipe objects into an ArrayList and sort them
        ArrayList<RecommendedRecipe> recommendedList = new ArrayList<>();
        recommendedList.add(recommendedRecipe2);
        recommendedList.add(recommendedRecipe1);

        SortByPercentMatch sorter = new SortByPercentMatch();
        Collections.sort(recommendedList, sorter);

        assertEquals(recommendedRecipe1, recommendedList.get(0));
        assertEquals(recommendedRecipe2, recommendedList.get(1));
    }
}
