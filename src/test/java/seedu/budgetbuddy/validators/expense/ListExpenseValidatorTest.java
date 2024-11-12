package seedu.budgetbuddy.validators.expense;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.expense.ListExpenseCommand;
import seedu.budgetbuddy.transaction.Category;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.budgetbuddy.validators.DateValidator.validateYearMonth;

class DisplayExpenseValidatorTest {

    @Test
    void checkListType_categoryIsEmpty_expectNullCategory(){
        ListExpenseValidator expenseValidator = new ListExpenseValidator();
        Category category = null;
        YearMonth yearMonth = validateYearMonth("02/2024");
        ListExpenseCommand command = (ListExpenseCommand) expenseValidator.checkListType(category, yearMonth);
        assertEquals(null, command.getCategory());
        assertEquals(yearMonth,command.getMonth());
    }

    @Test
    void checkListType_monthIsEmpty_expectNullMonth(){
        ListExpenseValidator expenseValidator = new ListExpenseValidator();
        Category category = Category.FOOD;
        YearMonth yearMonth = null;
        ListExpenseCommand command = (ListExpenseCommand) expenseValidator.checkListType(category, yearMonth);
        assertEquals(category, command.getCategory());
        assertEquals(yearMonth,command.getMonth());
    }

    @Test
    void checkListType_categoryMonthSpecified_expectSameValues(){
        ListExpenseValidator expenseValidator = new ListExpenseValidator();
        Category category = Category.FOOD;
        YearMonth yearMonth = validateYearMonth("02/2024");
        ListExpenseCommand command = (ListExpenseCommand) expenseValidator.checkListType(category, yearMonth);
        assertEquals(category, command.getCategory());
        assertEquals(yearMonth,command.getMonth());
    }

    @Test
    void processCommand_validInput_expectListExpenseCommandType(){
        String userInput = "list expenses";
        Command validCommand = ListExpenseValidator.processCommand(userInput);
        assertEquals(validCommand.getClass(), ListExpenseCommand.class);
    }

    @Test
    void processCommand_validInputWithMonth_expectListExpenseCommandType(){
        String userInput = "list expenses m/12/2024";
        Command validCommand = ListExpenseValidator.processCommand(userInput);
        assertEquals(validCommand.getClass(), ListExpenseCommand.class);
    }

    @Test
    void processCommand_validInputWithCategory_expectListExpenseCommandType(){
        String userInput = "list expenses c/food";
        Command validCommand = ListExpenseValidator.processCommand(userInput);
        assertEquals(validCommand.getClass(), ListExpenseCommand.class);
    }

    @Test
    void processCommand_validInputWithMonthAndCategory_expectListExpenseCommandType(){
        String userInput = "list expenses m/12/2024 c/food";
        Command validCommand = ListExpenseValidator.processCommand(userInput);
        assertEquals(validCommand.getClass(), ListExpenseCommand.class);
    }

    @Test
    void processCommand_invalidInput_expectInvalidCommandType(){
        String userInput = "list expenses c/invalid";
        Command invalidCommand = ListExpenseValidator.processCommand(userInput);
        assertEquals(invalidCommand.getClass(), IncorrectCommand.class);
    }
}
