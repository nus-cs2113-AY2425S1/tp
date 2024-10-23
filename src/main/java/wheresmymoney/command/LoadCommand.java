package wheresmymoney.command;

import wheresmymoney.ExpenseList;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class LoadCommand extends Command {

    public LoadCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    @Override
    public void execute(ExpenseList expenseList) throws WheresMyMoneyException {
        try {
            expenseList.loadFromCsv("./data.csv");
        } catch (Exception e) {
            throw new WheresMyMoneyException("Load Failed");
        }
    }
}
