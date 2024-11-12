package seedu.duke.log;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Log class is a Singleton logger utility for logging messages
 * and exceptions at different levels. The logger handles three levels of
 * logging defined by LogLevels: INFO, WARNING, and SEVERE.
 * This class ensures that a single logging instance is used throughout the
 * application, which can be accessed by getInstance() method.
 * Each method allows for logging messages with optional exception tracing.
 */
public class Log {
    private final Logger logger;

    /**
     * Private constructor initializes the logger instance and sets appropriate logging level.
     */
    private Log() {
        logger = Logger.getLogger("FinanceBuddy Log");
        if (isRunningFromJar()) {
            logger.setLevel(Level.OFF); // Disable logging when running from JAR file
        } else {
            logger.setLevel(Level.INFO); // Default logging level during development/testing
        }
    }

    /**
     * Determines if the application is running from a JAR file.
     *
     * @return true if running from JAR, false otherwise
     */
    private boolean isRunningFromJar() {
        try {
            URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
            // Check if the path ends with .jar
            if (location != null) {
                File file = new File(location.toURI());
                return file.isFile() && file.getName().endsWith(".jar");
            }
        } catch (URISyntaxException e) {
            logger.log(Level.WARNING, "Failed to determine environment running from.", e);
        }
        return false;
    }

    /**
     * Holder class for Log singleton instance.
     */
    private static class LogHelper {
        private static final Log INSTANCE = new Log();
    }

    /**
     * Provides the Singleton instance of the Log class.
     *
     * @return the sole instance of the Log class
     */
    public static Log getInstance() {
        return LogHelper.INSTANCE;
    }

    /**
     * Logs a message at the specified log level.
     *
     * @param logLevel The level of logging, as defined by LogLevels
     * @param message The message string to log
     */
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

    /**
     * Logs a message along with an exception at the specified log level.
     *
     * @param logLevel The level of logging, as defined by LogLevels
     * @param message The message string to log
     * @param e The exception to log, providing context with the message
     */
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
