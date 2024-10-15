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
        assert recipes.getCounter() >= 0;
        ui.printList(recipes.getRecipes(), recipes.getCounter());
    }
}
