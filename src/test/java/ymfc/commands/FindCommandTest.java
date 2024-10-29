package ymfc.commands;

import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;
import ymfc.recipe.Recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class FindCommandTest {
    private Storage storage;
    private RecipeList recipeList;
    private IngredientList ingredientList;
    private Ui ui;
    private FindCommand findCommand;

    @BeforeEach
    void setUp() {
        recipeList = new RecipeList();
        ui = new Ui(System.in);
        storage = new Storage();
        ingredientList = new IngredientList();

        // Add some sample recipes
        ArrayList<String> pastaIngredients = new ArrayList<>();
        pastaIngredients.add("Pasta");
        pastaIngredients.add("Water");
        pastaIngredients.add("Salt");
        ArrayList<String> pastaSteps = new ArrayList<>();
        pastaSteps.add("Boil water.");
        pastaSteps.add("Add pasta.");
        pastaSteps.add("Cook for 10 minutes.");

        Recipe pastaRecipe = new Recipe("Pasta", pastaIngredients, pastaSteps, 2);

        ArrayList<String> saladIngredients = new ArrayList<>();
        saladIngredients.add("Lettuce");
        saladIngredients.add("Tomatoes");
        saladIngredients.add("Cucumber");
        ArrayList<String> saladSteps = new ArrayList<>();
        saladSteps.add("Chop veggies.");
        saladSteps.add("Add pasta.");
        saladSteps.add("Toss with dressing.");

        Recipe saladRecipe = new Recipe("salad", saladIngredients, saladSteps, 1);

        ArrayList<String> zebraFishIngredients = new ArrayList<>();
        zebraFishIngredients.add("Zebra Fish");
        zebraFishIngredients.add("Oil");
        ArrayList<String> zebraFishSteps = new ArrayList<>();
        zebraFishSteps.add("Scale fish");
        zebraFishSteps.add("Fry Fish");

        Recipe zebraFishRecipe = new Recipe("Zebra Fish", zebraFishIngredients, zebraFishSteps, 4);

        ArrayList<String> applePieIngredients = new ArrayList<>();
        applePieIngredients.add("Apple");
        applePieIngredients.add("Oil");
        ArrayList<String> applePieSteps = new ArrayList<>();
        applePieSteps.add("Chop apples.");
        applePieSteps.add("Roast apples in oil.");
        applePieSteps.add("Bake in pie.");

        Recipe applePieRecipe = new Recipe("apple pie", applePieIngredients, applePieSteps, 3);

        recipeList.addRecipe(pastaRecipe);
        recipeList.addRecipe(saladRecipe);
        recipeList.addRecipe(zebraFishRecipe);
        recipeList.addRecipe(applePieRecipe);
    }

    @Test
    void isFoundInSteps_searchNotInList_returnFalse() {
        // Assertions to verify that list was created as expected
        assertEquals(4, recipeList.getCounter());

        findCommand = new FindCommand("Roast the nuts", false, false, true);
        try {
            findCommand.execute(recipeList, ingredientList, ui, storage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(0, findCommand.getNumMatches());
    }

    @Test
    void isFoundInSteps_searchInList_returnTrue() {
        // Assertions to verify that list was created as expected
        assertEquals(4, recipeList.getCounter());

        findCommand = new FindCommand("Fry Fish", false, false, true);
        try {
            findCommand.execute(recipeList, ingredientList, ui, storage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(1, findCommand.getNumMatches());
    }

    @Test
    void isFoundInIngredients_searchNotInList_returnFalse() {
        // Assertions to verify that list was created as expected
        assertEquals(4, recipeList.getCounter());

        findCommand = new FindCommand("Caviar", false, true, false);
        try {
            findCommand.execute(recipeList, ingredientList, ui, storage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(0, findCommand.getNumMatches());
    }

    @Test
    void isFoundInIngredients_searchInList_returnTrue() {
        // Assertions to verify that list was created as expected
        assertEquals(4, recipeList.getCounter());

        findCommand = new FindCommand("Oil", false, true, false);
        try {
            findCommand.execute(recipeList, ingredientList, ui, storage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(2, findCommand.getNumMatches());
    }

    @Test
    void isQueryFoundInName_searchNotInList_returnFalse() {
        // Assertions to verify that list was created as expected
        assertEquals(4, recipeList.getCounter());

        findCommand = new FindCommand("Roast Pork", true, false, false);
        try {
            findCommand.execute(recipeList, ingredientList, ui, storage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(0, findCommand.getNumMatches());
    }

    @Test
    void isQueryFoundInName_searchInList_returnTrue() {
        // Assertions to verify that list was created as expected
        assertEquals(4, recipeList.getCounter());

        findCommand = new FindCommand("Pasta", true, false, false);
        try {
            findCommand.execute(recipeList, ingredientList, ui, storage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(1, findCommand.getNumMatches());
    }

}
