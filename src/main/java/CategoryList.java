import java.util.ArrayList;
import java.util.List;

public class CategoryList {
    private List<Category> categories;

    public CategoryList() {
        categories = new ArrayList<>();
        initializeDefaultCategories();
    }

    // Initialize
    private void initializeDefaultCategories() {
        categories.add(new Category("Food"));
        categories.add(new Category("Entertainment"));
        categories.add(new Category("Transport"));
        categories.add(new Category("Utilities"));
        categories.add(new Category("Others"));
    }

    // Add Category
    public void addCategory(String categoryName) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                System.out.println("Category '" + categoryName + "' already exists!");
                return;
            }
        }
        categories.add(new Category(categoryName));
        System.out.println("Category '" + categoryName + "' added successfully.");
    }

    // Delete Category
    public void deleteCategory(String categoryName) {
        Category toDelete = null;
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                toDelete = category;
                break;
            }
        }
        if (toDelete != null) {
            categories.remove(toDelete);
            System.out.println("Category '" + categoryName + "' deleted successfully.");
        } else {
            System.out.println("Category '" + categoryName + "' not found!");
        }
    }

    // List all the category
    public void listCategories() {
        System.out.println("Available categories:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
    }


    // Get category list
    public List<Category> getCategories() {
        return categories;
    }


    public void interactiveAddCategory(String categoryName) {
        addCategory(categoryName);

    }


}

