package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialList;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;




class EditEntryCommandTest {

    private FinancialList financialList;

    @BeforeEach
    void setUp() {
        financialList = new FinancialList();
        financialList.addEntry(new Expense(100.0, "Initial Entry", LocalDate.now()));
    }


    @Test
    void testEditEntryCommand_nullFinancialList() {
        EditEntryCommand command = new EditEntryCommand(1, 50.0, "Groceries", "01/10/23");

        Exception exception = assertThrows(FinanceBuddyException.class, () -> {
            command.execute(null);
        });

        String expectedMessage = "Financial list cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test 
    void testEditEntryCommand_editExpense() throws FinanceBuddyException {
        EditEntryCommand command = new EditEntryCommand(1, 50.0, "Groceries", "01/10/23");
        command.execute(financialList);

        assertEquals(1, financialList.getEntryCount());
        assertEquals(50.0, financialList.getEntry(0).getAmount());
        assertEquals("Groceries", financialList.getEntry(0).getDescription());
        assertEquals(LocalDate.of(2023, 10, 1), financialList.getEntry(0).getDate());
    }
}