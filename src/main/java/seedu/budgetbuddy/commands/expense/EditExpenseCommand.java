package seedu.budgetbuddy.commands.expense;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.util.LoggerSetup;
import seedu.budgetbuddy.validators.expense.EditExpenseValidator;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditExpenseCommand extends Command {

    private static final Logger LOGGER = LoggerSetup.getLogger();
    private static final double EMPTY_AMOUNT = -1.0;
    private static final Category EMPTY_CATEGORY = null;
    private static final LocalDate EMPTY_DATE = null;
    private static Category category;
    private static LocalDate date;
    private static double amount;
    private Expense expense;

    public EditExpenseCommand(Expense expense) {
        this.expense = expense;
    }

    /**
     * Checks if the provided command matches the command to edit expenses.
     *
     * @param command The command to be checked.
     * @return True if the command matches "edit expenses", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("edit expense");
    }
    
    /**
     * Set date that will be used to update expenses
     *
     * @param newDate new Expense Date
     */
    public static void setDate(LocalDate newDate) {
        date = newDate;
    }

    /**
     * Set amount that will be used to update expenses
     *
     * @param newAmount new Expense Amount
     */
    public static void setAmount(double newAmount) {
        amount = newAmount;
    }

    /**
     * Set category that will be used to update expenses
     *
     * @param newCategory new Expense Category
     */
    public static void setCategory(Category newCategory) {
        category = newCategory;
    }

    /**
     * Execute Command to get fields to edit an expense object
     * Ends Command if user input nothing
     * Shows Error Text if user input an invalid field
     * Else Edits the expense based on input fields
     */
    public void execute() {
        String editFields = getEditFields();
        LOGGER.log(Level.INFO, "Successfully retrieve edit fields");
        Boolean validInput;
        if (editFields.isEmpty()) {
            return;
        }
        validInput = EditExpenseValidator.processSecondCommand(editFields);
        if (validInput) {
            processEdit();
        }
        LOGGER.log(Level.INFO, "Successfully edit expense");
    }

    /**
     * Displays Custom Message that informs users to input edit fields
     * Then reads in user input.
     *
     * @return User input EditFields that will be used for editing Expenses {@code String}
     */
    public String getEditFields() {
        Ui.showMessage("Edit the following fields as follows: Amount: a/, Category: c/, Date: d/\n" +
                "You may the exit change menu by pressing enter with no input.\n" +
                "Currently Editing Entry:\n" +
                expense.toString());
        String editFields = Ui.getUserEditFields();
        return editFields;
    }

    /**
     * Process which fields to edit based on values stored
     * For any field that is not left empty by user, it will update the Expense object.
     */
    public void processEdit() {
        if (category != EMPTY_CATEGORY) {
            expense.editCategory(category);
        }
        if (date != EMPTY_DATE) {
            expense.editDate(date);
        }
        if (amount != EMPTY_AMOUNT) {
            expense.editAmount(amount);
        }
        Ui.displayToUser("Edited Expense:\n" + expense.toString());
    }

}
