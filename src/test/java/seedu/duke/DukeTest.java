package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ExpenseTest {
    @Test
    public void expenseConstructor() {
        Category category = new Category("Food");
        Expense expense = new Expense("Lunch", 12.50, category);
        assertEquals("Lunch", expense.getName());
        assertEquals(12.50, expense.getAmount());
        assertEquals(category, expense.getCategory());
    }
}

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
