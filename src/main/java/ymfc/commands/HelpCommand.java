package ymfc.commands;

import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.util.logging.Level;

import static ymfc.YMFC.logger;

public class HelpCommand extends Command {
    public HelpCommand() {
        super();

        logger.log(Level.FINEST, "Creating HelpCommand");

    }

    public void execute(RecipeList recipes, Ui ui, Storage storage) {
        logger.log(Level.FINEST, "Executing HelpCommand");

        ui.printHelp();
    }
}
