//@@author 3CCLY
package ymfc.commands;

import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.recipe.Recipe;
import ymfc.list.RecipeList;
import ymfc.recipe.RecommendedRecipe;
import ymfc.storage.Storage;
import ymfc.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecommendCommandTest {

    private Storage storage;
    private RecipeList recipeList;
    private Ui ui;
    private IngredientList ingredientList;
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private RecommendCommand recommendCommand;

    @BeforeEach
    void setUp() {
        recipeList = new RecipeList();
        ingredientList = new IngredientList();
        ui = new Ui(System.in);
        storage = new Storage();

        // Create 3 recipes
        ArrayList<Ingredient> ingredients1 = new ArrayList<>();
        ArrayList<Ingredient> ingredients2 = new ArrayList<>();
        ArrayList<Ingredient> ingredients3 = new ArrayList<>();
        ArrayList<String> steps = new ArrayList<>();
        steps.add("Cook abc in salted water for 76 days.");
        ingredients1.add(new Ingredient("abc"));
        recipe1 = new Recipe("abc", ingredients1, steps);
        ingredients2.add(new Ingredient("abc"));
        ingredients2.add(new Ingredient("water"));
        recipe2 = new Recipe("abc soup", ingredients2, steps);
        ingredients3.add(new Ingredient("abc"));
        ingredients3.add(new Ingredient("Water"));
        ingredients3.add(new Ingredient("salt"));
        recipe3 = new Recipe("salted abc soup", ingredients3, steps);

        recipeList.addRecipe(recipe1);
        recipeList.addRecipe(recipe2);
        recipeList.addRecipe(recipe3);

        ingredientList.addIngredient(new Ingredient("Water"));

        recommendCommand = new RecommendCommand();
    }

    @Test
    void testRandomRecipe() throws IOException {
        // Capture System.out printing
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        PrintStream testingStream = new PrintStream(message);
        PrintStream systemStream = System.out;
        System.setOut(testingStream);

        recommendCommand.execute(recipeList, ingredientList, ui, storage);

        // Return stream back to normal
        System.out.flush();
        System.setOut(systemStream);

        ArrayList<Ingredient> missingIngredients1 = new ArrayList<>();
        ArrayList<Ingredient> missingIngredients2 = new ArrayList<>();
        missingIngredients1.add(new Ingredient("abc"));
        RecommendedRecipe recommended1 = new RecommendedRecipe(recipe2, 50, missingIngredients1);
        missingIngredients2.add(new Ingredient("abc"));
        missingIngredients2.add(new Ingredient("salt"));
        RecommendedRecipe recommended2 = new RecommendedRecipe(recipe3, 33, missingIngredients2);
        String expected = ui.getLine() + System.lineSeparator()
                + "\tAlright, here are my recommendations:" + System.lineSeparator()
                + recommended1 + System.lineSeparator()
                + recommended2 + System.lineSeparator()
                + ui.getLine() + System.lineSeparator();

        assertEquals(expected, message.toString());
    }
    @Test
    void testEmptyIngredientList() {
        // Capture System.out printing
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        PrintStream testingStream = new PrintStream(message);
        PrintStream systemStream = System.out;
        System.setOut(testingStream);

        ingredientList = new IngredientList();
        recommendCommand = new RecommendCommand();
        recommendCommand.execute(recipeList, ingredientList, ui, storage);

        System.out.flush();
        System.setOut(systemStream);

        String expected = ui.getLine() + System.lineSeparator()
                + "\tUnfortunately I can't recommend you any recipes, "
                + System.lineSeparator()
                + "\tbecause you lack the ingredients for any recipes in my database."
                + System.lineSeparator()
                + "\tPerhaps you should hit up the grocery store."
                + System.lineSeparator()
                + "\tIt will do you some good to go outside once in a while."
                + System.lineSeparator()
                + ui.getLine() + System.lineSeparator();

        assertEquals(expected, message.toString());
    }
}
