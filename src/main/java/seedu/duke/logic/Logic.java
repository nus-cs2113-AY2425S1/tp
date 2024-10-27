package seedu.duke.logic;

import seedu.duke.command.AddExpenseCommand;
import seedu.duke.command.AddIncomeCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EditEntryCommand;
import seedu.duke.command.SeeAllEntriesCommand;
import seedu.duke.command.SeeAllExpensesCommand;
import seedu.duke.command.SeeAllIncomesCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;

import java.util.HashMap;

public class Logic {
    private final FinancialList financialList;

    // Constructor that accepts financialList as a parameter
    public Logic(FinancialList financialList) {
        this.financialList = financialList;
    }

    /**
     * Adds a new expense entry to the financial list based on the provided command arguments.
     *
     * The method extracts the description and amount for the expense from the command arguments.
     * An {@link AddExpenseCommand} is created and executed to add the expense to the financial list.
     *
     * @param commandArguments A map of parsed command arguments that contains the description of the expense
     *                         and the amount ("/a").
     */
    public void addExpense(HashMap<String, String> commandArguments) {
        String description = commandArguments.get("argument");
        double amount = Double.parseDouble(commandArguments.get("/a"));

        AddExpenseCommand addExpenseCommand = new AddExpenseCommand(amount, description);
        addExpenseCommand.execute(financialList);
    }

    /**
     * Adds a new income entry to the financial list based on the provided command arguments.
     *
     * The method extracts the description and amount for the income from the command arguments.
     * An {@link AddIncomeCommand} is created and executed to add the income to the financial list.
     *
     * @param commandArguments A map of parsed command arguments that contains the description of the income
     *                         and the amount ("/a").
     */
    public void addIncome(HashMap<String, String> commandArguments) {
        String description = commandArguments.get("argument");
        double amount = Double.parseDouble(commandArguments.get("/a"));

        AddIncomeCommand addIncomeCommand = new AddIncomeCommand(amount, description);
        addIncomeCommand.execute(financialList);

    }

    /**
     * Edits an existing financial entry in the financial list based on the provided command arguments.
     *
     * The method extracts the index of the entry to be edited, as well as new values for the amount
     * and description (if provided). If no new value is provided for amount or description, the
     * existing values are retained. Finally, an {@link EditEntryCommand} is created and executed
     * to apply the changes to the financial list.
     *
     * @param commandArguments A map of parsed command arguments that contains the entry index and
     *                         optional new values for the amount ("/a") and description ("/des").
     */
    public void editEntry(HashMap<String, String> commandArguments) {
        int index = Integer.parseInt(commandArguments.get("argument"));

        FinancialEntry entry = financialList.getEntry(index);

        String amountStr = commandArguments.get("/a");
        double amount = (amountStr != null) ? Double.parseDouble(amountStr) : entry.getAmount();

        String description = commandArguments.getOrDefault("/des", entry.getDescription());

        EditEntryCommand editEntryCommand = new EditEntryCommand(index, amount, description);
        editEntryCommand.execute(financialList);
    }

    /**
     * Deletes an existing entry from the financial list based on the provided command arguments.
     *
     * The method extracts the index of the entry to be deleted from the command arguments.
     * A {@link DeleteCommand} is created and executed to remove the entry from the financial list.
     *
     * @param commandArguments A map of parsed command arguments that contains the index of the entry
     *                         to be deleted.
     */
    public void deleteEntry(HashMap<String, String> commandArguments) {
        int index = Integer.parseInt(commandArguments.get("argument"));

        DeleteCommand deleteCommand = new DeleteCommand(index);
        deleteCommand.execute(financialList);
    }

    /**
     * This method helps execute the appropriate command based on the "argument"
     * provided in the commandArguments HashMap. If the argument is "expense", it will
     * execute the SeeAllExpensesCommand. If the argument is "income", it will execute
     * the SeeAllIncomesCommand. If no argument or an unknown argument is provided,
     * it defaults to executing SeeAllEntriesCommand to list all entries.
     *
     * @param commandArguments A HashMap containing the command argument with the key "argument".
     *                         The value can be "expense", "income", or null/empty for listing all entries.
     */
    public void listHelper(HashMap<String, String> commandArguments) {
        String type = commandArguments.get("argument");

        if (type != null) {
            if (type.equals("expense")) {
                SeeAllExpensesCommand seeAllExpensesCommand = new SeeAllExpensesCommand();
                seeAllExpensesCommand.execute(financialList);
            } else if (type.equals("income")) {
                SeeAllIncomesCommand seeAllIncomesCommand = new SeeAllIncomesCommand();
                seeAllIncomesCommand.execute(financialList);
            } else {
                System.out.println("Unknown argument: " + type);
            }
        } else {
            SeeAllEntriesCommand seeAllEntriesCommand = new SeeAllEntriesCommand();
            seeAllEntriesCommand.execute(financialList);
        }
    }

    /**
     * Prints help menu when user inputs 'help' command.
     */
    public void printHelpMenu() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.execute(financialList);
    }
}
