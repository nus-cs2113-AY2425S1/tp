package seedu.duke.command;

import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Expense;


public class SeeAllExpensesCommand extends Command{

    public SeeAllExpensesCommand() {}

    @Override
    public void execute(FinancialList list) {
        System.out.println("Here's a list of all recorded expenses:");
        for (int i = 0; i < list.getEntryCount(); i++) {
            FinancialEntry entry = list.getEntry(i);
            if (entry instanceof Expense) {
                System.out.println((i + 1) + ". " + entry);
            }
        }
    }

}
