package YMFC.commands;

import YMFC.RecipeList;
import YMFC.Ui;

public class listCommand extends Command {

    public listCommand() {
        super();
    }

    @Override
    public void execute(RecipeList recipes, Ui ui) {
        ui.printList(recipes.getRecipes(), recipes.getCounter());
    };
}
