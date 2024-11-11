package seedu.duke.command;

import seedu.duke.financial.Expense;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;
import seedu.duke.log.Log;
import seedu.duke.log.LogLevels;
import seedu.duke.util.Commons;

import java.time.LocalDate;
import java.util.Map.Entry;

/**
 * Command to print all entries recorded in the financial list.
 */
public class SeeAllEntriesCommand extends Command {
    protected static final int ZERO_TO_ONE_BASED_INDEX_OFFSET = 1;
    protected static Log logger = Log.getInstance();
    protected final String entriesListedMessage = "Here's a list of all recorded entries:";
    protected final String noEntriesMessage = "No entries found.";
    protected final String cashflowHeader = "Net cashflow: $ ";

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
     * Method to return header when displaying cashflow.
     *
     * @return Header for cashflow display.
     */
    protected String getCashflowHeader() {
        return this.cashflowHeader;
    }

    /**
     * Retrieves and formats the highest spending and income category information from the financial list.
     *
     * @param list The financial list containing all entries.
     * @return A formatted string of highest spending and income categories and their respective amounts.
     */
    protected String getHighestCategoryInfo(FinancialList list) {
        Entry<Expense.Category, Double> highestExpenseCategory = list.getHighestExpenseCategory();
        Entry<Income.Category, Double> highestIncomeCategory = list.getHighestIncomeCategory();
        return formatHighestCategory(highestExpenseCategory, "Highest Expense") + "\r\n"
                + formatHighestCategory(highestIncomeCategory, "Highest Income");
    }

    /**
     * Method to express cashflow as a String for printing.
     *
     * @param cashflow Net cashflow to be printed.
     * @return String of cashflow as a 2d.p. decimal number.
     */
    protected String getCashflowString(double cashflow) {
        return String.format("%.2f", cashflow);
    }

    /**
     * Checks if a given financial entry falls within the specified date range.
     *
     * @param entry The financial entry to check.
     * @return true if entry is within the date range, false otherwise.
     */
    protected boolean isWithinGivenDates(FinancialEntry entry) {
        boolean withinStartDate = (start == null || !entry.getDate().isBefore(start));
        boolean withinEndDate = (end == null || !entry.getDate().isAfter(end));
        return withinStartDate && withinEndDate;
    }

    /**
     * Formats the highest category and amount as a string.
     *
     * @param categoryEntry Entry representing the category and amount.
     * @param label Label to indicate if it is income or expense.
     * @return Formatted string of the category and amount.
     */
    protected String formatHighestCategory(Entry<?, Double> categoryEntry, String label) {
        return label + " Category: " + categoryEntry.getKey()
                + " ($" + String.format("%.2f", categoryEntry.getValue()) + ")";
    }

    /**
     * Method to determine if an entry should be listed out based on its date.
     *
     * @param entry Financial Entry to analyze.
     * @return true if entry should be listed out, false otherwise.
     */
    protected boolean shouldBeIncluded(FinancialEntry entry) {
        return isWithinGivenDates(entry);
    }

    /**
     * Executes the command to display all recorded entries in the financial list.
     * Iterates through the financial list and collects all entries that are within the date range.
     * If no entries are found, it prints a message indicating no recorded entries.
     * Otherwise, it prints a list of all recorded entries (with their index in the financial list),
     * and the net cashflow.
     *
     * @param list The financial list on which the command will operate.
     */
    @Override
    public void execute(FinancialList list) throws FinanceBuddyException {
        if (list == null) {
            logger.log(LogLevels.SEVERE, "Financial list is null");
            throw new FinanceBuddyException("Financial list cannot be null");
        }

        String entryList = "";
        int entryCount = 0;
        double cashflow = 0;
        list.clearCategoryTotals();

        for (int i = 0; i < list.getEntryCount(); i++) {
            FinancialEntry entry = list.getEntry(i);
            if (!this.shouldBeIncluded(entry)) {
                continue;
            }
            entryCount++;
            entryList += (i + ZERO_TO_ONE_BASED_INDEX_OFFSET) + ". " + entry + System.lineSeparator();
            if (entry instanceof Income income) {
                cashflow += entry.getAmount();
                list.getTotalIncomeByCategory().merge(income.getCategory(), income.getAmount(), Double::sum);
            } else if (entry instanceof Expense expense) {
                cashflow -= entry.getAmount();
                list.getTotalExpenseByCategory().merge(expense.getCategory(), expense.getAmount(), Double::sum);
            }
        }

        printOutput(list, entryCount, entryList, cashflow);
        logger.log(LogLevels.INFO, "Listed " + list.getEntryCount() + " valid entries.");
    }

    /**
     * Helper method to print output after processing entries in Financial list
     *
     * @param list Financial List of app.
     * @param entryCount Number of entries to be listed.
     * @param entryList List of entries to be listed as a String.
     * @param cashflow Net cashflow of entries to be printed.
     */
    private void printOutput(FinancialList list, int entryCount, String entryList, double cashflow) {
        System.out.println(Commons.LINE_SEPARATOR);

        if (entryCount == 0) {
            System.out.println(this.getNoEntriesMessage());
            System.out.println(Commons.LINE_SEPARATOR);
            return;
        }

        System.out.println(this.getEntriesListedMessage());
        System.out.println(entryList);
        System.out.println("Total count: " + entryCount);
        System.out.println();
        String cashflowString = this.getCashflowString(cashflow);
        System.out.println(this.getCashflowHeader() + cashflowString);
        System.out.println();
        System.out.println(getHighestCategoryInfo(list));
        System.out.println(Commons.LINE_SEPARATOR);
    }
}
