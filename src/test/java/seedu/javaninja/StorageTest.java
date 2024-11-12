package seedu.javaninja;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.javaninja.Storage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {

    private static final String TEST_FILE_PATH = "./data/testResults.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() throws IOException {
        storage = new Storage(TEST_FILE_PATH);
        storage.clearFile();  // Clear any previous data in the test file
    }

    // === Unit Tests ===

    @Test
    public void loadData_withNoData_returnsEmptyList() {
        // Verify that loading from an empty file returns an empty list
        List<String> data = storage.loadData();
        assertTrue(data.isEmpty(), "Expected empty list, but got data");
    }

    @Test
    public void saveToFile_withNewData_overwritesFile() {
        List<String> data = Arrays.asList("Line 1", "Line 2");
        storage.saveToFile(data, false);  // Corrected method call

        List<String> loadedData = storage.loadData();
        assertEquals(data, loadedData, "Data saved to file does not match expected data");
    }

    // === Integration Tests ===

    @Test
    public void saveToFile_withAppendTrue_appendsDataToFile() {
        List<String> initialData = Arrays.asList("Line 1", "Line 2");
        storage.saveToFile(initialData, false);  // Corrected method call

        List<String> additionalData = Arrays.asList("Line 3", "Line 4");
        storage.saveToFile(additionalData, true);  // Corrected method call

        List<String> expectedData = Arrays.asList("Line 1", "Line 2", "Line 3", "Line 4");
        List<String> loadedData = storage.loadData();
        assertEquals(expectedData, loadedData, "Appended data does not match expected data");
    }

    @Test
    public void saveQuestionToFile_appendsQuestionLine() throws IOException {
        String questionLine = "Flashcard | Java | What is Java? | A programming language";
        storage.saveQuestionToFile(questionLine);

        List<String> data = storage.loadData();
        assertEquals(1, data.size(), "Expected 1 line, but got different count");
        assertEquals(questionLine, data.get(0), "Question line saved does not match expected line");
    }

    @Test
    public void clearFile_withExistingData_removesAllData() {
        // Write data to the file first
        List<String> data = Arrays.asList("Some content", "Another line");
        storage.saveToFile(data, false);  // Corrected method call

        // Clear the file
        storage.clearFile();

        // Check that the file is empty
        List<String> loadedData = storage.loadData();
        assertTrue(loadedData.isEmpty(), "File should be empty after clearing, but data is present");
    }
}
