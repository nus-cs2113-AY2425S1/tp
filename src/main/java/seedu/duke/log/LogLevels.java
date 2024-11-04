package seedu.duke.log;

/**
 * The LogLevels enum defines the levels of logging that can be used
 * with the Log class to specify the severity of log messages.
 *
 * #INFO: Represents informational messages that highlight
 *      the progress of the application.
 * #WARNING: Denotes potentially harmful situations that are not errors
 *      but could lead to problems, often due to user.
 * #SEVERE: Indicates a serious failure that might prevent part of the application
 *      from functioning properly.
 *
 */

public enum LogLevels {
    INFO, WARNING, SEVERE;
}
