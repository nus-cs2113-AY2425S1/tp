package seedu.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.category.Category;
import seedu.category.CategoryList;
import seedu.message.CommandResultMessages;
import seedu.message.ErrorMessages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DeleteCategoryCommandTest {

    CategoryList categoryList;
    DeleteCategoryCommand deleteCategoryCommand;

    @BeforeEach
    public void setUp() {
        categoryList = new CategoryList();
        deleteCategoryCommand = new DeleteCategoryCommand(categoryList);
    }

    @Test
    void execute_validArgument_categoryDeleted() {
        // Arrange
        categoryList.addCategory(new Category("Sports"));
        deleteCategoryCommand.setArguments(Map.of("", "Sports"));

        // Act
        List<String> result = deleteCategoryCommand.execute();
        List<Category> categories = categoryList.getCategories();

        // Assert
        assertEquals(0, categories.size(), "The category list should be empty after deletion.");
        assertEquals("Category deleted: Sports", result.get(0), "The result message should confirm " +
                "the category was deleted.");
    }

    @Test
    void execute_categoryNotFound_errorMessage() {
        // Arrange
        deleteCategoryCommand.setArguments(Map.of("", "NonExistent"));

        // Act
        List<String> result = deleteCategoryCommand.execute();

        // Assert
        assertEquals(CommandResultMessages.DELETE_CATEGORY_FAIL + ErrorMessages.CATEGORY_NOT_FOUND,
                result.get(0), "The result message should indicate that " +
                "the category was not found.");
    }

    @Test
    public void execute_commandExecutedLackArgument_expectedOutput() {
        // Prepare test arguments
        Map<String, String> arguments = new HashMap<>();

        // Set the arguments using the method
        deleteCategoryCommand.setArguments(arguments);

        // Actual message list
        List<String> result = deleteCategoryCommand.execute();

        // Assert that the actual messages match the expected messages
        assertEquals(ErrorMessages.LACK_ARGUMENTS_ERROR_MESSAGE, result.get(0));
    }
}
