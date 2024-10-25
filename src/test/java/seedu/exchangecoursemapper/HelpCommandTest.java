package seedu.exchangecoursemapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exchangecoursemapper.command.HelpCommand;

import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HelpCommandTest {

    private HelpCommand helpCommand;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        Logger logger = Logger.getLogger(HelpCommand.class.getName());
        logger.setLevel(Level.OFF);

        helpCommand = new HelpCommand();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
    }

    @Test
    public void getCommand_withSetCommand_expectSet() {
        String userInput = "help set";
        String CommandResult = helpCommand.getCommand(userInput);
        String expectedOutput = "set";
        assertEquals(expectedOutput, CommandResult);
    }

    @Test
    public void getCommand_withFilterCommand_expectFilter() {
        String userInput = "help filter";
        String CommandResult = helpCommand.getCommand(userInput);
        String expectedOutput = "filter";
        assertEquals(expectedOutput, CommandResult);
    }

    @Test
    public void getCommand_withListSchoolsCommand_expectListSchools() {
        String userInput = "help list schools";
        String CommandResult = helpCommand.getCommand(userInput);
        String expectedOutput = "list schools";
        assertEquals(expectedOutput, CommandResult);
    }

    @Test
    public void getCommand_withAddCommand_expectAdd() {
        String userInput = "help add";
        String CommandResult = helpCommand.getCommand(userInput);
        String expectedOutput = "add";
        assertEquals(expectedOutput, CommandResult);
    }

    @Test
    public void getCommand_withCommandsCommand_expectCommands() {
        String userInput = "help commands";
        String CommandResult = helpCommand.getCommand(userInput);
        String expectedOutput = "commands";
        assertEquals(expectedOutput, CommandResult);
    }

    @Test
    public void getCommand_withByeCommand_expectBye() {
        String userInput = "help bye";
        String CommandResult = helpCommand.getCommand(userInput);
        String expectedOutput = "bye";
        assertEquals(expectedOutput, CommandResult);
    }

    @Test
    public void getCommand_withObtainCommand_expectObtain() {
        String userInput = "help obtain";
        String CommandResult = helpCommand.getCommand(userInput);
        String expectedOutput = "obtain";
        assertEquals(expectedOutput, CommandResult);
    }

    @Test
    public void getCommand_withInvalidCommand_expectException() {
        String userInput = "help invalid";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> helpCommand.getCommand(userInput));
        String expectedOutput = "Invalid command.";
        assertEquals(expectedOutput, exception.getMessage());
    }

    @Test
    public void printHelp_withValidCommand_printHelpMessage() {
        String command = "set";
        helpCommand.printHelp(command);
        String expectedOutput = """
                The set function allows users to key in the partner university(PU)
                they are interested in and it will return NUS courses and the mappable PU courses.
                 The format to use this function is shown below:
                set [PU_NAME]
                e.g. set the university of western australia
                -----------------------------------------------------
                """;
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(expectedOutput.trim(), normalizeLineEndings(actualOutput));
    }

    @Test
    public void printHelp_withInvalidCommand_expectException() {
        String command = "invalid";
        String expectedOutput = "Invalid command";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> helpCommand.printHelp(command));
        assertEquals(expectedOutput, exception.getMessage());
    }

    @Test
    public void execute_withValidUserInput_printHelpMessage() {
        String userInput = "help filter";
        helpCommand.execute(userInput);
        String expectedOutput = """
                The filter function allows users to input a NUS course that they
                want to map and get a list of the mappable courses from PUs.
                The format to use this feature is shown below:
                filter [NUS_COURSE_CODE]
                e.g. filter cs2040
                -----------------------------------------------------
                """;
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(expectedOutput.trim(), normalizeLineEndings(actualOutput));
    }

    @Test
    public void execute_withInvalidUserInput_displayError() {
        String userInput = "help invalid";
        helpCommand.execute(userInput);

        String expectedOutput = """
                Invalid command.\s
                Please check the commands available by typing commands.
                """;

        String actualOutput = outputStreamCaptor.toString();
        assertEquals(expectedOutput.trim(), normalizeLineEndings(actualOutput));
    }

    String normalizeLineEndings(String input) {
        return input.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n").trim();
    }
}
