package seedu.duke.command;

import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;

/**
 * Command to add an expense to the financial list.
 */
public class AddIncomeCommand extends Command {
    private double amount;
    private String description;

    /**
     * Constructs an AddIncomeCommand with the specified amount and description.
     *
     * @param amount The amount of the income.
     * @param description The description of the income.
     */
    AddIncomeCommand(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    /**
     * Executes the command by adding the income to the provided financial list.
     *
     * @param list The financial list where the income will be added.
     */
    @Override
    public void execute(FinancialList list) {
        Income income = new Income(amount, description);
        list.addEntry(income);
        System.out.println("OK! I've added this income:");
        System.out.println(income);
    }
}
