package ymfc.commands;

import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;
import ymfc.recipe.Recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.ArrayList;


public class SortCommandTest {
    private Storage storage;
    private RecipeList recipeList;
    private IngredientList ingredientList;
    private RecipeList nameSortedRecipeList;
    private RecipeList timeSortedRecipeList;
    private Ui ui;
    private SortCommand sortByNameCommand;
    private SortCommand sortByTimeCommand;

    @BeforeEach
    void setUp() {
        recipeList = new RecipeList();
        nameSortedRecipeList = new RecipeList();
        timeSortedRecipeList = new RecipeList();
        ui = new Ui(System.in);
        storage = new Storage();

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
        applePieIngredients.add("Pie");
        ArrayList<String> applePieSteps = new ArrayList<>();
        applePieSteps.add("Chop veggies.");
        applePieSteps.add("Add pasta.");
        applePieSteps.add("Toss with dressing.");

        Recipe applePieRecipe = new Recipe("apple pie", applePieIngredients, applePieSteps, 3);

        recipeList.addRecipe(pastaRecipe);
        recipeList.addRecipe(saladRecipe);
        recipeList.addRecipe(zebraFishRecipe);
        recipeList.addRecipe(applePieRecipe);

        nameSortedRecipeList.addRecipe(applePieRecipe);
        nameSortedRecipeList.addRecipe(pastaRecipe);
        nameSortedRecipeList.addRecipe(saladRecipe);
        nameSortedRecipeList.addRecipe(zebraFishRecipe);

        timeSortedRecipeList.addRecipe(saladRecipe);
        timeSortedRecipeList.addRecipe(pastaRecipe);
        timeSortedRecipeList.addRecipe(applePieRecipe);
        timeSortedRecipeList.addRecipe(zebraFishRecipe);

        sortByNameCommand = new SortCommand("name");
        sortByTimeCommand = new SortCommand("time");
    }

    @Test
    void testSortRecipe() {
        // Assertions to verify that list was created as expected
        assertEquals(4, recipeList.getCounter());

        assertNotEquals(nameSortedRecipeList.getRecipes(), recipeList.getRecipes());
        // Execute the SortCommand to sort by name
        sortByNameCommand.execute(recipeList, ingredientList, ui, storage);
        // Assertion to verify that recipes were sorted by name
        assertEquals(nameSortedRecipeList.getRecipes(), recipeList.getRecipes());

        assertNotEquals(timeSortedRecipeList.getRecipes(), recipeList.getRecipes());
        // Execute the SortCommand to sort by time
        sortByTimeCommand.execute(recipeList, ingredientList, ui, storage);
        // Assertion to verify that recipes were sorted by time
        assertEquals(timeSortedRecipeList.getRecipes(), recipeList.getRecipes());
    }
}
