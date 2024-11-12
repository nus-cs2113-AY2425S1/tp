package seedu.budgetbuddy.validators.income;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.income.DeleteIncomeCommand;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.validators.IndexValidator;

/**
 * Validates the command for deleting an income.
 */
public class DeleteIncomeValidator {

    /**
     * Processes the delete income command and returns the appropriate command object.
     *
     * @param command The command string entered by the user.
     * @return A Command object representing the delete income action or an IncorrectCommand if the index is invalid.
     */
    public static Command processCommand(String command) {
        String trimmedCommand;

        try {
            trimmedCommand = command.substring("delete income ".length());
        } catch (IndexOutOfBoundsException e) {
            return new IncorrectCommand("Index not given");
        }

        int index = IndexValidator.validateIncomeIndex(trimmedCommand);

        if (index == -1) {
            return new IncorrectCommand("Invalid Index");
        }

        return new DeleteIncomeCommand(index - 1);
    }
}
