package seedu.duke.command;

import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.Income;

import java.time.LocalDate;

/**
 * Command to print all incomes recorded in the financial list.
 */
public class SeeAllIncomesCommand extends SeeAllEntriesCommand {
    protected final String ENTRIES_LISTED_MESSAGE = "Here's a list of all recorded incomes:";
    protected final String NO_ENTRIES_MESSAGE = "No incomes found.";

    public SeeAllIncomesCommand(LocalDate start, LocalDate end) {
        super(start, end);
    }

    @Override
    protected String getNoEntriesMessage() {
        return this.NO_ENTRIES_MESSAGE;
    }

    @Override
    protected String getEntriesListedMessage() {
        return this.ENTRIES_LISTED_MESSAGE;
    }

    /**
     * Method to determine if an entry should be listed out based on its date and if it is an income.
     *
     * @param entry Financial Entry to analyze.
     * @return true if entry should be listed out, false otherwise.
     */
    @Override
    protected boolean shouldBeIncluded(FinancialEntry entry) {
        return entry instanceof Income && (end == null || entry.getDate().isBefore(end))
                && (start == null || entry.getDate().isAfter(start));
    }
}
