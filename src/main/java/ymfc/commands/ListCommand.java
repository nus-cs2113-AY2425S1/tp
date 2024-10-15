package ymfc.commands;

import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.util.logging.Level;

import static ymfc.YMFC.logger;

public class ListCommand extends Command {

    public ListCommand() {
        super();

        logger.log(Level.FINEST, "Creating ListCommand");
    }

    public void execute(RecipeList recipes, Ui ui, Storage storage) {
        logger.log(Level.FINEST, "Executing HelpCommand");

        assert recipes.getCounter() >= 0;
        ui.printList(recipes.getRecipes(), recipes.getCounter());
    }
}
