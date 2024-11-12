package seedu.budgetbuddy.validators.income;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.income.EditIncomeCommand;
import seedu.budgetbuddy.transaction.income.Income;
import seedu.budgetbuddy.transaction.income.IncomeManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.LocalDate;
import java.util.logging.Logger;

import static seedu.budgetbuddy.validators.AmountValidator.validateAmount;
import static seedu.budgetbuddy.validators.DateValidator.validateDate;

public class EditIncomeValidator {

    private static final Logger LOGGER = LoggerSetup.getLogger();

    /**
     * Processes the command string to determine if it is valid income index.
     * If valid, it creates an EditIncomeCommand object which will be used for subsequent execution.
     * otherwise, it returns an IncorrectCommand object with the error.
     *
     * @param command first input given by user
     * @return command object
     */
    public static Command processFirstCommand(String command) {
        if (command.equals("edit income")) {
            return new IncorrectCommand("No index detected, try again with an index.");
        }
        if (command.startsWith("edit incomes")) {
            return new IncorrectCommand("Invalid input");
        }
        try {
            String trimmedCommand = command.substring("edit income ".length());
            String[] parts = trimmedCommand.split(" ");
            int editIndex = Integer.parseInt(parts[0]);
            if (editIndex < 0) {
                return new IncorrectCommand("Edit index must be greater than 0.");
            }
            Income income = IncomeManager.getIncomeByIndex(editIndex);
            if (income == null) {
                return new IncorrectCommand("Input index is larger than the number of incomes. " +
                        "Try with a smaller index");
            } else {
                return new EditIncomeCommand(income);
            }
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Index must be a valid number larger than 0.");
        }

    }

    /**
     * Processes the command string to determine if it is valid for editing.
     * If valid, it updates the fields to be changed and returns a true for validity of command.
     * otherwise, it will display error message and return a false validity of command.
     *
     * @param command Input given by user
     * @return Validity of command {@code Boolean}
     */
    public static boolean processSecondCommand(String command) {

        String[] parts = command.split(" ");

        //Process Initial Value
        LocalDate date = null;
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
            } else if (part.startsWith("a/")) {
                amount = validateAmount(part);
                if (amount < 0.0) {
                    Ui.displayToUser("Invalid amount format. Amount should be a positive number.");
                    return false;
                }
            }
        }

        if (date == null && amount == -1.0) {
            Ui.displayToUser("No valid fields detected, Exiting change menu.");
            return false;
        }

        //set Fields to be changed
        EditIncomeCommand.setDate(date);
        EditIncomeCommand.setAmount(amount);

        return true;
    }
}
