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

    private List<Course> mockedStorageData;
    private ListPersonalTrackerCommand command;
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

        command = new ListPersonalTrackerCommand(mockStorage);
    }

    @Test
    void execute_noModulesInPersonalTracker_displaysNoModulesMessage() {
        command.execute("");
        assertEquals("No modules mapped yet or you may have " +
                "changed the file/directory name." +
                "Please start adding courses and check that the " +
                "file/directory has not been changed.", outContent.toString().trim());
    }

    @Test
    void execute_withMultipleMappedModules_displaysModulesWithIndexing() {
        mockedStorageData.add(new Course("COMP103", "CS2040", "Victoria University of Wellington"));
        mockedStorageData.add(new Course("INFO20003", "CS2102", "The University of Melbourne"));

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
        mockedStorageData.add(new Course("INFO20003", "CS2102", "The University of Melbourne"));

        command.execute("");
        String expectedOutput = "Mapped Modules:" + System.lineSeparator() +
                LINE_SEPARATOR + System.lineSeparator() +
                "1. CS2102 | The University of Melbourne | INFO20003" + System.lineSeparator() +
                LINE_SEPARATOR;

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    void execute_illegalArgumentExceptionThrown_logsWarningAndPrintsMessage() {
        // Create a custom Storage class that throws an exception
        Storage mockStorage = new Storage() {
            @Override
            public List<Course> loadAllCourses() {
                throw new IllegalArgumentException("Test exception for logging");
            }
        };

        command = new ListPersonalTrackerCommand(mockStorage);

        // Execute the command to trigger the exception
        command.execute("");

        // Verify output contains the exception message
        assertEquals("Test exception for logging", outContent.toString().trim());
    }
}

