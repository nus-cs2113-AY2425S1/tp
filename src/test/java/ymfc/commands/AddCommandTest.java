package ymfc.commands;

import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.recipe.Recipe;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AddCommandTest {

    private Storage storage;
    private RecipeList recipeList;
    private Ui ui;
    private Recipe recipe;
    private IngredientList ingredientList;

    @BeforeEach
    void setUp() {
        recipeList = new RecipeList();
        ingredientList = new IngredientList();
        ui = new Ui(System.in);
        storage = new Storage();

        // Sample ingredients and steps
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Pasta"));
        ingredients.add(new Ingredient("Water"));
        ingredients.add(new Ingredient("Salt"));

        ArrayList<String> steps = new ArrayList<>();
        steps.add("Boil water.");
        steps.add("Add pasta.");
        steps.add("Cook for 10 minutes.");

        recipe = new Recipe("Pasta", ingredients, steps);
    }

    @Test
    void testAddNewRecipe() throws IOException {
        AddCommand addCommand = new AddCommand(recipe);
        addCommand.execute(recipeList, ingredientList, ui, storage);

        assertEquals(1, recipeList.getCounter());
        assertEquals(recipe, recipeList.getRecipe(0));
    }

    @Test
    void testNoDuplicateRecipe() throws IOException {
        AddCommand addCommand = new AddCommand(recipe);
        addCommand.execute(recipeList, ingredientList, ui, storage);
        assertEquals(1, recipeList.getCounter());

        // Capture System.out printing
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        PrintStream testingStream = new PrintStream(message);
        PrintStream systemStream = System.out;
        System.setOut(testingStream);

        addCommand.execute(recipeList, ingredientList, ui, storage); // attempt to add duplicate recipe
        assertEquals(1, recipeList.getCounter()); // confirms that there is still only 1 recipe in recipeList

        System.out.flush();
        System.setOut(systemStream);

        String expected = ui.getLine() + System.lineSeparator()
                + "\tThere already exists a recipe called: Pasta!"
                + System.lineSeparator()
                + ui.getLine() + System.lineSeparator();

        assertEquals(expected, message.toString());
    }
}
