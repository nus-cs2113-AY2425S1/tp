package ymfc.commands;

import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SortIngredientCommandTest {
    private Storage storage;
    private RecipeList recipeList;
    private IngredientList ingredientList;
    private IngredientList nameSortedIngredientList;
    private Ui ui;
    private SortIngredientCommand sortByNameCommand;

    @BeforeEach
    void setUp() {
        ingredientList = new IngredientList();
        recipeList = new RecipeList();
        nameSortedIngredientList = new IngredientList();
        ui = new Ui(System.in);
        storage = new Storage();

        // Add some sample ingredients
        Ingredient pastaIngredientOne = new Ingredient("salt");
        Ingredient pastaIngredientTwo = new Ingredient("Pasta");
        Ingredient pastaIngredientThree = new Ingredient("Water");
        Ingredient pastaIngredientFour = new Ingredient("tomatoes");
        Ingredient pastaIngredientFive = new Ingredient("Sauce");

        ingredientList.addIngredient(pastaIngredientOne);
        ingredientList.addIngredient(pastaIngredientTwo);
        ingredientList.addIngredient(pastaIngredientThree);
        ingredientList.addIngredient(pastaIngredientFour);
        ingredientList.addIngredient(pastaIngredientFive);

        nameSortedIngredientList.addIngredient(pastaIngredientTwo);
        nameSortedIngredientList.addIngredient(pastaIngredientOne);
        nameSortedIngredientList.addIngredient(pastaIngredientFive);
        nameSortedIngredientList.addIngredient(pastaIngredientFour);
        nameSortedIngredientList.addIngredient(pastaIngredientThree);

        sortByNameCommand = new SortIngredientCommand();
    }

    @Test
    void testSortRecipe() {
        // Assertions to verify that list was created as expected
        assertEquals(5, ingredientList.getCounter());

        assertNotEquals(nameSortedIngredientList.getIngredients(), ingredientList.getIngredients());
        // Execute the SortCommand to sort by name
        sortByNameCommand.execute(recipeList, ingredientList, ui, storage);
        // Assertion to verify that ingredients were sorted by name
        assertEquals(nameSortedIngredientList.getIngredients(), ingredientList.getIngredients());
    }
}
