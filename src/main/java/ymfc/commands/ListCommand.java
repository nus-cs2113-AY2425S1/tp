package ymfc.commands;

import ymfc.RecipeList;
import ymfc.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(RecipeList recipes, Ui ui) {
        ui.printList(recipes.getRecipes(), recipes.getCounter());
    };
}
