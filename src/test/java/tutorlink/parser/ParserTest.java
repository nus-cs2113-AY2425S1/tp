//@@author yeekian
package tutorlink.parser;

import org.junit.jupiter.api.Test;
import tutorlink.command.AddStudentCommand;
import tutorlink.command.Command;
import tutorlink.command.DeleteStudentCommand;
import tutorlink.command.ExitCommand;
import tutorlink.command.FindStudentCommand;
import tutorlink.command.InvalidCommand;
import tutorlink.command.ListStudentCommand;
import tutorlink.exceptions.IllegalValueException;


import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    Parser parser = new Parser();

    @Test
    void parserTestInterchangingArgument() {
        String input = "add_student i/A1234567X n/John Doe";
        String input2 = "add_student n/John Doe i/A1234567X";
        String[] args = new String[]{"i/, n/"};

        var out1 = parser.getArguments(args, input);
        var out2 = parser.getArguments(args, input2);

        assertEquals(out1, out2);
    }

    @Test
    void getCommand_addStudentCommand_addStudentCommandReturned() {

        Parser parser = new Parser();
        String input = "add_student i/A1234567X n/John";
        Command actualCommand = parser.getCommand(input);

        assertEquals(AddStudentCommand.class, actualCommand.getClass());
    }

    @Test
    void getCommand_deleteStudentCommand_deleteStudentCommandReturned() {
        Parser parser = new Parser();
        String input = "delete_student i/A1234567X";
        Command actualCommand = parser.getCommand(input);

        assertEquals(DeleteStudentCommand.class, actualCommand.getClass());
    }

    @Test
    void getCommand_exitCommand_exitCommandReturned() {
        Parser parser = new Parser();
        String input = "bye";
        Command actualCommand = parser.getCommand(input);

        assertEquals(ExitCommand.class, actualCommand.getClass());
    }

    @Test
    void getCommand_listStudentCommand_listStudentCommandReturned() {
        Parser parser = new Parser();
        String input = "list_student";
        Command actualCommand = parser.getCommand(input);

        assertEquals(ListStudentCommand.class, actualCommand.getClass());
    }

    @Test
    void getCommand_findStudentCommand_findStudentCommandReturned() {
        Parser parser = new Parser();
        String input = "find_student i/A1234567X n/John Doe";
        Command actualCommand = parser.getCommand(input);

        assertEquals(FindStudentCommand.class, actualCommand.getClass());
    }

    @Test
    void getCommand_invalidCommand_invalidCommandReturned() {
        Parser parser = new Parser();
        String input = "test_input";
        Command actualCommand = parser.getCommand(input);

        assertEquals(InvalidCommand.class, actualCommand.getClass());
    }

    @Test
    void getArguments_addStudentCommand_addStudentCommandHashMapReturned() {
        Parser parser = new Parser();
        String line = "add_student i/A1234567X n/John Doe";

        Command currentCommand = new AddStudentCommand();
        String[] argumentPrefixes = currentCommand.getArgumentPrefixes();

        HashMap<String, String> arguments = parser.getArguments(argumentPrefixes, line);

        assertEquals(2, arguments.size());
        assertEquals("A1234567X", arguments.get("i/")); // Check matriculation number
        assertEquals("John Doe", arguments.get("n/")); // Check
    }

    @Test
    void getArguments_exitCommandNoArgumentPrefix_exitCommandHashMapReturned() {
        Parser parser = new Parser();
        String line = "bye";

        Command currentCommand = new ExitCommand();
        String[] argumentPrefixes = currentCommand.getArgumentPrefixes();

        HashMap<String, String> arguments = parser.getArguments(argumentPrefixes, line);

        assertEquals(0, arguments.size());
    }

    @Test
    void getArguments_addStudentCommandExtraArguments_ignoreExtraTag() {
        Parser parser = new Parser();
        String line = "add_student i/A1234567X n/John Doe t/extraTag";

        Command currentCommand = new AddStudentCommand();
        String[] argumentPrefixes = currentCommand.getArgumentPrefixes();

        HashMap<String, String> arguments = parser.getArguments(argumentPrefixes, line);

        assertEquals(2, arguments.size());
        assertEquals("A1234567X", arguments.get("i/")); // Check matriculation number
        assertEquals("John Doe", arguments.get("n/")); // Check
    }

    @Test
    void getArguments_addStudentCommandSwappedArguments_addStudentCommandHashMapReturned() {
        Parser parser = new Parser();
        String line = "add_student n/John Doe i/A1234567X ";

        Command currentCommand = new AddStudentCommand();
        String[] argumentPrefixes = currentCommand.getArgumentPrefixes();

        HashMap<String, String> arguments = parser.getArguments(argumentPrefixes, line);

        assertEquals(2, arguments.size());
        assertEquals("A1234567X", arguments.get("i/")); // Check matriculation number
        assertEquals("John Doe", arguments.get("n/")); // Check
    }

    @Test
    void getArguments_addStudentCommandMissingArguments_hashMapWithOnlyGivenArgumentsReturned() {
        Parser parser = new Parser();
        String line = "add_student n/John Doe";

        Command currentCommand = new AddStudentCommand();
        String[] argumentPrefixes = currentCommand.getArgumentPrefixes();

        HashMap<String, String> arguments = parser.getArguments(argumentPrefixes, line);

        assertEquals(1, arguments.size());
        assertEquals("John Doe", arguments.get("n/")); // Check
    }

    @Test
    void getArguments_addStudentCommandInvalidArguments_emptyHashMapReturned() {
        Parser parser = new Parser();
        String line = "add_student j/test";

        Command currentCommand = new AddStudentCommand();
        String[] argumentPrefixes = currentCommand.getArgumentPrefixes();

        HashMap<String, String> arguments = parser.getArguments(argumentPrefixes, line);

        assertEquals(0, arguments.size());
    }

    @Test
    void getArguments_duplicatePrefix_exception() {
        Parser parser = new Parser();
        String line = "add_student n/Ethan n/Ethan i/A0276007H";
        AddStudentCommand currentCommand = new AddStudentCommand();
        String[] argumentPrefixes = currentCommand.getArgumentPrefixes();
        assertThrows(IllegalValueException.class, () -> parser.getArguments(argumentPrefixes, line));
    }

    @Test
    void getArguments_duplicatePrefixAgain_exception() {
        Parser parser = new Parser();
        String line = "add_student n/Ethan i/A0276007H i/A0276007H";
        AddStudentCommand currentCommand = new AddStudentCommand();
        String[] argumentPrefixes = currentCommand.getArgumentPrefixes();
        assertThrows(IllegalValueException.class, () -> parser.getArguments(argumentPrefixes, line));
    }
}
//@@author
