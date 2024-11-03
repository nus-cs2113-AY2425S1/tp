package seedu.command;

import seedu.category.Category;
import seedu.category.CategoryList;
import seedu.datastorage.Storage;
import seedu.message.ErrorMessages;
import seedu.message.CommandResultMessages;

import java.util.ArrayList;
import java.util.List;

// Command class for adding a new category
public class AddCategoryCommand extends Command {
    public static final String COMMAND_WORD = "add-category";
    // A guide or description of the command
    public static final String COMMAND_GUIDE = "add-category CATEGORY_NAME: Add a new category";
    public static final String[] COMMAND_MANDATORY_KEYWORDS = { "" };

    private CategoryList categoryList;

    public AddCategoryCommand(CategoryList categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public List<String> execute() {
        if (!isArgumentsValid()) {
            List<String> messages = new ArrayList<>();
            messages.add(ErrorMessages.LACK_ARGUMENTS_ERROR_MESSAGE);
            messages.add(COMMAND_GUIDE);
            return messages;
        }

        String categoryName = arguments.get("");
        Category category = new Category(categoryName);
        Category temp = categoryList.addCategory(category);

        if(temp == null){
            return List.of(CommandResultMessages.ADD_CATEGORY_FAIL + ErrorMessages.DUPLICATED_CATEGORY);
        }

        Storage.saveCategory(categoryList.getCategories());
        return List.of(CommandResultMessages.ADD_CATEGORY_SUCCESS + categoryName);
    }
    @Override
    protected String[] getMandatoryKeywords() {
        return COMMAND_MANDATORY_KEYWORDS;
    }

    @Override
    protected String[] getExtraKeywords() {
        return new String[0];
    }

    @Override
    protected String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    protected String getCommandGuide() {
        return COMMAND_GUIDE;
    }
}
