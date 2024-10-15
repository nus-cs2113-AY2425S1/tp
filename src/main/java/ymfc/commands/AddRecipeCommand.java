package ymfc.commands;

import ymfc.recipe.Recipe;
import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.IOException;

public class AddRecipeCommand extends Command {
    private Recipe recipe;

    public AddRecipeCommand(Recipe recipe) {
        super();
        this.recipe = recipe;
        assert recipe != null;
    }

    @Override
    public void execute(RecipeList recipes, Ui ui, Storage storage) {
        assert recipes != null;
        addNewRecipe(recipes, recipe, ui, storage);
    }

    public void addNewRecipe(RecipeList recipes, Recipe newRecipe, Ui ui, Storage storage) {
        recipes.addRecipe(newRecipe);

        try {
            storage.saveRecipes(recipes);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        ui.printAddedRecipe(newRecipe.toString(), recipes.getCounter());
    }

    public void addLoadedRecipe(RecipeList recipes) {
        recipes.addRecipe(recipe);
    }
}
