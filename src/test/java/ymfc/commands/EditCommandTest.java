//@@author 3CCLY
package ymfc.commands;

import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.recipe.Recipe;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;
import ymfc.exception.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditCommandTest {

    private Storage storage;
    private RecipeList emptyList;
    private RecipeList nonEmptyList;
    private IngredientList ingredientList;
    private Ui ui;
    private Recipe recipe;
    private Recipe editedRecipe;
    private AddCommand addCommand;
    private EditCommand editCommand;

    @BeforeEach
    void setUp() {
        emptyList = new RecipeList();
        nonEmptyList = new RecipeList();
        ingredientList = new IngredientList();
        ui = new Ui(System.in);
        storage = new Storage();

        // Sample ingredients and steps
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("magi mee"));
        ingredients.add(new Ingredient("water"));

        ArrayList<String> steps = new ArrayList<>();
        steps.add("boil water");
        steps.add("add magi mee");
        steps.add("wait 5 min");

        recipe = new Recipe("instant noodles", ingredients, steps);
        addCommand = new AddCommand(recipe);
        nonEmptyList.addRecipe(recipe);

        // Sample edited steps
        ArrayList<String> newSteps = new ArrayList<>();
        newSteps.add("boil water");
        newSteps.add("eat magi mee");
        newSteps.add("drink water");

        editedRecipe = new Recipe("instant noodles", ingredients, newSteps);
        editCommand = new EditCommand("instant noodles", null, null,
                newSteps, null, null);
    }

    @Test
    void testEditRecipe_success() throws IOException, InvalidArgumentException {
        addCommand.execute(emptyList, ingredientList, ui, storage);

        assertEquals(1, emptyList.getCounter());
        assertEquals(recipe, emptyList.getRecipe(0));

        editCommand.execute(emptyList, ingredientList, ui, storage);
        assertEquals(1, emptyList.getCounter());
        assertEquals(editedRecipe.toString(), emptyList.getRecipe(0).toString());
    }

    @Test
    void testEditRecipe_fail() {
        EditCommand dummyEditCommand = new EditCommand("ramen", null, null,
                null, null, null);
        assertThrows(InvalidArgumentException.class,
                () -> dummyEditCommand.execute(nonEmptyList, ingredientList, ui, storage));
    }
}
