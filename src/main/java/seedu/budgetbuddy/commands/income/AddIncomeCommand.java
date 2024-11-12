package seedu.budgetbuddy.commands.income;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.income.IncomeManager;
import seedu.budgetbuddy.transaction.income.Income;

import java.time.LocalDate;

/**
 * Represents a command to add an income entry in the BudgetBuddy application.
 * This command will create a new income with the given description, amount, and date
 * and add it to the IncomeManager.
 */
public class AddIncomeCommand extends Command {
    private String description;
    private double amount;
    private LocalDate date;

    /**
     * Constructs an AddIncomeCommand with the specified description, amount, and date.
     *
     * @param description A description of the income.
     * @param amount The amount of the income.
     * @param date The date of the income.
     */
    public AddIncomeCommand(String description, double amount, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    /**
     * Checks if the given command string starts with "add income".
     *
     * @param command The command string entered by the user.
     * @return true if the command starts with "add income", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("add income");
    }

    /**
     * Executes the AddIncomeCommand by adding the specified income to the IncomeManager.
     */
    @Override
    public void execute() {
        IncomeManager.addIncome(new Income(description, amount, date));
    }

    /**
     * Gets the description of the item.
     *
     * @return The description as a String.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the amount associated with the item.
     *
     * @return The amount as a double.
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Gets the date associated with the item.
     *
     * @return The date as a LocalDate object.
     */
    public LocalDate getDate() {
        return this.date;
    }

}
