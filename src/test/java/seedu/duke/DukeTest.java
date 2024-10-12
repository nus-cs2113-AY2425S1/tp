package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    public void categoryConstructorSuccess() {
        Category category = new Category("Entertainment");
        assertEquals("Entertainment", category.getName());
    }

    @Test
    public void categoryNameSuccess() {
        Category category = new Category("Groceries");
        assertEquals("Groceries", category.toString());
    }

    @Test
    public void getNameSuccess() {
        Category category = new Category("Transport");
        assertNotNull(category.getName());
        assertEquals("Transport", category.getName());
    }
}
