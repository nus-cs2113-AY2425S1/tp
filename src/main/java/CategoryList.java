import java.util.ArrayList;
import java.util.List;

public class CategoryList {
    private List<Category> categories;

    public CategoryList() {
        categories = new ArrayList<>();
        initializeDefaultCategories();
    }

    // 初始化内置类别
    private void initializeDefaultCategories() {
        categories.add(new Category("Food"));
        categories.add(new Category("Entertainment"));
        categories.add(new Category("Transport"));
        categories.add(new Category("Utilities"));
        categories.add(new Category("Others"));
    }

    // 添加自定义类别
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

    // 删除类别
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

    // 列出所有类别
    public void listCategories() {
        System.out.println("Available categories:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
    }

    // 获取类别列表
    public List<Category> getCategories() {
        return categories;
    }

    // 交互式添加类别（便于用户在记账时方便选择和添加）
    public void interactiveAddCategory(String categoryName) {
        addCategory(categoryName);

    }


}

