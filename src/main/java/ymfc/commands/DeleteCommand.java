package ymfc.commands;

import ymfc.exception.InvalidArgumentException;
import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.IOException;
import java.util.logging.Level;

import static ymfc.YMFC.logger;

public class DeleteCommand extends Command {

    public static final String USAGE_EXAMPLE = """
            Use example:
            \tdelete n/pasta
            """;

    private String recipeName;

    public DeleteCommand(String recipeName) {
        super();

        logger.log(Level.FINEST, "Creating DeleteCommand");
        assert recipeName != null;
        this.recipeName = recipeName;
    }

    public void execute(RecipeList recipes, Ui ui, Storage storage) throws InvalidArgumentException {
        logger.log(Level.FINEST, "Executing DeleteCommand");
        assert recipes != null;

        boolean isRemoved = recipes.removeRecipeByName(recipeName);
        if (isRemoved) {
            ui.printDeletedTask(recipeName, recipes.getCounter());
            try {
                storage.saveRecipes(recipes);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new InvalidArgumentException("Recipe not found: " + recipeName);
        }
    }
}
