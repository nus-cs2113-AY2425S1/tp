package seedu.duke.command;

import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;

/**
 * Command to print all incomes recorded in the financial list.
 */
public class SeeAllIncomesCommand extends Command {

    public SeeAllIncomesCommand() {}

    @Override
    public void execute(FinancialList list) {
        System.out.println("Here's a list of all recorded incomes:");
        int incomeCount = 0;
        for (int i = 0; i < list.getEntryCount(); i++) {
            FinancialEntry entry = list.getEntry(i);
            if (entry instanceof Income) {
                System.out.println((++incomeCount) + ". " + entry);
            }
        }
    }
}
