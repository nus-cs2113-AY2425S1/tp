package wheresmymoney.command;

import wheresmymoney.ExpenseList;
import wheresmymoney.Parser;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.InvalidInputException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class EditCommand extends Command {

    public EditCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Edits an expense or recurring expense
     * 
     * @param expenseList List of expenses
     * @param recurringExpenseList List of recurring expenses
     */
    @Override
    public void execute(ExpenseList expenseList, RecurringExpenseList recurringExpenseList) 
            throws WheresMyMoneyException {
        try {
            int index = Integer.parseInt(argumentsMap.get(Parser.ARGUMENT_MAIN)) - 1;
            String category = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
            float price = Float.parseFloat(argumentsMap.get(Parser.ARGUMENT_PRICE));
            String description = argumentsMap.get(Parser.ARGUMENT_DESCRIPTION);
            if (this.isRecur()) {
                String lastAddedDate = argumentsMap.get(Parser.ARGUMENT_DATE);
                String frequency = argumentsMap.get(Parser.ARGUMENT_FREQUENCY);
                recurringExpenseList.editRecurringExpense(index, price, description, category, lastAddedDate, frequency);
            } else {
                expenseList.editExpense(index, price, description, category);
            }
        } catch (NullPointerException | NumberFormatException e) {
            throw new InvalidInputException("Invalid Arguments.");
        }
    }
}
