package wheresmymoney.command;

import wheresmymoney.ExpenseList;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class SaveCommand extends Command {

    public SaveCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    @Override
    public void execute(ExpenseList expenseList) throws WheresMyMoneyException {
        expenseList.saveToCsv("./data.csv");
    }
}