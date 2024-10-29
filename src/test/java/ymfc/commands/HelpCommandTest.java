package ymfc.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {
    private Storage storage;
    private RecipeList recipeList;
    private IngredientList ingredientList;
    private Ui ui;
    private HelpCommand helpCommand;

    private final PrintStream out = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        recipeList = new RecipeList();
        ui = new Ui(System.in);
        storage = new Storage();
        ingredientList = new IngredientList();

        helpCommand = new HelpCommand();

        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testHelpCommand() {
        helpCommand.execute(recipeList, ingredientList, ui, storage);

        String correctOutput = ui.getLine() + System.lineSeparator() + ui.getListOfCommands()
                + System.lineSeparator() + ui.getLine();

        assertEquals(correctOutput, outContent.toString().stripTrailing());
    }

    @AfterEach
    void tearDown() {
        System.setOut(out);
    }

}
