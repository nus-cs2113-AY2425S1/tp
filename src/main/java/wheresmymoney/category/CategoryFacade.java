package wheresmymoney.category;

import wheresmymoney.Expense;
import wheresmymoney.ExpenseList;
import wheresmymoney.exception.StorageException;
import wheresmymoney.exception.WheresMyMoneyException;

public class CategoryFacade {
    private CategoryTracker categoryTracker;
    private CategoryFilter categoryFilter;
    
    public CategoryFacade() {
        this.categoryTracker = new CategoryTracker();
        this.categoryFilter = new CategoryFilter();
        this.categoryFilter.setCategoryFacade(this);
    }
    
    public CategoryTracker getCategoryTracker() {
        return categoryTracker;
    }
    
    
    public void addCategory(CategoryTracker categoryTracker, String category, float price) throws WheresMyMoneyException {
        categoryTracker.addCategory(category, price);
        categoryTracker.checkLimitOf(category);
    }
    public void deleteCategory(CategoryTracker categoryTracker, Expense expense) throws WheresMyMoneyException {
        categoryTracker.deleteCategory(expense.getCategory(), expense.getPrice());
    }
    public void editCategory(CategoryTracker categoryTracker, String oldCategory, String newCategory, float price) throws WheresMyMoneyException {
        categoryTracker.editCategory(oldCategory, newCategory, price);
        categoryTracker.checkLimitOf(newCategory);
    }
    
    public void loadCategoryInfo(ExpenseList expenseList) throws WheresMyMoneyException {
        CategoryStorage.loadFromCsv("./category_spending_limit.csv",
                CategoryStorage.trackCategoriesOf(expenseList.getExpenseList());
    }
    public void displayFilteredCategories() {
        categoryFilter.getCategoriesFiltered();
        categoryFilter.displayExceededCategories();
        categoryFilter.displayNearingCategories();
    }
    public void saveCategoryInfo() throws StorageException {
        CategoryStorage.saveToCsv("./category_spending_limit.csv",
                categoryTracker.getTracker());
    }
    public void setCategorySpendingLimit(String category, float limit) throws WheresMyMoneyException {
        categoryTracker.setSpendingLimitFor(category, limit);
    }
}
