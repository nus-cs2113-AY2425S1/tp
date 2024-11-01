package seedu.exchangecoursemapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exchangecoursemapper.command.ListPersonalTrackerCommand;
import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.courses.Course;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;

class ListPersonalTrackerCommandTest {

    private List<String> mockedStorageData;
    private ListPersonalTrackerCommand command;
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

        command = new ListPersonalTrackerCommand(mockStorage);
    }

    @Test
    void execute_noModulesInPersonalTracker_displaysNoModulesMessage() {
        command.execute("");
        assertEquals("No modules mapped yet.", outContent.toString().trim());
    }

    @Test
    void execute_withMultipleMappedModules_displaysModulesWithIndexing() {
        mockedStorageData.add("CS2040 | Victoria University of Wellington | COMP103");
        mockedStorageData.add("CS2102 | The University of Melbourne | INFO20003");

        command.execute("");
        String expectedOutput = "Mapped Modules:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "1. CS2040 | Victoria University of Wellington | COMP103" + System.lineSeparator() +
                "2. CS2102 | The University of Melbourne | INFO20003" + System.lineSeparator() +
                LINE_SEPARATOR;


        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    void execute_withOneMappedModule_displaysSingleModuleWithIndexing() {
        mockedStorageData.add("CS2102 | The University of Melbourne | INFO20003");

        command.execute("");
        String expectedOutput = "Mapped Modules:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "1. CS2102 | The University of Melbourne | INFO20003" + System.lineSeparator() +
                LINE_SEPARATOR;

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

}

