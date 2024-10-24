package wheresmymoney;

import wheresmymoney.command.Command;
import wheresmymoney.exception.WheresMyMoneyException;


public class Main {
    /**
     * Main entry-point for the application
     */
    public static void main(String[] args) {
        ExpenseList expenseList = new ExpenseList();
        Ui.displayIntroText();
        Logging.getInstance(); // Initialise at the start

        boolean isExit = false;
        while (!isExit){
            try {
                String line = Ui.getCommand();
                Command command = Parser.parseInputToCommand(line);
                command.execute(expenseList);
                isExit = command.isExit();
            } catch (WheresMyMoneyException e){
                Ui.displayMessage(e.getMessage());
            }
        }

        Ui.close();
    }
}
