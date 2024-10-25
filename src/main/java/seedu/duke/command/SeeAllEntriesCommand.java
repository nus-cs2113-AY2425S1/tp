package seedu.duke.command;

import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;

import java.time.LocalDate;

/**
 * Command to print all entries recorded in the financial list.
 */
public class SeeAllEntriesCommand extends Command {
    private LocalDate start;
    private LocalDate end;

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
     * Executes the command to display all recorded entries in the financial list.
     * Iterates through the financial list and collects all entries that are within the date range.
     * If no entries are found, it prints a message indicating no recorded entries.
     * Otherwise, it prints a list of all recorded entries, and the net cashflow.
     *
     * @param list The financial list on which the command will operate.
     */
    @Override
    public void execute(FinancialList list) {
        System.out.println("--------------------------------------------");
        String entryList = "";
        int entryCount = 0;
        double cashflow = 0;

        for (int i = 0; i < list.getEntryCount(); i++) {
            FinancialEntry entry = list.getEntry(i);
            if (shouldBeIncluded(entry)) {
                entryList += (++entryCount) + ". " + entry + System.lineSeparator();
                if (entry instanceof Income) {
                    cashflow += entry.getAmount();
                } else if (entry instanceof Expense) {
                    cashflow -= entry.getAmount();
                }
            }
        }

        if (entryCount == 0) {
            System.out.println("No entries found.");
            System.out.println("--------------------------------------------");
            return;
        }
        System.out.println("Here's a list of all recorded entries:");
        System.out.print(entryList);
        System.out.println();
        String cashflowString = String.format("%.2f", cashflow);
        System.out.println("Net cashflow: $ " + cashflowString);
        System.out.println("--------------------------------------------");
    }
}
