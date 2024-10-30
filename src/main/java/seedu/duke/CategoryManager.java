package seedu.duke;

import java.util.List;

public class CategoryManager {

    public void addCategory(TrackerData trackerData, String categoryName) {
        List<Category> categories = trackerData.getCategories();
        String trimmedCategoryName = categoryName.substring("add category".length()).trim();
        if (trimmedCategoryName.isEmpty()) {
            System.out.println("Category name is empty!");
            return;
        }

        String formattedCategoryName = formatInput(trimmedCategoryName.trim());
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(formattedCategoryName)) {
                System.out.println("Category '" + formattedCategoryName + "' already exists!");
                return;
            }
        }
        Category newCategory = new Category(formattedCategoryName);
        categories.add(newCategory);
        trackerData.setCategories(categories);
        System.out.println("Category '" + newCategory + "' added successfully.");
    }

//    public Category findCategoryByName(TrackerData trackerData, String categoryName) {
//        List<Category> categories = trackerData.getCategories();
//        String formattedCategoryName = formatInput(categoryName.trim());
//
//        for (Category category : categories) {
//            if (category.getName().equalsIgnoreCase(formattedCategoryName)) {
//                return category;
//            }
//        }
//        return null;
//    }

    private String formatInput(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}

