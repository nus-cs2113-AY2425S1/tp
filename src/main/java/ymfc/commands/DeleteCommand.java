package ymfc.commands;

import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command{

    private String recipeName;

    public DeleteCommand(String recipeName) {
        super();
        this.recipeName = recipeName;
    }

    @Override
    public void execute(RecipeList recipes, Ui ui, Storage storage) {

        boolean isRemoved = recipes.removeRecipeByName(recipeName);
        if (isRemoved) {
            ui.printDeletedTask(recipeName, recipes.getCounter());

            try {
                storage.saveRecipes(recipes);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } else {
            ui.printMessage(new String[]{"Recipe not found: " + recipeName});
        }
    }
}
