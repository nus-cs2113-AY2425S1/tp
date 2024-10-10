package YMFC.commands;

import YMFC.Recipe;
import YMFC.RecipeList;
import YMFC.Ui;

public class AddRecipeCommand extends Command {
    private Recipe recipe;

    public AddRecipeCommand(Recipe recipe) {
        super();
        this.recipe = recipe;
    }

    @Override
    public void execute(RecipeList recipes, Ui ui) {
        addNewRecipe(recipes, recipe, ui);
    }

    public void addNewRecipe(RecipeList recipes, Recipe newRecipe, Ui ui) {
        recipes.addRecipe(newRecipe);
        ui.printAddedRecipe(newRecipe.toString(), recipes.getCounter());
    }
}
