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
        case INFO:
            logger.log(Level.INFO, message);
            break;
        case WARNING:
            logger.log(Level.WARNING, message);
            break;
        case SEVERE:
            logger.log(Level.SEVERE, message);
            break;
        default:
            logger.log(Level.INFO, message);
            break;
        }
    }

    public void log(LogLevels logLevel, String message, Exception e) {
        switch (logLevel) {
        case INFO:
            logger.log(Level.INFO, message, e);
            break;
        case WARNING:
            logger.log(Level.WARNING, message, e);
            break;
        case SEVERE:
            logger.log(Level.SEVERE, message, e);
            break;
        default:
            logger.log(Level.INFO, message, e);
            break;
        }
    }
}
