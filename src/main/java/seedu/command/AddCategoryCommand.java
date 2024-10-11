package seedu.command;

import java.util.List;
import java.util.Map;
import seedu.Category;
import seedu.CategoryList;



// Command class for adding a new category
public class AddCategoryCommand extends Command {
    public static final String COMMAND_WORD = "add_category";
    public static final String[] COMMAND_MANDATORY_KEYWORDS = { "name" };

    private CategoryList categoryList;

    public AddCategoryCommand(CategoryList categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public List<String> execute() {
        if (!isArgumentsValid()) {
            return List.of(LACK_ARGUMENTS_ERROR_MESSAGE);
        }
        String categoryName = arguments.get("name");
        Category category = new Category(categoryName);
        categoryList.addCategory(String.valueOf(category));
        return List.of("Category added: " + categoryName);
    }

    @Override
    protected String[] getMandatoryKeywords() {
        return new String[0];
    }

    @Override
    protected String[] getExtraKeywords() {
        return new String[0];
    }

    @Override
    protected String getCommandWord() {
        return "";
    }

    @Override
    protected String getCommandGuide() {
        return "";
    }
}