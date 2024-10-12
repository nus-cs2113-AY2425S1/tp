package seedu.exchangecoursemapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exchangecoursemapper.command.ListSchoolCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListSchoolCommandTest {

    private ListSchoolCommand listSchoolCommand;

    @BeforeEach
    public void setUp() {
        listSchoolCommand = new ListSchoolCommand();
    }

    @Test
    public void execute_validJsonFile_printsUniversityNames() throws Exception {
        String result = executeAndCaptureOutput("list schools");

        assertTrue(result.contains("Chulalongkorn University"));
        assertTrue(result.contains("The University of Melbourne"));
        assertTrue(result.contains("The Australian National University"));
        assertTrue(result.contains("Victoria University of Wellington"));
        assertTrue(result.contains("The University of Western Australia"));
    }

    private String executeAndCaptureOutput(String userInput) throws Exception {
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));
        listSchoolCommand.execute(userInput);
        return outContent.toString();
    }
}


