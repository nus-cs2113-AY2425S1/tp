package wheresmymoney;

import org.junit.jupiter.api.Test;
import wheresmymoney.exception.WheresMyMoneyException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpenseTest {
    
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
    public void setDateAdded_nullInput_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
                () -> initRecurringExpenseWithNoNullParts().setDateAdded(null));
    }
}
