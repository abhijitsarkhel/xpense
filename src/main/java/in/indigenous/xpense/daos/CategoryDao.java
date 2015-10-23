package in.indigenous.xpense.daos;

import java.util.List;

import in.indigenous.xpense.models.Category;

public interface CategoryDao {

	List<Category> getCategories();
	Category getCategory(int categoryId);
	int addCategory(Category category);
}
