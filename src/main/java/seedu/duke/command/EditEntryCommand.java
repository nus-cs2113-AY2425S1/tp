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
        list.editEntry(index, amount, description);
        System.out.println("--------------------------------------------");
        System.out.println("Got it. I've edited this expense:");
        System.out.println(list.getEntry(index));
        System.out.println("--------------------------------------------");

    }

}
