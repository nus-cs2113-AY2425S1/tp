package parser;

import command.Command;
import command.ExitCommand;
import command.LogCommand;
import command.InvalidCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void testParse_nullInput() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(null));
    }

    @Test
    public void testParse_emptyCommand() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse("  "));
    }

    @Test
    public void testParse_unknownCommand() {
        Command command = parser.parse("unknownCommand");
        assertInstanceOf(InvalidCommand.class, command, "Expected InvalidCommand for unknown command");
    }

    @Test
    public void testParse_progCommand() {
        Command command = parser.parse("prog someArgument");
        assertInstanceOf(Command.class, command, "Expected valid command");
    }

    @Test
    public void testParse_exitCommand() {
        Command command = parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command, "Expected ExitCommand");
    }

    @Test
    public void testParse_logCommand() {
        Command command = parser.parse("log /p 2 /d 3 /t 21-12-2023");
        assertInstanceOf(LogCommand.class, command, "Expected LogCommand");
    }

    @Test
    public void testPrepareLogCommand_validArguments() {
        LogCommand expectedCommand = new LogCommand(1,2,LocalDate.of(2023,12,21));

        String fullCommand = "log /p 2 /d 3 /t 21-12-2023";
        Command command = parser.parse(fullCommand);
        LogCommand actualCommand = (LogCommand) command;

        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void testPrepareLogCommand_missingFlags() {
        String fullCommand = "log /p 2";

        assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand));
    }

    @Test
    public void testPrepareLogCommand_invalidProgrammeIndex() {
        // Test when programme index is invalid
        String fullCommand = "log /p abc /d 3 /t 21-12-2023";

        assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand));
    }

    @Test
    public void testPrepareLogCommand_invalidDayIndex() {
        // Test when day index is invalid
        String fullCommand = "log /p 2 /d abc /t 21-12-2023";

        assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand));
    }

    @Test
    public void testPrepareLogCommand_invalidDateFormat() {
        // Test when the date format is invalid
        String fullCommand = "log /p 2 /d 3 /t 21-2023-12";

        assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand));
    }

    @Test
    public void testPrepareLogCommand_emptyProgrammeIndex() {
        // Test when the programme index is empty
        String fullCommand = "log /p  /d 3 /t 21-12-2023";

        assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand));
    }

    @Test
    public void testPrepareLogCommand_emptyDayIndex() {
        // Test when the day index is empty
        String fullCommand = "log /p 2 /d  /t 21-12-2023";

         assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand));

    }

    @Test
    public void testPrepareLogCommand_emptyDate() {
        // Test when the date is empty
        String fullCommand = "log /p 2 /d 3 /t  ";

        assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand));

    }
}
