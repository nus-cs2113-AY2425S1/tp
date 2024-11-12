package seedu.budgetbuddy.validators.expense;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.expense.EditExpenseCommand;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EditExpenseValidatorTest {

    private ExpenseManager testExpenseManager;

    void createTestCase (){
        testExpenseManager = new ExpenseManager();
        Expense testExpense = new Expense("testExpense", 12.00, LocalDate.now(), Category.OTHERS);
        testExpenseManager.addExpense(testExpense);
    }

    @Test
    void processFirstCommand_validIndexGiven_expectEditExpenseCommandType() {
        createTestCase();
        String userInput = "edit expense 1";
        Command validCommand = EditExpenseValidator.processFirstCommand(userInput);
        assertEquals(EditExpenseCommand.class, validCommand.getClass());
    }

    @Test
    void processFirstCommand_invalidInputGiven_expectIncorrectCommandType() {
        String userInput = "edit expense 50";
        Command invalidCommand = EditExpenseValidator.processFirstCommand(userInput);
        assertEquals(IncorrectCommand.class ,invalidCommand.getClass());
    }

    @Test
    void processSecondCommand_validAmountGiven_expectTrue() {
        String userInput = "a/500";
        Boolean validResult = EditExpenseValidator.processSecondCommand(userInput);
        assertEquals(true, validResult);
    }

    @Test
    void processSecondCommand_validCategoryGiven_expectTrue() {
        String userInput = "c/food";
        Boolean validResult = EditExpenseValidator.processSecondCommand(userInput);
        assertEquals(true, validResult);
    }

    @Test
    void processSecondCommand_validDateGiven_expectTrue() {
        String userInput = "d/12/10/2024";
        Boolean validResult = EditExpenseValidator.processSecondCommand(userInput);
        assertEquals(true, validResult);
    }

    @Test
    void processSecondCommand_invalidAmountGiven_expectFalse() {
        String userInput = "a/-500";
        Boolean invalidResult = EditExpenseValidator.processSecondCommand(userInput);
        assertEquals(false, invalidResult);
    }
    @Test
    void processSecondCommand_invalidDateGiven_expectFalse() {
        String userInput = "d/20202020";
        Boolean invalidResult = EditExpenseValidator.processSecondCommand(userInput);
        assertEquals(false, invalidResult);
    }

    @Test
    void processSecondCommand_invalidCategoryGiven_expectFalse() {
        String userInput = "c/invalid";
        Boolean invalidResult = EditExpenseValidator.processSecondCommand(userInput);
        assertEquals(false, invalidResult);
    }
}
