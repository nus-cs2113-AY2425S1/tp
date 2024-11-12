package seedu.budgetbuddy.commands.expense;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;

/**
 * Represents a command to delete an expense in the BudgetBuddy application.
 * This command removes an expense at the specified index from the expense list.
 */
public class DeleteExpenseCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteExpenseCommand with the specified index of the expense to be deleted.
     *
     * @param index The index of the expense to delete.
     */
    public DeleteExpenseCommand(int index) {
        this.index = index;
    }

    /**
     * Determines if the given command string matches the delete expense command.
     *
     * @param command The command string entered by the user.
     * @return true if the command is a delete expense command, false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("delete expense");
    }

    /**
     * Executes the delete expense command by removing the expense at the specified index
     * from the expense list.
     */
    @Override
    public void execute(){
        ExpenseManager.deleteExpense(index);
    }
}
