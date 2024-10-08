package seedu.duke.financial;

import java.util.ArrayList;

/**
 * Represents the list of financial entries (income and expenses).
 * Provides methods for adding, deleting, and viewing entries.
 */
public class FinancialList {
    private ArrayList<FinancialEntry> entries;

    /**
     * Constructs a FinancialList object with an empty list.
     */
    public FinancialList() {
        entries = new ArrayList<>();
    }

    /**
     * Adds a new financial entry to the list.
     *
     * @param entry The financial entry (income or expense) to be added.
     */
    public void addEntry(FinancialEntry entry) {
        entries.add(entry);
    }

    /**
     * Deletes a financial entry from the list by index.
     *
     * @param index The index of the entry to be deleted.
     */
    public void deleteEntry(int index) {
        if (index >= 0 && index < entries.size()) {
            entries.remove(index);
        } else {
            System.out.println("OOPS!!! The entry does not exist.");
        }
    }

    /**
     * Returns the financial entry at the specified index.
     *
     * @param index The index of the entry to be retrieved.
     * @return The financial entry at the specified index.
     */
    public FinancialEntry getEntry(int index) {
        if (index >= 0 && index < entries.size()) {
            return entries.get(index);
        } else {
            System.out.println("OOPS!!! The entry does not exist.");
            return null;
        }
    }

    /**
     * edits an expense in the list by index.
     * @param index The index of the entry to be edited.
     * @param amount The new amount of the expense.
     * @param description The new description of the expense.
     */
    public void editExpense(int index, double amount, String description) {
        if (index >= 0 && index < entries.size()) {
            FinancialEntry entry = entries.get(index);
            if (entry instanceof Expense) {
                Expense expense = (Expense) entry;
                expense.setAmount(amount);
                expense.setDescription(description);
            } else {
                System.out.println("OOPS!!! The entry is not an expense.");
            }
        } else {
            System.out.println("OOPS!!! The entry does not exist.");
        }
    }
}
