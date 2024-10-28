package wheresmymoney;

import org.junit.jupiter.api.Test;
import wheresmymoney.exception.WheresMyMoneyException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpenseTest {
    
    private Expense initExpenseWithNoNullParts() throws WheresMyMoneyException {
        return new Expense(0.0F, "desc", "category", "25-10-2024");
    }

    @Test
    public void createExpense_nullPrice_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
            () -> new Expense(null, "desc", "category", "25-10-2024"));
    }
    @Test
    public void createExpense_nullDescription_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
            () -> new Expense(0.0F, null, "category", "25-10-2024"));
    }
    @Test
    public void createExpense_nullCategory_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
            () -> new Expense(0.0F, "desc", null, "25-10-2024"));
    }
    @Test
    public void createExpense_nullDateAdded_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
            () -> new Expense(0.0F, "desc", "category", null));
    }
    

    @Test
    public void setPrice_nullInput_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
                () -> initExpenseWithNoNullParts().setPrice(null));
    }
    @Test
    public void setDescription_nullInput_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
                () -> initExpenseWithNoNullParts().setDescription(null));
    }
    @Test
    public void setCategory_nullInput_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
                () -> initExpenseWithNoNullParts().setCategory(null));
    }
    @Test
    public void setDateAdded_nullInput_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
                () -> initExpenseWithNoNullParts().setDateAdded(null));
    }
}
