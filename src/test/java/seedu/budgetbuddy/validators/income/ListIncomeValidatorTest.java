package seedu.budgetbuddy.validators.income;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.income.ListIncomeCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ListIncomeValidatorTest {

    @Test
    void processCommand_validInput_expectListExpenseCommandType(){
        String userInput = "list incomes";
        Command validCommand = ListIncomeValidator.processCommand(userInput);
        assertEquals(validCommand.getClass(), ListIncomeCommand.class);
    }

    @Test
    void processCommand_validInputWithMont_expectListExpenseCommandType(){
        String userInput = "list incomes m/12/2024";
        Command validCommand = ListIncomeValidator.processCommand(userInput);
        assertEquals(validCommand.getClass(), ListIncomeCommand.class);
    }

    @Test
    void processCommand_invalidInput_expectInvalidCommandType(){
        String userInput = "list incomes m/20/2024";
        Command invalidCommand = ListIncomeValidator.processCommand(userInput);
        assertEquals(invalidCommand.getClass(), IncorrectCommand.class);
    }
}
