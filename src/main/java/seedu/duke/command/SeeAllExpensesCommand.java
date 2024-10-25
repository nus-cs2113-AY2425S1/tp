package seedu.duke.command;

import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.Expense;

import java.time.LocalDate;

/**
 * The SeeAllExpensesCommand class is responsible for displaying all recorded expenses
 * from the provided FinancialList. It extends the Command class and overrides the 
 * execute method to perform this functionality.
 */
public class SeeAllExpensesCommand extends SeeAllEntriesCommand{
    protected final String ENTRIES_LISTED_MESSAGE = "Here's a list of all recorded expenses:";
    protected final String NO_ENTRIES_MESSAGE = "No expenses found.";

    /**
     * Constructor for SeeAllExpensesCommand.
     *
     * @param start Start date of all entries to be listed, null if no start date to be specified.
     * @param end End date of all entries to be listed, null if no end date to be specified.
     */
    public SeeAllExpensesCommand(LocalDate start, LocalDate end) {
        super(start, end);
    }

    /**
     * Method to return message when no entries are to be listed as a String.
     *
     * @return Message when no entries are to be listed.
     */
    @Override
    protected String getNoEntriesMessage() {
        return this.NO_ENTRIES_MESSAGE;
    }

    /**
     * Method to return header when there are entries to be listed as a String.
     *
     * @return Header when there are entries to be listed.
     */
    @Override
    protected String getEntriesListedMessage() {
        return this.ENTRIES_LISTED_MESSAGE;
    }
    /**
     * Method to determine if an entry should be listed out based on its date and if it is an expense.
     *
     * @param entry Financial Entry to analyze.
     * @return true if entry should be listed out, false otherwise.
     */
    @Override
    protected boolean shouldBeIncluded(FinancialEntry entry) {
        return entry instanceof Expense && (end == null || entry.getDate().isBefore(end))
                && (start == null || entry.getDate().isAfter(start));
    }
}
