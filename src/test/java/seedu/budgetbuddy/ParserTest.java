package seedu.budgetbuddy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.IncorrectCommand;
import seedu.budgetbuddy.commands.expense.AddExpenseCommand;
import seedu.budgetbuddy.commands.budget.AddBudgetCommand;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.budget.Budget;
import seedu.budgetbuddy.transaction.budget.BudgetManager;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.transaction.income.IncomeManager;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        ExpenseManager expenseManager = new ExpenseManager();
        IncomeManager incomeManager = new IncomeManager();
        BudgetManager budgetManager = new BudgetManager();
        parser = new Parser(expenseManager, incomeManager, budgetManager);
    }

    @Test
    public void parseCommand_validAddExpenseCommand_returnsAddExpenseCommand() throws BudgetBuddyException {
        String command = "add expense KFC lunch a/10.50 d/28/10/2024 c/FOOD";

        Command result = parser.parseCommand(command);

        assertInstanceOf(AddExpenseCommand.class, result);
    }

    @Test
    public void parseCommand_validAddBudgetCommand_returnsAddBudgetCommand() throws BudgetBuddyException {
        String command = "add budget a/700 m/11/2024 c/TRANSPORT";

        Command result = parser.parseCommand(command);

        assertInstanceOf(AddBudgetCommand.class, result);
    }

    @Test
    public void parseCommand_invalidCommand_returnsIncorrectCommand() throws BudgetBuddyException {
        String command = "invalid command";

        Command result = parser.parseCommand(command);

        assertInstanceOf(IncorrectCommand.class, result);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        assertEquals("Invalid input", incorrectCommand.getFeedbackToUser());
    }

    @Test
    public void parseFile_validExpenseLine_parsesExpenseCorrectly() {
        // Given a valid expense input line
        String input = "expense | Lunch | 15 | 10/10/2024 | OTHERS";

        // Parse the file input (use the class name since parseFile is static)
        Parser.parseFile(input);

        Expense expense= ExpenseManager.getExpenses().get(0);
        assertEquals(expense.getAmount(), 15);
        assertEquals(expense.getDescription(), "Lunch");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate expectedDate = LocalDate.parse("10/10/2024", formatter);
        assertEquals(expectedDate, expense.getDate());

        assertEquals(Category.OTHERS, expense.getCategory());
    }

    @Test
    public void parseFile_validBudgetLine_parsesBudgetCorrectly() {
        // Given a valid budget input line
        String input = "budget | Budget for October | 2024-10 | {food=100, entertainment=50}";

        // Parse the file input (use the class name)
        Parser.parseFile(input);

        Budget budget = BudgetManager.getBudgets().get(0);
        assertEquals(budget.getCategoryBudgetAmount(Category.FOOD), 100);
        assertEquals(budget.getCategoryBudgetAmount(Category.ENTERTAINMENT), 50);

        YearMonth expectedDate = YearMonth.parse("2024-10");
        assertEquals(budget.getDate(), expectedDate);
    }
}


