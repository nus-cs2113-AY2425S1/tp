package ymfc.commands;

import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.IOException;
import java.util.logging.Level;

import static ymfc.YMFC.logger;

public class AddIngredientCommand extends Command {

    public static final String USAGE_EXAMPLE = """
            Use example:
            \tnew n/carrot q/2
            """;

    private Ingredient ingredient;

    public AddIngredientCommand(Ingredient ingredient) {
        super();

        logger.log(Level.FINEST, "Creating AddIngredientCommand");
        this.ingredient = ingredient;
        assert ingredient != null;
    }

    public void execute(RecipeList recipes, IngredientList ingredients, Ui ui, Storage storage) throws IOException {
        logger.log(Level.FINEST, "Executing AddIngredientCommand");

        assert ingredients != null;
        addNewIngredient(ingredients, ingredient, ui, storage);
    }

    public void addNewIngredient(IngredientList ingredients, Ingredient newIngredient,
                                 Ui ui, Storage storage) throws IOException {
        ingredients.addIngredient(newIngredient);

        storage.saveIngredients(ingredients);

        ui.printAddedIngredient(newIngredient.toString(), ingredients.getCounter());
    }

    public void addLoadedIngredient(IngredientList ingredients) {
        ingredients.addIngredient(ingredient);
    }
}
