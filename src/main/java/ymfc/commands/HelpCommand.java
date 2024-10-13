package ymfc.commands;

import ymfc.recipelist.RecipeList;
import ymfc.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {
        super();
    }

    @Override
    public void execute(RecipeList recipes, Ui ui) {
        ui.printHelp();
    }
}
