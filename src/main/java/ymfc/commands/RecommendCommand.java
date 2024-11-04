package ymfc.commands;

import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.recipe.Recipe;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.util.ArrayList;
import java.util.logging.Level;
import static ymfc.YMFC.logger;

/**
 * Represents the command to recommend the user a recipe from {@code RecipeList}.
 * Recipes with ingredients that matches what the user has in {@code IngredientList}
 * are recommended to the user via {@code Ui}.
 */
public class RecommendCommand extends Command {

    public static final String USAGE_EXAMPLE = """
            Use example:
            \trecommend
            """;

    public RecommendCommand() {
        super();

        logger.log(Level.FINEST, "Creating RecommendCommand");
    }

    /**
     * Executes the {@code RecommendCommand}.
     */
    public void execute(RecipeList recipes, IngredientList ingredients, Ui ui, Storage storage) {
        logger.log(Level.FINEST, "Executing RecommendCommand");
        assert recipes.getCounter() > 0;

        RecipeList recommendList = new RecipeList();
        ArrayList<String> ingredientsList = ingredients.getIngredientsString();
        // Iterate through all recipes and find recipes with matching ingredients
        for (int i = 0; i < recipes.getCounter(); i++) {
            Recipe targetRecipe = recipes.getRecipe(i);
            // Find ingredients of recipe that matches ingredient list
            // A clone is used so that the retainAll method doesn't overwrite the original recipe
            ArrayList<String> matchIngredients = (ArrayList<String>) targetRecipe.getIngredients().clone();
            matchIngredients.retainAll(ingredientsList);
            // Add recipe to list of recommended recipes if matching ingredients found
            if (!matchIngredients.isEmpty()) {
                recommendList.addRecipe(targetRecipe);
            }
        }

        ui.printRecommendedRecipes(recommendList);
    }
}