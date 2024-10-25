package seedu.duke.command;

import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.Income;

import java.time.LocalDate;

/**
 * Command to print all incomes recorded in the financial list.
 */
public class SeeAllIncomesCommand extends SeeAllEntriesCommand {
    protected final String entriesListedMessage = "Here's a list of all recorded incomes:";
    protected final String noEntriesMessage = "No incomes found.";

    /**
     * Constructor for SeeAllIncomesCommand.
     *
     * @param start Start date of all entries to be listed, null if no start date to be specified.
     * @param end End date of all entries to be listed, null if no end date to be specified.
     */
    public SeeAllIncomesCommand(LocalDate start, LocalDate end) {
        super(start, end);
    }

    /**
     * Method to return message when no entries are to be listed as a String.
     *
     * @return Message when no entries are to be listed.
     */
    @Override
    protected String getNoEntriesMessage() {
        return this.noEntriesMessage;
    }

    /**
     * Method to return header when there are entries to be listed as a String.
     *
     * @return Header when there are entries to be listed.
     */
    @Override
    protected String getEntriesListedMessage() {
        return this.entriesListedMessage;
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
