package seedu.duke.data.logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * Configures global logging for the MediTask application.
 * This setup logs all messages to a file while restricting console output
 * to SEVERE level messages only.
 */
public class LoggerConfig {

    public static void configureGlobalLogging() {
        Logger rootLogger = Logger.getLogger(""); // Root logger

        // Remove all existing handlers to avoid duplicate logging
        for (var handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }

        try {
            // Set up log file path relative to the program's location
            Path logFilePath = Paths.get("logs", "meditask.log").toAbsolutePath();
            logFilePath.getParent().toFile().mkdirs(); // Ensure directories exist

            // Set up a FileHandler to log all messages to the file
            FileHandler fileHandler = new FileHandler(logFilePath.toString(), true); // Append mode
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL); // Log all levels to the file
            rootLogger.addHandler(fileHandler);

            // Set up a ConsoleHandler to log only SEVERE messages to the console
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.SEVERE); // Only SEVERE messages appear in console
            rootLogger.addHandler(consoleHandler);

            // Set the global log level to the lowest level to ensure all messages are processed
            rootLogger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println("Failed to set up global logging to file: " + e.getMessage());
        }
    }
}
