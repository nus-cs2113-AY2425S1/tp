package seedu.duke.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
    private final Logger logger;

    private Log() {
        logger = Logger.getLogger("FinanceBuddy Log");
    }

    private static class LogHelper {
        private static final Log INSTANCE = new Log();
    }

    public static Log getInstance() {
        return LogHelper.INSTANCE;
    }

    public void log(LogLevels logLevel, String message) {
        switch (logLevel) {
        case WARNING:
            logger.log(Level.WARNING, message);
            break;
        case ERROR:
            logger.log(Level.SEVERE, message);
            break;
        default:
            logger.log(Level.INFO, message);
            break;
        }
    }
}