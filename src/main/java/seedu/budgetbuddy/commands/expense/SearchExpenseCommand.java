package seedu.budgetbuddy.commands.expense;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;

public class SearchExpenseCommand extends Command {
    private String keyword;

    /**
     * Constructs SearchExpenseCommand object with empty string as keyword
     */
    public SearchExpenseCommand(){
        keyword = "";
    }

    /**
     * Constructs SearchExpenseCommand object with keyword set as specified keyword.
     * @param keyword
     */
    public SearchExpenseCommand(String keyword){
        this.keyword = keyword;
    }

    /**
     * Processes user input to check if search command is called by user.
     * @param command user input
     * @return True if command starts with "search", False otherwise
     */
    public static boolean isCommand(String command){
        return command.startsWith("search expenses");
    }

    /**
     * Executes command to search expense list based on keyword.
     */
    public void execute(){
        if (getKeyword().equals("")){
            Ui.searchEmptyMessage();
        } else{
            Ui.displayToUser(ExpenseManager.searchExpenses(getKeyword()));
        }
    }

    public String getKeyword(){
        return keyword;
    }
}
