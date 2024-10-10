package ymfc.commands;

import ymfc.recipe.Recipe;
import ymfc.recipelist.RecipeList;
import ymfc.ui.Ui;

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
