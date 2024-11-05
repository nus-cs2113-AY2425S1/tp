package fittrack.storage;

import fittrack.exception.InvalidSaveDataException;
import fittrack.fitnessgoal.Goal;
import fittrack.reminder.Reminder;
import fittrack.trainingsession.TrainingSession;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static fittrack.logger.FitTrackLogger.LOGGER;

public class Storage {

    public static final String SAVE_FILE = "data/saveFile.txt"; // Path to the save file
    public static final File SAVEFILE = new File(SAVE_FILE); // File object for the save file

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
                System.out.println("SaveFile directory could not be created");
                LOGGER.info("SaveFile directory could not be created");
                throw new RuntimeException(e);
            }
        }

        // Create a save file if it does not already exist
        try {
            if (file.createNewFile()) {
                System.out.println("No existing save file found. New save file created: " + file.getName());
                LOGGER.info("No existing save file found. New save file created: " + file.getName());
            } else {
                System.out.println("Accessing existing save file...");
                LOGGER.info("Accessing existing save file...");
            }
        } catch (IOException e) {
            System.out.println("Save file could not be created.");
            LOGGER.info("Save file could not be created.");
            throw new RuntimeException(e);
        }

        // Assert that the file exists after initialization
        assert file.exists() : "Save file should exist after initialization";
    }

    /**
     * Loads Saveable items from the save file into a list.
     *
     * @param loadList  A list of Saveable items loaded from the save file.
     */
    public static void loadSaveFile(ArrayList<Saveable> loadList) {
        try (Scanner scanner = new Scanner(SAVEFILE)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Saveable item = createSaveableFromString(line);
                loadList.add(item);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Scanner object failed to initialise.");
            LOGGER.info("Scanner object failed to initialise.");
            return;
        } catch (InvalidSaveDataException e) {
            System.out.println("Invalid save data found. Data loading terminated.");
            LOGGER.info("Invalid save data found. Data loading terminated.");
            return;
        }

        System.out.println("Save file successfully loaded.");
        LOGGER.info("Save file successfully loaded.");
    }

    // Factory method to create Saveable objects based on type
    private static Saveable createSaveableFromString(String saveString) throws InvalidSaveDataException {
        if (saveString.startsWith("TrainingSession")) {
            return TrainingSession.fromSaveString(saveString);
        } else if (saveString.startsWith("Goal")) {
            return Goal.fromSaveString(saveString);
        } else if (saveString.startsWith("Reminder")) {
            return Reminder.fromSaveString(saveString);
        }
        throw new InvalidSaveDataException("Unrecognised Saveable descriptor detected.");
    }

    /**
     * Updates the save file with the current list of sessions.
     *
     * @param sessionList The list of sessions to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void updateSaveFile(ArrayList<TrainingSession> sessionList, ArrayList<Goal> goalList,
                                      ArrayList<Reminder> reminderList ) throws IOException {

        // Determine  provided lists are not null before saving
        if (sessionList == null || goalList == null || reminderList == null) {
            throw new IOException("Save file could not be updated. Invalid null list passed to function");
        }

        try (FileWriter fw = new FileWriter(SAVEFILE)) {
            for (TrainingSession session : sessionList) {
                // Assert that session objects are valid
                assert session != null : "Training session must not be null";
                fw.write(session.toSaveString()); // Write the session to the file
                fw.write(System.lineSeparator()); // Add a new line after each session
                LOGGER.info("Save file successfully updated.");
            }
            for (Goal goal : goalList) {
                // Assert that session objects are valid
                assert goal != null : "Training session must not be null";
                fw.write(goal.toSaveString()); // Write the session to the file
                fw.write(System.lineSeparator()); // Add a new line after each session
                LOGGER.info("Save file successfully updated.");
            }
            for (Reminder reminder : reminderList) {
                // Assert that session objects are valid
                assert reminder != null : "Training session must not be null";
                fw.write(reminder.toSaveString()); // Write the session to the file
                fw.write(System.lineSeparator()); // Add a new line after each session
                LOGGER.info("Save file successfully updated with.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Save file could not be updated (File could not be found).");
        }
    }
}
