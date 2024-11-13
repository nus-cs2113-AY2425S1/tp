package seedu.commands;

import seedu.classes.Ui;
import seedu.type.Income;
import seedu.type.IncomeList;
import seedu.type.SpendingList;
import seedu.type.Spending;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.classes.Constants.INCORRECT_PARAMS_NUMBER;
import static seedu.classes.Constants.INVALID_CATEGORY;
import static seedu.classes.Constants.INVALID_LIST_OPTION;
import static seedu.classes.Constants.LIST_COMMAND_FORMAT;
import static seedu.classes.Constants.INCOMES_TIME_RANGE_MESSAGE;
import static seedu.classes.Constants.SPENDINGS_TIME_RANGE_MESSAGE;
import static seedu.classes.Constants.NEXT_LINE;
import static seedu.classes.Constants.NO_TAGS_FOUND;
import static seedu.classes.Constants.SEPARATOR;
import static seedu.classes.Constants.TAB;
import static seedu.classes.Constants.SELECT_TIME_RANGE_MESSAGE_INCOMES;
import static seedu.classes.Constants.SELECT_TIME_RANGE_MESSAGE_SPENDINGS;
import static seedu.classes.Constants.TODAY;
import static seedu.classes.Ui.commandInputForTest;

//@@author wongwh2002
class ListCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private final IncomeList incomes = new IncomeList();
    private final SpendingList spendings = new SpendingList();

    @BeforeEach
    public void setUp() {
        spendings.add(new Spending(10, "girlfriends", TODAY, "", null, null, 0));
        spendings.add(new Spending(10, "macdonalds", TODAY, "food", null, null, 0));
        incomes.add(new Income(10, "savings", TODAY, "", null, null, 0));
        incomes.add(new Income(10, "dividends", TODAY, "investment", null, null, 0));
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restore() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void execute_emptyList_success() {
        IncomeList emptyIncomes = new IncomeList();
        SpendingList emptySpendings = new SpendingList();
        commandInputForTest("list", emptyIncomes, emptySpendings);

        assertEquals(TAB + "Spendings" + NEXT_LINE +
                TAB + "Total spendings: 0" + NEXT_LINE +
                TAB + "Incomes" + NEXT_LINE +
                TAB + "Total incomes: 0" + NEXT_LINE,
                outContent.toString());
    }

    @Test
    public void execute_listEmptyTags_noTagsFoundMessage() {
        IncomeList emptyIncomes = new IncomeList();
        SpendingList emptySpendings = new SpendingList();
        commandInputForTest("list tags", emptyIncomes, emptySpendings);

        assertEquals(TAB + NO_TAGS_FOUND + NEXT_LINE, outContent.toString());
    }

    @Test
    public void execute_listSpecificEmptyTags_noSuchTagFoundMessage() {
        IncomeList emptyIncomes = new IncomeList();
        SpendingList emptySpendings = new SpendingList();
        commandInputForTest("list tags tag", emptyIncomes, emptySpendings);

        assertEquals(TAB + "No entries with tag: tag. Please input tags first!"
                + NEXT_LINE, outContent.toString());
    }

    @Test
    public void execute_allLists_success() {
        commandInputForTest("list", incomes, spendings);

        assertEquals(TAB + "Spendings" + NEXT_LINE +
                TAB + "1. girlfriends - 10 - " + TODAY + NEXT_LINE +
                TAB + "2. macdonalds - 10 - " + TODAY + " - Tag: food" + NEXT_LINE +
                TAB + "Total spendings: 20" + NEXT_LINE +
                TAB + "Incomes" + NEXT_LINE +
                TAB + "1. savings - 10 - " + TODAY + NEXT_LINE +
                TAB + "2. dividends - 10 - " + TODAY + " - Tag: investment" + NEXT_LINE +
                TAB + "Total incomes: 20" + NEXT_LINE,
                outContent.toString());
    }

    @Test
    public void execute_listIncome_success() {
        Ui.userInputForTest("1");
        commandInputForTest("list income", incomes, spendings);

        assertEquals(TAB + SELECT_TIME_RANGE_MESSAGE_INCOMES + NEXT_LINE +
                TAB + SEPARATOR + NEXT_LINE +
                TAB + "Incomes" + NEXT_LINE +
                TAB + "1. savings - 10 - " + TODAY + NEXT_LINE +
                TAB + "2. dividends - 10 - " + TODAY + " - Tag: investment" + NEXT_LINE +
                TAB + "Total incomes: 20" + NEXT_LINE,
                outContent.toString());
    }

    @Test
    public void execute_invalidCategory_invalidCategoryMessage() {
        commandInputForTest("list invalidCategory", incomes, spendings);
        assertEquals(TAB + INVALID_CATEGORY + LIST_COMMAND_FORMAT
                + NEXT_LINE, outContent.toString());
    }

    @Test
    public void execute_extraListSpendingsArguments_incorrectParamsNumberMessage() {
        commandInputForTest("list spending income", incomes, spendings);
        assertEquals(TAB + INCORRECT_PARAMS_NUMBER + LIST_COMMAND_FORMAT
                + NEXT_LINE, outContent.toString());
    }

    @Test
    public void execute_extraListIncomesArguments_incorrectParamsNumberMessage() {
        commandInputForTest("list income spending", incomes, spendings);
        assertEquals(TAB + INCORRECT_PARAMS_NUMBER + LIST_COMMAND_FORMAT
                + NEXT_LINE, outContent.toString());
    }

    @Test
    public void execute_listTags_success() {
        commandInputForTest("list tags", incomes, spendings);
        assertEquals(TAB + "Tags" + NEXT_LINE +
                TAB + "1. food" + NEXT_LINE +
                TAB + "2. investment" + NEXT_LINE,
                outContent.toString());
    }

    @Test
    void execute_listSpecificInvestmentTag_success() {
        commandInputForTest("list tags investment", incomes, spendings);
        assertEquals(TAB + "Tag: investment" + NEXT_LINE +
                TAB + "Incomes" + NEXT_LINE +
                TAB + "2. dividends - 10 - " + TODAY + " - Tag: investment" + NEXT_LINE,
                outContent.toString());
    }

    @Test
    void execute_listSpecificFoodTag_success() {
        commandInputForTest("list tags food", incomes, spendings);
        assertEquals(TAB + "Tag: food" + NEXT_LINE +
                TAB + "Spendings" + NEXT_LINE +
                TAB + "2. macdonalds - 10 - " + TODAY + " - Tag: food" + NEXT_LINE,
                outContent.toString());
    }

    @Test
    public void execute_listSpendingAllStatistics_success() {
        Ui.userInputForTest(String.format("1%sY", NEXT_LINE));
        commandInputForTest("list spending", incomes, spendings);

        assertEquals(TAB + SELECT_TIME_RANGE_MESSAGE_SPENDINGS + NEXT_LINE +
                TAB + SEPARATOR + NEXT_LINE +
                TAB + "List all statistics? [Y/N]:" + NEXT_LINE +
                TAB + "____________________________________________________________" + NEXT_LINE +
                TAB + "Spendings" + NEXT_LINE +
                TAB + "1. girlfriends - 10 - " + TODAY + NEXT_LINE +
                TAB + "2. macdonalds - 10 - " + TODAY + " - Tag: food" + NEXT_LINE +
                TAB + "Total spendings: 20" + NEXT_LINE +
                TAB + TAB + "Daily spendings: 20" + NEXT_LINE +
                TAB + TAB + "Daily Budget: 0" + NEXT_LINE +
                TAB + TAB + "Daily budget left: -20" + NEXT_LINE +
                TAB + TAB + "Monthly spendings: 20" + NEXT_LINE +
                TAB + TAB + "Monthly Budget: 0" + NEXT_LINE +
                TAB + TAB + "Monthly budget left: -20" + NEXT_LINE +
                TAB + TAB + "Yearly spendings: 20" + NEXT_LINE +
                TAB + TAB + "Yearly Budget: 0" + NEXT_LINE +
                TAB + TAB + "Yearly budget left: -20" + NEXT_LINE,
                outContent.toString());
    }

    @Test
    public void execute_listSpendingNotAllStatistics_success() {
        Ui.userInputForTest(String.format("1%sN", NEXT_LINE));
        commandInputForTest("list spending", incomes, spendings);
        assertEquals(TAB + SELECT_TIME_RANGE_MESSAGE_SPENDINGS + NEXT_LINE +
                TAB + SEPARATOR + NEXT_LINE +
                TAB + "List all statistics? [Y/N]:" + NEXT_LINE +
                TAB + SEPARATOR + NEXT_LINE +
                TAB + "Spendings" + NEXT_LINE +
                TAB + "1. girlfriends - 10 - " + TODAY + NEXT_LINE +
                TAB + "2. macdonalds - 10 - " + TODAY + " - Tag: food" + NEXT_LINE +
                TAB + "Total spendings: 20" + NEXT_LINE,
                outContent.toString());
    }

    private static LocalDate getMondayDate(LocalDate currDate) {
        while (currDate.getDayOfWeek() != DayOfWeek.MONDAY) {
            currDate = currDate.minusDays(1);
        }
        return currDate;
    }

    private static LocalDate getSundayDate(LocalDate currDate) {
        while (currDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
            currDate = currDate.plusDays(1);
        }
        return currDate;
    }

    @Test
    public void execute_listWeeklySpendings_success() {
        spendings.add(new Spending(10, "lunch", TODAY.minusDays(7), "", null, null, 0));
        Ui.userInputForTest("2");
        commandInputForTest("list spending", incomes, spendings);
        LocalDate monday = getMondayDate(TODAY);
        LocalDate sunday = getSundayDate(TODAY);
        assertEquals(TAB + SELECT_TIME_RANGE_MESSAGE_SPENDINGS + NEXT_LINE +
                TAB + SEPARATOR + NEXT_LINE +
                TAB + SPENDINGS_TIME_RANGE_MESSAGE + monday + " to " +
                sunday + NEXT_LINE +
                TAB + "2. girlfriends - 10 - " + TODAY + NEXT_LINE +
                TAB + "3. macdonalds - 10 - " + TODAY + " - Tag: food" + NEXT_LINE +
                TAB + "Total: 20" + NEXT_LINE,
                outContent.toString());
        spendings.remove(2);
    }

    @Test
    public void execute_listBiweeklySpendings_success() {
        spendings.add(new Spending(10, "lunch", TODAY.minusDays(14), "", null, null, 0));
        Ui.userInputForTest("3");
        commandInputForTest("list spending", incomes, spendings);
        LocalDate lastMonday = getMondayDate(TODAY.minusDays(7));
        LocalDate sunday = getSundayDate(TODAY);
        assertEquals(TAB + SELECT_TIME_RANGE_MESSAGE_SPENDINGS + NEXT_LINE +
                TAB + SEPARATOR + NEXT_LINE +
                TAB + SPENDINGS_TIME_RANGE_MESSAGE + lastMonday + " to " +
                sunday + NEXT_LINE +
                TAB + "2. girlfriends - 10 - " + TODAY + NEXT_LINE +
                TAB + "3. macdonalds - 10 - " + TODAY + " - Tag: food" + NEXT_LINE +
                TAB + "Total: 20" + NEXT_LINE,
                outContent.toString());
        spendings.remove(2);
    }

    @Test
    public void execute_listMonthlySpendings_success() {
        spendings.add(new Spending(10, "lunch", TODAY.minusDays(31), "", null, null, 0));
        Ui.userInputForTest("4");
        commandInputForTest("list spending", incomes, spendings);
        LocalDate monthStart = LocalDate.of(TODAY.getYear(), TODAY.getMonth(), 1);
        LocalDate monthEnd = monthStart.plusDays(TODAY.getMonth().length(TODAY.isLeapYear()) - 1);
        assertEquals(TAB + SELECT_TIME_RANGE_MESSAGE_SPENDINGS + NEXT_LINE +
                TAB + SEPARATOR + NEXT_LINE +
                TAB + SPENDINGS_TIME_RANGE_MESSAGE + monthStart + " to " +
                monthEnd + NEXT_LINE +
                TAB + "2. girlfriends - 10 - " + TODAY + NEXT_LINE +
                TAB + "3. macdonalds - 10 - " + TODAY + " - Tag: food" + NEXT_LINE +
                TAB + "Total: 20" + NEXT_LINE,
                outContent.toString());
        spendings.remove(2);
    }

    @Test
    public void execute_listWeeklyIncomes_success() {
        incomes.add(new Income(1000, "salary", TODAY.minusDays(7), "", null, null, 0));
        Ui.userInputForTest("2");
        commandInputForTest("list income", incomes, spendings);
        LocalDate monday = getMondayDate(TODAY);
        LocalDate sunday = getSundayDate(TODAY);
        assertEquals(TAB + SELECT_TIME_RANGE_MESSAGE_INCOMES + NEXT_LINE +
                TAB + SEPARATOR + NEXT_LINE +
                TAB + INCOMES_TIME_RANGE_MESSAGE + monday + " to " +
                sunday + NEXT_LINE +
                TAB + "2. savings - 10 - " + TODAY + NEXT_LINE +
                TAB + "3. dividends - 10 - " + TODAY + " - Tag: investment" + NEXT_LINE +
                TAB + "Total: 20" + NEXT_LINE,
                outContent.toString());
        incomes.remove(2);
    }

    @Test
    public void execute_listBiweeklyIncomes_success() {
        incomes.add(new Income(1000, "salary", TODAY.minusDays(14), "", null, null, 0));
        Ui.userInputForTest("3");
        commandInputForTest("list income", incomes, spendings);
        LocalDate lastMonday = getMondayDate(TODAY.minusDays(7));
        LocalDate sunday = getSundayDate(TODAY);
        assertEquals(TAB + SELECT_TIME_RANGE_MESSAGE_INCOMES + NEXT_LINE +
                TAB + SEPARATOR + NEXT_LINE +
                TAB + INCOMES_TIME_RANGE_MESSAGE + lastMonday + " to " +
                sunday + NEXT_LINE +
                TAB + "2. savings - 10 - " + TODAY + NEXT_LINE +
                TAB + "3. dividends - 10 - " + TODAY + " - Tag: investment" + NEXT_LINE +
                TAB + "Total: 20" + NEXT_LINE,
                outContent.toString());
        incomes.remove(2);
    }

    @Test
    public void execute_listMonthlyIncomes_success() {
        incomes.add(new Income(1000, "salary", TODAY.minusDays(31), "", null, null, 0));
        Ui.userInputForTest("4");
        commandInputForTest("list income", incomes, spendings);
        LocalDate monthStart = LocalDate.of(TODAY.getYear(), TODAY.getMonth(), 1);
        LocalDate monthEnd = monthStart.plusDays(TODAY.getMonth().length(TODAY.isLeapYear()) - 1);
        assertEquals(TAB + SELECT_TIME_RANGE_MESSAGE_INCOMES + NEXT_LINE +
                TAB + SEPARATOR + NEXT_LINE +
                TAB + INCOMES_TIME_RANGE_MESSAGE + monthStart + " to " +
                monthEnd + NEXT_LINE +
                TAB + "2. savings - 10 - " + TODAY + NEXT_LINE +
                TAB + "3. dividends - 10 - " + TODAY + " - Tag: investment" + NEXT_LINE +
                TAB + "Total: 20" + NEXT_LINE,
                outContent.toString());
        incomes.remove(2);
    }

    @Test
    public void execute_listInvalidTimeRangeWeekly_invalidListOptionPrintTimeRangeMessageAgain() {
        incomes.add(new Income(1000, "salary", TODAY.minusDays(7), "", null, null, 0));
        Ui.userInputForTest(String.format("5%s2", NEXT_LINE));
        commandInputForTest("list income", incomes, spendings);
        LocalDate monday = getMondayDate(TODAY);
        LocalDate sunday = getSundayDate(TODAY);
        assertEquals(TAB + SELECT_TIME_RANGE_MESSAGE_INCOMES + NEXT_LINE +
                TAB + SEPARATOR + NEXT_LINE +
                TAB + INVALID_LIST_OPTION + NEXT_LINE +
                TAB + SELECT_TIME_RANGE_MESSAGE_INCOMES + NEXT_LINE +
                TAB + SEPARATOR + NEXT_LINE +
                TAB + INCOMES_TIME_RANGE_MESSAGE + monday + " to " +
                sunday + NEXT_LINE +
                TAB + "2. savings - 10 - " + TODAY + NEXT_LINE +
                TAB + "3. dividends - 10 - " + TODAY + " - Tag: investment" + NEXT_LINE +
                TAB + "Total: 20" + NEXT_LINE,
                outContent.toString());
        incomes.remove(2);
    }
}


