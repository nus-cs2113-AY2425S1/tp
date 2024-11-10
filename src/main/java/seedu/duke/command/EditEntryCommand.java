package seedu.duke.command;

import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;
import seedu.duke.log.Log;
import seedu.duke.log.LogLevels;
import seedu.duke.parser.DateParser;
import seedu.duke.util.Commons;

import java.time.LocalDate;
/**
 * The EditEntryCommand class is responsible for editing an existing entry in the financial list.
 * It extends the Command class and provides functionality to update the amount and description
 * of a specified entry.
 * 
 * <p>Usage example:
 * <pre>
 *     FinancialList list = new FinancialList();
 *     EditEntryCommand command = new EditEntryCommand(1, 50.0, "Groceries");
 *     command.execute(list);
 * </pre>
 * </p>
 * 
 * <p>Class Invariants:
 * <ul>
 *     <li>The amount must be non-negative.</li>
 *     <li>The description must not be null or empty.</li>
 * </ul>
 * </p>
 * 
 * <p>Logging:
 * <ul>
 *     <li>Logs a severe message if the financial list is null.</li>
 *     <li>Logs an info message when an entry is successfully edited.</li>
 *     <li>Logs a warning message if the specified index is invalid.</li>
 * </ul>
 * </p>
 * 
 * @see Command
 * @see FinancialList
 */
public class EditEntryCommand extends Command {
    private static final Log logger = Log.getInstance();
    private int index;
    private double amount;
    private String description;
    private LocalDate date;
    private final Enum<?> category;

    /**
     * Constructs an EditEntryCommand with the specified index, amount, and description.
     *
     * @param index       The index of the entry to be edited.
     * @param amount      The new amount for the entry. Must be non-negative.
     * @param description The new description for the entry. Must not be null or empty.
     * @param date The new date for the entry.
     * @throws FinanceBuddyException if date is input with invalid format.
     */
    public EditEntryCommand(int index, double amount, String description, String date, Enum<?> category)
            throws FinanceBuddyException {

        this.index = index;
        this.amount = Math.round(amount * 100.0)/100.0;
        this.description = description;
        this.category = category;
        try {
            this.date = DateParser.parse(date);
        } catch (FinanceBuddyException e) {
            logger.log(LogLevels.SEVERE, "Error parsing date: " + date, e);
            throw e;
        }

        assert description !=null && !description.isEmpty() : "Description should not be empty";
    }

    /**
     * Executes the command to edit an entry in the financial list.
     *
     * @param list The financial list containing the entries.
     * @throws FinanceBuddyException if the financial list is null, or if invalid parameters
     *     are passed into the class.
     */
    @Override
    public void execute(FinancialList list) throws FinanceBuddyException {
        checkValidParams();

        if (list == null) {
            logger.log(LogLevels.SEVERE, "Financial list is null");
            throw new FinanceBuddyException("Financial list cannot be null");
        }

        int zeroBasedIndex = index - 1;
        FinancialEntry entry = list.getEntry(zeroBasedIndex);
        FinancialEntry replacementEntry;

        if (entry instanceof Expense) {
            replacementEntry = new Expense(amount, description, date, (Expense.Category) category);
        } else {
            assert entry instanceof Income;
            replacementEntry = new Income(amount, description, date, (Income.Category) category);
        }

        list.deleteEntry(zeroBasedIndex);
        int correctIndex = getRightfulInsertIndex(list, replacementEntry, zeroBasedIndex);
        list.addEntryAtSpecificIndex(replacementEntry, correctIndex);

        System.out.println(Commons.LINE_SEPARATOR);
        System.out.println("Got it. I've edited this expense:");
        System.out.println(replacementEntry);
        System.out.println(Commons.LINE_SEPARATOR);
        logger.log(LogLevels.INFO, "Edited entry at index " + index + " to " + amount + " " + description);
    }

    private void checkValidParams() throws FinanceBuddyException {
        if (amount < 0.01) {
            throw new FinanceBuddyException(Commons.ERROR_MESSAGE_AMOUNT_TOO_SMALL);
        }
        if (amount > 9999999.00) {
            throw new FinanceBuddyException(Commons.ERROR_MESSAGE_AMOUNT_TOO_LARGE);
        }
        if (this.date.isAfter(LocalDate.now())) {
            throw new FinanceBuddyException(Commons.ERROR_MESSAGE_DATE_TOO_LATE);
        }
    }

    private int getRightfulInsertIndex(FinancialList financialList, FinancialEntry entry, int initialIndex)
            throws FinanceBuddyException {
        int finalIndex = initialIndex;
        while (finalIndex != 0 && financialList.getEntry(finalIndex - 1).getDate().isAfter(entry.getDate())) {
            finalIndex--;
            assert finalIndex >= 0 : "Index is not negative";
        }
        int listLength = financialList.getEntryCount();
        while (finalIndex != listLength && financialList.getEntry(finalIndex).getDate().isBefore(entry.getDate())) {
            finalIndex++;
            assert finalIndex <= listLength : "Index is within financial list length";
        }
        return finalIndex;
    }
}
