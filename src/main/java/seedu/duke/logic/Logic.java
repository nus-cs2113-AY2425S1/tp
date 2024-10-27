package seedu.duke.logic;

import seedu.duke.command.AddExpenseCommand;
import seedu.duke.command.AddIncomeCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EditEntryCommand;
import seedu.duke.command.SeeAllEntriesCommand;
import seedu.duke.command.SeeAllExpensesCommand;
import seedu.duke.command.SeeAllIncomesCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import seedu.duke.parser.DateParser;

import java.time.LocalDate;
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
     *                         and the amount ("/a") and the date ("/d")
     */
    public void addExpense(HashMap<String, String> commandArguments) throws FinanceBuddyException {
        String description = commandArguments.get("argument");
        double amount = 0;
        try {
            amount = Double.parseDouble(commandArguments.get("/a"));
        } catch (NumberFormatException e) {
            throw new FinanceBuddyException("Invalid amount. Please use a number.");
        } catch (NullPointerException e) {
            throw new FinanceBuddyException("Invalid argument. Please do not leave compulsory arguments blank.");
        }
        String date = commandArguments.get("/d");

        try {
            AddExpenseCommand addExpenseCommand = new AddExpenseCommand(amount, description, date);
            addExpenseCommand.execute(financialList);
        } catch (FinanceBuddyException e) {
            System.out.println(e.getMessage());  // Display error message when invalid date is provided
        }

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
    public void addIncome(HashMap<String, String> commandArguments) throws FinanceBuddyException {
        String description = commandArguments.get("argument");
        double amount = 0;
        try {
            amount = Double.parseDouble(commandArguments.get("/a"));
        } catch (NumberFormatException e) {
            throw new FinanceBuddyException("Invalid amount. Please use a number.");
        } catch (NullPointerException e) {
            throw new FinanceBuddyException("Invalid argument. Please do not leave compulsory arguments blank.");
        }
        String date = commandArguments.get("/d");

        try {
            AddIncomeCommand addIncomeCommand = new AddIncomeCommand(amount, description, date);
            addIncomeCommand.execute(financialList);
        } catch (FinanceBuddyException e) {
            System.out.println(e.getMessage());  // Display error message when invalid date is provided
        }

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
    public void editEntry(HashMap<String, String> commandArguments) throws FinanceBuddyException {
        int index = 0;
        try {
            index = Integer.parseInt(commandArguments.get("argument"));
        } catch (NumberFormatException e) {
            throw new FinanceBuddyException("Invalid index. Please provide a valid integer.");
        }

        assert index > 0 : "Index of entry to edit must be greater than 0";
        assert index <= financialList.getEntryCount() : "Index of entry to edit must be within the list size";

        FinancialEntry entry = null;
        try {
            entry = financialList.getEntry(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new FinanceBuddyException("Invalid index. Please provide a valid integer.");
        }

        String amountStr = commandArguments.get("/a");
        double amount = 0;
        try {
            amount = (amountStr != null) ? Double.parseDouble(amountStr) : entry.getAmount();
        } catch (NumberFormatException e) {
            throw new FinanceBuddyException("Invalid amount. Please use a number.");
        }

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
    public void deleteEntry(HashMap<String, String> commandArguments) throws FinanceBuddyException {
        int index = 0;
        try {
            index = Integer.parseInt(commandArguments.get("argument"));
        } catch (NumberFormatException e) {
            throw new FinanceBuddyException("Invalid index. Please provide a valid integer.");
        }

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
    public void listHelper(HashMap<String, String> commandArguments) throws FinanceBuddyException {
        String type = commandArguments.get("argument");
        String start = commandArguments.get("/from");
        String end = commandArguments.get("/to");

        if ((start != null && start.isBlank()) || (end != null && end.isBlank())) {
            throw new FinanceBuddyException("Please enter a valid start/end date");
        }

        LocalDate startDate = start != null ? DateParser.parse(commandArguments.get("/from")) : null;
        LocalDate endDate = end != null ? DateParser.parse(commandArguments.get("/to")) : null;

        if (type != null) {
            if (type.equals("expense")) {
                SeeAllExpensesCommand seeAllExpensesCommand = new SeeAllExpensesCommand(startDate, endDate);
                seeAllExpensesCommand.execute(financialList);
            } else if (type.equals("income")) {
                SeeAllIncomesCommand seeAllIncomesCommand = new SeeAllIncomesCommand(startDate, endDate);
                seeAllIncomesCommand.execute(financialList);
            } else {
                System.out.println("Unknown argument: " + type);
                System.out.println("--------------------------------------------");
            }
        } else {
            SeeAllEntriesCommand seeAllEntriesCommand = new SeeAllEntriesCommand(startDate, endDate);
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
