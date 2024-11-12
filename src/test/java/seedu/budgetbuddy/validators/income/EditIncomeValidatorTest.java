package seedu.budgetbuddy.validators.income;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.income.EditIncomeCommand;
import seedu.budgetbuddy.transaction.income.Income;
import seedu.budgetbuddy.transaction.income.IncomeManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EditIncomeValidatorTest {

    private IncomeManager testIncomeManager;

    void createTestCase (){
        testIncomeManager = new IncomeManager();
        Income testIncome = new Income("testExpense", 12.00, LocalDate.now());
        testIncomeManager.addIncome(testIncome);
    }

    @Test
    void processFirstCommand_validIndexGiven_expectEditExpenseCommandType() {
        createTestCase();
        String userInput = "edit income 1";
        Command validCommand = EditIncomeValidator.processFirstCommand(userInput);
        assertEquals(EditIncomeCommand.class, validCommand.getClass());
    }

    @Test
    void processFirstCommand_invalidInputGiven_expectIncorrectCommandType() {
        String userInput = "edit income 50";
        Command invalidCommand = EditIncomeValidator.processFirstCommand(userInput);
        assertEquals(IncorrectCommand.class ,invalidCommand.getClass());
    }

    @Test
    void processSecondCommand_validAmountGiven_expectTrue() {
        String userInput = "a/500";
        Boolean validResult = EditIncomeValidator.processSecondCommand(userInput);
        assertEquals(true, validResult);
    }

    @Test
    void processSecondCommand_validDateGiven_expectTrue() {
        String userInput = "d/12/10/2024";
        Boolean validResult = EditIncomeValidator.processSecondCommand(userInput);
        assertEquals(true, validResult);
    }

    @Test
    void processSecondCommand_emptyInputGiven_expectFalse() {
        String userInput = "";
        Boolean invalidResult = EditIncomeValidator.processSecondCommand(userInput);
        assertEquals(false, invalidResult);
    }

    @Test
    void processSecondCommand_invalidAmountGiven_expectFalse() {
        String userInput = "a/-500";
        Boolean invalidResult = EditIncomeValidator.processSecondCommand(userInput);
        assertEquals(false, invalidResult);
    }

    @Test
    void processSecondCommand_invalidDateGiven_expectFalse() {
        String userInput = "d/202020202";
        Boolean invalidResult = EditIncomeValidator.processSecondCommand(userInput);
        assertEquals(false, invalidResult);
    }
}
