package seedu.duke.logic;

import seedu.duke.command.AddExpenseCommand;
import seedu.duke.command.AddIncomeCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EditEntryCommand;
import seedu.duke.command.SeeAllEntriesCommand;
import seedu.duke.command.SeeAllExpensesCommand;
import seedu.duke.command.SeeAllIncomesCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;
import seedu.duke.log.Log;
import seedu.duke.log.LogLevels;
import seedu.duke.parser.DateParser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.AppUi;
import seedu.duke.util.Commons;

import java.time.LocalDate;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;

/**
 * The Logic class handles the core functionalities of FinanceBuddy, including
 * adding, editing, and deleting financial entries, and providing help and exit options.
 * It uses command objects to execute operations on the financial list.
 */
public class Logic {
    private static final Log logger = Log.getInstance();
    public final FinancialList financialList;
    private final Storage storage;
    private final AppUi ui;
    private final BudgetLogic budgetLogic;

    /**
     * Constructor for the Logic class.
     * Initializes the logic with the provided financial list, storage, and UI.
     *
     * @param financialList The financial list used to store financial entries.
     * @param storage       The storage used to load and save financial data.
     * @param ui            The UI component to interact with the user.
     */
    public Logic(FinancialList financialList, Storage storage, AppUi ui, BudgetLogic budgetLogic) {
        this.financialList = financialList;
        this.storage = storage;
        this.ui = ui;
        this.budgetLogic = budgetLogic;
    }

    /**
     * Adds a new expense entry to the financial list based on the provided command arguments.
     * <p>
     * The method extracts the description and amount for the expense from the command arguments.
     * An {@link AddExpenseCommand} is created and executed to add the expense to the financial list.
     *
     * @param commandArguments A map of parsed command arguments that contains the description of the expense
     *                         and the amount ("/a") and the date ("/d") and category ("/c") of the expense
     */
    public void addExpense(HashMap<String, String> commandArguments) throws FinanceBuddyException {
        String description = commandArguments.get(Commons.KEY_FIRST_ARGUMENT);
        double amount = parseAmount(commandArguments.get(Commons.FLAG_AMOUNT));
        String date = commandArguments.get(Commons.FLAG_DATE);
        Expense.Category category = parseExpenseCategoryOrDefault(commandArguments.get(Commons.FLAG_CATEGORY));

        try {
            AddExpenseCommand addExpenseCommand = new AddExpenseCommand(amount, description, date, category);
            addExpenseCommand.execute(financialList);
            budgetLogic.changeBalanceFromExpenseString(-amount, date);
            budgetLogic.printBalanceAmount();
        } catch (FinanceBuddyException e) {
            Commons.printSingleLineWithBars(e.getMessage());  // Display error message when invalid date is provided
        }
    }

    /**
     * Adds a new income entry to the financial list based on the provided command arguments.
     * <p>
     * The method extracts the description and amount for the income from the command arguments.
     * An {@link AddIncomeCommand} is created and executed to add the income to the financial list.
     *
     * @param commandArguments A map of parsed command arguments that contains the description of the income
     *                         and the amount ("/a").
     */
    public void addIncome(HashMap<String, String> commandArguments) throws FinanceBuddyException {
        String description = commandArguments.get(Commons.KEY_FIRST_ARGUMENT);
        double amount = parseAmount(commandArguments.get(Commons.FLAG_AMOUNT));
        String date = commandArguments.get(Commons.FLAG_DATE);
        Income.Category category = parseIncomeCategoryOrDefault(commandArguments.get(Commons.FLAG_CATEGORY));

        try {
            AddIncomeCommand addIncomeCommand = new AddIncomeCommand(amount, description, date, category);
            addIncomeCommand.execute(financialList);
        } catch (FinanceBuddyException e) {
            Commons.printSingleLineWithBars(e.getMessage());  // Display error message when invalid date is provided
        }
    }

    /**
     * Parses the amount from a string. Throws a FinanceBuddyException if invalid.
     */
    private double parseAmount(String amountStr) throws FinanceBuddyException {
        try {
            return Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            throw new FinanceBuddyException(Commons.ERROR_MESSAGE_NON_NUMBER_AMOUNT);
        } catch (NullPointerException e) {
            throw new FinanceBuddyException(Commons.ERROR_MESSAGE_EMPTY_AMOUNT);
        }
    }

    /**
     * Parses the expense category from a string or returns the default if null.
     */
    private Expense.Category parseExpenseCategoryOrDefault(String categoryStr) throws FinanceBuddyException {
        return (categoryStr == null) ? Expense.Category.UNCATEGORIZED : parseExpenseCategory(categoryStr);
    }

    /**
     * Parses the income category from a string or returns the default if null.
     */
    private Income.Category parseIncomeCategoryOrDefault(String categoryStr) throws FinanceBuddyException {
        return (categoryStr == null) ? Income.Category.UNCATEGORIZED : parseIncomeCategory(categoryStr);
    }

    /**
     * Edits an existing financial entry in the financial list based on the provided command arguments.
     * <p>
     * The method extracts the index of the entry to be edited, as well as new values for the amount
     * and description (if provided). If no new value is provided for amount or description, the
     * existing values are retained. Finally, an {@link EditEntryCommand} is created and executed
     * to apply the changes to the financial list.
     *
     * @param commandArguments A map of parsed command arguments that contains the entry index and
     *                         optional new values for the amount ("/a") and description ("/des").
     */
    public void editEntry(HashMap<String, String> commandArguments) throws FinanceBuddyException {
        int index = processIndexToAmend(commandArguments);
        FinancialEntry entry = financialList.getEntry(index - 1);

        double amount = parseAmountOrDefault(commandArguments.get(Commons.FLAG_AMOUNT), entry.getAmount());
        String description = commandArguments.getOrDefault(Commons.FLAG_DESCRIPTION, entry.getDescription());
        String date = parseDateOrDefault(commandArguments.get(Commons.FLAG_DATE), entry.getDate());

        Enum<?> category = getCategoryFromInput(commandArguments, entry);

        if (hasNoChange(amount, description, date, category, entry)){
            throw new FinanceBuddyException(Commons.ERROR_MESSAGE_NO_CHANGES_DETECTED);
        }

        EditEntryCommand editEntryCommand = new EditEntryCommand(index, amount, description, date, category);
        editEntryCommand.execute(financialList);

        if (entry instanceof Expense) {
            updateExpenseBalance((Expense) entry, amount, date);
        }
    }

    private boolean hasNoChange (double amount, String description, String date, Enum<?> category,
                                 FinancialEntry entry) {
        boolean isSameAmount = amount == entry.getAmount();
        boolean isSameDescription = description.equals(entry.getDescription());
        boolean isSameDate = date.equals(entry.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        boolean isSameCategory;
        if (entry instanceof Expense) {
            isSameCategory = category.equals(((Expense) entry).getCategory());
        } else {
            isSameCategory = category.equals(((Income) entry).getCategory());
        }
        return isSameAmount && isSameDescription && isSameDate && isSameCategory;
    }

    private boolean isEmptyArgument(HashMap<String, String> commandArguments) {
        return commandArguments.get(Commons.KEY_FIRST_ARGUMENT) == null
                || commandArguments.get(Commons.KEY_FIRST_ARGUMENT).isBlank();
    }

    /**
     * Parses the index from the argument string.
     */
    private int parseIndex(String indexStr) throws FinanceBuddyException {
        try {
            int index = Integer.parseInt(indexStr);
            if (index <= 0 || index > financialList.getEntryCount()) {
                throw new FinanceBuddyException(Commons.ERROR_MESSAGE_OUT_OF_BOUNDS_INDEX + index);
            }
            return index;
        } catch (NumberFormatException e) {
            throw new FinanceBuddyException(Commons.ERROR_MESSAGE_INVALID_INDEX);
        }
    }

    /**
     * Parses the amount or returns the default amount if null.
     */
    private double parseAmountOrDefault(String amountStr, double defaultAmount) throws FinanceBuddyException {
        try {
            return (amountStr != null) ? Double.parseDouble(amountStr) : defaultAmount;
        } catch (NumberFormatException e) {
            throw new FinanceBuddyException(Commons.ERROR_MESSAGE_NON_NUMBER_AMOUNT);
        }
    }

    /**
     * Parses the date or returns the default date if null.
     */
    private String parseDateOrDefault(String dateStr, LocalDate defaultDate) {
        return (dateStr != null) ? dateStr : defaultDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Updates the balance if the entry is an expense.
     */
    private void updateExpenseBalance(Expense entry, double newAmount, String newDate) {
        double oldAmount = entry.getAmount();
        LocalDate oldDate = entry.getDate();
        try {
            budgetLogic.changeBalanceFromExpense(oldAmount, oldDate);
            budgetLogic.changeBalanceFromExpenseString(-newAmount, newDate);
            budgetLogic.printBalanceAmount();
        } catch (FinanceBuddyException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Retrieves or parses the category based on input and entry type.
     */
    private Enum<?> getCategoryFromInput(HashMap<String, String> commandArguments, FinancialEntry entry) {
        String categoryStr = commandArguments.get(Commons.FLAG_CATEGORY);
        if (categoryStr != null) {
            return parseCategory(categoryStr, entry);
        } else if (entry instanceof Income) {
            return ((Income) entry).getCategory();
        } else {
            assert entry instanceof Expense;
            return ((Expense) entry).getCategory();
        }
    }


    /**
     * Deletes an existing entry or a range of entries from the financial list based on the command arguments.
     *
     * @param commandArguments A map of parsed command arguments. Includes:
     *                         - "argument": The start index (or "all" for deleting all entries).
     *                         - "/to": The end index for range deletion (optional).
     * @throws FinanceBuddyException If indices are invalid, out of range, or missing.
     */
    public void deleteEntry(HashMap<String, String> commandArguments) throws FinanceBuddyException {
        String end = commandArguments.get(Commons.FLAG_END_POINT);

        if ("all".equalsIgnoreCase(commandArguments.get(Commons.KEY_FIRST_ARGUMENT))) {
            handleDeleteAll();
            return;
        }

        int startIndex;
        if (end == null || end.isBlank()){
            startIndex = processIndexToAmend(commandArguments);
        } else {
            startIndex = parseIndex(commandArguments.get(Commons.KEY_FIRST_ARGUMENT));
        }

        if (end == null || end.isBlank()) {
            deleteSingleEntry(startIndex);
        } else {
            int endIndex = parseIndex(end);
            deleteRangeByIndex(startIndex, endIndex);
        }
    }

    /**
     * Deletes a range of entries in the financial list from the specified start index to the end index.
     *
     * @param start The start index (1-based).
     * @param end   The end index (1-based).
     * @throws FinanceBuddyException If the range is invalid or out of bounds.
     */
    public void deleteRangeByIndex(int start, int end) throws FinanceBuddyException {
        if (start > end) {
            throw new FinanceBuddyException("Start index must be less than or equal to end index.");
        } else if (start < 1 || end > financialList.getEntryCount()) {
            throw new FinanceBuddyException("Invalid range. Ensure indices are within the list bounds.");
        }

        // Delete entries in reverse to avoid shifting issues
        for (int i = end; i >= start; i--) {
            adjustBalanceAndDelete(i - 1); // Convert to 0-based index
        }

        Commons.printSingleLineWithBars("Entries from index " + start + " to " + end + " have been deleted.");
    }


    /**
     * Deletes all entries in the financial list and resets the balance.
     *
     * @throws FinanceBuddyException If any issue occurs during the deletion process.
     */
    public void handleDeleteAll() throws FinanceBuddyException {
        int totalEntries = financialList.getEntryCount();
        if (totalEntries == 0) {
            ui.displayEmptyListMessage();
            return;
        }

        financialList.clear();
        financialList.resetLastAmendedIndex();
        budgetLogic.recalculateBalance(financialList);
        ui.displayDeleteAllMessage(totalEntries);
    }

    /**
     * Deletes a single entry from the financial list, adjusts the budget balance, and resets the last amended index.
     *
     * @param index The index of the entry to delete (0-based).
     * @throws FinanceBuddyException If the index is invalid or out of bounds.
     */
    private void adjustBalanceAndDelete(int index) throws FinanceBuddyException {
        FinancialEntry entry = financialList.getEntry(index);
        if (entry instanceof Expense) {
            double amount = entry.getAmount();
            budgetLogic.modifyBalance(amount);
        }
        financialList.deleteEntry(financialList.getEntries().indexOf(entry));
        financialList.resetLastAmendedIndex();
    }

    /**
     * Deletes a single entry at the specified index and adjusts the balance.
     *
     * @param index The index of the entry to delete (1-based).
     * @throws FinanceBuddyException If the index is invalid or out of bounds.
     */
    private void deleteSingleEntry(int index) throws FinanceBuddyException {
        FinancialEntry entry = financialList.getEntry(index - 1);

        DeleteCommand deleteCommand = new DeleteCommand(index);
        deleteCommand.execute(financialList);
        financialList.resetLastAmendedIndex();

        if (entry instanceof Expense) {
            double amount = entry.getAmount();
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = entry.getDate().format(pattern);
            try {
                budgetLogic.changeBalanceFromExpenseString(amount, date);
                budgetLogic.printBalanceAmount();
            } catch (FinanceBuddyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int processIndexToAmend(HashMap<String, String> commandArguments) throws FinanceBuddyException {
        if (isEmptyArgument(commandArguments)) {
            if (financialList.getLastAmendedIndex() == -1) {
                throw new FinanceBuddyException("No record of last amended entry. Please enter a valid index.");
            }
            return financialList.getLastAmendedIndex() + 1; //+1 to offset zero-based indexing
        }
        return parseIndex(commandArguments.get(Commons.KEY_FIRST_ARGUMENT));
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
        String type = commandArguments.get(Commons.KEY_FIRST_ARGUMENT);
        String start = commandArguments.get(Commons.FLAG_START_POINT);
        String end = commandArguments.get(Commons.FLAG_END_POINT);

        if ((start != null && start.isBlank()) || (end != null && end.isBlank())) {
            throw new FinanceBuddyException("Please enter a valid start/end date");
        }

        LocalDate startDate = start != null ? DateParser.parse(commandArguments.get(Commons.FLAG_START_POINT)) : null;
        LocalDate endDate = end != null ? DateParser.parse(commandArguments.get(Commons.FLAG_END_POINT)) : null;

        executeListCommand(type, startDate, endDate);
    }

    private void executeListCommand(String type, LocalDate startDate, LocalDate endDate) throws FinanceBuddyException {
        if (type == null || type.isEmpty()) {
            SeeAllEntriesCommand seeAllEntriesCommand = new SeeAllEntriesCommand(startDate, endDate);
            seeAllEntriesCommand.execute(financialList);
            budgetLogic.printBudgetAndBalance();
        } else if (type.equals("expense")) {
            SeeAllExpensesCommand seeAllExpensesCommand = new SeeAllExpensesCommand(startDate, endDate);
            seeAllExpensesCommand.execute(financialList);
            budgetLogic.printBudgetAndBalance();
        } else if (type.equals("income")) {
            SeeAllIncomesCommand seeAllIncomesCommand = new SeeAllIncomesCommand(startDate, endDate);
            seeAllIncomesCommand.execute(financialList);
            budgetLogic.printBudgetAndBalance();
        } else {
            Commons.printSingleLineWithBars("Unknown argument: " + type);
        }
    }

    /**
     * Sets the budget amount based on the provided input.
     *
     * <p>This method processes a budget amount passed in the {@code commandArguments} map,
     * validates the input, and updates the budget. If the amount is 0, the budget is reset.
     * If the amount is invalid or cannot be parsed, an exception is thrown. After setting
     * a valid budget, the balance is recalculated.</p>
     *
     * @param commandArguments a {@code HashMap} containing the budget amount under the key "argument".
     * @throws FinanceBuddyException if the budget amount is null, empty, not a valid number,
     *                                or fails validation rules.
     */
    public void setBudget(HashMap<String, String> commandArguments) throws FinanceBuddyException {
        String amountInput = commandArguments.get(Commons.KEY_FIRST_ARGUMENT);

        if (amountInput == null || amountInput.trim().isEmpty()) {
            logger.log(LogLevels.WARNING, "Null or empty input for budget amount.");
            throw new FinanceBuddyException("Budget amount cannot be null or empty. Please provide a valid number.");
        }

        double amount;
        try {
            amount = Double.parseDouble(amountInput);
        } catch (NumberFormatException e) {
            logger.log(LogLevels.WARNING, "Invalid number entered.");
            throw new FinanceBuddyException(Commons.ERROR_MESSAGE_NON_NUMBER_AMOUNT);
        }

        if (amount == 0) {
            budgetLogic.resetBudget();
            storage.deleteBudgetFromFile();
        } else if (!budgetLogic.isValidBudgetAmount(amount)) {
            throw new FinanceBuddyException(Commons.ERROR_MESSAGE_AMOUNT_RANGE);
        } else {
            budgetLogic.handleSetBudget(financialList, amount);
            storage.update(financialList, budgetLogic);
        }
    }

    /**
     * Prints help menu when user inputs 'help' command.
     */
    public void printHelpMenu() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.execute(financialList);
    }

    /**
     * Matches and executes a command based on the user input.
     *
     * @param command          The command string parsed from user input.
     * @param commandArguments The arguments for the command.
     * @return A boolean indicating if the application should continue running.
     * @throws FinanceBuddyException if the command execution encounters an error.
     */
    public boolean matchCommand(String command, HashMap<String, String> commandArguments)
            throws FinanceBuddyException {
        switch (command) {
        case "list":
            listHelper(commandArguments);
            break;
        case "expense":
            addExpense(commandArguments);
            storage.update(financialList, budgetLogic);
            break;
        case "income":
            addIncome(commandArguments);
            storage.update(financialList, budgetLogic);
            break;
        case "edit":
            editEntry(commandArguments);
            storage.update(financialList, budgetLogic);
            break;
        case "delete":
            deleteEntry(commandArguments);
            storage.update(financialList, budgetLogic);
            break;
        case "budget":
            setBudget(commandArguments);
            break;
        case "help":
            printHelpMenu();
            break;
        case "exit":
            ExitCommand exitCommand = new ExitCommand();
            exitCommand.execute(financialList);
            return exitCommand.shouldContinueLoop();
        default:
            ui.showUnknownCommandMessage();
            break;
        }
        return true;
    }

    /**
     * Parses and validates the expense category argument.
     *
     * @param categoryStr The category as a string.
     * @return The corresponding Expense.Category enum, defaulting to UNCATEGORIZED if no category was given
     */
    private Expense.Category parseExpenseCategory(String categoryStr) {
        if (categoryStr == null || categoryStr.trim().isEmpty()) {
            return Expense.Category.UNCATEGORIZED;
        } else {
            try {
                return Expense.Category.valueOf(categoryStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category: " + categoryStr + ". Defaulting to UNCATEGORIZED.");
                return Expense.Category.UNCATEGORIZED;
            }
        }
    }

    /**
     * Parses and validates the income category argument.
     *
     * @param categoryStr The category as a string.
     * @return The corresponding Income.Category enum, defaulting to UNCATEGORIZED if no category was given.
     */
    private Income.Category parseIncomeCategory(String categoryStr) {
        if (categoryStr == null || categoryStr.trim().isEmpty()) {
            return Income.Category.UNCATEGORIZED;
        } else {
            try {
                return Income.Category.valueOf(categoryStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category: " + categoryStr + ". Defaulting to UNCATEGORIZED.");
                return Income.Category.UNCATEGORIZED;
            }
        }
    }

    private Enum<?> parseCategory(String categoryStr, FinancialEntry entry) {
        if (entry instanceof Expense) {
            return parseExpenseCategory(categoryStr);
        } else if (entry instanceof Income) {
            return parseIncomeCategory(categoryStr);
        }
        return null;
    }
}
