package seedu.duke.command;

import seedu.duke.financial.FinancialList;

/**
 * Command to print help menu.
 */
public class HelpCommand extends Command{
    /**
     * Constructor for Help Command object.
     */
    public HelpCommand() {}

    /**
     * Executes help command by printing out help menu.
     */
    @Override
    public void execute(FinancialList financialList) {
        String helpMenu = "--------------------------------------------\n" +
                "List of commands:\n" +
                "--------------------------------------------\n" +
                "1. list [income|expense] [/from START_DATE] [/to END_DATE]\n " +
                "   - Shows a list of logged transactions\n" +
                "    - Also displays categories with highest income/expenditure, monthly budget and balance\n" +
                "    - Optional: Specify 'income' or 'expense' to filter the list\n" +
                "    - Optional: Specify start/end date to only list transactions before/after specified dates\n" +
                "2. expense DESCRIPTION /a AMOUNT [/d DATE] [/c CATEGORY]\n" +
                "   - Adds a new expense with an optional date and category\n" +
                "    - Categories include: FOOD, TRANSPORT, ENTERTAINMENT, UTILITIES, OTHER, UNCATEGORIZED\n" +
                "3. income DESCRIPTION /a AMOUNT [/d DATE] [/c CATEGORY]\n" +
                "   - Adds a new income with an optional date and category\n" +
                "    - Categories include: SALARY, INVESTMENT, GIFT, OTHER, UNCATEGORIZED\n" +
                "4. edit INDEX [/des DESCRIPTION] [/a AMOUNT] [/d DATE] [/c CATEGORY]\n" +
                "   - Edits the transaction at the specified INDEX with optional fields\n" +
                "5. delete INDEX\n " +
                "   - Deletes the transaction at the specified INDEX\n" +
                "6. budget\n" +
                "   - Set/modify your monthly budget\n" +
                "6. exit\n " +
                "   - Exits the program\n" +
                "7. help\n " +
                "   - Shows a list of all valid commands\n" +
                "--------------------------------------------\n";
        System.out.print(helpMenu);
    }
}
