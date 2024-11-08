package seedu.javaninja;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object and ensures that the directory for the file path exists.
     * @param filePath The path to the file that the Storage class will manage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        File directory = file.getParentFile();

        // Check if the directory exists, if not, create it
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Loads quiz results from the file.
     * @return A list of past quiz results loaded from the file.
     * @throws IOException If there is an error reading the file.
     */
    public List<String> loadData() throws IOException {
        List<String> data = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return data;  // Return an empty list if the file doesn't exist
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            data.add(scanner.nextLine());
        }
        scanner.close();
        return data;
    }

    /**
     * Saves the quiz results to the file.
     * @param lines The list of data to save.
     * @param filePath The filepath of which it should save into.
     * @param append Check if it should rewrite data in the file or not
     * @throws IOException If there is an error writing to the file.
     */
    public void saveToFile(String filePath, List<String> lines, boolean append) throws IOException {
        File file = new File(filePath);
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves a single question line to the file.
     * @param questionLine The question line to save.
     * @throws IOException If there is an error writing to the file.
     */
    public void saveQuestionToFile(String questionLine) throws IOException {
        File file = new File(filePath);
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        // Append the question line to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(questionLine);
            writer.newLine();
        }
    }

    /**
     * Clears the file by overwriting it with an empty content.
     * Useful for resetting test files between test runs.
     * @throws IOException If there is an error clearing the file.
     */
    public void clearFile() throws IOException {
        FileWriter writer = new FileWriter(filePath, false); // 'false' to overwrite the file
        writer.write(""); // Clear file content
        writer.close();
    }
}
