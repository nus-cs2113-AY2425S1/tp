package ymfc.commands;

import org.junit.jupiter.api.BeforeEach;
import ymfc.list.IngredientList;
import ymfc.list.RecipeList;
import ymfc.storage.Storage;
import ymfc.ui.Ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByeCommandTest {
    private Storage storage;
    private RecipeList recipeList;
    private IngredientList ingredientList;
    private Ui ui;
    private ByeCommand byeCommand;

    @BeforeEach
    void setUp() {
        recipeList = new RecipeList();
        ui = new Ui(System.in);
        storage = new Storage();

        byeCommand = new ByeCommand();
    }

    @Test
    void testByeCommand() {
        try {
            byeCommand.execute(recipeList, ingredientList, ui, storage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertTrue(byeCommand.isBye());
    }
}
