package seedu.budgetbuddy.validators.expense;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.expense.EditExpenseCommand;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.LocalDate;
import java.util.logging.Logger;

import static seedu.budgetbuddy.validators.AmountValidator.validateAmount;
import static seedu.budgetbuddy.validators.CategoryValidator.validateSearchCategory;
import static seedu.budgetbuddy.validators.DateValidator.validateDate;

public class EditExpenseValidator {

    private static final Logger LOGGER = LoggerSetup.getLogger();

    /**
     * Processes the command string to determine if it is valid expense index.
     * If valid, it creates an EditExpenseCommand object which will be used for subsequent execution.
     * otherwise, it returns an IncorrectCommand object with the error.
     *
     * @param command first input given by user
     * @return command object
     */
    public static Command processFirstCommand(String command) {
        if (command.equals("edit expense")) {
            return new IncorrectCommand("No index detected, try again with an index.");
        }
        if (command.startsWith("edit expenses")) {
            return new IncorrectCommand("Invalid input");
        }
        try {
            String trimmedCommand = command.substring("edit expense ".length());
            String[] parts = trimmedCommand.split(" ");
            int editIndex = Integer.parseInt(parts[0]);
            if (editIndex < 0) {
                return new IncorrectCommand("Edit index must be greater than 0.");
            }
            Expense expense = ExpenseManager.getExpenseByIndex(editIndex);
            if (expense == null) {
                return new IncorrectCommand("Input index is larger than the number of expenses. " +
                        "Try with a smaller index");
            } else {
                return new EditExpenseCommand(expense);
            }
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Index must be a valid number larger than 0.");
        }

    }

    /**
     * Processes the command string to determine if it is valid for editing.
     * If valid, it updates the fields to be changed and returns a true for validity of command.
     * otherwise, it will display error message and return a false validity of command.
     * For any invalid category input, it will be set to others by default.
     *
     * @param command Input given by user
     * @return Validity of command {@code Boolean}
     */
    public static boolean processSecondCommand(String command){

        String[] parts = command.split(" ");

        //Process Initial Value
        LocalDate date = null;
        Category category = null;
        double amount = -1.0;

        //Process parts to extract details
        for (String part : parts) {
            if (part.startsWith("d/")) {
                date = validateDate(part);
                if (date == null) {
                    LOGGER.warning("Invalid date format. Date found: " + part);
                    Ui.displayToUser("Invalid month format. Use d/dd/mm/yyyy.");
                    return false;
                }
            } else if (part.startsWith("c/")) {
                category = validateSearchCategory(part);
                if (category == null) {
                    LOGGER.warning("Invalid category format. Category found: " + part);
                    Ui.displayToUser("Unknown category detected. Try again with a valid category.");
                    return false;
                }
            } else if (part.startsWith("a/")) {
                amount = validateAmount(part);
                if (amount <= 0.0) {
                    Ui.displayToUser("Amount should be a positive number with up to 2 decimal places.");
                    return false;
                }
            }
        }

        //set Fields to be changed
        EditExpenseCommand.setDate(date);
        EditExpenseCommand.setCategory(category);
        EditExpenseCommand.setAmount(amount);

        return true;
    }
}
