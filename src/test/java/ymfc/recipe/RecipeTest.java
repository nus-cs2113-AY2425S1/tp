package ymfc.recipe;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RecipeTest {

    @Test
    public void getName_validName_returnCorrectName() {
        Recipe recipe = new Recipe("Pasta", new ArrayList<>(), new ArrayList<>());
        assertEquals("Pasta", recipe.getName());
    }

    @Test
    void setName_validName_nameUpdatedCorrectly() {
        Recipe recipe = new Recipe("Pasta", new ArrayList<>(), new ArrayList<>());
        recipe.setName("Pizza");
        assertEquals("Pizza", recipe.getName());
    }

    @Test
    void getIngredients_validIngredients_returnCorrectIngredients() {
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Tomato");
        ingredients.add("Cheese");
        Recipe recipe = new Recipe("Pasta", ingredients, new ArrayList<>());
        assertEquals(ingredients, recipe.getIngredients());
    }

    @Test
    void setIngredients_validIngredients_ingredientsUpdatedCorrectly() {
        Recipe recipe = new Recipe("Pasta", new ArrayList<>(), new ArrayList<>());
        ArrayList<String> newIngredients = new ArrayList<>();
        newIngredients.add("Flour");
        newIngredients.add("Water");
        recipe.setIngredients(newIngredients);
        assertEquals(newIngredients, recipe.getIngredients());
    }

    @Test
    void getSteps_validSteps_returnCorrectSteps() {
        ArrayList<String> steps = new ArrayList<>();
        steps.add("Boil water");
        steps.add("Add pasta");
        Recipe recipe = new Recipe("Pasta", new ArrayList<>(), steps);
        assertEquals(steps, recipe.getSteps());
    }

    @Test
    void setSteps_validSteps_stepsUpdatedCorrectly() {
        Recipe recipe = new Recipe("Pasta", new ArrayList<>(), new ArrayList<>());
        ArrayList<String> newSteps = new ArrayList<>();
        newSteps.add("Mix flour and water");
        newSteps.add("Knead dough");
        recipe.setSteps(newSteps);
        assertEquals(newSteps, recipe.getSteps());
    }

    @Test
    void toString_validIngredientsAndSteps_correctFormat() {

        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Flour");
        ingredients.add("Eggs");
        ingredients.add("Milk");
        ingredients.add("Sugar");
        ingredients.add("Baking powder");

        ArrayList<String> steps = new ArrayList<>();
        steps.add("Mix ingredients");
        steps.add("Heat the pan");
        steps.add("Pour batter");
        steps.add("Flip the pancake");
        steps.add("Serve hot");

        Recipe recipe = new Recipe("Pancakes", ingredients, steps);

        // Expected string representation
        String expected = "Recipe: Pancakes" + System.lineSeparator() +
                "  Ingredients: " + System.lineSeparator() +
                "    - Flour" + System.lineSeparator() +
                "    - Eggs" + System.lineSeparator() +
                "    - Milk" + System.lineSeparator() +
                "    - Sugar" + System.lineSeparator() +
                "    - Baking powder" + System.lineSeparator() +
                "  Steps: " + System.lineSeparator() +
                "    1. Mix ingredients" + System.lineSeparator() +
                "    2. Heat the pan" + System.lineSeparator() +
                "    3. Pour batter" + System.lineSeparator() +
                "    4. Flip the pancake" + System.lineSeparator() +
                "    5. Serve hot";

        // Assert the output of toString method matches expected
        assertEquals(expected, recipe.toString());
    }

    @Test
    public void getCuisine_validCuisine_returnCorrectString() {
        Recipe recipe = new Recipe("Pasta", new ArrayList<>(), new ArrayList<>(), "Italian", 4);
        assertEquals("Italian", recipe.getCuisine());
    }

    @Test
    void setCuisine_validCuisine_stringUpdatedCorrectly() {
        Recipe recipe = new Recipe("Pasta", new ArrayList<>(), new ArrayList<>(), "Italian", 4);
        assertEquals("Italian", recipe.getCuisine());
        recipe.setCuisine("New York");
        assertEquals("New York", recipe.getCuisine());
    }

    @Test
    void setTimeTaken_validTime_integerUpdatedCorrectly() {
        Recipe recipe = new Recipe("Pasta", new ArrayList<>(), new ArrayList<>(), "Italian", 4);
        assertEquals(4, recipe.getTimeTaken());
        recipe.setTimeTaken(8);
        assertEquals(8, recipe.getTimeTaken());
    }

    @Test
    void equals_isSameRecipe_returnsTrue() {
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Flour");

        ArrayList<String> steps = new ArrayList<>();
        steps.add("Bake");

        Recipe recipe1 = new Recipe("Pancakes", ingredients, steps, "American", 3);
        Recipe recipe2 = new Recipe("Pancakes", ingredients, steps, "American", 3);

        assertTrue(recipe1.equals(recipe2));
    }

    @Test
    void equals_isNotSameRecipe_returnsFalse() {
        ArrayList<String> ingredients1 = new ArrayList<>();
        ingredients1.add("Flour");
        ArrayList<String> ingredients2 = new ArrayList<>();
        ingredients2.add("Rice");

        ArrayList<String> steps1 = new ArrayList<>();
        steps1.add("Bake");
        ArrayList<String> steps2 = new ArrayList<>();
        steps2.add("Cook");

        Recipe recipe1 = new Recipe("Pancakes", ingredients1, steps1, "American", 3);
        Recipe recipe2 = new Recipe("Pancakes", ingredients2, steps1, "American", 3);
        assertFalse(recipe1.equals(recipe2)); // Different ingredients

        Recipe recipe3 = new Recipe("Pancakes", ingredients1, steps2, "American", 3);
        assertFalse(recipe1.equals(recipe3)); // Different steps

        Recipe recipe4 = new Recipe("Ricecakes", ingredients1, steps1, "American", 3);
        assertFalse(recipe1.equals(recipe4)); // Different name
    }
}
