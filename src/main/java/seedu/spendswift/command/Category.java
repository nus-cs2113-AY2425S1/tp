//@@author glenda-1506
package seedu.spendswift.command;

/**
 * Represents a category in the SpendSwift application.
 * A {@code Category} is used to classify different types of expenses or income.
 * Each category has a name that uniquely identifies it.
 */
public class Category {

    /**
     * The name of a category.
     */
    private String name;

    /**
     * Constructs a {@code Category} with the specified name.
     * @param name The name of the category. It should not be null or empty.
     */
    public Category(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name for this category.
     * @return name The name of this category.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the string representation of this category.
     * This typically returns the name of the category.
     *
     * @return The name of the category as a string.
     */
    @Override
    public String toString() {
        return name;
    }
}
