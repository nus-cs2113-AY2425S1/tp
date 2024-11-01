package wheresmymoney;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import wheresmymoney.exception.WheresMyMoneyException;

class CategoryDataTest {
    
    private CategoryData initCategoryDataWithNoNullParts() throws WheresMyMoneyException {
        return new CategoryData(50.0F, 100.0F);
    }
    
    @Test
    public void createCategoryData_nullCurrExpenditure_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
            () -> new CategoryData(null));
    }
    @Test
    public void createCategoryData_nullCurrExpenditureWithNotNullMaxExpenditure_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
            () -> new CategoryData(null, 100.00F));
    }
    @Test
    public void createCategoryData_NotNullCurrExpenditureWithNullMaxExpenditure_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
            () -> new CategoryData(50.00F, null));
    }
    
    @Test
    public void setCurrExpenditure_nullInput_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
            () -> initCategoryDataWithNoNullParts().setCurrExpenditure(null));
    }
    @Test
    public void setMaxExpenditure_nullInput_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
            () -> initCategoryDataWithNoNullParts().setMaxExpenditure(null));
    }
    
    @Test
    public void increaseCurrExpenditureBy_nullInput_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
            () -> initCategoryDataWithNoNullParts().increaseCurrExpenditureBy(null));
    }
    @Test
    public void decreaseCurrExpenditureBy_nullInput_throwsWheresMyMoneyException() {
        assertThrows(WheresMyMoneyException.class,
            () -> initCategoryDataWithNoNullParts().decreaseCurrExpenditureBy(null));
    }
    
    @Test
    public void isNearingLimit_expenditureBelow80Percent_returnsFalse() throws WheresMyMoneyException {
        CategoryData categoryData = new CategoryData(79.99F, 100.0F);
        assertFalse(categoryData.isNearingLimit());
    }
    @Test
    public void isNearingLimit_expenditureEquals80Percent_returnsTrue() throws WheresMyMoneyException {
        CategoryData categoryData = new CategoryData(80.00F, 100.0F);
        assertTrue(categoryData.isNearingLimit());
    }
    @Test
    public void isNearingLimit_expenditureAbove80Percent_returnsTrue() throws WheresMyMoneyException {
        CategoryData categoryData = new CategoryData(80.01F, 100.0F);
        assertTrue(categoryData.isNearingLimit());
    }
    @Test
    public void hasExceededLimit_expenditureBelowMax_returnsFalse() throws WheresMyMoneyException {
        CategoryData categoryData = new CategoryData(99.99F, 100.00F);
        assertFalse(categoryData.hasExceededLimit());
    }
    @Test
    public void hasExceededLimit_expenditureEqualsMax_returnsFalse() throws WheresMyMoneyException {
        CategoryData categoryData = new CategoryData(100.00F, 100.00F);
        assertFalse(categoryData.hasExceededLimit());
    }
    @Test
    public void hasExceededLimit_expenditureAboveMax_returnsTrue() throws WheresMyMoneyException {
        CategoryData categoryData = new CategoryData(100.01F, 100.00F);
        assertTrue(categoryData.hasExceededLimit());
    }
}
