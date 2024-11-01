package wheresmymoney;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CategoryFilter {
    private static PriorityQueue<Map.Entry<String, CategoryData>> exceededCategories;
    private static PriorityQueue<Map.Entry<String, CategoryData>> nearingCategories;
    
    private static PriorityQueue<Map.Entry<String, CategoryData>> sortCategoriesIntoHeap() {
        return new PriorityQueue<>(
                (cat1, cat2) -> cat2.getValue().getCurrExpenditure().compareTo(cat1.getValue().getCurrExpenditure())
        );
    }
    public static void getCategoriesFiltered(HashMap<String, CategoryData> tracker) {
        exceededCategories = sortCategoriesIntoHeap();
        nearingCategories = sortCategoriesIntoHeap();
        for (Map.Entry<String, CategoryData> entry : tracker.entrySet()) {
            CategoryData categoryData = entry.getValue();
            if (categoryData.hasExceededLimit()) {
                exceededCategories.add(entry);
            } else if (categoryData.isNearingLimit()) {
                nearingCategories.add(entry);
            }
        }
    }
    private static void displayFilteredCategories(PriorityQueue<Map.Entry<String, CategoryData>> filtered,
                                          String messageIfFound, String messageIfNoneFound) {
        if (!filtered.isEmpty()) {
            System.out.println(messageIfFound);
            int index = 1;
            while (!filtered.isEmpty()) {
                Map.Entry<String, CategoryData> entry = filtered.poll();
                System.out.println(index + ". " + entry.getKey() + ", totalling to " +
                        entry.getValue().getCurrExpenditure() + ", exceeds " + entry.getValue().getMaxExpenditure());
                index++;
            }
        } else {
            System.out.println(messageIfNoneFound);
        }
    }
    public static void displayExceededCategories() {
        displayFilteredCategories(exceededCategories,
                "These categories have exceeded their spending limits: ",
                "None of the categories have exceeded their respective spending limits.");

    }
    public static void displayNearingCategories() {
        displayFilteredCategories(exceededCategories,
                "These categories are close to their spending limits: ",
                "None of the categories were close to their respective spending limits.");
    }
}
