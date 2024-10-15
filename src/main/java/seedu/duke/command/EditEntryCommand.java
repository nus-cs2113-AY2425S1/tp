package seedu.duke.command;

import seedu.duke.financial.FinancialList;

public class EditEntryCommand extends Command {
    private int index;
    private double amount;
    private String description;

    public EditEntryCommand(int index, double amount, String description) {
        this.index = index;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public void execute(FinancialList list) {
        if (index >= 0 && index <= list.getEntryCount()) {
            list.editEntry(index - 1, amount, description);
            System.out.println("--------------------------------------------");
            System.out.println("Got it. I've edited this expense:");
            System.out.println(list.getEntry(index - 1));
            System.out.println("--------------------------------------------");
        } else {
            System.out.println("OOPS!!! The entry does not exist.");
            System.out.println(index);
            System.out.println(list.getEntryCount());
        }
    }
}
