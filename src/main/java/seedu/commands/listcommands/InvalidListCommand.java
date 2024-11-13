package seedu.commands.listcommands;

import seedu.classes.Ui;
import seedu.classes.WiagiLogger;
import seedu.commands.Command;
import seedu.exception.WiagiInvalidInputException;
import seedu.type.IncomeList;
import seedu.type.SpendingList;

import java.util.logging.Level;

public class InvalidListCommand extends Command {

    private final WiagiInvalidInputException exception;

    public InvalidListCommand(WiagiInvalidInputException e) {
        this.exception = e;
    }

    /**
     * Prints the exception message when an invalid list command is executed
     * @param incomes   list of incomes in the application
     * @param spendings list of spendings in the application
     */
    @Override
    public void execute(IncomeList incomes, SpendingList spendings) {
        WiagiLogger.logger.log(Level.WARNING, "Invalid list command", exception);
        Ui.printWithTab(exception.getMessage());
    }
}
