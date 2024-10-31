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

    private List<String> mockedStorageData;
    private CompareMappedCommand command;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        mockedStorageData = new ArrayList<>();

        Storage mockStorage = new Storage() {
            @Override
            public List<String> loadAllCourses() {
                return mockedStorageData;
            }

            @Override
            public void addCourse(Course course) {
                mockedStorageData.add(course.getNusCourseCode() + " | " +
                        course.getPartnerUniversity() + " | " +
                        course.getPuCourseCode());
            }
        };

        command = new CompareMappedCommand(mockStorage);
    }

    @Test
    void execute_withValidUniversitiesAndCommonMappings_displaysCorrectOutput() {
        mockedStorageData.add("CS3244 | The Australian National University | COMP3670");
        mockedStorageData.add("CS2102 | The University of Melbourne | INFO20003");
        mockedStorageData.add("CS3244 | The University of Melbourne | COMP30027");

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
        mockedStorageData.add("CS3236 | The Australian National University | COMP2610");
        mockedStorageData.add("CS3244 | The University of Melbourne | COMP30027");

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
        mockedStorageData.add("CS3244 | The Australian National University | COMP3670");
        mockedStorageData.add("CS3244 | The University of Melbourne | COMP30027");
        mockedStorageData.add("CS3236 | The Australian National University | COMP2610");
        mockedStorageData.add("CS2105 | The University of Melbourne | COMP90007");

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
