package seedu.duke.command;

import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialList;

import java.time.LocalDate;
import java.util.Map.Entry;

/**
 * The SeeAllExpensesCommand class is responsible for displaying all recorded expenses
 * from the provided FinancialList. It extends the Command class and overrides the 
 * execute method to perform this functionality.
 */
public class SeeAllExpensesCommand extends SeeAllEntriesCommand{
    protected final String entriesListedMessage = "Here's a list of all recorded expenses:";
    protected final String noEntriesMessage = "No expenses found.";
    protected final String cashflowHeader = "Total expense: $ ";

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
     * Method to return header when displaying cashflow.
     *
     * @return Header for cashflow display.
     */
    @Override
    protected String getCashflowHeader() {
        return this.cashflowHeader;
    }

    /**
     * Method to express total expense as a String for printing.
     * Method name is kept as getCashflowString to allow for use in super.execute().
     *
     * @param cashflow Total expense to be printed.
     * @return String of cashflow as a 2d.p. decimal number.
     */
    @Override
    protected String getCashflowString(double cashflow) {
        return String.format("%.2f", -cashflow);
    }

    /**
     * Method to determine if an entry should be listed out based on its date and if it is an expense.
     *
     * @param entry Financial Entry to analyze.
     * @return true if entry should be listed out, false otherwise.
     */
    @Override
    protected boolean shouldBeIncluded(FinancialEntry entry) {
        return (entry instanceof Expense) && isWithinGivenDates(entry);
    }

    @Override
    protected String getHighestCategoryInfo(FinancialList list) {
        Entry<Expense.Category, Double> highestExpenseCategory = list.getHighestExpenseCategory();
        return formatHighestCategory(highestExpenseCategory, "Highest Expense");
    }
}
