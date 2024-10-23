package wheresmymoney.command;

import wheresmymoney.ExpenseList;
import wheresmymoney.WheresMyMoneyException;

import java.util.HashMap;

public class SaveCommand extends Command {

    public SaveCommand(HashMap<String, String> argumentsList) {
        super(argumentsList);
    }

    @Override
    public void execute(ExpenseList expenseList) throws WheresMyMoneyException {
        try {
            expenseList.saveToCsv("./data.csv");
        } catch (Exception e) {
            throw new WheresMyMoneyException("Save Failed");
        }
    }

    @Override
    public String getHelp() {
        return "";
    }
}