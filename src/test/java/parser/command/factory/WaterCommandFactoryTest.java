package parser.command.factory;

import command.Command;
import command.water.AddWaterCommand;
import command.water.DeleteWaterCommand;
import command.water.ViewWaterCommand;
import exceptions.FlagExceptions;
import exceptions.ParserExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WaterCommandFactoryTest {

    private WaterCommandFactory waterCommandFactory;

    @BeforeEach
    public void setUp() {
        waterCommandFactory = new WaterCommandFactory();
    }

    @Test
    public void testParseAddWaterCommand() {
        String argumentString = "add /v 1.5 /t 31-10-2024";
        AddWaterCommand expectedCommand = new AddWaterCommand(1.5f, LocalDate.of(2024, 10, 31));

        Command result = waterCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result, "Parsed command should be equal to the expected AddWaterCommand.");
    }

    @Test
    public void testParseDeleteWaterCommand() {
        String argumentString = "delete /w 1 /t 31-10-2024";
        DeleteWaterCommand expectedCommand = new DeleteWaterCommand(0, LocalDate.of(2024, 10, 31));

        Command result = waterCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result, "Parsed command should be equal to the expected DeleteWaterCommand.");
    }

    @Test
    public void testParseViewWaterCommand() {
        String argumentString = "view 31-10-2024";
        ViewWaterCommand expectedCommand = new ViewWaterCommand(LocalDate.of(2024, 10, 31));

        Command result = waterCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result, "Parsed command should be equal to the expected ViewWaterCommand.");
    }

    @Test
    public void testPrepareAddCommandMissingRequiredFlags() {
        // Missing /v (volume) flag
        String argumentString = "/t 31-10-2024";

        assertThrows(FlagExceptions.class, () -> waterCommandFactory.prepareAddCommand(argumentString),
                "Missing required flag /v should throw FlagException.");
    }

    @Test
    public void testPrepareDeleteCommandMissingRequiredFlags() {
        // Missing /w (water index) flag
        String argumentString = "/t 31-10-2024";

        assertThrows(FlagExceptions.class,
                () -> waterCommandFactory.prepareDeleteCommand(argumentString),
                "Missing required flag /w should throw FlagException.");
    }

    @Test
    public void testPrepareViewCommandInvalidDate() {
        String argumentString = "invalid-date";

        assertThrows(ParserExceptions.class,
                () -> waterCommandFactory.prepareViewCommand(argumentString),
                "Invalid date format should throw FlagException.");
    }
}

