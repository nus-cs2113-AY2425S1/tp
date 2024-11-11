package seedu.exchangecoursemapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import seedu.exchangecoursemapper.courses.Course;
import seedu.exchangecoursemapper.storage.DataIntegrityChecker;
import seedu.exchangecoursemapper.storage.Storage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DataIntegrityCheckerTest {

    private static final String TEST_FILE_PATH = "./data/test_myList.json";
    private DataIntegrityChecker dataIntegrityChecker;
    private Storage storage;

    @BeforeEach
    void setUp() {
        // Initialize the storage with a temporary test file
        storage = new Storage(TEST_FILE_PATH);
        dataIntegrityChecker = new DataIntegrityChecker();
    }

    @AfterEach
    void tearDown() throws Exception {
        // Delete the temporary test file after each test
        Files.deleteIfExists(Path.of(TEST_FILE_PATH));
    }

    @Test
    void checkForDuplicateCourses_noDuplicates_returnsFalse() {
        // Arrange
        Course course1 = new Course("comp30027", "cs3244", "the university of melbourne");
        Course course2 = new Course("comp3670", "cs3244", "the australian national university");
        storage.addCourse(course1);
        storage.addCourse(course2);

        List<Course> courses = storage.loadAllCourses();
        assertEquals(2, courses.size());

        // Act
        boolean hasDuplicates = dataIntegrityChecker.checkForDuplicateCourses(courses, storage);

        // Assert
        assertFalse(hasDuplicates);
    }

    @Test
    void checkForDuplicateCourses_withDuplicates_returnsTrueAndRemovesDuplicates() {
        // Arrange
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("comp30027", "cs3244", "the university of melbourne"));
        courses.add(new Course("comp30027", "cs3244", "the university of melbourne"));
        courses.add(new Course("comp3670", "cs3244", "the australian national university"));

        // Act
        boolean hasDuplicates = dataIntegrityChecker.checkForDuplicateCourses(courses, storage);

        // Assert
        assertTrue(hasDuplicates);
        assertEquals(2, storage.loadAllCourses().size()); // Should have removed duplicates
    }

    @Test
    void validateFileIntegrity_validEntries_returnsTrue() {
        // Arrange
        List<String> entries = List.of(
                "cs3244 | the university of melbourne | comp30027",
                "cs3244 | the australian national university | comp3670"
        );

        // Act
        boolean isValid = dataIntegrityChecker.validateFileIntegrity(entries);

        // Assert
        assertTrue(isValid);
    }

    @Test
    void validateFileIntegrity_invalidPUCourse_returnsFalse() {
        // Arrange
        List<String> entries = List.of("cs3244 | the australian national university | INVALID");

        // Act
        boolean result = dataIntegrityChecker.validateFileIntegrity(entries);

        // Assert
        assertFalse(result);
    }

    @Test
    void validateFileIntegrity_invalidPU_returnsFalse() {
        // Arrange
        List<String> entries = List.of("cs3244 | INVALID | comp3670");

        // Act
        boolean result = dataIntegrityChecker.validateFileIntegrity(entries);

        // Assert
        assertFalse(result);
    }

    @Test
    void validateFileIntegrity_invalidCourseCode_returnsFalse() {
        // Arrange
        List<String> entries = List.of("INVALID | the australian national university | comp3670");

        // Act
        boolean result = dataIntegrityChecker.validateFileIntegrity(entries);

        // Assert
        assertFalse(result);
    }


}