package seedu.command;

import seedu.category.Category;
import seedu.category.CategoryList;
import seedu.datastorage.Storage;
import seedu.exceptions.InvalidDateFormatException;
import seedu.main.UI;
import seedu.message.ErrorMessages;
import seedu.message.CommandResultMessages;
import seedu.transaction.Expense;
import seedu.transaction.Transaction;
import seedu.transaction.TransactionList;
import java.util.List;
import java.util.Objects;

public class AddExpenseCommand extends AddTransactionCommand {
    public static final String COMMAND_WORD = "add-expense";
    public static final String COMMAND_GUIDE = "add-expense [DESCRIPTION] a/ AMOUNT [d/ DATE] [c/ CATEGORY]:" +
            " Add an expense";
    public static final String[] COMMAND_MANDATORY_KEYWORDS = {"a/"};
    public static final String[] COMMAND_EXTRA_KEYWORDS = {"d/", "c/"};

    private final UI ui;
    private final CategoryList categoryList;

    public AddExpenseCommand(TransactionList transactions, UI ui, CategoryList categoryList) {
        super(transactions);
        this.ui = ui;
        this.categoryList = categoryList;
    }

    @Override
    public List<String> execute() {
        if (!isArgumentsValid()) {
            return List.of(ErrorMessages.LACK_ARGUMENTS_ERROR_MESSAGE);
        }

        String expenseName = parseDescription(arguments);

        Double amount;
        try {
            amount = parseAmount(arguments.get(COMMAND_MANDATORY_KEYWORDS[0]));
        } catch (Exception e) {
            return List.of(CommandResultMessages.ADD_TRANSACTION_FAIL + e.getMessage());
        }

        String dateString;
        try {
            dateString = parseDate(arguments.get(COMMAND_EXTRA_KEYWORDS[0]));
        } catch (InvalidDateFormatException e) {
            return List.of(CommandResultMessages.ADD_TRANSACTION_FAIL + e.getMessage());
        }

        Category category = handleCategoryInput(arguments.get(COMMAND_EXTRA_KEYWORDS[1]));
        Transaction transaction = null;
        try {
            transaction = (!Objects.equals(category.getName(), "")) ?
                    createTransaction(amount, expenseName, dateString, category) :
                    createTransaction(amount, expenseName, dateString);
        } catch (Exception e) {
            return List.of(CommandResultMessages.ADD_TRANSACTION_FAIL + e.getMessage());
        }
        transactions.addTransaction(transaction);
        Storage.saveTransaction(transactions.getTransactions());
        return List.of(CommandResultMessages.ADD_TRANSACTION_SUCCESS + transaction.toString());
    }

    private Category handleCategoryInput(String categoryName) {
        if (categoryName == null || categoryName.isEmpty()) {
            ui.printMiddleMessage("No category specified. Enter a category or type 'no' to skip: ");
            categoryName = ui.getUserInput().trim();
            if (categoryName.equalsIgnoreCase("no")) {
                return new Category(""); // Proceed without category
            }
        }

        return getOrCreateCategory(categoryName);
    }

    private Category getOrCreateCategory(String categoryName) {
        Category category = categoryList.findCategory(categoryName);
        if (category != null) {
            return category;
        }

        while (true) {
            ui.printMessage("Category '" + categoryName + "' does not exist.");
            ui.printMiddleMessage("Type 'yes' to create a new category or enter an existing category name: ");
            String response = ui.getUserInput().trim();

            if (response.equalsIgnoreCase("yes")) {
                category = new Category(categoryName);
                categoryList.addCategory(category);
                ui.printMessage("New category '" + categoryName + "' created.");
                return category;
            } else {
                category = categoryList.findCategory(response);
                if (category != null) {
                    return category;
                }
            }
        }
    }

    @Override
    protected Transaction createTransaction(double amount, String description, String date) throws Exception {
        return new Expense(amount, description, date);
    }

    protected Transaction createTransaction(double amount, String description,
                                            String date, Category category) throws Exception {
        return new Expense(amount, description, date, category);
    }

    @Override
    protected String[] getMandatoryKeywords() {
        return COMMAND_MANDATORY_KEYWORDS;
    }

    @Override
    protected String[] getExtraKeywords() {
        return COMMAND_EXTRA_KEYWORDS;
    }

    @Override
    protected String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    protected String getCommandGuide() {
        return COMMAND_GUIDE;
    }
}
