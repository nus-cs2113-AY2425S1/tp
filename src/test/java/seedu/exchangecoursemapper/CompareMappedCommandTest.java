package seedu.exchangecoursemapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exchangecoursemapper.command.CompareMappedCommand;
import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.courses.Course;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.exchangecoursemapper.constants.Messages.COMPARISON_RESULTS_HEADER;
import static seedu.exchangecoursemapper.constants.Messages.COMMON_MAPPINGS_HEADER;
import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;
import static seedu.exchangecoursemapper.constants.Messages.UNIQUE_MAPPINGS_HEADER;
import static seedu.exchangecoursemapper.constants.Messages.NO_UNIQUE_MAPPINGS;
import static seedu.exchangecoursemapper.constants.Messages.NO_COMMON_MAPPINGS;
import static seedu.exchangecoursemapper.constants.Messages.INVALID_INPUT_FORMAT;

class CompareMappedCommandTest {

    private List<Course> mockedStorageData;
    private CompareMappedCommand command;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        mockedStorageData = new ArrayList<>();

        Storage mockStorage = new Storage() {
            @Override
            public List<Course> loadAllCourses() {
                return mockedStorageData;
            }
        };

        command = new CompareMappedCommand(mockStorage);
    }

    @Test
    void execute_withValidUniversitiesAndCommonMappings_displaysCorrectOutput() {
        mockedStorageData.add(new Course("COMP3670", "CS3244", "The Australian National University"));
        mockedStorageData.add(new Course("INFO20003", "CS2102", "The University of Melbourne"));
        mockedStorageData.add(new Course("COMP30027", "CS3244", "The University of Melbourne"));

        command.execute("compare pu/The Australian National University pu/The University of Melbourne");

        String expectedOutput =
                COMPARISON_RESULTS_HEADER + "The Australian National University and The University of Melbourne:"
                + System.lineSeparator() +

                "\n" + COMMON_MAPPINGS_HEADER  + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "CS3244 | The Australian National University | COMP3670" + System.lineSeparator() +
                "CS3244 | The University of Melbourne | COMP30027" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +

                "\n" + UNIQUE_MAPPINGS_HEADER + "The Australian National University:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                NO_UNIQUE_MAPPINGS + "The Australian National University" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +

                "\n" + UNIQUE_MAPPINGS_HEADER + "The University of Melbourne:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "CS2102 | The University of Melbourne | INFO20003" + System.lineSeparator() +
                LINE_SEPARATOR;

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    void execute_withValidUniversitiesNoCommonMappings_displaysNoCommonMappings() {
        mockedStorageData.add(new Course("COMP2610", "CS3236", "The Australian National University"));
        mockedStorageData.add(new Course("COMP30027", "CS3244", "The University of Melbourne"));

        command.execute("compare pu/The Australian National University pu/The University of Melbourne");

        String expectedOutput =
                COMPARISON_RESULTS_HEADER + "The Australian National University and The University of Melbourne:"
                + System.lineSeparator() +

                "\n" + COMMON_MAPPINGS_HEADER  + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                NO_COMMON_MAPPINGS + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +

                "\n" + UNIQUE_MAPPINGS_HEADER + "The Australian National University:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "CS3236 | The Australian National University | COMP2610" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +

                "\n" + UNIQUE_MAPPINGS_HEADER + "The University of Melbourne:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "CS3244 | The University of Melbourne | COMP30027" + System.lineSeparator() +
                LINE_SEPARATOR;

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    void execute_withNoMappedModules_displaysNoModulesMessage() {
        command.execute("compare pu/The Australian National University pu/The University of Melbourne");

        String expectedOutput =
                COMPARISON_RESULTS_HEADER + "The Australian National University and The University of Melbourne:"
                + System.lineSeparator() +

                "\n" + COMMON_MAPPINGS_HEADER  + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                NO_COMMON_MAPPINGS + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +

                "\n" + UNIQUE_MAPPINGS_HEADER + "The Australian National University:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                NO_UNIQUE_MAPPINGS + "The Australian National University" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +

                "\n" + UNIQUE_MAPPINGS_HEADER + "The University of Melbourne:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                NO_UNIQUE_MAPPINGS + "The University of Melbourne" + System.lineSeparator() +
                LINE_SEPARATOR;

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    void execute_withInvalidInputFormat_displaysErrorMessage() {
        command.execute("compare pu/The University of Melbourne");

        assertEquals(INVALID_INPUT_FORMAT, outContent.toString().trim());
    }

    @Test
    void execute_withCommonAndUniqueMappings_displaysBothCorrectly() {
        mockedStorageData.add(new Course("COMP3670", "CS3244", "The Australian National University"));
        mockedStorageData.add(new Course("COMP30027", "CS3244", "The University of Melbourne"));
        mockedStorageData.add(new Course("COMP2610", "CS3236", "The Australian National University"));
        mockedStorageData.add(new Course("COMP90007", "CS2105", "The University of Melbourne"));

        command.execute("compare pu/The Australian National University pu/The University of Melbourne");

        String expectedOutput =
                COMPARISON_RESULTS_HEADER + "The Australian National University and The University of Melbourne:"
                + System.lineSeparator() +

                "\n" + COMMON_MAPPINGS_HEADER  + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "CS3244 | The Australian National University | COMP3670" + System.lineSeparator() +
                "CS3244 | The University of Melbourne | COMP30027" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +

                "\n" + UNIQUE_MAPPINGS_HEADER + "The Australian National University:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "CS3236 | The Australian National University | COMP2610" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +

                "\n" + UNIQUE_MAPPINGS_HEADER + "The University of Melbourne:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "CS2105 | The University of Melbourne | COMP90007" + System.lineSeparator() +
                LINE_SEPARATOR;

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

}
