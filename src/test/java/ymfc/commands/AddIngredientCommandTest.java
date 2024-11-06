package ymfc.commands;

import org.junit.jupiter.api.Test;
import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AddIngredientCommandTest {

    private Storage storage = new Storage();
    private RecipeList emptyList = new RecipeList();
    private Ui ui = new Ui(System.in);
    private IngredientList ingredientList = new IngredientList();
    private Ingredient ingredient = new Ingredient("Chicken");

    @Test
    void addIngredientCommand_success() throws IOException {
        AddIngredientCommand command = new AddIngredientCommand(ingredient);
        command.execute(emptyList, ingredientList, ui, storage);
        assertEquals(1, ingredientList.getIngredients().size());
        assertEquals(ingredient, ingredientList.getIngredients().get(0));

        ingredientList.addIngredient(new Ingredient("Bad apple"));
        ingredientList.sortAlphabetically(); // Make sure "Chicken" is not the first ingredient
        command.execute(emptyList, ingredientList, ui, storage);
        // Duplicate ingredient will not be added
        assertNotEquals(3, ingredientList.getIngredients().size());
    }
}
