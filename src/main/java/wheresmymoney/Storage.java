package wheresmymoney;

import wheresmymoney.category.CategoryFacade;
import wheresmymoney.exception.StorageException;
import wheresmymoney.exception.WheresMyMoneyException;

public class Storage {
    private static final String EXPENSES_FILE_PATH = "./expenses_data.csv";
    private static final String RECURRING_EXPENSES_FILE_PATH = "./recurring_expenses_data.csv";
    private static final String CATEGORIES_FILE_PATH = "./category_spending_limit.csv";

    /**
     * Saves to the respective file paths.
     * If no file paths are given, it saves all the data to the default file paths.
     *
     * @param expenseList Expense List to save to
     * @param categoryFacade CategoryFacade to save to
     * @param recurringExpenseList RecurringExpenseList to save to
     * @param expenseFilePath Expense list file path
     * @param categoryFilePath Category limit file path
     * @param recurrExpenseFilePath Recurring expense list file path
     * @throws WheresMyMoneyException If there is a saving error
     */
    public static void save(
            ExpenseList expenseList, CategoryFacade categoryFacade, RecurringExpenseList recurringExpenseList,
            String expenseFilePath, String categoryFilePath, String recurrExpenseFilePath) throws StorageException {
        if (expenseFilePath == null && recurrExpenseFilePath == null && categoryFilePath == null){
            expenseList.saveToCsv(EXPENSES_FILE_PATH);
            categoryFacade.saveCategoryInfo(CATEGORIES_FILE_PATH);
            recurringExpenseList.saveToCsv(RECURRING_EXPENSES_FILE_PATH);
            Ui.displayMessage("Saved to default file paths");
            return;
        }

        if (expenseFilePath != null) {
            expenseList.saveToCsv(expenseFilePath);
            Ui.displayMessage("Saved Expense List");
        }

        if (categoryFilePath != null) {
            categoryFacade.saveCategoryInfo(categoryFilePath);
            Ui.displayMessage("Saved Category Info");
        }

        if (recurrExpenseFilePath != null) {
            recurringExpenseList.saveToCsv(recurrExpenseFilePath);
            Ui.displayMessage("Saved Recurring Expense List");
        }
    }

    /**
     * Loads from the respective file paths.
     * If no file paths are given, it loads all the data from the default file paths.
     *
     * @param expenseList Expense List to load to
     * @param categoryFacade CategoryFacade to load to
     * @param recurringExpenseList RecurringExpenseList to load to
     * @param expenseFilePath Expense list file path
     * @param categoryFilePath Category limit file path
     * @param recurrExpenseFilePath Recurring expense list file path
     * @throws WheresMyMoneyException If there is a loading error
     */
    public static void load(
            ExpenseList expenseList, CategoryFacade categoryFacade, RecurringExpenseList recurringExpenseList,
            String expenseFilePath, String categoryFilePath, String recurrExpenseFilePath)
            throws WheresMyMoneyException {
        if (expenseFilePath == null && recurrExpenseFilePath == null && categoryFilePath == null){
            expenseList.loadFromCsv(categoryFacade, EXPENSES_FILE_PATH);
            categoryFacade.loadCategoryInfo(expenseList, CATEGORIES_FILE_PATH);
            recurringExpenseList.loadFromCsv(RECURRING_EXPENSES_FILE_PATH);
            Ui.displayMessage("Loaded from default file paths");
            return;
        }

        if (expenseFilePath != null) {
            expenseList.loadFromCsv(categoryFacade, expenseFilePath);
            Ui.displayMessage("Loaded Expense List");
        }

        if (categoryFilePath != null) {
            categoryFacade.loadCategoryInfo(expenseList, categoryFilePath);
            Ui.displayMessage("Loaded Category Info");
        }

        if (recurrExpenseFilePath != null) {
            recurringExpenseList.loadFromCsv(recurrExpenseFilePath);
            Ui.displayMessage("Loaded Recurring Expense List");
        }
    }
}
