package ymfc.commands;

import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {
        super();
        setBye();
    }

    @Override
    public void execute(RecipeList recipes, Ui ui, Storage storage) {
        ui.bidFarewell();
    }
}
