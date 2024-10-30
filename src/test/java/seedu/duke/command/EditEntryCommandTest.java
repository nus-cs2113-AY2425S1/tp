package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialList;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;



class EditEntryCommandTest {

    private FinancialList financialList;

    @BeforeEach
    void setUp() throws FinanceBuddyException {
        financialList = new FinancialList();
        financialList.addEntry(new Expense(100.0, "Initial Entry", LocalDate.now()));
    }


    @Test
    void testEditEntryCommand_nullFinancialList() {
        EditEntryCommand command = new EditEntryCommand(1, 50.0, "Groceries", "01/10/23",
                Expense.Category.FOOD);

        Exception exception = assertThrows(FinanceBuddyException.class, () -> {
            command.execute(null);
        });

        String expectedMessage = "Financial list cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test 
    void testEditEntryCommand_editExpense() throws FinanceBuddyException {
        EditEntryCommand command = new EditEntryCommand(1, 50.0, "Groceries", "01/10/23",
                Expense.Category.FOOD);
        command.execute(financialList);

        assertEquals(1, financialList.getEntryCount());
        assertEquals(50.0, financialList.getEntry(0).getAmount());
        assertEquals("Groceries", financialList.getEntry(0).getDescription());
        assertEquals(LocalDate.of(2023, 10, 1), financialList.getEntry(0).getDate());
        assertEquals(Expense.Category.FOOD, ((Expense) financialList.getEntry(0)).getCategory());
    }

    @Test
    void testEditEntryCommand_editExpenseCategory() throws FinanceBuddyException {
        financialList.addEntry(new Expense(100.0, "Initial Entry", LocalDate.now()));
        EditEntryCommand command = new EditEntryCommand(2, 50.0, "Salary", "01/10/23",
                Expense.Category.FOOD);
        command.execute(financialList);

        assertEquals(2, financialList.getEntryCount());
        assertEquals(50.0, financialList.getEntry(1).getAmount());
        assertEquals("Salary", financialList.getEntry(1).getDescription());
        assertEquals(LocalDate.of(2023, 10, 1), financialList.getEntry(1).getDate());
        assertEquals(Expense.Category.FOOD, ((Expense) financialList.getEntry(1)).getCategory());
    }

    @Test
    void testEditEntryCommand_editDate() throws FinanceBuddyException {
        EditEntryCommand command = new EditEntryCommand(1, 50.0, "Groceries", "01/10/23",
                Expense.Category.FOOD);
        command.execute(financialList);

        assertEquals(1, financialList.getEntryCount());
        assertEquals(50.0, financialList.getEntry(0).getAmount());
        assertEquals("Groceries", financialList.getEntry(0).getDescription());
        assertEquals(LocalDate.of(2023, 10, 1), financialList.getEntry(0).getDate());
        assertEquals(Expense.Category.FOOD, ((Expense) financialList.getEntry(0)).getCategory());
    }

    @Test 
    void testEditEntryCommand_multipleEdits() throws FinanceBuddyException {
        EditEntryCommand command = new EditEntryCommand(1, 50.0, "Groceries", "01/10/23",
                Expense.Category.FOOD);
        command.execute(financialList);

        assertEquals(1, financialList.getEntryCount());
        assertEquals(50.0, financialList.getEntry(0).getAmount());
        assertEquals("Groceries", financialList.getEntry(0).getDescription());
        assertEquals(LocalDate.of(2023, 10, 1), financialList.getEntry(0).getDate());
        assertEquals(Expense.Category.FOOD, ((Expense) financialList.getEntry(0)).getCategory());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        command = new EditEntryCommand(1, 100.0, "Initial Entry", LocalDate.now().format(formatter),
                Expense.Category.UTILITIES);
        command.execute(financialList);

        assertEquals(1, financialList.getEntryCount());
        assertEquals(100.0, financialList.getEntry(0).getAmount());
        assertEquals("Initial Entry", financialList.getEntry(0).getDescription());
        assertEquals(LocalDate.now(), financialList.getEntry(0).getDate());
        assertEquals(Expense.Category.UTILITIES, ((Expense) financialList.getEntry(0)).getCategory());
    }

    @Test
    void testEditEntryCommand_invalidIndex() throws FinanceBuddyException {
        EditEntryCommand command = new EditEntryCommand(2, 50.0, "Groceries", "01/10/23",
                Expense.Category.FOOD);
        command.execute(financialList);

        assertEquals(1, financialList.getEntryCount());
    }
}
