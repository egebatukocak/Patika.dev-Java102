import java.util.ArrayList;
import java.util.List;

public class CategoryController {
    private static List<Category> categories = new ArrayList<>();
    private static int lastCategoryId;

    public CategoryController() {
        createDefaultObjects();
    }

    private void createDefaultObjects() {
        Category cellphone = new Category(++lastCategoryId, "Cep Telefonu");
        Category notebook = new Category(++lastCategoryId, "Notebook");
        categories.add(cellphone);
        categories.add(notebook);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Category getCategoryByName(String name) {
        for (Category category : categories) {
            if (category.getCategoryName().equalsIgnoreCase(name)) return category;
        }

        return null;
    }

    public int getLastCategoryId() {
        return lastCategoryId;
    }

    public void addCategory(String name) {
        categories.add(new Category(++lastCategoryId, name));
    }

    public Category addAndGetCategory(String name) {
        Category category = new Category(++lastCategoryId, name);
        categories.add(category);
        return category;
    }

    public void deleteCategory(Category category) {
        categories.remove(category);
    }
}
