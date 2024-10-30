package seedu.command;

import seedu.budget.BudgetTracker;
import seedu.utils.AmountUtils;

import java.util.List;

public class AddBudgetCommand extends Command {
    public static final String COMMAND_WORD = "add-budget";
    public static final String COMMAND_GUIDE = "add-budget a/ AMOUNT m/ MONTH : Add a new category";
    public static final String[] COMMAND_MANDATORY_KEYWORDS = { "a/", "m/" };
    public static final String ERROR_MESSAGE = "Error creating Budget!";
    private final BudgetTracker budgetTracker;

    public AddBudgetCommand(BudgetTracker budgetTracker) {
        this.budgetTracker = budgetTracker;
    }

    @Override
    public List<String> execute() {
        if (!isArgumentsValid()) {
            return List.of(LACK_ARGUMENTS_ERROR_MESSAGE);
        }

        String amountStr = arguments.get(COMMAND_MANDATORY_KEYWORDS[0]);
        Double amount = null;
        try {
            amount = AmountUtils.parseAmount(amountStr);
        } catch (Exception e) {
            return List.of(ERROR_MESSAGE + ": " + e.getMessage());
        }

        String monthStr = arguments.get(COMMAND_MANDATORY_KEYWORDS[1]);
        try {
            budgetTracker.setBudget(monthStr, amount);
        } catch (Exception e) {
            return List.of(ERROR_MESSAGE + ": " + e.getMessage());
        }

        return List.of("Budget added successfully!");
    }


    @Override
    protected String[] getMandatoryKeywords() {
        return COMMAND_MANDATORY_KEYWORDS;
    }

    @Override
    protected String[] getExtraKeywords() {
        return new String[0];
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
