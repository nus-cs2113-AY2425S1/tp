package ymfc.commands;

import org.junit.jupiter.api.Test;
import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

class ListIngredientCommandTest {


    private Storage storage = new Storage();
    private RecipeList emptyList = new RecipeList();
    private Ui ui = new Ui(System.in);
    private IngredientList ingredientList = new IngredientList();
    private Ingredient ingredient = new Ingredient("Chicken");


    @Test
    void listIngredients_success() {
        ListIngredientCommand command = new ListIngredientCommand();
        ingredientList.addIngredient(ingredient);
        command.execute(emptyList, ingredientList, ui, storage);

    }
}
