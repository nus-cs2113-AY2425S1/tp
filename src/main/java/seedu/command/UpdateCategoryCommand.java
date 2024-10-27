package seedu.command;

import seedu.category.Category;
import seedu.transaction.TransactionEditor;
import java.util.List;

public class UpdateCategoryCommand extends Command {
    private final int transactionIndex;
    private final Category newCategory;
    private final TransactionEditor transactionEditor;

    public UpdateCategoryCommand(int transactionIndex, Category newCategory, TransactionEditor transactionEditor) {
        this.transactionIndex = transactionIndex;
        this.newCategory = newCategory;
        this.transactionEditor = transactionEditor;
    }

    @Override
    public List<String> execute() {
        boolean isUpdated = transactionEditor.updateCategory(transactionIndex, newCategory);
        if (isUpdated) {
            return List.of("Category updated successfully for transaction at index " +  transactionIndex);
        } else {
            return List.of("Failed to update category. Please check the transaction index.");
        }
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
