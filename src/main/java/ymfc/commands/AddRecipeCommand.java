package ymfc.commands;

import ymfc.recipe.Recipe;
import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.IOException;
import java.util.logging.Level;

import static ymfc.YMFC.logger;

public class AddRecipeCommand extends Command {

    public static final String USAGE_EXAMPLE = """
            Use example:
            \tadd n/instant noodles i/magi mee i/water s1/boil water s2/add magi mee s3/wait 5 min
            """;

    private Recipe recipe;

    public AddRecipeCommand(Recipe recipe) {
        super();

        logger.log(Level.FINEST, "Creating AddRecipeCommand");
        this.recipe = recipe;
        assert recipe != null;
    }
    @Override
    public void execute(RecipeList recipes, Ui ui, Storage storage) throws IOException {
        logger.log(Level.FINEST, "Executing AddRecipeCommand");

        assert recipes != null;
        addNewRecipe(recipes, recipe, ui, storage);
    }

    public void addNewRecipe(RecipeList recipes, Recipe newRecipe, Ui ui, Storage storage) throws IOException {
        recipes.addRecipe(newRecipe);

        storage.saveRecipes(recipes);

        ui.printAddedRecipe(newRecipe.toString(), recipes.getCounter());
    }

    public void addLoadedRecipe(RecipeList recipes) {
        recipes.addRecipe(recipe);
    }
}
