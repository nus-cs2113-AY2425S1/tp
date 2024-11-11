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
        String userInput = "help list courses";
        String commandResult = helpCommand.getCommand(userInput);
        String expectedOutput = "list courses";
        assertEquals(expectedOutput, commandResult);
    }

    @Test
    public void getCommand_withFilterCommand_expectFilter() {
        String userInput = "help filter";
        String commandResult = helpCommand.getCommand(userInput);
        String expectedOutput = "filter";
        assertEquals(expectedOutput, commandResult);
    }

    @Test
    public void getCommand_withListSchoolsCommand_expectListSchools() {
        String userInput = "help list schools";
        String commandResult = helpCommand.getCommand(userInput);
        String expectedOutput = "list schools";
        assertEquals(expectedOutput, commandResult);
    }

    @Test
    public void getCommand_withAddCommand_expectAdd() {
        String userInput = "help add";
        String commandResult = helpCommand.getCommand(userInput);
        String expectedOutput = "add";
        assertEquals(expectedOutput, commandResult);
    }

    @Test
    public void getCommand_withCommandsCommand_expectCommands() {
        String userInput = "help commands";
        String commandResult = helpCommand.getCommand(userInput);
        String expectedOutput = "commands";
        assertEquals(expectedOutput, commandResult);
    }

    @Test
    public void getCommand_withByeCommand_expectBye() {
        String userInput = "help bye";
        String commandResult = helpCommand.getCommand(userInput);
        String expectedOutput = "bye";
        assertEquals(expectedOutput, commandResult);
    }

    @Test
    public void getCommand_withObtainCommand_expectObtain() {
        String userInput = "help obtain";
        String commandResult = helpCommand.getCommand(userInput);
        String expectedOutput = "obtain";
        assertEquals(expectedOutput, commandResult);
    }

    @Test
    public void getCommand_withInvalidCommand_expectException() {
        String userInput = "help invalid";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> helpCommand.getCommand(userInput));
        String expectedOutput = """
                Invalid command.
                Please check the commands available by typing commands.
                """;
        assertEquals(expectedOutput.trim(), exception.getMessage().trim());
    }

    @Test
    public void printHelp_withValidCommand_printHelpMessage() {
        String command = "list courses";
        helpCommand.printHelp(command);
        String expectedOutput = """
                The list courses function allows users to key in the partner university (PU)
                they are interested in and it will return NUS courses and the mappable PU courses.
                The format to use this function is shown below:
                list courses [PU_NAME]
                For example, set the university of western australia
                -----------------------------------------------------
                """;
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(expectedOutput.trim(), normalizeLineEndings(actualOutput));
    }

    @Test
    public void printHelp_withListMappedCommand_printHelpMessage() {
        String command = "list mapped";
        helpCommand.printHelp(command);
        String expectedOutput = """
                The list mapped function allows users to list out all the
                course mapping their have added into their personalised tracker using the add function.
                To use this feature, please key in: list mapped
                -----------------------------------------------------
                """;
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(expectedOutput.trim(), normalizeLineEndings(actualOutput));
    }

    @Test
    public void printHelp_withInvalidCommand_expectException() {
        String command = "invalid";
        String expectedOutput = """
                Invalid command.
                Please check the commands available by typing commands.
                """;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> helpCommand.printHelp(command));
        assertEquals(expectedOutput.trim(), exception.getMessage());
    }

    @Test
    public void execute_withValidUserInput_printHelpMessage() {
        String userInput = "help filter";
        helpCommand.execute(userInput);
        String expectedOutput = """
                The filter function allows users to input a NUS course that they want to map
                and get a list of the mappable courses that includes NUS courses in the format of:
                Partner University: [PU_NAME]
                Partner University Course Code: [PU_COURSE_CODE]
                The format to use this feature is shown below:
                filter [NUS_COURSE_CODE]
                For example, filter cs2040
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
                Invalid command.
                Please check the commands available by typing commands.
                -----------------------------------------------------
                """;

        String actualOutput = outputStreamCaptor.toString();
        assertEquals(expectedOutput.trim(), normalizeLineEndings(actualOutput).trim());
    }

    @Test
    public void execute_withHelpListCourses_expectSetHelpMessage() {
        String userInput = "help list courses";
        helpCommand.execute(userInput);
        String expectedOutput = """
                The list courses function allows users to key in the partner university (PU)
                they are interested in and it will return NUS courses and the mappable PU courses.
                The format to use this function is shown below:
                list courses [PU_NAME]
                For example, set the university of western australia
                -----------------------------------------------------
                """;
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(expectedOutput.trim(), normalizeLineEndings(actualOutput).trim());
    }

    @Test
    public void execute_withHelpListMapped_expectListMappedHelp() {
        String userInput = "help list mapped";
        helpCommand.execute(userInput);
        String expectedOutput = """
                The list mapped function allows users to list out all the
                course mapping their have added into their personalised tracker using the add function.
                To use this feature, please key in: list mapped
                -----------------------------------------------------
                """;
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(expectedOutput.trim(), normalizeLineEndings(actualOutput).trim());
    }

    @Test
    public void execute_withHelpAddCommand_expectAddHelp() {
        String userInput = "help add";
        helpCommand.execute(userInput);
        String expectedOutput = """
                The add function allows users to add a course mapping plan into
                their personalised tracker for future use.
                The format to use this feature is shown below:
                add <NUS_COURSE_CODE> /pu <NAME_OF_PU> /coursepu <PU_COURSE_CODE>
                For example, add CS2040 /pu The university of western australia /coursepu CITS2200
                -----------------------------------------------------
                """;

        String actualOutput = outputStreamCaptor.toString();
        assertEquals(expectedOutput.trim(), normalizeLineEndings(actualOutput).trim());
    }

    @Test
    public void execute_withHelpDeleteCommand_expectDeleteHelp() {
        String userInput = "help delete";
        helpCommand.execute(userInput);
        String expectedOutput = """
                The delete function allows users to delete a course mapping plan in their personalised tracker.
                The format to use this feature is shown below:
                delete [MAPPING_NUMBER]
                For example, delete 1 - which means deleting mapping 1
                -----------------------------------------------------
                """;

        String actualOutput = outputStreamCaptor.toString();
        assertEquals(expectedOutput.trim(), normalizeLineEndings(actualOutput).trim());
    }

    @Test
    public void execute_withHelpFindCommand_expectFindHelp() {
        String userInput = "help find";
        helpCommand.execute(userInput);
        String expectedOutput = """
                The find function allows users to input a NUS course that they
                want to find and get the relevant course mapping plans in their personal tracker.
                The format to use this feature is shown below:
                find [NUS_COURSE_CODE]
                For example, find cs2040
                -----------------------------------------------------
                """;

        String actualOutput = outputStreamCaptor.toString();
        assertEquals(expectedOutput.trim(), normalizeLineEndings(actualOutput).trim());
    }

    @Test
    public void execute_withHelpObtainCommand_expectObtainHelp() {
        String userInput = "help obtain";
        helpCommand.execute(userInput);
        String expectedOutput = """
                The obtain function allows users to obtain information of Partner
                Universities such as PU's email and PU's contact number.
                To format to use this feature is shown below:
                1. obtain <SCHOOL_NAME> /email - To obtain PU's email
                2. obtain <SCHOOL_NAME> /number - To obtain PU's contact number
                For example, obtain the university of western australia /number
                -----------------------------------------------------
                """;

        String actualOutput = outputStreamCaptor.toString();
        assertEquals(expectedOutput.trim(), normalizeLineEndings(actualOutput).trim());
    }

    String normalizeLineEndings(String input) {
        return input.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n").trim();
    }
}
