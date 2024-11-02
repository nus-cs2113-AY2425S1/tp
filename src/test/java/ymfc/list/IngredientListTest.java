package ymfc.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ymfc.ingredient.Ingredient;
import ymfc.recipe.Recipe;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientListTest {
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Ingredient ingredient3;

    private IngredientList testIngredientList;

    @BeforeEach
    void setUp() {
        ingredient1 = new Ingredient("White Rice");
        ingredient2 = new Ingredient("Plain Bread");
        ingredient3 = new Ingredient("Mash Potatoes");
    }

    @Test
    public void getIngredients_emptyIngredientList_returnEmptyArrayList() {
        assertEquals(new ArrayList<Ingredient>(), new IngredientList().getIngredients());
    }

    @Test
    public void addRecipe_addingMultipleRecipes_success() {
        testIngredientList = new IngredientList();
        assertEquals(0, testIngredientList.getCounter());

        testIngredientList.addIngredient(ingredient1);
        assertEquals(1, testIngredientList.getCounter());

        testIngredientList.addIngredient(ingredient2);
        assertEquals(2, testIngredientList.getCounter());

        testIngredientList.addIngredient(ingredient3);
        assertEquals(3, testIngredientList.getCounter());
    }

    @Test
    public void removeRecipe_deletingMultipleRecipes_success() {
        testIngredientList = new IngredientList();
        testIngredientList.addIngredient(ingredient1);
        testIngredientList.addIngredient(ingredient2);
        testIngredientList.addIngredient(ingredient2);

        testIngredientList.removeIngredient(0);
        assertEquals(2, testIngredientList.getCounter());
        assertSame(ingredient1, testIngredientList.getIngredient(1));

        testIngredientList.removeIngredient(1);
        assertEquals(1, testIngredientList.getCounter());
        assertSame(ingredient2, testIngredientList.getIngredient(0));
    }

    @Test
    public void removeRecipe_inputOutOfBounds_exceptionThrown() {
        testIngredientList = new IngredientList();
        testIngredientList.addIngredient(ingredient1);
        testIngredientList.addIngredient(ingredient2);
        testIngredientList.addIngredient(ingredient3);

        assertThrows(IndexOutOfBoundsException.class, () -> testIngredientList.removeIngredient(-1));
    }

    @Test
    public void removeRecipeByName_deletingMultipleRecipes_success() {
        testIngredientList = new IngredientList();
        testIngredientList.addIngredient(ingredient1);
        testIngredientList.addIngredient(ingredient2);
        testIngredientList.addIngredient(ingredient3);

        testIngredientList.removeIngredientByName(ingredient2.getName());
        assertEquals(2, testIngredientList.getCounter());
        assertSame(ingredient3, testIngredientList.getIngredient(1));

        testIngredientList.removeIngredientByName(ingredient1.getName());
        assertEquals(1, testIngredientList.getCounter());
        assertSame(ingredient3, testIngredientList.getIngredient(0));
    }
}
