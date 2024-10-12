package seedu.exchangecoursemapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.exchangecoursemapper.command.ListUniCoursesCommand;

public class ListUniCoursesCommandTest {

    private ListUniCoursesCommand listUniCoursesCommand;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp () {
        System.setOut(new PrintStream(outputStreamCaptor));
        listUniCoursesCommand = new ListUniCoursesCommand();
    }

    @Test
    public void getPuName_withValidInput_success() {
        String userInput = "set the university of western australia";
        String puName = listUniCoursesCommand.getPuName(userInput);
        assertEquals("the university of western australia", puName);
    }

    @Test
    public void getPuName_withInvalidInput_success() {
        String userInput = "set";
        String getPuNameOutput = listUniCoursesCommand.getPuName(userInput);
        assertEquals("", getPuNameOutput);
    }

}
