package ymfc.commands;

import ymfc.recipelist.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.IOException;
import java.util.logging.Level;

import static ymfc.YMFC.logger;

public class SortCommand extends Command{
    public static final String USAGE_EXAMPLE = """
            Use example:
            \tsort s/name
            or
            \tsort s/time
            """;

    private String listBy;

    public SortCommand(String listBy) {
        super();
        this.listBy = listBy;
    }


    public void execute(RecipeList recipes, Ui ui, Storage storage) {
        logger.log(Level.FINEST, "Executing SortCommand");

        if (listBy.equals("name")) {
            recipes.sortAlphabetically();
        } else if (listBy.equals("time")) {
            recipes.sortByTimeTaken();
        }
        try {
            storage.saveRecipes(recipes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ui.printList(recipes.getRecipes(), recipes.getCounter());
    }
}
