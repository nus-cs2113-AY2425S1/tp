package wheresmymoney;

import java.util.HashMap;

import wheresmymoney.exception.WheresMyMoneyException;

public class CategoryTracker {
    private HashMap<String, CategoryData> tracker;
    
    public CategoryTracker() {
        this.tracker = new HashMap<>();
    }
    private CategoryData getCategoryDataOf(String category) throws WheresMyMoneyException {
        if (!tracker.containsKey(category)) {
            throw new WheresMyMoneyException("No such category exists.");
        }
        return tracker.get(category);
    }
    private void checkLimitOf(String category) throws WheresMyMoneyException {
        CategoryData categoryData = getCategoryDataOf(category);
        if (categoryData.hasExceededLimit()) {
            System.out.println("Alert! You have exceeded the spending limit for this category: " + category);
        } else if (categoryData.isNearingLimit()) {
            System.out.println("Warning! You are close to the spending limit for this category: " + category);
        }
    }
    
    public void addCategory(String category, Float price) throws WheresMyMoneyException {
        if (tracker.containsKey(category)) {
            CategoryData categoryData = getCategoryDataOf(category);
            assert categoryData != null : "Category exists.";
            categoryData.increaseCurrExpenditureBy(price);
        } else {
            CategoryData categoryData = new CategoryData(price);
            tracker.put(category, categoryData);
        }
        checkLimitOf(category);
    }
    public void editCategory(String oldCategory, String newCategory, Float price) throws WheresMyMoneyException {
        if (!oldCategory.equals(newCategory)) {
            deleteCategory(oldCategory, price);
            addCategory(newCategory, price);
        }
    }
    public void deleteCategory(String category, Float price) throws WheresMyMoneyException {
        CategoryData categoryData = getCategoryDataOf(category);
        assert categoryData != null : "Category exists.";
        categoryData.decreaseCurrExpenditureBy(price);
        if (categoryData.getCurrExpenditure() <= 0) {
            tracker.remove(category);
        }
    }
}
