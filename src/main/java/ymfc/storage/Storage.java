package ymfc.storage;

import ymfc.commands.AddRecipeCommand;
import ymfc.commands.ListCommand;
import ymfc.commands.ListIngredientsCommand;
import ymfc.exception.InvalidArgumentException;
import ymfc.parser.Parser;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String saveRecipeFilePath = "./data/recipes.txt";
    private static final String saveIngredientFilePath = "./data/recipes.txt";

    public Storage() {
    }

    public void saveRecipes(RecipeList recipes) throws IOException {
        File dir = new File("./data");
        if (!dir.isDirectory()) {
            dir.mkdir();
        }

        FileWriter writer = new FileWriter(saveRecipeFilePath);
        for (int i = 0; i < recipes.getCounter(); i++) {
            if (i == recipes.getCounter() - 1) {
                writer.write(recipes.getRecipe(i).toSaveString());
            } else {
                writer.write(recipes.getRecipe(i).toSaveString() + System.lineSeparator());
            }
        }
        writer.close();
    }

    public void saveIngredients(IngredientList ingredients) throws IOException {
        File dir = new File("./data");
        if (!dir.isDirectory()) {
            dir.mkdir();
        }

        FileWriter writer = new FileWriter(saveIngredientFilePath);
        for (int i = 0; i < ingredients.getCounter(); i++) {
            if (i == ingredients.getCounter() - 1) {
                writer.write(ingredients.getIngredient(i).toSaveString());
            } else {
                writer.write(ingredients.getIngredient(i).toSaveString() + System.lineSeparator());
            }
        }
        writer.close();
    }

    public void loadRecipes(RecipeList recipes, IngredientList ingredients, Ui ui, Storage storage) throws FileNotFoundException {
        File tasksFile = new File(saveRecipeFilePath);
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
        ListIngredientsCommand c = new ListIngredientsCommand();
        c.execute(recipes, ingredients, ui, storage);
    }

    public void loadIngredients(RecipeList recipes, IngredientList ingredients, Ui ui, Storage storage) throws FileNotFoundException {
        File tasksFile = new File(saveIngredientFilePath);
        Scanner reader = new Scanner(tasksFile);
        boolean isEmpty = true;
        while (reader.hasNext()) {
            String line = reader.nextLine();
            isEmpty = false;
            try {
                addIngredient(ingredients, line);
            } catch (InvalidArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        if (isEmpty) {
            return;
        }
        ListCommand c = new ListCommand();
        c.execute(recipes, ingredients, ui, storage);
    }

    private void addRecipe(RecipeList recipes, String line) throws InvalidArgumentException {
        AddRecipeCommand command = Parser.parseLoadedCommand(line);
        command.addLoadedRecipe(recipes);
    }

    private void addIngredient(IngredientList ingredients, String line) throws InvalidArgumentException {

    }
}
