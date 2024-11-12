package wheresmymoney.category;

import wheresmymoney.ExpenseList;
import wheresmymoney.exception.StorageException;
import wheresmymoney.exception.WheresMyMoneyException;

/**
 * The {@code CategoryFacade} class provides a simplified interface for category management.
 *
 * <p>
 * It acts as a facade to the underlying CategoryTracker and CategoryFilter components,
 * allowing commands to perform operations on categories such as:
 * <li> CRUD operations </li>
 * <li> filtering operations </li>
 * <li> file operations </li>
 * </p>
 */
public class CategoryFacade {
    private CategoryTracker categoryTracker;
    private CategoryFilter categoryFilter;
    private CategoryStorage categoryStorage;
    
    public CategoryFacade() {
        this.categoryTracker = new CategoryTracker();
        this.categoryFilter = new CategoryFilter();
        this.categoryStorage = new CategoryStorage();
        this.categoryFilter.setCategoryFacade(this);
        this.categoryStorage.setCategoryFacade(this);
    }
    public CategoryTracker getCategoryTracker() {
        return categoryTracker;
    }
    
    /**
     * Updates {@code CategoryTracker}'s tracker when the user adds a new Expense.
     *
     * @param category the category of the newly added Expense
     * @param price the price of the newly added Expense
     * @throws WheresMyMoneyException if there is an error while adding the category
     */
    public void addCategory(String category, float price) throws WheresMyMoneyException {
        categoryTracker.addCategory(category, price);
        categoryTracker.checkLimitOf(category);
    }
    /**
     * Updates {@code CategoryTracker}'s tracker when the user deletes an Expense.
     *
     * @param category the category of the Expense to be deleted
     * @param price the price of the Expense to be deleted
     * @throws WheresMyMoneyException if there is an error while deleting the category
     */
    public void deleteCategory(String category, Float price) throws WheresMyMoneyException {
        categoryTracker.deleteCategory(category, price);
    }
    /**
     * Updates {@code CategoryTracker}'s tracker when the user edits an Expense.
     *
     * @param oldCategory the current name of the category to be edited
     * @param newCategory the new name for the category
     * @param oldPrice    The current price of the {@code Expense}.
     * @param newPrice    The new price of the {@code Expense}.
     * @throws WheresMyMoneyException if there is an error while editing the category
     */
    public void editCategory(String oldCategory, String newCategory, Float oldPrice, Float newPrice)
            throws WheresMyMoneyException {
        categoryTracker.editCategory(oldCategory, newCategory, oldPrice, newPrice);
        categoryTracker.checkLimitOf(newCategory);
    }
    
    /**
     * Calls {@code CategoryStorage} to load category information from a CSV file,
     * then to show filtered categories based on spending limits.
     *
     * @param expenseList the list of expenses to track category information
     * @throws WheresMyMoneyException if there is an error while loading category info
     */
    public void loadCategoryInfo(ExpenseList expenseList, String filePath) throws WheresMyMoneyException {
        categoryTracker.clear();
        categoryTracker = categoryStorage.trackCategoriesOf(expenseList.getExpenseList());
        categoryStorage.loadFromCsv(filePath, categoryTracker);
        displayFilteredCategories();
    }


    /**
     * Calls {@code CategoryFilter} to display the categories which are
     * nearing or exceeding the limit.
     */
    public void displayFilteredCategories() {
        categoryFilter.getCategoriesFiltered();
        categoryFilter.displayExceededCategories();
        categoryFilter.displayNearingCategories();
    }
    /**
     * Calls {@code CategoryStorage} to save current category information to a CSV file.
     *
     * @throws StorageException if there is an error while saving category info
     */
    public void saveCategoryInfo(String filePath) throws StorageException {
        categoryStorage.saveToCsv(filePath, categoryTracker.getTracker());
    }
    /**
     * Calls {@code CategoryTracker} to set a spending limit for a specified category.
     *
     * @param category the name of the category for which to set the spending limit for
     * @param limit the spending limit to set for the category
     * @throws WheresMyMoneyException if there is an error while setting the spending limit
     */
    public void setCategorySpendingLimit(String category, float limit) throws WheresMyMoneyException {
        categoryTracker.setSpendingLimitFor(category, limit);
        categoryTracker.checkLimitOf(category);
    }
}
