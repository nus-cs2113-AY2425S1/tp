package seedu.budgetbuddy.validators.income;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.income.DisplayIncomeSpentCommand;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;
import seedu.budgetbuddy.transaction.income.IncomeManager;

import java.time.LocalDate;
import java.time.YearMonth;

import static seedu.budgetbuddy.validators.DateValidator.validateYearMonth;

/**
 * Validates and processes the command to display the percentage of income spent for a specified month.
 */
public class DisplayIncomeSpentValidator {

    /**
     * Processes the given command and returns a corresponding Command object.
     *
     * @param command The command string to process.
     * @return Command representing the display income spent command for the specified month.
     * @throws BudgetBuddyException If no income is recorded for the specified month.
     */
    public static Command processCommand(String command) throws BudgetBuddyException {
        YearMonth month = null;

        if (command.equals("display income spent")) {
            month = YearMonth.from(LocalDate.now());
        } else {
            String trimmedCommand = command.substring("display income spent ".length());
            String[] parts = trimmedCommand.split(" ");

            //Process parts to extract details
            for (String part : parts) {
                if (part.startsWith("m/")) {
                    month = validateYearMonth(part);
                    if (month == null) {
                        throw new BudgetBuddyException("Invalid month format. Use m/MM/yyyy.");
                    }
                } else {
                    throw new BudgetBuddyException("Unrecognised input: '" + part + "'. " +
                            "Please check the command format.");
                }
            }
        }

        if (IncomeManager.getMonthlyIncome(month) <= 0) {
            throw new BudgetBuddyException("No income recorded for the month: " + month);
        }

        return new DisplayIncomeSpentCommand(month);
    }
}
