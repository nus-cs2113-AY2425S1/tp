package seedu.budgetbuddy.commands.expense;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;

import java.time.LocalDate;

/**
 * Represents a command to add an expense in the BudgetBuddy application.
 * This command will create a new expense with the given description, amount, date, and category
 * and add it to the expense manager.
 */
public class AddExpenseCommand extends Command {
    private String description;
    private double amount;
    private LocalDate date;
    private Category category;

    /**
     * Constructs an AddExpenseCommand with the specified description, amount, date, and category.
     *
     * @param description A description of the expense.
     * @param amount The amount of the expense.
     * @param date The date of the expense.
     * @param category The category of the expense.
     */
    public AddExpenseCommand(String description, double amount, LocalDate date, Category category) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    /**
     * Checks if the given command string starts with "add expense".
     *
     * @param command The command string entered by the user.
     * @return true if the command starts with "add expense", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("add expense");
    }

    /**
     * Executes the AddExpenseCommand by adding the specified expense to the expense manager.
     */
    @Override
    public void execute() {
        ExpenseManager.addExpense(new Expense(description, amount, date, category));
    }

    /**
     * Gets the description of the expense.
     *
     * @return The description of the expense.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the amount of the expense.
     *
     * @return The amount of the expense.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the date of the expense.
     *
     * @return The date of the expense.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the category of the expense.
     *
     * @return The category of the expense.
     */
    public Category getCategory() {
        return category;
    }
}
