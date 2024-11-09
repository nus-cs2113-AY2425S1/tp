package wheresmymoney;

import org.junit.jupiter.api.Test;
import wheresmymoney.exception.WheresMyMoneyException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ReccuringExpenseTest {
    
    private RecurringExpense initRecurringExpenseWithNoNullParts() throws WheresMyMoneyException {
        return new RecurringExpense(0.0F, "desc", "category", "25-10-2024", "daily");
    }

    @Test
    public void createRecurringExpense_nullPrice_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
            () -> new RecurringExpense(null, "desc", "category", "25-10-2024", "daily"));
    }

    @Test
    public void createRecurringExpense_nullDescription_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
            () -> new RecurringExpense(0.0F, null, "category", "25-10-2024", "daily"));
    }

    @Test
    public void createRecurringExpense_nullCategory_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
            () -> new RecurringExpense(0.0F, "desc", null, "25-10-2024", "daily"));
    }

    @Test
    public void createRecurringExpense_nullDateAdded_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
            () -> new RecurringExpense(0.0F, "desc", "category", null, "daily"));
    }

    @Test
    public void createRecurringExpense_nullFrequencyAdded_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
            () -> new RecurringExpense(0.0F, "desc", "category", "25-10-2024", null));
    }
    
    @Test
    public void setPrice_nullInput_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
                () -> initRecurringExpenseWithNoNullParts().setPrice(null));
    }

    @Test
    public void setDescription_nullInput_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
                () -> initRecurringExpenseWithNoNullParts().setDescription(null));
    }

    @Test
    public void setCategory_nullInput_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
                () -> initRecurringExpenseWithNoNullParts().setCategory(null));
    }

    @Test
    public void setLastAddedDate_nullInput_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
                () -> initRecurringExpenseWithNoNullParts().setLastAddedDate(null));
    }

    @Test
    public void setLastAddedDate_wrongDateFormat_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
                () -> initRecurringExpenseWithNoNullParts().setLastAddedDate("2024-10-25"));
    }

    @Test
    public void setFrequency_nullInput_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
                () -> initRecurringExpenseWithNoNullParts().setFrequency(null));
    }

    @Test
    public void setFrequency_wrongInput_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
                () -> initRecurringExpenseWithNoNullParts().setFrequency("test"));
    }

    @Test
    public void setFrequency_emptyInput_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
                () -> initRecurringExpenseWithNoNullParts().setFrequency(""));
    }
}
