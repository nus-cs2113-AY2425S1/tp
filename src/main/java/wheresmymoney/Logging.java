package wheresmymoney;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logging {
    private static Logging loggingInstance = null;

    private final Logger logger;
    private final String LOGGER_NAME = "WheresMyMoney";
    private final String LOG_FILE_PATH = "./MyLogFile.log";

    private Logging(){
        logger = Logger.getLogger(LOGGER_NAME);
        disableConsoleLogging();
        try {
            enableFileLogging();
        } catch (SecurityException | IOException e) {
            Ui.displayMessage("Logging not working");
        }
        logger.info("Started Logging");
    }

    public void enableFileLogging() throws IOException {
        // This block configure the logger with handler and formatter
        FileHandler fh = new FileHandler(LOG_FILE_PATH, true);
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }

    public void disableConsoleLogging() {
        LogManager.getLogManager().reset();
    }

    public void logMessage(Level level, String message) {
        logger.log(level, message);
    }

    public static Logging getInstance() {
        if (Logging.loggingInstance == null) {
            Logging.loggingInstance = new Logging();
        }
        return Logging.loggingInstance;
    }

    public static void log(Level level, String message){
        Logging.getInstance().logMessage(level, message);
    }
}
