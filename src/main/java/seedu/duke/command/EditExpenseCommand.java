package seedu.duke.command;

import seedu.duke.financial.FinancialList;

public class EditExpenseCommand extends Command {
    private int index;
    private double amount;
    private String description;

    public EditExpenseCommand(int index, double amount, String description) {
        this.index = index;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public void execute(FinancialList list) {
        list.editExpense(index, amount, description);
        System.out.println("Got it. I've edited this expense.");
    }

}
