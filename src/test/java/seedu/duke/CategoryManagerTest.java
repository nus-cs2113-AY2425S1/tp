package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryManagerTest {
    @Test
    void addCategoryNewCategory() {
        CategoryManager categoryManager = new CategoryManager();
        TrackerData trackerData = new TrackerData();

        categoryManager.addCategory(trackerData, "add-category Food");

        assertEquals(1, trackerData.getCategories().size());
        assertEquals("Food", trackerData.getCategories().get(0).getName());
    }

    @Test
    void addCategoryExistingCategory() {
        CategoryManager categoryManager = new CategoryManager();
        TrackerData trackerData = new TrackerData();

        trackerData.getCategories().add(new Category("Food"));

        categoryManager.addCategory(trackerData, "add-category Food");

        assertEquals(1, trackerData.getCategories().size());
    }

    @Test
    void addCategory_doesNotAddIfNameIsEmpty() {
        CategoryManager categoryManager = new CategoryManager();
        TrackerData trackerData = new TrackerData();

        categoryManager.addCategory(trackerData, "add-category  ");

        assertTrue(trackerData.getCategories().isEmpty());
    }

}
