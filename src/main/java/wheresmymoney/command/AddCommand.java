package wheresmymoney.command;

import wheresmymoney.ExpenseList;
import wheresmymoney.Parser;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.InvalidInputException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class AddCommand extends Command {

    public AddCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Add a expense or recurring expense after prasing through arguments
     * @param expenseList List of normal expenses
     * @param recurringExpenseList List of recurring expenses
     */
    @Override
    public void execute(ExpenseList expenseList, RecurringExpenseList recurringExpenseList) 
            throws WheresMyMoneyException {
        try {
            float price = Float.parseFloat(argumentsMap.get(Parser.ARGUMENT_PRICE));
            String description = argumentsMap.get(Parser.ARGUMENT_DESCRIPTION);
            String category = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
            if (this.isRecur()) {
                String lastAddedDate = argumentsMap.get(Parser.ARGUMENT_DATE);
                String frequency = argumentsMap.get(Parser.ARGUMENT_FREQUENCY);
                recurringExpenseList.addRecurringExpense(price, description, category, lastAddedDate, frequency);
            } else {
                expenseList.addExpense(price, description, category);
            }
        }catch(NullPointerException | NumberFormatException e) {
            throw new InvalidInputException("Invalid Arguments");
        }
    }
}
