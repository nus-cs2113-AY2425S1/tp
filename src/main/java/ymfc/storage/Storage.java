package ymfc.storage;

import ymfc.commands.AddRecipeCommand;
import ymfc.commands.ListCommand;
import ymfc.exception.InvalidArgumentException;
import ymfc.parser.Parser;
import ymfc.recipelist.RecipeList;
import ymfc.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveRecipes(RecipeList recipes) throws IOException {
        File dir = new File("./data");
        if (!dir.isDirectory()) {
            dir.mkdir();
        }

        FileWriter writer = new FileWriter(filePath);
        for (int i = 0; i < recipes.getCounter(); i++) {
            if (i == recipes.getCounter() - 1) {
                writer.write(recipes.getRecipe(i).toSaveString());
            } else {
                writer.write(recipes.getRecipe(i).toSaveString() + System.lineSeparator());
            }
        }
        writer.close();
    }

    public void loadRecipes(RecipeList recipes, Ui ui, Storage storage) throws FileNotFoundException {
        File tasksFile = new File(filePath);
        Scanner reader = new Scanner(tasksFile);
        boolean isEmpty = true;
        while (reader.hasNext()) {
            String line = reader.nextLine();
            isEmpty = false;
            try {
                addRecipe(recipes, line);
            } catch (InvalidArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        if (isEmpty) {
            return;
        }
        ListCommand c = new ListCommand();
        c.execute(recipes, ui, storage);
    }

    private void addRecipe(RecipeList recipes, String line) throws InvalidArgumentException {
        AddRecipeCommand command = Parser.parseLoadedCommand(line);
        command.addLoadedRecipe(recipes);
    }
}
