package seedu.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.command.Command;
import seedu.command.TestCommand;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    private Parser parser;
    private TestCommand command;
    @BeforeEach
    public void setUp() {
        parser = new Parser();
        command = new TestCommand();
        parser.registerCommands(command);
    }

    @Test
    public void testRegisterCommands_newCommand_commandFound() {
        assertNotNull(parser.getCommands().get(TestCommand.COMMAND_WORD));
        assertEquals(command, parser.getCommands().get(TestCommand.COMMAND_WORD));
    }

    @Test
    public void testParseCommand_withValidCommand_commandFound() {
        Command parsedCommand = parser.parseCommand(TestCommand.COMMAND_WORD);

        assertNotNull(parsedCommand);
        assertEquals(command, parsedCommand);
    }

    @Test
    public void testParseCommand_withInvalidCommand_returnNull() {
        Command parsedCommand = parser.parseCommand("Random");

        assertNull(parsedCommand);
    }

    @Test
    public void testExtractArguments_withValidArguments_correctExtraction() {
        String argumentString = "arg1/ value1 value1b arg2/ value2";
        Map<String, String> extractedArgs = parser.extractArguments(command, argumentString);

        assertEquals("value1 value1b", extractedArgs.get("arg1/"));
        assertEquals("value2", extractedArgs.get("arg2/"));
    }

    @Test
    public void testExtractArguments_withDuplicatedKeys_duplicatesHandled() {
        String argumentString = "arg1/ value1 arg1/ value1b arg2/ value2";
        Map<String, String> extractedArgs = parser.extractArguments(command, argumentString);

        assertEquals("value1 arg1/ value1b", extractedArgs.get("arg1/")); // Assuming first occurrence is kept
        assertEquals("value2", extractedArgs.get("arg2/"));
    }

    @Test
    public void testExtractArguments_withNoArguments_emptyMapReturned() {
        Map<String, String> extractedArgs = parser.extractArguments(command, "");

        assertTrue(extractedArgs.isEmpty());
    }
}
