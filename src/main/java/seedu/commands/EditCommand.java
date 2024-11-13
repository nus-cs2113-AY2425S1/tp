package seedu.commands;

import seedu.classes.Ui;
import seedu.classes.WiagiLogger;
import seedu.exception.WiagiInvalidIndexException;
import seedu.exception.WiagiInvalidInputException;
import seedu.exception.WiagiMissingParamsException;
import seedu.type.IncomeList;
import seedu.type.SpendingList;
import seedu.type.EntryType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.classes.Constants.EDIT_COMMAND_FORMAT;
import static seedu.classes.Constants.INCORRECT_PARAMS_NUMBER;
import static seedu.classes.Constants.INDEX_NOT_INTEGER;
import static seedu.classes.Constants.INDEX_OUT_OF_BOUNDS;
import static seedu.classes.Constants.INVALID_CATEGORY;
import static seedu.classes.Constants.INVALID_FIELD;
import static seedu.classes.Constants.OVER_MAX_LIST_AMOUNT_FOR_EDIT;
import static seedu.classes.Constants.MAX_LIST_TOTAL_AMOUNT;
import static seedu.classes.Constants.WHITESPACE;
import static seedu.classes.Constants.INCOME;
import static seedu.classes.Constants.SPENDING;

public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    private static final Logger LOGGER = WiagiLogger.logger;
    private static final int TYPE_INDEX = 1;
    private static final int INDEX_OF_ENTRY_INDEX = 2;
    private static final int FIELD_INDEX = 3;
    private static final int NEW_VALUE_INDEX = 4;
    private static final int EDIT_COMPULSORY_ARGUMENTS_LENGTH = 5;
    private static final String AMOUNT_FIELD = "amount";
    private static final String DESCRIPTION_FIELD = "description";
    private static final String DATE_FIELD = "date";
    private static final String TAG_FIELD = "tag";

    private final String fullCommand;

    public EditCommand(String fullCommand) {
        assert fullCommand != null : "Full command cannot be null";
        assert fullCommand.trim().length() > 0 : "Full command cannot be empty";
        LOGGER.log(Level.INFO, "Creating new EditCommand with command: {0}", fullCommand);
        this.fullCommand = fullCommand;
    }

    /**
     * Execute editing with the given arguments
     *
     * @param incomes   list of incomes in the application
     * @param spendings list of spendings in the application
     */
    @Override
    public void execute(IncomeList incomes, SpendingList spendings) {
        assert incomes != null : "Income list cannot be null";
        assert spendings != null : "Spending list cannot be null";
        LOGGER.log(Level.INFO, "Executing edit command");
        try {
            handleCommand(incomes, spendings);
            LOGGER.log(Level.INFO, "Edit command executed successfully");
        } catch (WiagiMissingParamsException e) {
            LOGGER.log(Level.WARNING, "Missing parameters in edit command", e);
            Ui.printWithTab(e.getMessage());
        } catch (WiagiInvalidInputException e) {
            LOGGER.log(Level.WARNING, "Invalid input in edit command", e);
            Ui.printWithTab(e.getMessage());
        } catch (WiagiInvalidIndexException e) {
            LOGGER.log(Level.WARNING, "Invalid index in edit command", e);
            Ui.printWithTab(e.getMessage());
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Invalid number format in edit command", e);
            Ui.printWithTab(e.getMessage());
        }
    }

    private void handleCommand(IncomeList incomes, SpendingList spendings)
            throws WiagiMissingParamsException, WiagiInvalidIndexException {
        String[] arguments = extractArguments();
        String typeOfList = arguments[TYPE_INDEX];
        switch (typeOfList) {
        case INCOME:
            editList(arguments, incomes);
            incomes.sort(Comparator.comparing(EntryType::getDate));
            break;
        case SPENDING:
            editList(arguments, spendings);
            spendings.sort(Comparator.comparing(EntryType::getDate));
            break;
        default:
            throw new WiagiInvalidInputException(INVALID_CATEGORY + EDIT_COMMAND_FORMAT);
        }
    }

    private String[] extractArguments() throws WiagiMissingParamsException {
        LOGGER.log(Level.FINE, "Extracting arguments from command: {0}", fullCommand);
        String[] arguments = fullCommand.split(WHITESPACE, EDIT_COMPULSORY_ARGUMENTS_LENGTH);
        if (arguments.length < EDIT_COMPULSORY_ARGUMENTS_LENGTH) {
            throw new WiagiMissingParamsException(INCORRECT_PARAMS_NUMBER + EDIT_COMMAND_FORMAT);
        }
        return arguments;
    }

    private <T extends EntryType> void editList(String[] arguments, ArrayList<T> list)
            throws WiagiInvalidIndexException {
        String index = arguments[INDEX_OF_ENTRY_INDEX];
        EntryType entryToEdit = extractEntry(list, index);
        String newValue = arguments[NEW_VALUE_INDEX];
        String field = arguments[FIELD_INDEX];
        switch (field) {
        case AMOUNT_FIELD:
            throwExceptionIfTotalExceeded(newValue, entryToEdit.getAmount(), list);
            entryToEdit.editAmount(newValue);
            if (list instanceof SpendingList) {
                ((SpendingList) list).checkOverspend();
            }
            break;
        case DESCRIPTION_FIELD:
            entryToEdit.editDescription(newValue);
            break;
        case DATE_FIELD:
            entryToEdit.editDate(newValue);
            break;
        case TAG_FIELD:
            entryToEdit.editTag(newValue);
            break;
        default:
            throw new WiagiInvalidInputException(INVALID_FIELD + EDIT_COMMAND_FORMAT);
        }
        Ui.printWithTab("Edit Successful!");
    }

    private <T extends EntryType> void throwExceptionIfTotalExceeded(String newValue, double oldAmount,
                                                                     ArrayList<T> list) {
        double newAmount = CommandUtils.formatAmount(newValue, EDIT_COMMAND_FORMAT);
        double currTotal;
        if (list instanceof IncomeList) {
            currTotal = ((IncomeList) list).getTotal();
        } else {
            currTotal = ((SpendingList) list).getTotal();
        }

        double totalAmountAfterRecur = currTotal + newAmount - oldAmount;
        if (totalAmountAfterRecur > MAX_LIST_TOTAL_AMOUNT) {
            throw new WiagiInvalidInputException(OVER_MAX_LIST_AMOUNT_FOR_EDIT);
        }

        if (list instanceof IncomeList) {
            ((IncomeList) list).setTotal(totalAmountAfterRecur);
        } else {
            ((SpendingList) list).setTotal(totalAmountAfterRecur);
        }
    }

    private <T extends EntryType> EntryType extractEntry(ArrayList<T> list, String stringIndex)
            throws WiagiInvalidIndexException {
        assert list != null : "List cannot be null";
        assert stringIndex != null : "String index cannot be null";
        try {
            int index = Integer.parseInt(stringIndex) - 1;
            return list.get(index);
        } catch (NumberFormatException e) {
            throw new WiagiInvalidIndexException(INDEX_NOT_INTEGER);
        } catch (IndexOutOfBoundsException e) {
            throw new WiagiInvalidIndexException(INDEX_OUT_OF_BOUNDS);
        }
    }
}
