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
        mockedStorageData.add(new Course("comp3670", "cs3244", "the australian national university"));
        mockedStorageData.add(new Course("info20003", "cs2102", "the university of melbourne"));
        mockedStorageData.add(new Course("comp30027", "cs3244", "the university of melbourne"));

        command.execute("compare pu/The Australian National University pu/The University of Melbourne");

        String expectedOutput =
                COMPARISON_RESULTS_HEADER + "the australian national university and the university of melbourne:"
                + System.lineSeparator() +

                "\n" + COMMON_MAPPINGS_HEADER  + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "cs3244 | the australian national university | comp3670" + System.lineSeparator() +
                "cs3244 | the university of melbourne | comp30027" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +

                "\n" + UNIQUE_MAPPINGS_HEADER + "the australian national university:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                NO_UNIQUE_MAPPINGS + "the australian national university" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +

                "\n" + UNIQUE_MAPPINGS_HEADER + "the university of melbourne:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "cs2102 | the university of melbourne | info20003" + System.lineSeparator() +
                LINE_SEPARATOR;

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    void execute_withValidUniversitiesNoCommonMappings_displaysNoCommonMappings() {
        mockedStorageData.add(new Course("comp2610", "cs3236", "the australian national university"));
        mockedStorageData.add(new Course("comp30027", "cs3244", "the university of melbourne"));

        command.execute("compare pu/The Australian National University pu/The University of Melbourne");

        String expectedOutput =
                COMPARISON_RESULTS_HEADER + "the australian national university and the university of melbourne:"
                + System.lineSeparator() +

                "\n" + COMMON_MAPPINGS_HEADER  + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                NO_COMMON_MAPPINGS + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +

                "\n" + UNIQUE_MAPPINGS_HEADER + "the australian national university:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "cs3236 | the australian national university | comp2610" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +

                "\n" + UNIQUE_MAPPINGS_HEADER + "the university of melbourne:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "cs3244 | the university of melbourne | comp30027" + System.lineSeparator() +
                LINE_SEPARATOR;

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    void execute_withNoMappedModules_displaysNoModulesMessage() {
        command.execute("compare pu/The Australian National University pu/The University of Melbourne");

        String expectedOutput =
                COMPARISON_RESULTS_HEADER + "the australian national university and the university of melbourne:"
                + System.lineSeparator() +

                "\n" + COMMON_MAPPINGS_HEADER  + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                NO_COMMON_MAPPINGS + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +

                "\n" + UNIQUE_MAPPINGS_HEADER + "the australian national university:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                NO_UNIQUE_MAPPINGS + "the australian national university" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +

                "\n" + UNIQUE_MAPPINGS_HEADER + "the university of melbourne:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                NO_UNIQUE_MAPPINGS + "the university of melbourne" + System.lineSeparator() +
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
        mockedStorageData.add(new Course("comp3670", "cs3244", "the australian national university"));
        mockedStorageData.add(new Course("comp30027", "cs3244", "the university of melbourne"));
        mockedStorageData.add(new Course("comp2610", "cs3236", "the australian national university"));
        mockedStorageData.add(new Course("comp90007", "cs2105", "the university of melbourne"));

        command.execute("compare pu/The Australian National University pu/The University of Melbourne");

        String expectedOutput =
                COMPARISON_RESULTS_HEADER + "the australian national university and the university of melbourne:"
                + System.lineSeparator() +

                "\n" + COMMON_MAPPINGS_HEADER  + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "cs3244 | the australian national university | comp3670" + System.lineSeparator() +
                "cs3244 | the university of melbourne | comp30027" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +

                "\n" + UNIQUE_MAPPINGS_HEADER + "the australian national university:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "cs3236 | the australian national university | comp2610" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +

                "\n" + UNIQUE_MAPPINGS_HEADER + "the university of melbourne:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "cs2105 | the university of melbourne | comp90007" + System.lineSeparator() +
                LINE_SEPARATOR;

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

}
