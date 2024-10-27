package seedu.duke.command;

import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.parser.DateParser;

import java.time.LocalDate;

/**
 * Command to add an expense to the financial list.
 */
public abstract class AddEntryCommand extends Command {

    protected double amount;
    protected String description;
    protected LocalDate date;

    /**
     * Constructs an AddIncomeCommand with the specified amount and description.
     *
     * @param amount The amount of the income.
     * @param description The description of the income.
     */
    protected AddEntryCommand(double amount, String description, String date) throws FinanceBuddyException{
        this.amount = amount;
        this.description = description;
        this.date = DateParser.parse(date);
    }

}

