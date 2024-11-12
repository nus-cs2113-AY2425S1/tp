//@@author 3CCLY
package ymfc.commands;

import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.recipe.Recipe;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomCommandTest {

    private Storage storage;
    private RecipeList emptyList;
    private IngredientList ingredientList;
    private Ui ui;
    private Recipe recipe;
    private AddCommand addCommand;
    private RandomCommand randomCommand;

    @BeforeEach
    void setUp() {
        emptyList = new RecipeList();
        ingredientList = new IngredientList();
        ui = new Ui(System.in);
        storage = new Storage();

        // Sample ingredients and steps
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("magi mee"));
        ingredients.add(new Ingredient("water"));

        ArrayList<String> steps = new ArrayList<>();
        steps.add("boil water");
        steps.add("add magi mee");
        steps.add("wait 5 min");

        recipe = new Recipe("instant noodles", ingredients, steps);
        addCommand = new AddCommand(recipe);
        randomCommand = new RandomCommand();
    }

    @Test
    void testRandomRecipe() throws IOException {
        addCommand.execute(emptyList, ingredientList, ui, storage);

        // Capture System.out printing
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        PrintStream testingStream = new PrintStream(message);
        PrintStream systemStream = System.out;
        System.setOut(testingStream);

        randomCommand.execute(emptyList, ingredientList, ui, storage);

        // Return stream back to normal
        System.out.flush();
        System.setOut(systemStream);

        String expected = ui.getLine() + System.lineSeparator()
                + "\tYou want me to call you a random recipe?" + System.lineSeparator()
                + "\t..." + System.lineSeparator()
                + "\tYou need to call it. I can't call it for you." + System.lineSeparator()
                + "\tIt wouldn't be fair. It wouldn't be right." + System.lineSeparator()
                + "\tIt's either one or another, and you have to say. Call it." + System.lineSeparator()
                + "\t..." + System.lineSeparator()
                + "\tOkay who am I kidding, this is still a country for old men." + System.lineSeparator()
                + "\tI will call it for you, here's your random recipe:" + System.lineSeparator()
                + "\t" + recipe + System.lineSeparator()
                + ui.getLine() + System.lineSeparator();
        assertEquals(expected, message.toString());
    }
}
