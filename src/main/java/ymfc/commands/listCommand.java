package ymfc.commands;

import ymfc.RecipeList;
import ymfc.Ui;

public class listCommand extends Command {

    public listCommand() {
        super();
    }

    @Override
    public void execute(RecipeList recipes, Ui ui) {
        ui.printList(recipes.getRecipes(), recipes.getCounter());
    };
}
