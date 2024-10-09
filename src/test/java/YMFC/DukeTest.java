package YMFC;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class DukeTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

}

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
}
