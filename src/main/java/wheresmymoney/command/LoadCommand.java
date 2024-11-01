package wheresmymoney.command;

import wheresmymoney.CategoryFilter;
import wheresmymoney.CategoryStorage;
import wheresmymoney.CategoryTracker;
import wheresmymoney.ExpenseList;
import wheresmymoney.exception.StorageException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class LoadCommand extends Command {

    public LoadCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    @Override
    public void execute(ExpenseList expenseList, CategoryTracker categoryTracker) throws WheresMyMoneyException {
        try {
            expenseList.loadFromCsv("./data.csv");

            categoryTracker = CategoryStorage.trackCategoriesOf(expenseList.getExpenseList());
            CategoryStorage.loadFromCsv("./category_spending_limit.csv", categoryTracker);

            CategoryFilter.getCategoriesFiltered(categoryTracker.getTracker());
            CategoryFilter.displayExceededCategories();
            CategoryFilter.displayNearingCategories();
        } catch (StorageException e) {
            throw new WheresMyMoneyException("Exception occurred when reading from file.");
        }
    }
}
