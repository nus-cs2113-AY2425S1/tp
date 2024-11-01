package wheresmymoney.category;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CategoryFilter {
    private CategoryFacade categoryFacade;
    private static PriorityQueue<Map.Entry<String, CategoryData>> exceededCategories;
    private static PriorityQueue<Map.Entry<String, CategoryData>> nearingCategories;
    
    public void setCategoryFacade(CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }
    
   /**
     * Initializes and returns a new {@code PriorityQueue} sorted by the
     * categories' current total expenditure in descending order.
     *
     * @return a {@code PriorityQueue} that sorts categories by current expenditure.
     */
    private static PriorityQueue<Map.Entry<String, CategoryData>> sortCategoriesIntoHeap() {
        return new PriorityQueue<>(
                (cat1, cat2) -> cat2.getValue().getCurrExpenditure().compareTo(cat1.getValue().getCurrExpenditure())
        );
    }
    /**
     * Filters categories from the provided {@code CategoryTracker} into two heaps.
     * <p>
     * The heaps are:
     * <li> For categories that have exceeded their spending limits. <li/>
     * For categories that are close to, but not exceeded, their spending limits.
     * </p>
     */
    public void getCategoriesFiltered() {
        HashMap<String, CategoryData> tracker = categoryFacade.getCategoryTracker().getTracker();
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
    
    /**
     * Displays the categories in the provided category heap.
     * <p>
     * If the queue is not empty, it prints the provided {@code messageIfFound},
     * then a list of categories, each with its current and maximum expenditures.
     * If the queue is empty, it prints {@code messageIfNoneFound}.
     * </p>
     *
     * @param filtered the {@code PriorityQueue} containing the filtered categories to display.
     * @param messageIfFound the message to display if the queue contains categories.
     * @param messageIfNoneFound the message to display if the queue is empty.
     */
    private void displayFilteredCategories(PriorityQueue<Map.Entry<String, CategoryData>> filtered,
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
    /**
     * Displays the categories that have exceeded their spending limits.
     * If there are such categories, they are listed in descending order of current expenditure.
     * Otherwise, a message indicating that no categories have exceeded their limits is displayed.
     */
    public void displayExceededCategories() {
        displayFilteredCategories(exceededCategories,
                "These categories have exceeded their spending limits: ",
                "None of the categories have exceeded their respective spending limits.");

    }
    /**
     * Displays the categories that are nearing their spending limits.
     * If there are such categories, they are listed in descending order of current expenditure.
     * Otherwise, a message indicating that no categories are nearing their limits is displayed.
     */
    public void displayNearingCategories() {
        displayFilteredCategories(exceededCategories,
                "These categories are close to their spending limits: ",
                "None of the categories were close to their respective spending limits.");
    }
}
