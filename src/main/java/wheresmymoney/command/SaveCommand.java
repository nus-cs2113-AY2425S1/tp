package wheresmymoney.command;

import wheresmymoney.CategoryStorage;
import wheresmymoney.CategoryTracker;
import wheresmymoney.ExpenseList;
import wheresmymoney.exception.StorageException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class SaveCommand extends Command {

    public SaveCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    @Override
    public void execute(ExpenseList expenseList, CategoryTracker categoryTracker) throws WheresMyMoneyException {
        try {
            expenseList.saveToCsv("./data.csv");
            CategoryStorage.saveToCsv("./category_spending_limit.csv", categoryTracker);
        } catch (StorageException e) {
            throw new WheresMyMoneyException("Exception occurred when saving to file.");
        }
    }
}
