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
     * Saves a list of Saveable items to the save file.
     *
     * @param items The list of Saveable items to save.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void saveToFile(ArrayList<Saveable> saveableItems) throws IOException {
        try (FileWriter fw = new FileWriter(SAVEFILE)) {
            for (Saveable item : saveableItems) {
                fw.write(item.toSaveString()); // Write the Saveable item's serialized string
                fw.write(System.lineSeparator()); // Add a new line after each serialized string
            }
            LOGGER.info("Save file successfully updated.");
        } catch (FileNotFoundException e) {
            System.out.println("FileWriter object failed to initialise.");
        }

        LOGGER.info("Save file successfully updated.");
    }

    /**
     * Loads Saveable items from the save file into a list.
     *
     * @return A list of Saveable items loaded from the save file.
     * @throws FileNotFoundException If the save file is not found.
     */
    public static void loadFromFile(ArrayList<Saveable> loadList) {
        try (Scanner scanner = new Scanner(SAVEFILE)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Saveable item = createSaveableFromString(line);
                if (item != null) {
                    loadList.add(item);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Scanner object failed to initialise.");
        } catch (InvalidSaveDataException e) {
            System.out.println("Invalid save data.");
        }

        System.out.println("Save file successfully loaded.");
        LOGGER.info("Save file successfully loaded.");
    }

    // Factory method to create Saveable objects based on type
    private static Saveable createSaveableFromString(String saveString) {
        if (saveString.startsWith("TrainingSession")) {
            return TrainingSession.fromSaveString(saveString);
        } else if (saveString.startsWith("Goal")) {
            return Goal.fromSaveString(saveString);
        } else if (saveString.startsWith("Reminder")) {
            return Reminder.fromSaveString(saveString);
        }
        return null;
    }


    /**
     * Updates the save file with the current list of sessions.
     *
     * @param sessionList The list of sessions to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void updateSaveFile(ArrayList<TrainingSession> sessionList) throws IOException {
        // Assert that the session list is not null before saving
        assert sessionList != null : "Session list must not be null";

        try (FileWriter fw = new FileWriter(SAVEFILE)) {
            for (TrainingSession session : sessionList) {
                // Assert that session objects are valid
                assert session != null : "Training session must not be null";
                fw.write(session.toString()); // Write the session to the file
                fw.write(System.lineSeparator()); // Add a new line after each session
                LOGGER.info("Save file successfully updated.");
            }
        }
        // Assert that the file has been written to successfully
        assert SAVEFILE.length() > 0 : "Save file should not be empty after update";
    }

    public static void updateGoalsFile(ArrayList<Goal> goals) throws IOException {
        try (FileWriter fw = new FileWriter(SAVEFILE, true)) {
            fw.write("Goals:\n");
            for (Goal goal : goals) {
                fw.write(goal.toString() + "\n");
            }
        }
    }

    public static void updateRemindersFile(ArrayList<Reminder> reminderList) throws IOException {
        try (FileWriter fw = new FileWriter(SAVEFILE, true)) {
            fw.write("Reminders:\n");
            for (Reminder reminder : reminderList) {
                fw.write(reminder.toString() + "\n");
            }
        }
        catch (IOException e) {
            // TODO:
        }
    }



}
