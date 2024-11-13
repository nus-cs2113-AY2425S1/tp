package seedu.recurrence;

import seedu.classes.Parser;
import seedu.classes.Ui;
import seedu.exception.WiagiInvalidInputException;
import seedu.type.Income;
import seedu.type.IncomeList;
import seedu.type.Spending;
import seedu.type.SpendingList;
import seedu.type.EntryType;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static seedu.classes.Constants.OVER_MAX_LIST_AMOUNT_FOR_RECURRENCE;
import static seedu.classes.Constants.MAX_LIST_TOTAL_AMOUNT;
import static seedu.classes.Constants.TODAY;

/**
 * Abstract class for {@code DailyRecurrence}, {@code MonthlyRecurrence} and {@code YearlyRecurrence}. Used to manage
 * recurring entries in the user's {@code IncomeList} and {@code SpendingList} and add recurring entries when needed
 */
public abstract class Recurrence {
    /**
     * Queries the user if backlog of recurring entries from {@code toAdd} entry date until current date are
     * necessary.
     *
     * @param toAdd Entry to add into either {@code IncomeList} or {@code SpendingList}
     */
    public static <T extends EntryType> void checkRecurrenceBackLog(T toAdd, ArrayList<T> list) {
        if (!isAbleToBacklog(toAdd)) {
            return;
        }
        Recurrence recurrence = Parser.parseRecurrence(toAdd);
        assert recurrence != null : "previously checked that recurrence frequency is not NONE";
        boolean hasRecurrenceBacklog = Ui.hasRecurrenceBacklog(toAdd);
        long numOfRecur = getNumberOfRecurringEntries(recurrence, toAdd);
        if (toAdd instanceof Spending) {
            SpendingList spendings = (SpendingList) list;
            throwExceptionIfTotalExceeded(numOfRecur, spendings.getTotal(), toAdd.getAmount());
            recurrence.checkSpendingRecurrence((Spending)toAdd, spendings, hasRecurrenceBacklog);
            assert spendings.getTotal() <= MAX_LIST_TOTAL_AMOUNT;
        } else {
            IncomeList incomes = (IncomeList) list;
            throwExceptionIfTotalExceeded(numOfRecur, incomes.getTotal(), toAdd.getAmount());
            recurrence.checkIncomeRecurrence((Income)toAdd, incomes, hasRecurrenceBacklog);
            assert incomes.getTotal() <= MAX_LIST_TOTAL_AMOUNT;
        }
        if (hasRecurrenceBacklog) {
            Ui.printWithTab("All entries to recur are added!");
        } else {
            Ui.printWithTab("Ok! The entry will not be backlogged");
        }
    }

    private static void throwExceptionIfTotalExceeded(long numOfRecur, double currListTotal, double addAmount) {
        double totalAmountAfterRecur = (numOfRecur * addAmount) + currListTotal;
        if (totalAmountAfterRecur > MAX_LIST_TOTAL_AMOUNT) {
            throw new WiagiInvalidInputException(OVER_MAX_LIST_AMOUNT_FOR_RECURRENCE);
        }
    }

    private static <T extends EntryType> long getNumberOfRecurringEntries(Recurrence recurrence, T toAdd) {
        if (recurrence instanceof DailyRecurrence) {
            return ChronoUnit.DAYS.between(toAdd.getDate(), TODAY);
        } else if (recurrence instanceof MonthlyRecurrence) {
            return ChronoUnit.MONTHS.between(toAdd.getDate(), TODAY);
        } else {
            return ChronoUnit.YEARS.between(toAdd.getDate(), TODAY);
        }
    }

    /**
     * Retrieves the last day of the month, given the current month and year. Includes leap year as well.
     *
     * @param newEntry New entry that is added to {@code SpendingList} or {@code IncomeList} from recurrence
     * @return Last day of the month
     */
    protected int getLastDayOfMonth(LocalDate newEntry) {
        YearMonth date = YearMonth.of(newEntry.getYear(), newEntry.getMonth());
        return date.atEndOfMonth().getDayOfMonth();
    }

    /**
     * Checks if date of recurrence entry is legitimate due to varying total days in months, causing actual date of
     * recurrence to be overwritten
     *
     * @param newEntry New entry that is added to `SpendingList` or `IncomeList` from recurrence
     * @param checkDate Date to be checked if it is correct
     * @param list {@code SpendingList} or {@code IncomeList} to add entry to
     * @param isAdding Set to true to allow adding of backlog entries, otherwise to only update {@code lastRecurred}
     *      attribute of entry
     */
    protected <T extends EntryType> void checkIfDateAltered(T newEntry, LocalDate checkDate,
                ArrayList<T> list, boolean isAdding) {
        int dayOfSupposedRecurrence = newEntry.getDayOfRecurrence();
        int lastDayOfNewEntryMonth = getLastDayOfMonth(checkDate);
        int actualDayToRecur = Math.min(dayOfSupposedRecurrence, lastDayOfNewEntryMonth);
        newEntry.editDateWithLocalDate(checkDate.withDayOfMonth(actualDayToRecur));
        if (!newEntry.getDate().isAfter(TODAY) && isAdding) {
            list.add(newEntry);
        }
    }

    /**
     * Checks the {@code Income} entry if there is a need to add a recurring {@code Income} entry to the user's
     * {@code IncomeList}
     *
     * @param recurringIncome {@code Income} entry to be checked
     * @param incomes {@code IncomeList} of the user
     * @param isAdding Set to true to allow adding of backlog entries, otherwise to only update {@code lastRecurred}
     *      attribute of entry
     */
    public abstract void checkIncomeRecurrence(Income recurringIncome, IncomeList incomes, boolean isAdding);

    /**
     * Checks the {@code Spending} entry if there is a need to add a recurring{@code spending} entry to the user's
     * {@code SpendingList}
     *
     * @param recurringSpending {@code Spending} entry to be checked
     * @param spendings {@code SpendingList} of the user
     * @param isAdding Set to true to allow adding of backlog entries, otherwise to only update {@code lastRecurred}
     *      attribute of entry
     */
    public abstract void checkSpendingRecurrence(Spending recurringSpending, SpendingList spendings, boolean isAdding);

    private static <T extends EntryType> boolean isAbleToBacklog(T toAdd) {
        return toAdd.getRecurrenceFrequency() != RecurrenceFrequency.NONE && toAdd.getDate().isBefore(TODAY);
    }
}
