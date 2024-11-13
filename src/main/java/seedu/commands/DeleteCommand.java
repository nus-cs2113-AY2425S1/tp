package seedu.commands;

import seedu.classes.Ui;
import seedu.classes.WiagiLogger;
import seedu.exception.WiagiInvalidIndexException;
import seedu.exception.WiagiInvalidInputException;
import seedu.exception.WiagiMissingParamsException;
import seedu.type.Income;
import seedu.type.IncomeList;
import seedu.type.Spending;
import seedu.type.SpendingList;

import java.util.ArrayList;
import java.util.logging.Level;

import static seedu.classes.Constants.DELETE_COMMAND_FORMAT;
import static seedu.classes.Constants.INCORRECT_PARAMS_NUMBER;
import static seedu.classes.Constants.INDEX_NOT_INTEGER;
import static seedu.classes.Constants.INDEX_OUT_OF_BOUNDS;
import static seedu.classes.Constants.INVALID_CATEGORY;
import static seedu.classes.Constants.WHITESPACE;
import static seedu.classes.Constants.INCOME;
import static seedu.classes.Constants.SPENDING;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private static final int LIST_TYPE_INDEX = 1;
    private static final int INDEX_OF_ENTRY_INDEX = 2;
    private static final int DELETE_COMPULSORY_ARGUMENTS_LENGTH = 3;

    private final String fullCommand;
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    // @@author wx-03

    /**
     * Executes delete command with the given arguments
     * @param incomes   list of incomes in the application
     * @param spendings list of spendings in the application
     */
    @Override
    public void execute(IncomeList incomes, SpendingList spendings) {
        assert incomes != null;
        assert spendings != null;
        try {
            handleCommand(incomes, spendings);
        } catch (WiagiMissingParamsException | WiagiInvalidInputException | WiagiInvalidIndexException e) {
            WiagiLogger.logger.log(Level.WARNING, "User input error", e);
            Ui.printWithTab(e.getMessage());
        }
    }

    private void handleCommand(IncomeList incomes, SpendingList spendings)
            throws WiagiMissingParamsException, WiagiInvalidIndexException {
        String[] arguments = extractArguments();
        String typeOfList = arguments[LIST_TYPE_INDEX];
        switch (typeOfList) {
        case INCOME:
            deleteEntry(arguments, incomes);
            break;
        case SPENDING:
            deleteEntry(arguments, spendings);
            break;
        default:
            throw new WiagiInvalidInputException(INVALID_CATEGORY + DELETE_COMMAND_FORMAT);
        }
    }

    private String[] extractArguments() throws WiagiMissingParamsException {
        String[] arguments = fullCommand.split(WHITESPACE, DELETE_COMPULSORY_ARGUMENTS_LENGTH);
        if (arguments.length < DELETE_COMPULSORY_ARGUMENTS_LENGTH) {
            throw new WiagiMissingParamsException(INCORRECT_PARAMS_NUMBER
                    + DELETE_COMMAND_FORMAT);
        }
        return arguments;
    }

    private <T> void deleteEntry(String[] arguments, ArrayList<T> arrList) throws WiagiInvalidIndexException {
        String stringIndex = arguments[INDEX_OF_ENTRY_INDEX];
        try {
            int index = Integer.parseInt(stringIndex) - 1;
            T entryToDelete = arrList.get(index);
            arrList.remove(index);
            editListTotalAfterDelete(arrList, entryToDelete);
        } catch (NumberFormatException e) {
            throw new WiagiInvalidInputException(INDEX_NOT_INTEGER + DELETE_COMMAND_FORMAT);
        } catch (IndexOutOfBoundsException e) {
            throw new WiagiInvalidIndexException(INDEX_OUT_OF_BOUNDS);
        }
        Ui.printWithTab("Successfully deleted!");
    }

    private <T> void editListTotalAfterDelete(ArrayList<T> arrList, T entryToDelete) {
        if (arrList instanceof IncomeList) {
            IncomeList incomes = (IncomeList) arrList;
            double amountToBeRemoved = ((Income) entryToDelete).getAmount();
            double updatedAmount = incomes.getTotal() - amountToBeRemoved;
            incomes.setTotal(updatedAmount);
        } else {
            SpendingList spendings = (SpendingList) arrList;
            double amountToBeRemoved = ((Spending) entryToDelete).getAmount();
            double updatedAmount = spendings.getTotal() - amountToBeRemoved;
            spendings.setTotal(updatedAmount);
        }
    }
}
