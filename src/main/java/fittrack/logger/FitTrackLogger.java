package fittrack.logger;

import java.io.IOException;
import java.util.logging.*;

public class FitTrackLogger {
    public final static Logger LOGGER = Logger.getLogger(FitTrackLogger.class.getName());
    public static void setupLogger() {
        // LEVELS: OFF, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, ALL

        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        LOGGER.addHandler(consoleHandler);

        try {
            FileHandler fileHandler = new FileHandler("FitTrackLogger.log");
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error: Logger FileHandler not working ", e);
        }
    }
}
