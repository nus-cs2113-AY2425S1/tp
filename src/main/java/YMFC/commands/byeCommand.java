package YMFC.commands;

import YMFC.RecipeList;
import YMFC.Ui;

public class byeCommand extends Command {

    public byeCommand() {
        super();
        setBye();
    }

    @Override
    public void execute(RecipeList recipes, Ui ui) {
        ui.bidFarewell();
    }
}
