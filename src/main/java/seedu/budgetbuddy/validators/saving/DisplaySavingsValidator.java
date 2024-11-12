package seedu.budgetbuddy.validators.saving;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.saving.DisplaySavingsCommand;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;
import seedu.budgetbuddy.util.LoggerSetup;

import java.util.logging.Logger;

/**
 * Validator class for validating user input and the "display savings" command.
 */
public class DisplaySavingsValidator {
    private static final Logger LOGGER = LoggerSetup.getLogger();

    /**
     * Validates command input by user to see if string input has a keyword provided as a descriptor
     * @param command Command input by user.
     * @return new DisplaySavingsCommand object.
     * @throws BudgetBuddyException if user input is not "display savings" or "display savings m/"
     */
    public static Command processCommand(String command) throws BudgetBuddyException{
        assert command != null: "Command cannot be null";
        if (command.equals("display savings m/")){
            return new DisplaySavingsCommand(true);
        } else if (command.equals("display savings")){
            return new DisplaySavingsCommand(false);
        }
        LOGGER.info("Invalid display savings message by user.");
        throw new BudgetBuddyException("Key in display savings m/ or display savings");
    }
}
