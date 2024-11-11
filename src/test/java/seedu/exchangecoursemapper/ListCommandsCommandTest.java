package seedu.exchangecoursemapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.exchangecoursemapper.command.ListCommandsCommand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListCommandsCommandTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        // Redirect system output to the ByteArrayOutputStream to capture it for testing
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testListCommands() {
        // Test the ListCommandsCommand directly
        ListCommandsCommand listCommandsCommand = new ListCommandsCommand();
        listCommandsCommand.execute("");

        // Check if the output contains the expected list of commands
        String expectedCommand1 = "Here are the available commands:";
        String expectedCommand2 = "filter <subject code> - Filter courses by subject code.";
        String expectedCommand3 = "list courses <SCHOOL_NAME>   - To list out the mappable courses provided by a specific PU.";
        String expectedCommand4 = "list schools          - List all available partner universities.";
        String expectedCommand5 = "add <NUS_COURSE_CODE> /pu <NAME_OF_PU> /coursepu <PU_COURSE_CODE>";
        String expectedCommand6 = "- Add mapped courses between NUS and partner universities.";
        String expectedCommand7 = "obtain <SCHOOL_NAME> /email  - Obtain partner university contact email.";
        String expectedCommand8 = "obtain <SCHOOL_NAME> /number - Obtain partner university contact number.";
        String expectedCommand9 = "delete <LIST_INDEX>          - Delete a course mapping.";
        String expectedCommand10 = "list mapped                  - List all course mapping saved.";
        String expectedCommand11 = "compare pu/<uni1> pu/<uni2>  - Compare course mappings between 2 universities.";
        String expectedCommand12 = "find <NUS_COURSE_CODE>       - Find courses with subject code in your list.";
        String expectedCommand13 = "bye                          - End the program.";
        String expectedCommand14 = "help <COMMAND>               - To get more specific information of the commands.";

        assertTrue(outputStreamCaptor.toString().contains(expectedCommand1));
        assertTrue(outputStreamCaptor.toString().contains(expectedCommand2));
        assertTrue(outputStreamCaptor.toString().contains(expectedCommand3));
        assertTrue(outputStreamCaptor.toString().contains(expectedCommand4));
        assertTrue(outputStreamCaptor.toString().contains(expectedCommand5));
        assertTrue(outputStreamCaptor.toString().contains(expectedCommand6));
        assertTrue(outputStreamCaptor.toString().contains(expectedCommand7));
        assertTrue(outputStreamCaptor.toString().contains(expectedCommand8));
        assertTrue(outputStreamCaptor.toString().contains(expectedCommand9));
        assertTrue(outputStreamCaptor.toString().contains(expectedCommand10));
        assertTrue(outputStreamCaptor.toString().contains(expectedCommand11));
        assertTrue(outputStreamCaptor.toString().contains(expectedCommand12));
        assertTrue(outputStreamCaptor.toString().contains(expectedCommand13));
        assertTrue(outputStreamCaptor.toString().contains(expectedCommand14));
    }
}
