package ymfc.commands;

import ymfc.Recipe;
import ymfc.RecipeList;
import ymfc.Ui;

public class addRecipeCommand extends Command {
    private Recipe recipe;

    public addRecipeCommand(Recipe recipe) {
        super();
        this.recipe = recipe;
    }
    @Override
    public void execute(RecipeList recipes, Ui ui) {
        addNewRecipe(recipes, recipe, ui);
    }

    @Override
    public void addNewRecipe(RecipeList recipes, Recipe newRecipe, Ui ui) {

    }
}
