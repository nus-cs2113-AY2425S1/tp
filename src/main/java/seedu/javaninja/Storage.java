package seedu.javaninja;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * The `Storage` class handles the saving and loading of data from files.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a `Storage` object and ensures the directory for the file path exists.
     * If the file is empty or missing, it initializes the file with default data from a resource.
     *
     * @param filePath The path to the file that the `Storage` class will manage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        File directory = file.getParentFile();

        // Ensure the directory exists, or create it if it doesn't
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        // Create the file if it does not exist and initialize with default data if needed
        try {
            if (!file.exists() || file.length() == 0) {
                if (file.createNewFile()) {
                    initializeWithDefaultData();
                }
            }
        } catch (IOException e) {
            System.err.println("Error creating or initializing file: " + e.getMessage());
        }
    }

    /**
     * Loads data from the file.
     * @return A list of strings containing the data loaded from the file.
     */
    public List<String> loadData() {
        List<String> data = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return data;  // Return an empty list if the file doesn't exist
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing data. File might be corrupted.");
        }
        return data;
    }

    /**
     * Saves a list of strings to the file.
     * @param lines The list of data to save.
     * @param append Whether to append to the file or overwrite it.
     */
    public void saveToFile(List<String> lines, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Saves a single question line to the file by appending it.
     * @param questionLine The question line to save.
     */
    public void saveQuestionToFile(String questionLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(questionLine);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing question to file: " + e.getMessage());
        }
    }

    /**
     * Clears the content of the file.
     */
    public void clearFile() {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write(""); // Clear file content
        } catch (IOException e) {
            System.err.println("Error clearing file: " + e.getMessage());
        }
    }

    /**
     * Initializes the file with default data from a bundled resource (e.g., `Questions.txt`).
     */
    private void initializeWithDefaultData() {
        try (InputStream defaultData = getClass().getResourceAsStream("/Questions.txt");
             OutputStream outputStream = new FileOutputStream(filePath)) {
            if (defaultData != null) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = defaultData.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } else {
                System.err.println("Default Questions.txt not found in resources.");
            }
        } catch (IOException e) {
            System.err.println("Error initializing with default data: " + e.getMessage());
        }
    }
}
