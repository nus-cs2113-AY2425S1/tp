package ymfc.commands;

import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.util.logging.Level;
import static ymfc.YMFC.logger;

public class ListIngredientsCommand extends Command {

    public ListIngredientsCommand() {
        super();

        logger.log(Level.FINEST, "Creating ListIngredientsCommand");
    }


    public void execute(RecipeList recipes, IngredientList ingredients, Ui ui, Storage storage) {
        logger.log(Level.FINEST, "Executing ListIngredientsCommand");

        assert ingredients.getCounter() >= 0;
        ui.printIngredientList(ingredients.getIngredients(), ingredients.getCounter());
    }
}
