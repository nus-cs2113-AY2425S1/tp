package wheresmymoney;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import wheresmymoney.exception.WheresMyMoneyException;

class CategoryTrackerTest {
    
    @Test
    void getCategoryDataOf_categoryInMap_returnsCategoryData() {
        try {
            CategoryTracker tracker = new CategoryTracker();
            tracker.addCategory("category", 0F);
            CategoryData actual = tracker.getCategoryDataOf("category");
            CategoryData expected = new CategoryData(0F, 100F);
            assertEquals(actual.getCurrExpenditure(), expected.getCurrExpenditure());
            assertEquals(actual.getMaxExpenditure(), expected.getMaxExpenditure());
        } catch (WheresMyMoneyException e) {
            fail("Exception thrown when Category and Price are valid.");
        }
    }
    @Test
    void getCategoryDataOf_categoryNotInMap_throwsWheresMyMoneyException() {
        CategoryTracker tracker = new CategoryTracker();
        assertThrows(WheresMyMoneyException.class,
                () -> tracker.getCategoryDataOf("category"));
    }
    
    @Test
    void addCategory_categoryNotInMap_categoryAddedToMap() {
        CategoryTracker tracker = new CategoryTracker();
    }
    @Test
    void addCategory_categoryInMap_incrementsThatCategoryCurrExpenditure() {
        CategoryTracker tracker = new CategoryTracker();
    }
    
    @Test
    void deleteCategory_categoryInMap_decrementsCurrExpenditure() {
        CategoryTracker tracker = new CategoryTracker();
    }
    @Test
    void deleteCategory_categoryInMap_removesCategoryFromMap() {
        CategoryTracker tracker = new CategoryTracker();
    }
}
