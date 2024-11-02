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
    public void testPrepareLogCommandValidArguments() {
        LogCommand expectedCommand = new LogCommand(1, 2,
                LocalDate.of(2023, 12, 21));

        String fullCommand = "prog log /p 2 /d 3 /t 21-12-2023";
        Command command = parser.parse(fullCommand);
        assertInstanceOf(LogCommand.class, command, "Expected LogCommand instance.");
        LogCommand actualCommand = (LogCommand) command;

        assertEquals(expectedCommand, actualCommand, "Expected command to be equal to the parsed LogCommand.");
    }

    @Test
    public void testPrepareLogCommandMissingFlags() {
        String fullCommand = "prog log /p 2";

        assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand),
                "Expected IllegalArgumentException for missing required flags.");
    }

    @Test
    public void testPrepareLogCommandInvalidProgrammeIndex() {
        // Test when programme index is invalid
        String fullCommand = "prog log /p abc /d 3 /t 21-12-2023";

        assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand),
                "Expected IllegalArgumentException for non-numeric programme index.");
    }

    @Test
    public void testPrepareLogCommandInvalidDayIndex() {
        // Test when day index is invalid
        String fullCommand = "prog log /p 2 /d abc /t 21-12-2023";

        assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand),
                "Expected IllegalArgumentException for non-numeric day index.");
    }

    @Test
    public void testPrepareLogCommandInvalidDateFormat() {
        // Test with an incorrectly formatted date
        String fullCommand = "prog log /p 2 /d 3 /t 2023-12-21";

        assertThrows(IllegalArgumentException.class, () -> parser.parse(fullCommand),
                "Expected IllegalArgumentException for invalid date format.");
    }

    @Test
    public void testPrepareLogCommandFutureDate() {
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
