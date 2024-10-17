package seedu.duke.command;

import seedu.duke.financial.FinancialList;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EditEntryCommand extends Command {
    private int index;
    private double amount;
    private String description;
    private static final Logger logger = Logger.getLogger(AddExpenseCommand.class.getName());

    public EditEntryCommand(int index, double amount, String description) {
        this.index = index;
        this.amount = amount;
        this.description = description;

        assert amount >= 0 : "Amount should be non-negative";
        assert description !=null && !description.isEmpty() : "Description should not be empty";
    }

    @Override
    public void execute(FinancialList list) {
        if (list == null) {
            logger.log(Level.SEVERE, "Financial list is null");
            throw new IllegalArgumentException("Financial list cannot be null");
        }
        if (index >= 0 && index <= list.getEntryCount()) {
            list.editEntry(index - 1, amount, description);
            assert list.getEntry(index - 1).getAmount() == amount : "Amount should be updated";
            assert list.getEntry(index - 1).getDescription().equals(description) : "Description should be updated";
            System.out.println("--------------------------------------------");
            System.out.println("Got it. I've edited this expense:");
            System.out.println(list.getEntry(index - 1));
            System.out.println("--------------------------------------------");
            logger.log(Level.INFO, "Edited entry at index " + index + " to " + amount + " " + description);
        } else {
            System.out.println("OOPS!!! The entry does not exist.");
            System.out.println(index);
            System.out.println(list.getEntryCount());
            logger.log(Level.WARNING, "Entry does not exist at index " + index);
        }
    }
}
