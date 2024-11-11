package seedu.EasInternship;

import org.junit.jupiter.api.Test;
import seedu.commands.AddCommand;
import seedu.commands.Command;
import seedu.commands.DeleteCommand;
import seedu.commands.FilterCommand;
import seedu.commands.HelpCommand;
import seedu.commands.ListCommand;
import seedu.commands.SortCommand;
import seedu.commands.UpdateCommand;

import seedu.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {

    private final Parser parser = new Parser();
    private final Ui ui = new Ui();

    // ==================== AddCommand Tests ====================

    @Test
    void parseCommand_validAddCommand_returnsAddCommand() {
        String input = "add -role Software Engineer -company Google -from 01/24 -to 08/24";
        Command command = parser.parseCommand(input);
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    void parseData_validAddCommand_returnsCorrectArguments() {
        String input = "add -role Software Engineer -company Google -from 01/24 -to 08/24";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        ArrayList<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("role Software Engineer");
        expectedArgs.add("company Google");
        expectedArgs.add("from 01/24");
        expectedArgs.add("to 08/24");
        assertEquals(expectedArgs, args);
    }

    @Test
    void parseData_addCommandMissingRole_returnsArgumentsWithoutRole() {
        String input = "add -company Google -from 01/24 -to 08/24";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        ArrayList<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("company Google");
        expectedArgs.add("from 01/24");
        expectedArgs.add("to 08/24");
        assertEquals(expectedArgs, args);
    }

    @Test
    void parseData_addCommandMissingCompany_returnsArgumentsWithoutCompany() {
        String input = "add -role Software Engineer -from 01/24 -to 08/24";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        ArrayList<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("role Software Engineer");
        expectedArgs.add("from 01/24");
        expectedArgs.add("to 08/24");
        assertEquals(expectedArgs, args);
    }

    @Test
    void parseData_addCommandEmptyInput_returnsNull() {
        String input = "add";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        assertNull(args); // No data provided after command
    }

    // ==================== DeleteCommand Tests ====================

    @Test
    void parseCommand_validDeleteCommand_returnsDeleteCommand() {
        String input = "delete 1";
        Command command = parser.parseCommand(input);
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    void parseData_validDeleteCommand_returnsCorrectArguments() {
        String input = "delete 1";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        ArrayList<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("1");
        assertEquals(expectedArgs, args);
    }

    @Test
    void parseData_deleteCommandNoId_returnsNull() {
        String input = "delete";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        assertNull(args); // Missing ID should result in null
    }

    @Test
    void parseData_deleteCommandInvalidId_returnsArguments() {
        String input = "delete abc";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        ArrayList<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("abc");
        assertEquals(expectedArgs, args); // Command may handle invalid ID later
    }

    // ==================== UpdateCommand Tests ====================

    @Test
    void parseCommand_validUpdateCommand_returnsUpdateCommand() {
        String input = "update 1 -company XYZ";
        Command command = parser.parseCommand(input);
        assertInstanceOf(UpdateCommand.class, command);
    }

    @Test
    void parseData_validUpdateCommand_returnsCorrectArguments() {
        String input = "update 1 -company XYZ -role Developer";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        ArrayList<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("1");
        expectedArgs.add("company XYZ");
        expectedArgs.add("role Developer");
        assertEquals(expectedArgs, args);
    }

    @Test
    void parseData_updateCommandNoFields_returnsNull() {
        String input = "update 1";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        assertNull(args); // Missing fields should result in null
    }

    // ==================== SortCommand Tests ====================

    @Test
    void parseCommand_validSortCommand_returnsSortCommand() {
        String input = "sort -alphabet";
        Command command = parser.parseCommand(input);
        assertInstanceOf(SortCommand.class, command);
    }

    @Test
    void parseData_validSortCommand_returnsCorrectArguments() {
        String input = "sort -alphabet";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        ArrayList<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("alphabet");
        assertEquals(expectedArgs, args);
    }

    @Test
    void parseData_sortCommandNoFlag_returnsEmptyArguments() {
        String input = "sort";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        assert(args.isEmpty());
    }

    @Test
    void parseData_sortCommandInvalidFlag_returnsArguments() {
        String input = "sort -unknown";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        ArrayList<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("unknown");
        assertEquals(expectedArgs, args); // Command may handle invalid flag later
    }

    // ==================== FilterCommand Tests ====================

    @Test
    void parseCommand_validFilterCommand_returnsFilterCommand() {
        String input = "filter -role Developer -company ABC Corp";
        Command command = parser.parseCommand(input);
        assertInstanceOf(FilterCommand.class, command);
    }

    @Test
    void parseData_validFilterCommand_returnsCorrectArguments() {
        String input = "filter -role Developer -company ABC Corp";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        ArrayList<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("role Developer");
        expectedArgs.add("company ABC Corp");
        assertEquals(expectedArgs, args);
    }

    @Test
    void parseData_filterCommandNoArguments_returnsNull() {
        String input = "filter";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        assertNull(args); // No arguments provided
    }

    @Test
    void parseData_filterCommandInvalidFlag_returnsArguments() {
        String input = "filter -invalidFlag Value";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        ArrayList<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("invalidFlag Value");
        assertEquals(expectedArgs, args); // Command may handle invalid flag later
    }

    @Test
    void parseData_filterCommandEmptyField_returnsArgumentsWithEmptyField() {
        String input = "filter -company";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        // The parser will include "company" as an argument with no value
        ArrayList<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("company");
        assertEquals(expectedArgs, args);
    }

    // ==================== ListCommand Tests ====================

    @Test
    void parseCommand_validListCommand_returnsListCommand() {
        String input = "list";
        Command command = parser.parseCommand(input);
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    void parseData_listCommand_returnsEmptyArguments() {
        String input = "list";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        assert(args.isEmpty());
    }

    @Test
    void parseData_listCommandWithExtraInput_returnsEmptyArguments() {
        String input = "list extra input";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        assert(args.isEmpty());
    }

    // ==================== HelpCommand Tests ====================

    @Test
    void parseCommand_validHelpCommand_returnsHelpCommand() {
        String input = "help";
        Command command = parser.parseCommand(input);
        assertInstanceOf(HelpCommand.class, command);
    }

    @Test
    void parseData_helpCommand_returnsEmptyArguments() {
        String input = "help";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        assert(args.isEmpty());
    }

    @Test
    void parseData_helpCommandWithExtraInput_returnsEmptyArguments() {
        String input = "help extra input";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        assert(args.isEmpty());
    }

    // ==================== Invalid Command Tests ====================

    @Test
    void parseCommand_invalidCommand_returnsNull() {
        String input = "invalidcommand";
        Command command = parser.parseCommand(input);
        assertNull(command);
    }

    @Test
    void parseCommand_invalidCommandWithSpace_returnsNull() {
        String input = "invalidcommand   ";
        Command command = parser.parseCommand(input);
        assertNull(command);
    }

    // ==================== Edge Case Tests ====================

    @Test
    void parseCommand_emptyInput_returnsNull() {
        String input = "";
        Command command = parser.parseCommand(input);
        assertNull(command);
    }

    @Test
    void parseCommand_commandWithOnlyWhitespace_returnsNull() {
        String input = "    ";
        Command command = parser.parseCommand(input);
        assertNull(command);
    }

    @Test
    void parseCommand_commandWithMixedCase_returnsCorrectCommand() {
        String input = "AdD -role Developer -company XYZ Corp";
        Command command = parser.parseCommand(input);
        assertNull(command); // Since the command map is case-sensitive
    }

    @Test
    void parseCommand_commandWithLeadingWhitespace_returnsCorrectCommand() {
        String input = "  add -role Developer -company XYZ Corp";
        Command command = parser.parseCommand(input);
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    void parseData_commandWithLeadingWhitespace_returnsCorrectData() {
        String input = "  add -role Developer -company XYZ Corp";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        ArrayList<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("role Developer");
        expectedArgs.add("company XYZ Corp");
        assertEquals(expectedArgs, args);
    }

    @Test
    void parseData_addCommandWithWhitespaceEverywhere_returnsCorrectData() {
        String input = "  add -      role          Developer -      company     XYZ Corp";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        ArrayList<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("role          Developer");
        expectedArgs.add("company     XYZ Corp");
        assertEquals(expectedArgs, args);
    }

    @Test
    void parseData_deleteCommandWithWhitespaceEverywhere_returnsCorrectData() {
        String input = "   delete     3    ";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        ArrayList<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("3");
        assertEquals(expectedArgs, args);
    }

    @Test
    void parseData_updateCommandWithWhitespaceEverywhere_returnsCorrectData() {
        String input = "   update     2       - role   engineer     -      company Google";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        ArrayList<String> expectedArgs = new ArrayList<>();
        expectedArgs.add("2");
        expectedArgs.add("role   engineer");
        expectedArgs.add("company Google");
        assertEquals(expectedArgs, args);
    }

    @Test
    void parseData_helpCommandWithAddedCharacters_returnsCorrectData() {
        String input = "   help /qwdhui- 12dj  ";
        Command command = parser.parseCommand(input);
        assertInstanceOf(HelpCommand.class, command);
    }

    @Test
    void parseData_addCommandWithMissingFlag_returnsCorrectData() {
        String input = " add -";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        assertInstanceOf(AddCommand.class, command);
        assertNull(args);
    }

    @Test
    void parseData_addCommandWithEmptyFlag_returnsCorrectData() {
        String input = " add    -   ";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        assertInstanceOf(AddCommand.class, command);
        assertNull(args);
    }

    @Test
    void parseData_updateCommandWithMissingFlag_returnsCorrectData() {
        String input = " update   2  -";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        assertInstanceOf(UpdateCommand.class, command);
        assertNull(args);
    }

    @Test
    void parseData_updateCommandWithEmptyFlag_returnsCorrectData() {
        String input = "update  2  -   ";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        assertInstanceOf(UpdateCommand.class, command);
        assertNull(args);
    }

    @Test
    void parseData_sortCommandWithMissingFlag_returnsCorrectData() {
        String input = "sort   6  -";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        ArrayList<String> expectedArgs = new ArrayList<>();
        assertInstanceOf(SortCommand.class, command);
        assertEquals(expectedArgs, args);
    }

    @Test
    void parseData_sortCommandWithEmptyFlag_returnsCorrectData() {
        String input = "sort uhi87 -  ";
        Command command = parser.parseCommand(input);
        ArrayList<String> args = parser.parseData(command, input);
        ArrayList<String> expectedArgs = new ArrayList<>();
        assertInstanceOf(SortCommand.class, command);
        assertEquals(expectedArgs, args);
    }
}
