package wheresmymoney.command;

import wheresmymoney.Expense;
import wheresmymoney.ExpenseList;
import wheresmymoney.Parser;
import wheresmymoney.Ui;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.ArrayList;
import java.util.HashMap;

public class HelpCommand extends Command {

    public HelpCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Displays list expenses as requested by user
     */
    @Override
    public void execute(ExpenseList expenseList) throws WheresMyMoneyException {
        Ui.displayMessage("Here are the list of commands");
        Ui.displayMessage("Take note that any words in SCREAMING_SNAKE_CASE is a parameter");
        Ui.displayMessage("");

        Ui.displayMessage("Use the add command to add an expense");
        Ui.displayMessage("Format:  add [/price PRICE] [/description DESCRIPTION] [/category CATEGORY]");
        Ui.displayMessage("Notes:");
        Ui.displayMessage("    - Price is a decimal number");
        Ui.displayMessage("    - Description and Category are Text");
        Ui.displayMessage("Examples: add /price 4.50 /description chicken rice /category food");
        Ui.displayMessage("");

        Ui.displayMessage("Use the edit command to edit an expense");
        Ui.displayMessage("Format: edit INDEX [/price PRICE] [/description DESCRIPTION] [/category CATEGORY]");
        Ui.displayMessage("Notes:");
        Ui.displayMessage("    - Price is a decimal number");
        Ui.displayMessage("    - Description and Category are Text");
        Ui.displayMessage("    - All parameters are optional and only the parameters that are" +
                "inputted will be reflected after the edit");
        Ui.displayMessage("Examples: edit 1 /price 5.50 /description chicken rice /category food");
        Ui.displayMessage("");

        Ui.displayMessage("Use the delete command to delete an expense");
        Ui.displayMessage("Format:  delete [INDEX]");
        Ui.displayMessage("Examples: delete 2");
        Ui.displayMessage("");

        Ui.displayMessage("Use the list command to display expenses and gives the sum of all expenses listed");
        Ui.displayMessage("Format:  list [/category CATEGORY]");
        Ui.displayMessage("Notes:");
        Ui.displayMessage("    - Category is text");
        Ui.displayMessage("    - Lists all expenses the user has if the category is not specified");
        Ui.displayMessage("    - Lists all expenses with that category if specified");
        Ui.displayMessage("Examples: list /category food");
        Ui.displayMessage("");
    }
}
