package in.indigenous.xpense.services;

import java.util.List;

import in.indigenous.xpense.models.Category;

public interface CategoryService {

	List<Category> getCategoryList();
	Category getCategory(int categoryId);
	int addCategory(Category category);
}
