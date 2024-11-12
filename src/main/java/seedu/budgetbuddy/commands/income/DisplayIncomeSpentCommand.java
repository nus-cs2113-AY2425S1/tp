package seedu.budgetbuddy.commands.income;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.income.IncomeSpent;

import java.time.YearMonth;

/**
 * Represents a command to display the percentage of income spent for a specified month.
 */
public class DisplayIncomeSpentCommand extends Command {
    private YearMonth month;

    /**
     * Constructs a DisplayIncomeSpentCommand with the specified month.
     *
     * @param month The month for which to display the income spent percentage.
     */
    public DisplayIncomeSpentCommand(YearMonth month) {
        this.month = month;
    }

    /**
     * Retrieves the month associated with this command. Useful for unit testing.
     *
     * @return The specified month of the income spent.
     */
    public YearMonth getMonth() {
        return month;
    }

    /**
     * Checks if the given command is a display income spent command.
     *
     * @param command The command string to check.
     * @return true if the command starts with "display income spent"; false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("display income spent");
    }

    /**
     * Executes the command to display the percentage of income spent for the specified month.
     */
    @Override
    public void execute() {
        IncomeSpent.displaySpentPercentage(month);
    }
}
