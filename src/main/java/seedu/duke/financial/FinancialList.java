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

    public FinancialEntry getEntry(int index) {
        return entries.get(index);
    }

}
