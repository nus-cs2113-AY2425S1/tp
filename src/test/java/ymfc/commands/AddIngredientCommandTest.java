package ymfc.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.recipe.Recipe;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
    }
}