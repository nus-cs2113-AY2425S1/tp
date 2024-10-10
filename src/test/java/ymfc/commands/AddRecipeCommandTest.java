package ymfc.commands;

import ymfc.recipe.Recipe;
import ymfc.recipelist.RecipeList;
import ymfc.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddRecipeCommandTest {

    private RecipeList emptyList;
    private Ui ui;
    private Recipe recipe;
    private AddRecipeCommand addRecipeCommand;

    @BeforeEach
    void setUp() {
        emptyList = new RecipeList();
        ui = new Ui(System.in);

        // Sample ingredients and steps
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Pasta");
        ingredients.add("Water");
        ingredients.add("Salt");

        ArrayList<String> steps = new ArrayList<>();
        steps.add("Boil water.");
        steps.add("Add pasta.");
        steps.add("Cook for 10 minutes.");

        recipe = new Recipe("Pasta", ingredients, steps);
        addRecipeCommand = new AddRecipeCommand(recipe);
    }

    @Test
    void testAddNewRecipe() {
        addRecipeCommand.execute(emptyList, ui);

        assertEquals(1, emptyList.getCounter());
        assertEquals(recipe, emptyList.getRecipe(0));

    }
}
