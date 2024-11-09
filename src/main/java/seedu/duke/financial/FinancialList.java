package seedu.duke.financial;

import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.util.Commons;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the list of financial entries (income and expenses).
 * Provides methods for adding, deleting, and viewing entries.
 */
public class FinancialList {
    private static final Double AMOUNTZERO = 0.0;
    private static final int DEFAULT_LAST_AMENDED_INDEX = -1;
    private ArrayList<FinancialEntry> entries;
    private Map<Expense.Category, Double> totalExpenseByCategory = new HashMap<>();
    private Map<Income.Category, Double> totalIncomeByCategory = new HashMap<>();
    private int lastAmendedIndex;

    /**
     * Constructs a FinancialList object with an empty list.
     */
    public FinancialList() {
        entries = new ArrayList<>();
        totalExpenseByCategory = new HashMap<>();
        totalIncomeByCategory = new HashMap<>();
        lastAmendedIndex = DEFAULT_LAST_AMENDED_INDEX;
    }

    /**
     * Determines if the entry should decrement index based on the date ordering.
     *
     * @param entry The financial entry to be checked.
     * @param insertIndex The index where the entry is considered to be inserted.
     * @return true if the previous entry date is after the current entry date.
     */
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
        lastAmendedIndex = insertIndex;
        updateCategoryTotal(entry);
    }

    /**
     * Updates the category totals based on the entry type and category.
     *
     * @param entry The financial entry being added.
     */
    private void updateCategoryTotal(FinancialEntry entry) {
        if (entry instanceof Expense) {
            Expense expense = (Expense) entry;
            totalExpenseByCategory.merge(expense.getCategory(), expense.getAmount(), Double::sum);
        } else if (entry instanceof Income) {
            Income income = (Income) entry;
            totalIncomeByCategory.merge(income.getCategory(), income.getAmount(), Double::sum);
        }
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
            throw new FinanceBuddyException(Commons.ERROR_MESSAGE_OUT_OF_BOUNDS_INDEX);
        }
    }

    /**
     * Getter to return the index of the last added/edited entry in the financial list.
     *
     * @return index of last added/edited entry.
     */
    public int getLastAmendedIndex() {
        return lastAmendedIndex;
    }

    /**
     * Method to reset last amended index to -1 after deleting an entry.
     */
    public void resetLastAmendedIndex() {
        lastAmendedIndex = DEFAULT_LAST_AMENDED_INDEX;
    }
    /**
     * Edits an existing financial entry in the list.
     *
     * @param index The index of the entry to be edited.
     * @param amount The new amount to be set for the entry.
     * @param description The new description to be set for the entry.
     * @param date The new date to be set for the entry.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= entries.size()).
     */
    public void editEntry(int index, double amount, String description, LocalDate date,
                          Enum<?> category) {
        assert amount >= 0.01 : "Amount < 0.01 entered.";
        assert amount <= 9999999.0 : "Amount > 9999999.0 entered.";
        assert !description.isBlank() : "Description is blank.";
        assert !date.isAfter(LocalDate.now()): "Date is after system date";

        FinancialEntry entry = entries.get(index);
        entry.setAmount(amount);
        entry.setDescription(description);
        entry.setDate(date);
        entry.setCategory(category);
    }

    /**
     * Retrieves the category totals map for expenses.
     *
     * @return A map of expense categories and their respective totals.
     */
    public Map<Expense.Category, Double> getTotalExpenseByCategory() {
        return totalExpenseByCategory;
    }

    /**
     * Retrieves the category totals map for income.
     *
     * @return A map of income categories and their respective totals.
     */
    public Map<Income.Category, Double> getTotalIncomeByCategory() {
        return totalIncomeByCategory;
    }

    /**
     * Gets the highest expense category and its total.
     * Breaks ties by returning the category that comes first alphabetically.
     *
     * @return A map entry of the highest expense category and its total.
     */
    public Map.Entry<Expense.Category, Double> getHighestExpenseCategory() {
        return totalExpenseByCategory.entrySet().stream()
                .filter(entry -> entry.getValue() > 0).min((entry1, entry2) -> {
                    int valueComparison = entry2.getValue().compareTo(entry1.getValue());  // Sort by value, descending
                    if (valueComparison == 0) {
                        return entry1.getKey().name().compareTo(entry2.getKey().name());  // Alphabetical order if tied
                    }
                    return valueComparison;
                })  // Get the first entry after sorting
                .orElse(Map.entry(Expense.Category.UNCATEGORIZED, AMOUNTZERO));
    }

    /**
     * Gets the highest income category and its total.
     * Breaks ties by returning the category that comes first alphabetically.
     *
     * @return A map entry of the highest income category and its total.
     */
    public Map.Entry<Income.Category, Double> getHighestIncomeCategory() {
        return totalIncomeByCategory.entrySet().stream()
                .filter(entry -> entry.getValue() > 0).min((entry1, entry2) -> {
                    int valueComparison = entry2.getValue().compareTo(entry1.getValue());  // Sort by value, descending
                    if (valueComparison == 0) {
                        return entry1.getKey().name().compareTo(entry2.getKey().name());  // Alphabetical order if tied
                    }
                    return valueComparison;
                })  // Get the first entry after sorting
                .orElse(Map.entry(Income.Category.UNCATEGORIZED, AMOUNTZERO));  // Default if no entries
    }

    /**
     * Clears all category totals for both expenses and income.
     */
    public void clearCategoryTotals() {
        totalExpenseByCategory.clear();
        totalIncomeByCategory.clear();
    }

}
