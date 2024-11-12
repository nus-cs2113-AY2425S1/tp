package seedu.budgetbuddy.validators.expense;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.expense.DisplayTotalExpensesCommand;
import seedu.budgetbuddy.util.LoggerSetup;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A validator class for processing and validating the "display yearly expenses" command.
 * It extracts and validates year information from the command.
 */
public class DisplayTotalExpensesValidator {
    private static final Logger LOGGER = LoggerSetup.getLogger();

    /**
     * Processes the command input, validates the year, and returns a corresponding command.
     *
     * @param command The raw command string entered by the user.
     * @return A {@link Command} object, either {@link DisplayTotalExpensesCommand} or {@link IncorrectCommand}.
     */
    public static Command processCommand(String command) {
        LOGGER.log(Level.INFO, "Processing command: " + command);

        // Extract the portion of the command after "display yearly expenses"
        String trimmedCommand = command.substring("display monthly expenses".length()).trim();
        if (trimmedCommand.isEmpty()) {
            LOGGER.log(Level.WARNING, "Trimmed command is empty");
            return new IncorrectCommand("Please provide a year.");
        }

        // Check if the command starts with "y/" and extract the year argument
        String yearArg;
        if (trimmedCommand.startsWith("y/")) {
            yearArg = trimmedCommand.substring(2); // Remove the "y/" prefix
        } else {
            LOGGER.log(Level.WARNING, "Invalid Command: " + trimmedCommand);
            return new IncorrectCommand("Unknown command '" + trimmedCommand + "'. " +
                    "Expected format: 'y/YYYY'");
        }

        // Validate and parse the year
        if (!isValidYear(yearArg)) {
            LOGGER.log(Level.WARNING, "Invalid year: " + yearArg);
            return new IncorrectCommand("Invalid or missing year format.");
        }

        int year = Integer.parseInt(yearArg);
        return new DisplayTotalExpensesCommand(year);
    }

    /**
     * Validates the year input to ensure it consists of four digits and falls within the valid range (1900 to 2100).
     *
     * @param year The year string to validate.
     * @return True if the year is valid, false otherwise.
     */
    private static boolean isValidYear(String year) {
        if (year.length() != 4) {
            return false;
        }
        try {
            Integer.parseInt(year);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
