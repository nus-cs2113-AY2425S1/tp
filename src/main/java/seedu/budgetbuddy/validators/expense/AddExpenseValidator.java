package seedu.budgetbuddy.validators.expense;

import seedu.budgetbuddy.commands.expense.AddExpenseCommand;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.transaction.Category;

import java.time.LocalDate;

import static seedu.budgetbuddy.validators.AmountValidator.validateAmount;
import static seedu.budgetbuddy.validators.CategoryValidator.validateCategory;
import static seedu.budgetbuddy.validators.DateValidator.validateDate;

public class AddExpenseValidator {

    /**
     * Processes the given command to create a new expense entry.
     *
     * @param command The command string to be processed.
     * @return A Command object representing the add expense command
     *         or an IncorrectCommand if validation fails.
     */
    public static Command processCommand(String command) {
        if (command.equals("add expense")) {
            return new IncorrectCommand("No description provided.");
        }

        String trimmedCommand = command.substring("add expense ".length());
        String[] parts = trimmedCommand.split(" ");

        // Initialize default values
        String description = "";
        double amount = 0; // invalid amount initially
        LocalDate date = LocalDate.now();
        Category category = Category.OTHERS;
        boolean hasAmount = false;

        // Process parts to extract details
        for (String part : parts) {
            String prefix = part.length() >= 2 ? part.substring(0, 2) : "";
            switch (prefix) {

            case "a/":
                amount = validateAmount(part);
                hasAmount = true;
                if (amount <= 0) {
                    return new IncorrectCommand("Amount should be a positive number with up to 2 " +
                            "decimal places.");
                }
                break;

            case "d/":
                date = validateDate(part);
                if (date == null) {
                    return new IncorrectCommand("Invalid date format. Use d/dd/MM/yyyy.");
                }
                break;

            case "c/":
                category = validateCategory(part);
                break;

            default:
                description += part + " ";
            }
        }

        description = description.trim();
        // Validate description
        if (description.isEmpty()) {
            return new IncorrectCommand("Description cannot be empty.");
        }

        // Validate amount
        if (!hasAmount) {
            return new IncorrectCommand("Amount not entered.");
        }

        // All validations passed, return the command
        return new AddExpenseCommand(description, amount, date, category);
    }
}