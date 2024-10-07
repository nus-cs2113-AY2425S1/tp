package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.commands.AddIncomeCommand;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;

import java.time.LocalDate;

import static seedu.budgetbuddy.validators.AmountValidator.validateAmount;
import static seedu.budgetbuddy.validators.DateValidator.validateDate;


/**
 * Validates the command for adding an income.
 */
public class AddIncomeValidator {

    /**
     * Processes the given command to create a new income entry.
     *
     * @param command The command string to be processed.
     * @return A Command object representing the add income command
     *         or an IncorrectCommand if validation fails.
     */
    public static Command processCommand(String command) {
        if (command.equals("add income")) {
            return new IncorrectCommand("No description provided.");
        }

        String trimmedCommand = command.substring("add income ".length());
        String[] parts = trimmedCommand.split(" ");

        // Initialize default values
        String description = "";
        double amount = 0; // invalid amount initially
        LocalDate date = LocalDate.now();

        // Process parts to extract details
        for (String part : parts) {
            if (part.startsWith("a/")) {
                amount = validateAmount(part);
                if (amount == -1) {
                    return new IncorrectCommand("Invalid amount format. Amount should be a positive number.");
                }
            } else if (part.startsWith("d/")) {
                date = validateDate(part);
                if (date == null) {
                    return new IncorrectCommand("Invalid date format. Use d/dd/MM/yyyy.");
                }
            } else {
                description += part + " ";
            }
        }

        description = description.trim();

        // Validate description
        if (description.isEmpty()) {
            return new IncorrectCommand("Description cannot be empty.");
        }

        // Validate amount
        if (amount == 0) {
            return new IncorrectCommand("Amount not entered.");
        } else if (amount < 0) {
            return new IncorrectCommand("Amount must be a positive value.");
        }

        // All validations passed, return the command
        return new AddIncomeCommand(description, amount, date);
    }
}
