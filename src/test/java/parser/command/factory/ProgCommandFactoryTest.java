package parser.command.factory;

import command.Command;
import command.programme.LogCommand;
import org.junit.jupiter.api.Test;
import parser.Parser;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProgCommandFactoryTest {

    private final Parser parser = new Parser();
    private final ProgCommandFactory factory = new ProgCommandFactory();


    @Test
    public void testPrepareLogCommand_validArguments() {
        LogCommand expectedCommand = new LogCommand(1, 2,
                LocalDate.of(2023, 12, 21));

        String fullCommand = "prog log /p 2 /d 3 /t 21-12-2023";
        Command command = parser.parse(fullCommand);
        assertInstanceOf(LogCommand.class, command, "Expected LogCommand instance.");
        LogCommand actualCommand = (LogCommand) command;

        assertEquals(expectedCommand, actualCommand, "Expected command to be equal to the parsed LogCommand.");
    }

    @Test
    public void testPrepareLogCommand_missingFlags() {
        String fullCommand = "prog log /p 2";

        assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand),
                "Expected IllegalArgumentException for missing required flags.");
    }

    @Test
    public void testPrepareLogCommand_invalidProgrammeIndex() {
        // Test when programme index is invalid
        String fullCommand = "prog log /p abc /d 3 /t 21-12-2023";

        assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand),
                "Expected IllegalArgumentException for non-numeric programme index.");
    }

    @Test
    public void testPrepareLogCommand_invalidDayIndex() {
        // Test when day index is invalid
        String fullCommand = "prog log /p 2 /d abc /t 21-12-2023";

        assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand),
                "Expected IllegalArgumentException for non-numeric day index.");
    }

    @Test
    public void testPrepareLogCommand_invalidDateFormat() {
        // Test with an incorrectly formatted date
        String fullCommand = "prog log /p 2 /d 3 /t 2023-12-21";

        assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand),
                "Expected IllegalArgumentException for invalid date format.");
    }

    @Test
    public void testPrepareLogCommand_futureDate() {
        String fullCommand = "prog log /p 2 /d 3 /t 21-12-2030";
        LogCommand expectedCommand = new LogCommand(1, 2,
                LocalDate.of(2030, 12, 21));

        Command command = parser.parse(fullCommand);
        assertInstanceOf(LogCommand.class, command, "Expected LogCommand instance.");
        LogCommand actualCommand = (LogCommand) command;

        assertEquals(expectedCommand, actualCommand, "Expected command to be equal " +
                "to the parsed LogCommand with a future date.");
    }
}
