package ymfc.commands;

import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(RecipeList recipes, Ui ui, Storage storage) {
        ui.printList(recipes.getRecipes(), recipes.getCounter());
    }
}
