package ymfc.ingredient;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @Test
    public void getName_validName_returnCorrectName() {
        Ingredient ingredient = new Ingredient("Water");
        assertEquals("Water", ingredient.getName());
    }

    @Test
    public void equals_isSameName_returnTrue() {
        Ingredient ingredient1 = new Ingredient("Water");
        Ingredient ingredient2 = new Ingredient("Water");
        assertTrue(ingredient1.equals(ingredient2));
    }

    @Test
    public void equals_isNotSameName_returnFalse() {
        Ingredient ingredient1 = new Ingredient("Water");
        Ingredient ingredient2 = new Ingredient("Oil");
        assertFalse(ingredient1.equals(ingredient2));
    }

    @Test
    void toString_validInput_correctFormat() {
        Ingredient ingredient = new Ingredient("Water");

        // Expected string representation
        String expected = "Water";

        // Assert the output of toString method matches expected
        assertEquals(expected, ingredient.toString());
    }

    @Test
    void toSaveString_validInput_correctFormat() {
        Ingredient ingredient = new Ingredient("Water");

        // Expected string representation
        String expected = "new n/Water";

        // Assert the output of toString method matches expected
        assertEquals(expected, ingredient.toSaveString());
    }

}
