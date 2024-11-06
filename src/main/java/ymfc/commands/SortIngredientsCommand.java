package ymfc.commands;

import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.IOException;
import java.util.logging.Level;

import static ymfc.YMFC.logger;

public class SortIngredientsCommand extends Command{
    public static final String USAGE_EXAMPLE = """
            Use example:
            \tsortI
            """;

    public SortIngredientsCommand() {
        super();
    }


    public void execute(RecipeList recipes, IngredientList ingredients, Ui ui, Storage storage) {
        logger.log(Level.FINEST, "Executing SortIngredientsCommand");

        ingredients.sortAlphabetically();

        try {
            storage.saveIngredients(ingredients);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        ui.printIngredientList(ingredients.getIngredients(), ingredients.getCounter());
    }
}
