package seedu.duke.financial;

import seedu.duke.exception.FinanceBuddyException;

import java.time.LocalDate;
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

    private boolean shouldDecrementIndex(FinancialEntry entry, int insertIndex) {
        assert insertIndex >= 0 : "Negative Index entered.";
        if (insertIndex == 0){
            return false;
        }
        assert insertIndex <= entries.size() : "Index out of bounds.";
        LocalDate dateOfPreviousEntry = entries.get(insertIndex - 1).getDate();
        //return true if previous entry has a later date than entry to be inserted
        return dateOfPreviousEntry.isAfter(entry.getDate());
    }

    /**
     * Adds a new financial entry to the list in ascending order of date.
     *
     * @param entry The financial entry (income or expense) to be added.
     */
    public void addEntry(FinancialEntry entry) {
        int insertIndex = entries.size();
        while (shouldDecrementIndex(entry, insertIndex)) {
            insertIndex--;
        }
        assert insertIndex >= 0 && insertIndex <= entries.size(): "Invalid insertion index";
        entries.add(insertIndex, entry);
    }

    /**
     * Deletes a financial entry from the list by index.
     *
     * @param index The index of the entry to be deleted.
     */
    public void deleteEntry(int index) {
        entries.remove(index);
    }

    /**
     * Returns the number of entries in the list.
     *
     * @return The number of entries in the list.
     */
    public int getEntryCount() {
        return entries.size();
    }

    /**
     * Returns the financial entry at the specified index.
     *
     * @param index The index of the entry to be retrieved.
     * @return The financial entry at the specified index.
     */
    public FinancialEntry getEntry(int index) throws FinanceBuddyException {
        try {
            return entries.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new FinanceBuddyException("Invalid entry. Please provide an index with a valid entry.");
        }
    }


    /**
     * Edits an existing financial entry in the list.
     *
     * @param index The index of the entry to be edited.
     * @param amount The new amount to be set for the entry.
     * @param description The new description to be set for the entry.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= entries.size()).
     */
    public void editEntry(int index, double amount, String description) throws FinanceBuddyException {
        FinancialEntry entry = entries.get(index);
        entry.setAmount(amount);
        entry.setDescription(description);
    }
}
