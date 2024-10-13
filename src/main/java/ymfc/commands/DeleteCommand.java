package ymfc.commands;

import ymfc.recipelist.RecipeList;
import ymfc.ui.Ui;

public class DeleteCommand extends Command{

    private String recipeName;

    public DeleteCommand(String recipeName) {
        super();
        this.recipeName = recipeName;
    }

    @Override
    public void execute(RecipeList recipes, Ui ui) {

        boolean isRemoved = recipes.removeRecipeByName(recipeName);
        if (isRemoved) {
            ui.printDeletedTask(recipeName, recipes.getCounter());
        } else {
            ui.printMessage(new String[]{"Recipe not found: " + recipeName});
        }
    }
}
