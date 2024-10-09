package YMFC.commands;

import YMFC.RecipeList;
import YMFC.Ui;

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
