package wheresmymoney;

import java.util.HashMap;

import wheresmymoney.exception.WheresMyMoneyException;

public class CategoryTracker {
    private HashMap<String, CategoryData> tracker;
    
    public CategoryTracker() {
        this.tracker = new HashMap<>();
    }
    private CategoryData getCategoryInfo(String category) throws WheresMyMoneyException {
        if (!tracker.containsKey(category)) {
            throw new WheresMyMoneyException("No such category exists.");
        }
        return tracker.get(category);
    }
    
    public void addCategory(String category, Float price) throws WheresMyMoneyException {
        if (tracker.containsKey(category)) {
            CategoryData categoryData = getCategoryInfo(category);
            assert categoryData != null : "Category exists.";
            categoryData.addToCurrExpenditure(price);
        } else {
            CategoryData categoryData = new CategoryData(price);
            tracker.put(category, categoryData);
        }
    }
    public void editCategory(String oldCategory, String newCategory, Float price) throws WheresMyMoneyException {
        deleteCategory(oldCategory, price);
        addCategory(newCategory, price);
    }
    public void deleteCategory(String category, Float price) throws WheresMyMoneyException {
        CategoryData categoryData = getCategoryInfo(category);
        assert categoryData != null : "Category exists.";
        categoryData.removeFromCurrExpenditure(price);
        if (categoryData.getCurrExpenditure() <= 0) {
            tracker.remove(category);
        }
    }
}
