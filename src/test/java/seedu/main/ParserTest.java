package seedu.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.command.Command;
import seedu.command.TestCommand;

import static org.junit.jupiter.api.Assertions.*;


class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }


    @Test
    public void testRegisterCommands_newCommand_commandFound() {
        TestCommand command = new TestCommand();
        parser.registerCommands(command);

        assertNotNull(parser.getCommands().get(TestCommand.COMMAND_WORD));
        assertEquals(command, parser.getCommands().get(TestCommand.COMMAND_WORD));
    }

    @Test
    public void testParseCommand_withValidCommand_rightArguments() {
        TestCommand command = new TestCommand();
        parser.registerCommands(command);

        Command parsedCommand = parser.parseCommand("test arg1/ value1 value1b arg2/ value2");

        assertNotNull(parsedCommand);
        assertEquals(command, parsedCommand);
        assertEquals("value1 value1b", parsedCommand.getArguments().get("arg1/"));
        assertEquals("value2", parsedCommand.getArguments().get("arg2/"));
    }

    @Test
    public void testParseCommand_withDuplicatedKeys_notConsiderTheDuplicated() {
        TestCommand command = new TestCommand();
        parser.registerCommands(command);

        Command parsedCommand = parser.parseCommand("test arg1/ value1 arg1/ value1b arg2/ value2");

        assertNotNull(parsedCommand);
        assertEquals(command, parsedCommand);
        assertEquals("value1 arg1/ value1b", parsedCommand.getArguments().get("arg1/"));
        assertEquals("value2", parsedCommand.getArguments().get("arg2/"));
    }

    @Test
    public void testParseCommand_withNoArguments_commandWillEmptyArguments() {
        TestCommand command = new TestCommand();
        parser.registerCommands(command);

        Command parsedCommand = parser.parseCommand("test");

        assertNotNull(parsedCommand);
        assertEquals(command, parsedCommand);
        assertTrue(parsedCommand.getArguments().isEmpty());
    }

    @Test
    public void testParseCommand_withInvalidCommand_nullCommand() {
        Command parsedCommand = parser.parseCommand("invalidCommand arg1/ value1");

        assertNull(parsedCommand);
    }

}