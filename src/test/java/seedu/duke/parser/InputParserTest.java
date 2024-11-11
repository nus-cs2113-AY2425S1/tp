package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.FinanceBuddyException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputParserTest {

    @Test
    void parseCommands_listCommandWithFilters_success() throws FinanceBuddyException {
        String input = "list income /from 01/01/2024 /to 31/12/2024";
        HashMap<String, String> result = InputParser.parseCommands(input);

        assertEquals(4, result.size());
        assertEquals("list", result.get(InputParser.COMMAND));
        assertEquals("income", result.get(InputParser.ARGUMENT));
        assertEquals("01/01/2024", result.get("/from"));
        assertEquals("31/12/2024", result.get("/to"));
    }

    @Test
    void parseCommands_expenseCommandWithAllArguments_success() throws FinanceBuddyException {
        String input = "expense Coffee /a 5.50 /d 10/11/2024 /c FOOD";
        HashMap<String, String> result = InputParser.parseCommands(input);

        assertEquals(5, result.size());
        assertEquals("expense", result.get(InputParser.COMMAND));
        assertEquals("Coffee", result.get(InputParser.ARGUMENT));
        assertEquals("5.50", result.get("/a"));
        assertEquals("10/11/2024", result.get("/d"));
        assertEquals("FOOD", result.get("/c"));
    }

    @Test
    void parseCommands_incomeCommandWithPartialArguments_success() throws FinanceBuddyException {
        String input = "income Bonus /a 1000";
        HashMap<String, String> result = InputParser.parseCommands(input);

        assertEquals(3, result.size());
        assertEquals("income", result.get(InputParser.COMMAND));
        assertEquals("Bonus", result.get(InputParser.ARGUMENT));
        assertEquals("1000", result.get("/a"));
    }

    @Test
    void parseCommands_editCommandWithSpecificFields_success() throws FinanceBuddyException {
        String input = "edit 3 /des Rent Payment /a 750 /d 01/12/2024 /c UTILITIES";
        HashMap<String, String> result = InputParser.parseCommands(input);

        assertEquals(6, result.size());
        assertEquals("edit", result.get(InputParser.COMMAND));
        assertEquals("3", result.get(InputParser.ARGUMENT));
        assertEquals("Rent Payment", result.get("/des"));
        assertEquals("750", result.get("/a"));
        assertEquals("01/12/2024", result.get("/d"));
        assertEquals("UTILITIES", result.get("/c"));
    }

    @Test
    void parseCommands_deleteCommand_success() throws FinanceBuddyException {
        String input = "delete 2";
        HashMap<String, String> result = InputParser.parseCommands(input);

        assertEquals(2, result.size());
        assertEquals("delete", result.get(InputParser.COMMAND));
        assertEquals("2", result.get(InputParser.ARGUMENT));
    }

    @Test
    void parseCommands_budgetCommand_success() throws FinanceBuddyException {
        String input = "budget";
        HashMap<String, String> result = InputParser.parseCommands(input);

        assertEquals(1, result.size());
        assertEquals("budget", result.get(InputParser.COMMAND));
    }

    @Test
    void parseCommands_exitCommand_success() throws FinanceBuddyException {
        String input = "exit";
        HashMap<String, String> result = InputParser.parseCommands(input);

        assertEquals(1, result.size());
        assertEquals("exit", result.get(InputParser.COMMAND));
    }

    @Test
    void parseCommands_helpCommand_success() throws FinanceBuddyException {
        String input = "help";
        HashMap<String, String> result = InputParser.parseCommands(input);

        assertEquals(1, result.size());
        assertEquals("help", result.get(InputParser.COMMAND));
    }

    @Test
    void parseCommands_listCommandWithoutFilters_success() throws FinanceBuddyException {
        String input = "list";
        HashMap<String, String> result = InputParser.parseCommands(input);

        assertEquals(1, result.size());
        assertEquals("list", result.get(InputParser.COMMAND));
    }

    @Test
    void parseCommands_invalidCommand_returnsEmptyCommand() throws FinanceBuddyException {
        String input = "invalidCommand";
        HashMap<String, String> result = InputParser.parseCommands(input);

        assertEquals(1, result.size());
        assertEquals("invalidCommand", result.get(InputParser.COMMAND));
    }
}
