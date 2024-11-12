package seedu.budgetbuddy.validators;

import seedu.budgetbuddy.transaction.Category;

/**
 * Validates category input for transactions.
 */
public class CategoryValidator {

    /**
     * Parses the category from the command part.
     *
     * @param part The command part containing the category.
     * @return The parsed category or OTHERS if invalid.
     */
    public static Category validateCategory(String part) {
        String categoryStr = part.substring(2).toUpperCase();
        try {
            return Category.valueOf(categoryStr);
        } catch (IllegalArgumentException e) {
            return Category.OTHERS;  // Default category if invalid
        }
    }

    /**
     * Parses the category from user inputs. Used for any interaction with data extraction from existing list
     * (example: edit operations)
     *
     * @param part The command part containing the category.
     * @return The parsed category or OTHERS if invalid.
     */
    public static Category validateSearchCategory(String part) {
        String categoryStr = part.substring(2).toUpperCase();
        try {
            return Category.valueOf(categoryStr);
        } catch (IllegalArgumentException e) {
            return null;  // Default category if invalid
        }
    }
}
