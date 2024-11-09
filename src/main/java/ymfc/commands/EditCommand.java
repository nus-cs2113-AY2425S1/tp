package ymfc.commands;

import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.recipe.Recipe;
import ymfc.exception.InvalidArgumentException;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import static ymfc.YMFC.logger;

/**
 * Represents the command to edit a recipe from the {@code RecipeList} by its name.
 * When executed, the {@code EditCommand} edits the recipe with the specified name
 * from the list, updates the storage, and informs the user of what was edited via the {@code Ui}.
 */
public class EditCommand extends Command {

    public static final String USAGE_EXAMPLE = """
            Use example:
            \tedit e/instant noodles i/magi mee i/water s1/boil water s2/eat magi mee s3/drink water
            """;

    private String matchName;
    private String newName;
    private ArrayList<Ingredient> newIngredients;
    private ArrayList<String> newSteps;
    private String newCuisine;
    private Integer newTimeTaken;

    public EditCommand(String matchName, String newName, ArrayList<Ingredient> newIngredients,
                       ArrayList<String> newSteps,String newCuisine, Integer newTimeTaken) {
        super();
        logger.log(Level.FINEST, "Creating EditCommand");
        assert matchName != null;
        this.matchName = matchName;
        this.newName = newName;
        this.newIngredients = newIngredients;
        this.newSteps = newSteps;
        this.newCuisine = newCuisine;
        this.newTimeTaken = newTimeTaken;
    }

    public Recipe craftEditedRecipe(Recipe toEditRecipe) {
        // Use new recipe parameters if they are specified (not null), else use original parameters
        String editedName;
        ArrayList<Ingredient> editedIngredients;
        ArrayList<String> editedSteps;
        String editedCuisine;
        Integer editedTimeTaken;
        if (newName != null) {
            editedName = newName;
        } else {
            editedName = toEditRecipe.getName();
        }
        if (newIngredients != null) {
            editedIngredients = newIngredients;
        } else {
            editedIngredients = toEditRecipe.getIngredients();
        }
        if (newSteps != null) {
            editedSteps = newSteps;
        } else {
            editedSteps = toEditRecipe.getSteps();
        }
        if (newCuisine != null) {
            if (newCuisine.isEmpty()) {
                editedCuisine = null;
            } else {
                editedCuisine = newCuisine;
            }
        } else {
            editedCuisine = toEditRecipe.getCuisine();
        }
        if (newTimeTaken != null) {
            if (newTimeTaken <= 0) {
                editedTimeTaken = null;
            } else {
                editedTimeTaken = newTimeTaken;
            }
        } else {
            // If original time taken is null, 0 will be returned by getTimeTaken, so return null back
            editedTimeTaken = toEditRecipe.getTimeTaken();
            if (editedTimeTaken == 0) {
                editedTimeTaken = null;
            }
        }

        return new Recipe(editedName,editedIngredients, editedSteps, editedCuisine, editedTimeTaken);
    }

    /**
     * Executes the {@code EditCommand}, finding a recipe matching the name inputted by the user
     * and then editing it based on the new parameters inputted. If the recipe of the specified name
     * is found and then editted, the {@code Storage} is updated and a confirmation message is displayed.
     *
     * @param recipes The {@code RecipeList} to edit the recipe from. Must not be {@code null}.
     * @param ingredients The {@code IngredientList}. Unused in this command.
     * @param ui The {@code Ui} to inform the user of the edited recipe or error.
     * @param storage The {@code Storage} to save the updated recipe list.
     * @throws InvalidArgumentException If the recipe with the specified name is not found in the list.
     */
    public void execute(RecipeList recipes, IngredientList ingredients,
                        Ui ui, Storage storage) throws InvalidArgumentException {
        logger.log(Level.FINEST, "Executing EditCommand");
        assert matchName != null;

        // Find recipe in recipes that match the name of recipe to edit
        int toEditRecipeIndex = recipes.getIndexByName(matchName);
        if (toEditRecipeIndex < 0) {
            throw new InvalidArgumentException("You want me to edit a non-existent recipe? How about no.");
        }
        Recipe toEditRecipe = recipes.getRecipe(toEditRecipeIndex);
        String originalName = toEditRecipe.getName();

        // Craft edited recipe based on pre-existing recipe and parameters to edit
        Recipe editedRecipe = craftEditedRecipe(toEditRecipe);

        // Update the newly edited recipe in recipes
        recipes.updateRecipe(toEditRecipeIndex, editedRecipe);

        // Save edited recipes back to storage
        try {
            storage.saveRecipes(recipes);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        ui.printEditedRecipe(originalName, editedRecipe);
    }
}
