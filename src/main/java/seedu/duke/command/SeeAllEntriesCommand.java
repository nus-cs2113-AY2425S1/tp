package seedu.duke.command;

import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command to print all entries recorded in the financial list.
 */
public class SeeAllEntriesCommand extends Command {
    protected static final String LINE_SEPARATOR = "--------------------------------------------";
    protected static Logger logger = Logger.getLogger(SeeAllExpensesCommand.class.getName());
    protected final String entriesListedMessage = "Here's a list of all recorded entries:";
    protected final String noEntriesMessage = "No entries found.";

    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructor for SeeAllEntriesCommand.
     *
     * @param start Start date of all entries to be listed, null if no start date to be specified.
     * @param end End date of all entries to be listed, null if no end date to be specified.
     */
    public SeeAllEntriesCommand(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Method to return message when no entries are to be listed as a String.
     *
     * @return Message when no entries are to be listed.
     */
    protected String getNoEntriesMessage() {
        return this.noEntriesMessage;
    }

    /**
     * Method to return header when there are entries to be listed as a String.
     *
     * @return Header when there are entries to be listed.
     */
    protected String getEntriesListedMessage() {
        return this.entriesListedMessage;
    }

    /**
     * Method to determine if an entry should be listed out based on its date.
     *
     * @param entry Financial Entry to analyze.
     * @return true if entry should be listed out, false otherwise.
     */
    protected boolean shouldBeIncluded(FinancialEntry entry) {
        return (end == null || entry.getDate().isBefore(end)) && (start == null || entry.getDate().isAfter(start));
    }

    /**
     * Executes the SeeAllEntriesCommand, listing out all entries.
     *
     * @param list The financial list on which the command will operate.
     */
    @Override
    public void execute(FinancialList list) {
        if (list == null) {
            logger.log(Level.SEVERE, "Financial list is null");
            assert list != null : "Financial list cannot be null";
            throw new IllegalArgumentException("Financial list cannot be null");
        }

        System.out.println(LINE_SEPARATOR);

        String entryList = "";
        int entryCount = 0;

        for (int i = 0; i < list.getEntryCount(); i++) {
            FinancialEntry entry = list.getEntry(i);
            if (this.shouldBeIncluded(entry)) {
                entryList += (++entryCount) + ". " + entry + System.lineSeparator();
            }
        }

        if (entryCount == 0) {
            System.out.println(this.getNoEntriesMessage());
            System.out.println(LINE_SEPARATOR);
            return;
        }
        System.out.println(this.getEntriesListedMessage());
        System.out.print(entryList);
        System.out.println(LINE_SEPARATOR);
    }
}
