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
    void addCategory_categoryInMap_incrementsThatCategoryCurrExpenditure() {
        CategoryTracker tracker = new CategoryTracker();
        try {
            tracker.addCategory("category", 23.00F);
            assertEquals(1, tracker.size());
            tracker.addCategory("category", 46.00F);
            assertEquals(1, tracker.size());
            Float newCurrExpenditure = tracker.getCategoryDataOf("category").getCurrExpenditure();
            assertEquals(69.00F, newCurrExpenditure);
        } catch (WheresMyMoneyException e) {
            fail("Exception thrown when inputs are not null.");
        }
    }
    @Test
    void addCategory_categoryNotInMap_categoryAddedToMap() {
        CategoryTracker tracker = new CategoryTracker();
        try {
            assertEquals(0, tracker.size());
            tracker.addCategory("category", 10.00F);
            assertEquals(1, tracker.size());
        } catch (WheresMyMoneyException e) {
            fail("Exception thrown when inputs are not null.");
        }
    }
    
    @Test
    void deleteCategory_categoryInMap_decrementsCurrExpenditure() {
        CategoryTracker tracker = new CategoryTracker();
        try {
            tracker.addCategory("category", 100.00F);
            assertEquals(1, tracker.size());
            tracker.deleteCategory("category", 31.00F);
            assertEquals(1, tracker.size());
            Float newCurrExpenditure = tracker.getCategoryDataOf("category").getCurrExpenditure();
            assertEquals(69.00F, newCurrExpenditure);
        } catch (WheresMyMoneyException e) {
            fail("Exception thrown when inputs are not null.");
        }
    }
    @Test
    void deleteCategory_categoryInMap_removesCategoryFromMap() {
        CategoryTracker tracker = new CategoryTracker();
        try {
            tracker.addCategory("category", 100.00F);
            assertEquals(1, tracker.size());
            tracker.deleteCategory("category", 100.00F);
            assertEquals(0, tracker.size());
        } catch (WheresMyMoneyException e) {
            fail("Exception thrown when inputs are not null.");
        }
    }
    @Test
    void deleteCategory_categoryNotInMap_throwsWheresMyMoneyException() {
        CategoryTracker tracker = new CategoryTracker();
        assertThrows(WheresMyMoneyException.class,
                () -> tracker.deleteCategory("category", 420.00F));
    }
    
}
