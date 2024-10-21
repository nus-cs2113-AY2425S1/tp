package parser;

import command.Command;
import command.ExitCommand;
import command.HistoryCommand;
import command.LogCommand;
import command.InvalidCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class ParserTest {

    private Parser parser;
    private ProgCommandParser mockProgCommandParser;

    @BeforeEach
    public void setUp() {
        mockProgCommandParser = mock(ProgCommandParser.class);
        parser = new Parser(mockProgCommandParser);  // Inject the mock parser
    }

    @Test
    public void testParse_nullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> parser.parse(null));

        assertEquals("Command cannot be empty. Please enter a valid command.", exception.getMessage());
    }

    @Test
    public void testParse_emptyCommand() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> parser.parse("  "));

        assertEquals("Command cannot be empty. Please enter a valid command.", exception.getMessage());
    }

    @Test
    public void testParse_unknownCommand() {
        Command command = parser.parse("unknownCommand");

        assertInstanceOf(InvalidCommand.class, command, "Expected InvalidCommand for unknown command");
    }

    @Test
    public void testParse_progCommand() {
        // Test valid "prog" command
        when(mockProgCommandParser.parse(anyString())).thenReturn(mock(Command.class));
        Command command = parser.parse("prog someArgument");
        verify(mockProgCommandParser, times(1)).parse("someArgument");
        assertNotNull(command, "Expected valid command");
    }

    @Test
    public void testParse_historyCommand() {
        Command command = parser.parse("history");

        assertInstanceOf(HistoryCommand.class, command, "Expected HistoryCommand");
    }

    @Test
    public void testParse_exitCommand() {
        Command command = parser.parse("bye");

        assertInstanceOf(ExitCommand.class, command, "Expected ExitCommand");
    }

    @Test
    public void testPrepareLogCommand_validArguments() {
        // Test valid log command with correct flags
        String fullCommand = "log /p 2 /d 3 /t 21-12-2023";
        Command command = parser.parse(fullCommand);

        assertInstanceOf(LogCommand.class, command, "Expected LogCommand");
        LogCommand logCommand = (LogCommand) command;

        assertEquals(1, logCommand.getProgrammeIndex(), "Expected programme index to be 1 (zero-based)");
        assertEquals(2, logCommand.getDayIndex(), "Expected day index to be 2 (zero-based)");
        assertEquals(LocalDate.of(2023, 12, 21), logCommand.getDate(), "Expected date to be 21-12-2023");
    }

    @Test
    public void testPrepareLogCommand_missingFlags() {
        // Test when not all flags are provided
        String fullCommand = "log /p 2 /d 3"; // Missing /t flag

        Exception exception = assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand));

        assertEquals("Please provide all log flags.", exception.getMessage());
    }

    @Test
    public void testPrepareLogCommand_invalidProgrammeIndex() {
        // Test when programme index is invalid
        String fullCommand = "log /p abc /d 3 /t 21-12-2023";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand));

        assertEquals("Invalid index. Please provide a valid number.", exception.getMessage());
    }

    @Test
    public void testPrepareLogCommand_invalidDayIndex() {
        // Test when day index is invalid
        String fullCommand = "log /p 2 /d abc /t 21-12-2023";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand));

        assertEquals("Invalid index. Please provide a valid number.", exception.getMessage());
    }

    @Test
    public void testPrepareLogCommand_invalidDateFormat() {
        // Test when the date format is invalid
        String fullCommand = "log /p 2 /d 3 /t 21-2023-12";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand));

        assertEquals("Invalid date format. Expected format: dd-MM-yyyy. Error: 21-2023-12", exception.getMessage());
    }

    @Test
    public void testPrepareLogCommand_emptyProgrammeIndex() {
        // Test when the programme index is empty
        String fullCommand = "log /p  /d 3 /t 21-12-2023";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand));

        assertEquals("Flag /p is missing a value.", exception.getMessage());
    }

    @Test
    public void testPrepareLogCommand_emptyDayIndex() {
        // Test when the day index is empty
        String fullCommand = "log /p 2 /d  /t 21-12-2023";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand));

        assertEquals("Flag /d is missing a value.", exception.getMessage());
    }

    @Test
    public void testPrepareLogCommand_emptyDate() {
        // Test when the date is empty
        String fullCommand = "log /p 2 /d 3 /t  ";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand));

        assertEquals("Flag /t is missing a value.", exception.getMessage());
    }

    @Test
    public void testPrepareLogCommand_invalidFlag() {
        // Test with an unrecognized flag
        String fullCommand = "log /x 2 /d 3 /t 21-12-2023";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand));

        assertEquals("Flag command not recognized: /x", exception.getMessage());
    }
}
