package seedu.command;

import seedu.category.Category;
import seedu.category.CategoryList;
import seedu.datastorage.Storage;
import seedu.message.ErrorMessages;
import seedu.message.CommandResultMessages;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the "delete-category" command to delete an existing category.
 * <p>
 * This command allows users to delete a category from the existing list of categories.
 * </p>
 */
public class DeleteCategoryCommand extends Command {
    /** Command word for invoking this command. */
    public static final String COMMAND_WORD = "delete-category";
    /** Command usage guide for users. */
    public static final String COMMAND_GUIDE = "delete-category CATEGORY_NAME: Delete an existing category";
    /** Mandatory keywords required for the command to execute correctly. */
    public static final String[] COMMAND_MANDATORY_KEYWORDS = { "" };

    /** The list of categories from which the category will be deleted. */
    private CategoryList categoryList;

    /**
     * Constructs a DeleteCategoryCommand with the specified CategoryList.
     *
     * @param categoryList The list of categories from which the category will be deleted.
     */
    public DeleteCategoryCommand(CategoryList categoryList) {
        this.categoryList = categoryList;
    }

    /**
     * Default constructor for DeleteCategoryCommand.
     */
    public DeleteCategoryCommand() {
        // Default constructor
    }

    /**
     * Executes the "delete-category" command to delete a category from the list.
     *
     * @return A list of strings containing either error messages or success messages.
     */
    @Override
    public List<String> execute() {
        if (!isArgumentsValid()) {
            List<String> messages = new ArrayList<>();
            messages.add(ErrorMessages.LACK_ARGUMENTS_ERROR_MESSAGE);
            messages.add(COMMAND_GUIDE);
            return messages;
        }
        String categoryName = arguments.get("");
        Category temp = categoryList.deleteCategory(categoryName);

        if (temp == null) {
            return List.of(CommandResultMessages.DELETE_CATEGORY_FAIL + ErrorMessages.CATEGORY_NOT_FOUND);
        }

        Storage.saveCategory(categoryList.getCategories());
        return List.of(CommandResultMessages.DELETE_CATEGORY_SUCCESS + categoryName);
    }

    /**
     * Returns the mandatory keywords for this command.
     *
     * @return An array of mandatory keywords.
     */
    @Override
    protected String[] getMandatoryKeywords() {
        return COMMAND_MANDATORY_KEYWORDS;
    }

    /**
     * Returns any extra keywords supported by this command.
     *
     * @return An array of extra keywords.
     */
    @Override
    protected String[] getExtraKeywords() {
        return new String[0];
    }

    /**
     * Returns the word used to invoke this command.
     *
     * @return The command word.
     */
    @Override
    protected String getCommandWord() {
        return COMMAND_WORD;
    }

    /**
     * Returns a guide on how to use this command.
     *
     * @return The command usage guide.
     */
    @Override
    protected String getCommandGuide() {
        return COMMAND_GUIDE;
    }
}
