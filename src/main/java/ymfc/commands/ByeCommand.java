package ymfc.commands;

import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.util.logging.Level;

import static ymfc.YMFC.logger;

public class ByeCommand extends Command {

    public ByeCommand() {
        super();

        logger.log(Level.FINEST, "Creating ByeCommand");
        setBye();
    }

    public void execute(RecipeList recipes, Ui ui, Storage storage) {
        logger.log(Level.FINEST, "Executing ByeCommand");

        ui.bidFarewell();
    }
}
