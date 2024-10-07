package seedu.duke;

/**
 * Command to add an expense to the financial list.
 */
public class AddExpenseCommand extends Command {
    private double amount;
    private String description;

    /**
     * Constructs an AddExpenseCommand with the specified amount and description.
     *
     * @param amount The amount of the expense.
     * @param description The description of the expense.
     */
    public AddExpenseCommand(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    /**
     * Executes the command by adding the expense to the provided financial list.
     *
     * @param list The financial list where the expense will be added.
     */
    @Override
    public void execute(FinancialList list) {
        Expense expense = new Expense(amount, description);
        list.addEntry(expense);
        System.out.println("Got it. I've added this expense:");
        System.out.println(expense);
    }
}
