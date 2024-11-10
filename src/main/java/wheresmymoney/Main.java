package wheresmymoney;

import wheresmymoney.category.CategoryFacade;
import wheresmymoney.command.Command;
import wheresmymoney.exception.WheresMyMoneyException;
import wheresmymoney.utils.Parser;


public class Main {
    /**
     * Main entry-point for the application
     */
    public static void main(String[] args) {
        ExpenseList expenseList = new ExpenseList();
        CategoryFacade categoryFacade = new CategoryFacade();
        RecurringExpenseList recurringExpenseList = new RecurringExpenseList(expenseList);
        Ui.displayIntroText();
        Logging.getInstance(); // Initialise at the start

        boolean isExit = false;
        while (!isExit){
            try {
                String line = Ui.getCommand();
                Command command = Parser.parseInputToCommand(line);
                command.execute(expenseList, categoryFacade, recurringExpenseList);
                isExit = command.isExit();
            } catch (WheresMyMoneyException e){
                Ui.displayMessage(e.getMessage());
            }
        }

        Ui.close();
    }
}
