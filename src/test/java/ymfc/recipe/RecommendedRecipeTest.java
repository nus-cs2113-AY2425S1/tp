//@@author 3CCLY
package ymfc.recipe;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import ymfc.ingredient.Ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecommendedRecipeTest {

    @Test
    public void getPercentMatch_validPercentMatch_returnCorrectPercentMatch() {
        // Initialise a Recipe object for testing
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("ABC"));
        ArrayList<String> steps = new ArrayList<>();
        steps.add("Cook ABC in water for 58 hours");
        Recipe recipe = new Recipe("ABC Soup", ingredients, steps);
        int percentMatch = 100;
        ArrayList<Ingredient> missingIngredients = new ArrayList<>();

        // Initialise a RecommendedRecipe object for testing
        RecommendedRecipe recommendedRecipe = new RecommendedRecipe(recipe, percentMatch, missingIngredients);
        assertEquals(percentMatch, recommendedRecipe.getPercentMatch());
    }

    @Test
    public void toString_validPercentMatchWithoutMissingIngredients_correctString() {
        // Initialise a Recipe object for testing
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("ABC"));
        ArrayList<String> steps = new ArrayList<>();
        steps.add("Cook ABC in water for 58 hours");
        Recipe recipe = new Recipe("ABC Soup", ingredients, steps);

        // Initialise a RecommendedRecipe object with null missingIngredients for testing
        int percentMatch = 100;
        ArrayList<Ingredient> missingIngredients = new ArrayList<>();
        RecommendedRecipe recommendedRecipe = new RecommendedRecipe(recipe, percentMatch, missingIngredients);

        // Expected toString return string
        String expected = "\tFor this recipe, you have " + percentMatch
                + "% of the ingredients needed" + System.lineSeparator()
                + "\t" + recipe + System.lineSeparator();

        assertEquals(expected, recommendedRecipe.toString());
    }

    @Test
    public void toString_validPercentMatchWithMissingIngredients_correctString() {
        // Initialise a Recipe object for testing
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("ABC"));
        ingredients.add(new Ingredient("Water"));
        ArrayList<String> steps = new ArrayList<>();
        steps.add("Cook ABC in water for 58 hours");
        Recipe recipe = new Recipe("ABC Soup", ingredients, steps);

        // Initialise a RecommendedRecipe object with missingIngredients for testing
        int percentMatch = 50;
        ArrayList<Ingredient> missingIngredients = new ArrayList<>();
        missingIngredients.add(new Ingredient("Water"));
        RecommendedRecipe recommendedRecipe = new RecommendedRecipe(recipe, percentMatch, missingIngredients);

        // Expected toString return string
        String expected = "\tFor this recipe, you have " + percentMatch
                + "% of the ingredients needed" + System.lineSeparator()
                + "\tYou are missing the following:" + System.lineSeparator()
                + "\t  - Water" + System.lineSeparator()
                + "\t" + recipe + System.lineSeparator();

        assertEquals(expected, recommendedRecipe.toString());
    }
}
