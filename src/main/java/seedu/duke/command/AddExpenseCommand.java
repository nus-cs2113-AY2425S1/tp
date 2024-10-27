package seedu.duke.command;

import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialList;
import seedu.duke.exception.FinanceBuddyException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command to add an expense to the financial list.
 */
public class AddExpenseCommand extends AddEntryCommand {
    private static final Logger logger = Logger.getLogger(AddExpenseCommand.class.getName());

    /**
     * Constructs an AddExpenseCommand with the specified amount and description.
     *
     * @param amount The amount of the expense.
     * @param description The description of the expense.
     */
    public AddExpenseCommand(double amount, String description, String date) throws FinanceBuddyException {
        super(amount, description, date);
        assert amount > 0 : "Amount should be positive";
        assert description != null && !description.isEmpty() : "Description should not be null or empty";
    }

    /**
     * Executes the command by adding the expense to the provided financial list.
     *
     * @param list The financial list where the expense will be added.
     */
    @Override
    public void execute(FinancialList list) throws FinanceBuddyException {
        if (list == null) {
            logger.log(Level.SEVERE, "Financial list is null");
            throw new IllegalArgumentException("Financial list cannot be null");
        }

        int preEntryCount = list.getEntryCount();
        Expense expense = new Expense(amount, description, date);
        list.addEntry(expense);
        assert list.getEntryCount() == preEntryCount + 1 : "Expense not added";
        System.out.println("--------------------------------------------");
        System.out.println("Got it! I've added this expense:");
        System.out.println(expense);
        System.out.println("--------------------------------------------");
        logger.log(Level.INFO, "Expense added to list: " + expense);

    }
}
