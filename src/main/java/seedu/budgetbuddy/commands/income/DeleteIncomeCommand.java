package seedu.budgetbuddy.commands.income;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.income.IncomeManager;

/**
 * Represents a command to delete an income in the BudgetBuddy application.
 * This command removes an income at the specified index from the income list.
 */
public class DeleteIncomeCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteIncomeCommand with the specified index of the income to be deleted.
     *
     * @param index The index of the income to delete.
     */
    public DeleteIncomeCommand(int index) {
        this.index = index;
    }

    /**
     * Determines if the given command string matches the delete income command.
     *
     * @param command The command string entered by the user.
     * @return true if the command is a delete income command, false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("delete income");
    }

    /**
     * Executes the delete income command by removing the income at the specified index
     * from the income list.
     */
    @Override
    public void execute(){
        IncomeManager.deleteIncome(index);
    }

    /**
     * Returns the index of income to delete.
     */
    public int getIndex() {
        return index;
    }
}
