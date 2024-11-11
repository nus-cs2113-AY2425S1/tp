package seedu.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.budget.BudgetTracker;
import seedu.message.CommandResultMessages;
import seedu.transaction.TransactionList;
import seedu.message.ErrorMessages;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrackProgressCommandTest {

    private BudgetTracker budgetTracker;
    private ViewBudgetCommand viewBudgetCommand;

    @BeforeEach
    public void setUp() {
        TransactionList transactionList = new TransactionList();
        budgetTracker = new BudgetTracker(transactionList);
        viewBudgetCommand = new ViewBudgetCommand(budgetTracker);
    }

    @Test
    public void execute_trackProgressAllValidFields_success() {
        budgetTracker.setBudget("2024-11", 1000.0);

        viewBudgetCommand.setArguments(Map.of("m/", "2024-11"));
        List<String> result = viewBudgetCommand.execute();

        assertEquals("Great job! You're on track. Spent so far: $0.00, " +
                "Projected spending: $0.00 (within the budget of $1000.00).", result.get(0));
    }

    @Test
    public void execute_trackProgressMissingMonth_failure() {
        // Missing mandatory field "m/" (month)
        List<String> result = viewBudgetCommand.execute();

        assertEquals(ErrorMessages.LACK_ARGUMENTS_ERROR_MESSAGE, result.get(0));
    }

    @Test
    public void execute_trackProgressInvalidMonthFormat_failure() {
        // Set an invalid month format
        viewBudgetCommand.setArguments(Map.of("m/", "12-2024"));
        List<String> result = viewBudgetCommand.execute();

        assertEquals(CommandResultMessages.TRACK_PROGRESS_FAIL +
                ErrorMessages.MESSAGE_INVALID_YEAR_MONTH_FORMAT, result.get(0));
    }

    @Test
    public void execute_trackProgressNoBudgetSet_failure() {
        // Valid month format, but no budget is set for this month
        viewBudgetCommand.setArguments(Map.of("m/", "2024-11"));
        List<String> result = viewBudgetCommand.execute();

        assertEquals(ErrorMessages.BUDGET_NOT_FOUND,
                result.get(0));
    }

    @Test
    public void execute_trackProgressFutureMonth_failure() {
        // Set a future month to check (e.g., 2 years in the future)
        YearMonth futureMonth = YearMonth.now().plusYears(2);
        viewBudgetCommand.setArguments(Map.of("m/", futureMonth.toString()));
        List<String> result = viewBudgetCommand.execute();

        assertEquals("Progress can only be checked for current or past months.", result.get(0));
    }
}
