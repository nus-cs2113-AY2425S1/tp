package ymfc.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ymfc.exception.InvalidArgumentException;
import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteIngredientCommandTest {

    private static IngredientList ingredientList = new IngredientList();

    private Storage storage = new Storage();
    private Ui ui = new Ui(System.in);
    private RecipeList recipeList = new RecipeList();

    @BeforeAll
    static void setUp() {
        ingredientList = new IngredientList();
        ingredientList.addIngredient(new Ingredient("Pasta"));
    }

    @Test
    void executeTest_success() throws InvalidArgumentException {
        DeleteIngredientCommand command = new DeleteIngredientCommand("Pasta");
        command.execute(recipeList, ingredientList, ui, storage);
        assertTrue(ingredientList.getIngredients().isEmpty());
    }

    @Test
    void executeTest_invalid() {
        DeleteIngredientCommand command = new DeleteIngredientCommand("Chicken");
        assertThrows(InvalidArgumentException.class, () -> command.execute(recipeList, ingredientList, ui, storage));
    }
}
