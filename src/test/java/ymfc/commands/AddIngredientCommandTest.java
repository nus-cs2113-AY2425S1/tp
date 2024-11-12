package ymfc.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ymfc.ingredient.Ingredient;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author Sukkaito
class AddIngredientCommandTest {

    private Storage storage;
    private RecipeList emptyList;
    private Ui ui;
    private IngredientList ingredientList;
    private Ingredient ingredient;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        emptyList = new RecipeList();
        ui = new Ui(System.in);
        ingredientList = new IngredientList();
        ingredient = new Ingredient("Chicken");
    }

    @Test
    void addIngredientCommand_success() throws IOException {
        AddIngredientCommand command = new AddIngredientCommand(ingredient);
        command.execute(emptyList, ingredientList, ui, storage);
        assertEquals(1, ingredientList.getIngredients().size());
        assertEquals(ingredient, ingredientList.getIngredients().get(0));
    }

    @Test
    void testAddDuplicateIngredients() throws IOException {
        AddIngredientCommand command = new AddIngredientCommand(ingredient);
        command.execute(emptyList, ingredientList, ui, storage);
        assertEquals(1, ingredientList.getIngredients().size());

        // Capture System.out printing
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        PrintStream testingStream = new PrintStream(message);
        PrintStream systemStream = System.out;
        System.setOut(testingStream);

        command.execute(emptyList, ingredientList, ui, storage); // Ensure duplicate ingredient will not be added
        assertEquals(1, ingredientList.getIngredients().size());

        System.out.flush();
        System.setOut(systemStream);

        String expected = ui.getLine() + System.lineSeparator()
                + "\tThere already exists an ingredient called: Chicken!"
                + System.lineSeparator()
                + ui.getLine() + System.lineSeparator();

        assertEquals(expected, message.toString());
    }
}
