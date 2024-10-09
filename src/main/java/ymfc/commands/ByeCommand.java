package ymfc.commands;

import ymfc.RecipeList;
import ymfc.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {
        super();
        setBye();
    }

    @Override
    public void execute(RecipeList recipes, Ui ui) {
        ui.bidFarewell();
    }
}
