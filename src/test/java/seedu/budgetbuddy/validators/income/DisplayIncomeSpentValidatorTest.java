package seedu.budgetbuddy.validators.income;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.income.DisplayIncomeSpentCommand;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;
import seedu.budgetbuddy.transaction.income.Income;
import seedu.budgetbuddy.transaction.income.IncomeManager;

import java.time.LocalDate;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DisplayIncomeSpentValidatorTest {

    private static final YearMonth VALID_MONTH = YearMonth.of(2024, 11);
    private static final YearMonth INVALID_MONTH = YearMonth.of(2023, 2);

    @BeforeEach
    public void setUp() {
        // Reset IncomeManager to ensure no residual data from other tests
        IncomeManager.getIncomes().clear();
    }

    @Test
    public void processCommand_validCommandWithoutMonth_returnsCommandForCurrentMonth() throws BudgetBuddyException {
        // Add mock income for current month
        IncomeManager.addIncome(new Income("Salary", 2000.0,
                LocalDate.of(2024, 11, 5)));

        // Call method with valid command without month specification
        Command command = DisplayIncomeSpentValidator.processCommand("display income spent");

        assertNotNull(command);
        assertTrue(command instanceof DisplayIncomeSpentCommand);
        DisplayIncomeSpentCommand displayCommand = (DisplayIncomeSpentCommand) command;
        assertEquals(VALID_MONTH, displayCommand.getMonth()); // Should match current month (November 2024)
    }

    @Test
    public void processCommand_validCommandWithValidMonth_returnsCommandForSpecifiedMonth()
            throws BudgetBuddyException {
        // Add dummy income for the specified month (November 2024)
        IncomeManager.addIncome(new Income("Salary", 2000.0,
                LocalDate.of(2024, 11, 5)));

        // Call method with valid command specifying a month
        Command command = DisplayIncomeSpentValidator.processCommand("display income spent m/11/2024");

        assertNotNull(command);
        assertTrue(command instanceof DisplayIncomeSpentCommand);
        DisplayIncomeSpentCommand displayCommand = (DisplayIncomeSpentCommand) command;
        assertEquals(VALID_MONTH, displayCommand.getMonth()); // Should match November 2024
    }

    @Test
    public void processCommand_invalidMonthFormat_throwsBudgetBuddyException() {
        // Call method with an invalid month format
        BudgetBuddyException thrown = assertThrows(BudgetBuddyException.class, () -> {
            DisplayIncomeSpentValidator.processCommand("display income spent m/invalid-month");
        });
        assertTrue(thrown.getMessage().contains("Invalid month format. Use m/MM/yyyy."));
    }

    @Test
    public void processCommand_noIncomeRecordedForMonth_throwsBudgetBuddyException() {
        // Call method with valid command specifying a month but no income added for that month
        BudgetBuddyException thrown = assertThrows(BudgetBuddyException.class, () -> {
            DisplayIncomeSpentValidator.processCommand("display income spent m/11/2024");
        });
        assertTrue(thrown.getMessage().contains("No income recorded for the month: 2024-11"));
    }

    @Test
    public void processCommand_validMonthNoIncome_throwsBudgetBuddyException() {
        // Add dummy income with a value of 0 for the month of November
        IncomeManager.addIncome(new Income("Salary", 0.0,
                LocalDate.of(2024, 11, 5)));

        // Call method with valid month and no income recorded for that month
        BudgetBuddyException thrown = assertThrows(BudgetBuddyException.class, () -> {
            DisplayIncomeSpentValidator.processCommand("display income spent m/11/2024");
        });
        assertTrue(thrown.getMessage().contains("No income recorded for the month: 2024-11"));
    }
}

