package seedu.budgetbuddy.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerSetup {
    private static final Logger logger = Logger.getLogger(LoggerSetup.class.getName());

    static {
        try {
            //Ensure the Log Directory Exist
            File logDirectory = new File("logs");
            if (!logDirectory.exists()) {
                logDirectory.mkdir();
            }

            // Create a file handler that writes logs messages to logs/app.log
            FileHandler fileHandler = new FileHandler("logs/app.log", true);
            fileHandler.setFormatter(new SimpleFormatter()); // Use a simple text format for the logs

            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);

        } catch (IOException e) {
            logger.severe("Failed to initialize log file handler: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
