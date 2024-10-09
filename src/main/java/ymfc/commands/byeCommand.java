package ymfc.commands;

import ymfc.RecipeList;
import ymfc.Ui;

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
