package seedu.command;

import seedu.budget.BudgetTracker;
import seedu.exceptions.InvalidDateFormatException;
import seedu.message.CommandResultMessages;
import seedu.message.ErrorMessages;
import java.time.YearMonth;
import java.util.List;
import static seedu.utils.DateTimeUtils.parseYearMonth;

public class ViewBudgetCommand extends Command {
    public static final String COMMAND_WORD = "view-budget";
    public static final String COMMAND_GUIDE = "view-budget [m/ MONTH]: view all budgets, or view budget for a specific month " +
            "Track your progress towards your budget for a certain month";
    public static final String[] COMMAND_MANDATORY_KEYWORDS = {};
    public static final String[] COMMAND_EXTRA_KEYWORDS = {"m/"};

    private final BudgetTracker budgetTracker;

    public ViewBudgetCommand(BudgetTracker budgetTracker) {
        this.budgetTracker = budgetTracker;
    }

    @Override
    public List<String> execute() {
        if (!isArgumentsValid()) {
            return List.of(ErrorMessages.LACK_ARGUMENTS_ERROR_MESSAGE);
        }
        String monthStr = arguments.get(COMMAND_EXTRA_KEYWORDS[0]);

        if (monthStr != null) {
            // Specific month view
            YearMonth month;
            try {
                month = parseYearMonth(monthStr);
            } catch (InvalidDateFormatException e) {
                return List.of(CommandResultMessages.TRACK_PROGRESS_FAIL + ErrorMessages.MESSAGE_INVALID_YEAR_MONTH_FORMAT);
            } catch (Exception e) {
                return List.of(ErrorMessages.UNEXPECTED_ERROR_MESSAGE + e.getMessage());
            }
            return List.of(budgetTracker.checkBudgetProgress(month));
        } else {
            // All budgets view
            return budgetTracker.getAllBudgets();
        }

    }

    @Override
    protected String[] getMandatoryKeywords() {
        return COMMAND_MANDATORY_KEYWORDS;
    }

    @Override
    protected String[] getExtraKeywords() {
        return COMMAND_EXTRA_KEYWORDS;
    }

    @Override
    protected String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    protected String getCommandGuide() {
        return COMMAND_GUIDE;
    }

}
