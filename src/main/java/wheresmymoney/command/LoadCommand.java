package wheresmymoney.command;

import wheresmymoney.ExpenseList;
import wheresmymoney.WheresMyMoneyException;

import java.util.HashMap;

public class LoadCommand extends Command {

    public LoadCommand(HashMap<String, String> argumentsList) {
        super(argumentsList);
    }

    @Override
    public void execute(ExpenseList expenseList) throws WheresMyMoneyException {
        try {
            expenseList.loadFromCsv("./data.csv");
        } catch (Exception e) {
            throw new WheresMyMoneyException("Load Failed");
        }
    }

    @Override
    public String getHelp() {
        return "";
    }
}
