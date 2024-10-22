package seedu.duke.command;

import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Expense;
import seedu.duke.financial.Income;

import java.time.LocalDate;

/**
 * The SeeAllExpensesCommand class is responsible for displaying all recorded expenses
 * from the provided FinancialList. It extends the Command class and overrides the 
 * execute method to perform this functionality.
 */
public class SeeAllExpensesCommand extends Command{
    private LocalDate start;
    private LocalDate end;

    public SeeAllExpensesCommand(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Method to determine if an entry should be listed out based on its date and if it is an expense.
     *
     * @param entry Financial Entry to analyze.
     * @return true if entry should be listed out, false otherwise.
     */
    private boolean shouldBeIncluded(FinancialEntry entry) {
        return entry instanceof Expense && (end == null || entry.getDate().isBefore(end))
                && (start == null || entry.getDate().isAfter(start));
    }

    /**
     * Executes the command to display all recorded expenses in the financial list.
     * Iterates through the financial list and collects all entries that are instances of Expense.
     * If no expenses are found, it prints a message indicating no recorded expenses.
     * Otherwise, it prints a list of all recorded expenses.
     *
     * @param list The financial list containing financial entries.
     */
    @Override
    public void execute(FinancialList list) {
        System.out.println("--------------------------------------------");
        String expenseList = "";
        int expenseCount = 0;

        for (int i = 0; i < list.getEntryCount(); i++) {
            FinancialEntry entry = list.getEntry(i);
            if (shouldBeIncluded(entry)) {
                expenseList += ((++expenseCount) + ". " + entry + System.lineSeparator());
            }
        }

        if (expenseCount == 0) {
            System.out.println("No recorded expenses found.");
            System.out.println("--------------------------------------------");
            return;
        }
        System.out.println("Here's a list of all recorded expenses:");
        System.out.print(expenseList);
        System.out.println("--------------------------------------------");
    }
}
