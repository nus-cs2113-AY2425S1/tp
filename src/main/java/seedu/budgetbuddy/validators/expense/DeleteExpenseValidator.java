package seedu.budgetbuddy.validators.expense;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.expense.DeleteExpenseCommand;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.validators.IndexValidator;

/**
 * Validates the command for deleting an expense.
 */
public class DeleteExpenseValidator {

    /**
     * Processes the delete expense command and returns the appropriate command object.
     *
     * @param command The command string entered by the user.
     * @return A Command object representing the delete expense action or an IncorrectCommand if the index is invalid.
     */
    public static Command processCommand(String command) {
        String trimmedCommand;

        try {
            trimmedCommand = command.substring("delete expense ".length());
        } catch (IndexOutOfBoundsException e) {
            return new IncorrectCommand("Index not given");
        }

        int index = IndexValidator.validateExpenseIndex(trimmedCommand);

        if (index == -1) {
            return new IncorrectCommand("Invalid Index");
        }

        return new DeleteExpenseCommand(index - 1);
    }
}
