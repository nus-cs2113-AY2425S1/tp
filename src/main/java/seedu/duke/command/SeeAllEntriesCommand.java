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
    protected final String ENTRIES_LISTED_MESSAGE = "Here's a list of all recorded entries:";
    protected static final String LINE_SEPARATOR = "--------------------------------------------";
    protected static final Logger logger = Logger.getLogger(SeeAllExpensesCommand.class.getName());
    protected final String NO_ENTRIES_MESSAGE = "No entries found.";

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
     * Method to determine if an entry should be listed out based on its date.
     *
     * @param entry Financial Entry to analyze.
     * @return true if entry should be listed out, false otherwise.
     */
    private boolean shouldBeIncluded(FinancialEntry entry) {
        return (end == null || entry.getDate().isBefore(end)) && (start == null || entry.getDate().isAfter(start));
    }

    /**
     * Executes the SeeAllEntriesCommand, listing out all entries.
     *
     * @param list The financial list on which the command will operate.
     */
    @Override
    public void execute(FinancialList list) {
        System.out.println("--------------------------------------------");
        if (list == null) {
            logger.log(Level.SEVERE, "Financial list is null");
            assert list != null : "Financial list cannot be null";
            throw new IllegalArgumentException("Financial list cannot be null");
        }

        String entryList = "";
        int entryCount = 0;

        for (int i = 0; i < list.getEntryCount(); i++) {
            FinancialEntry entry = list.getEntry(i);
            if (shouldBeIncluded(entry)) {
                entryList += (++entryCount) + ". " + entry + System.lineSeparator();
            }
        }

        if (entryCount == 0) {
            System.out.println("No entries found.");
            System.out.println("--------------------------------------------");
            return;
        }
        System.out.println("Here's a list of all recorded entries:");
        System.out.print(entryList);
        System.out.println("--------------------------------------------");
    }
}
