package fittrack.logger;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FitTrackLogger {
    public static final Logger LOGGER = Logger.getLogger(FitTrackLogger.class.getName());
    public static void setupLogger() {
        // LEVELS: OFF, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, ALL

        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        LOGGER.addHandler(consoleHandler);

        try {
            FileHandler fileHandler = new FileHandler("FitTrackLogger.log");
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.FINE);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error: Logger FileHandler not working ", e);
        }

        LOGGER.info("Logger successfully initialized");
    }
}
