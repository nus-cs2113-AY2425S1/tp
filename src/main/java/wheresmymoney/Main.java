package wheresmymoney;

import wheresmymoney.command.Command;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class Main {
    /**
     * Main entry-point for the application
     */
    public static void main(String[] args) {
        ExpenseList expenseList = new ExpenseList();
        Parser parser = new Parser();

        Ui.displayIntroText();

        boolean isExit = false;
        while (!isExit){
            try {
                String line = Ui.getCommand();
                Command command = parser.parseInputToCommand(line);
                command.execute(expenseList);
                isExit = command.isExit();
            } catch (WheresMyMoneyException e){
                Ui.displayMessage(e.getMessage());
            }
        }

        Ui.close();
    }
}
