package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static final String SAVE_FILE = "data/saveFile.txt"; // Path to the save file
    public static final File saveFile = new File(SAVE_FILE); // File object for the save file

    /**
     * Initializes the save file by creating the necessary directories and file if they do not exist.
     */
    public static void initialiseSaveFile() {
        Path dirPath = Paths.get("data"); // Path to the data directory
        Path filePath = dirPath.resolve("saveFile.txt"); // Path to the save file
        File file = new File(filePath.toString()); // File object for the save file

        // Create directories if they do not exist
        if (!file.exists()) {
            try {
                Files.createDirectories(dirPath);
            } catch (IOException e) {
                System.out.println("Directory could not be created");
                throw new RuntimeException(e);
            }
        }

        // Create a save file if it does not already exist
        try {
            if (file.createNewFile()) {
                System.out.println("No existing save file found. New save file created: " + file.getName());
            } else {
                System.out.println("Accessing existing save file...");
            }
        } catch (IOException e) {
            System.out.println("Save file could not be created.");
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads the sessions from the save file into the given session list.
     *
     * @param sessionList The list to populate with sessions from the save file.
     * @throws FileNotFoundException If the save file is not found.
     */
    public static void loadSaveFile(ArrayList<TrainingSession> sessionList) throws FileNotFoundException {
        Scanner s = new Scanner(saveFile); // Create a Scanner to read the save file
        while (s.hasNext()) {
            String line = s.nextLine(); // Read each line from the file

            // IMPLEMENT READ SAVE FILE HERE

        }
        System.out.println("Save file successfully loaded.");
    }

    /**
     * Updates the save file with the current list of sessions.
     *
     * @param sessionList The list of sessions to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void updateSaveFile(ArrayList<TrainingSession> sessionList) throws IOException {
        try (FileWriter fw = new FileWriter(saveFile)) {
            for (TrainingSession session : sessionList) {
                fw.write(session.toString()); // Write the session to the file
                fw.write(System.lineSeparator()); // Add a new line after each session
            }
        }
    }
}
