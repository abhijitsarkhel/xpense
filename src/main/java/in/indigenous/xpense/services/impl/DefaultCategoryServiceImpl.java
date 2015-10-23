package in.indigenous.xpense.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.indigenous.xpense.daos.CategoryDao;
import in.indigenous.xpense.models.Category;
import in.indigenous.xpense.services.CategoryService;

@Component(value="categoryService")
public class DefaultCategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public List<Category> getCategoryList()
	{
		return categoryDao.getCategories();
	}
	
	public Category getCategory(int categoryId)
	{
		return categoryDao.getCategory(categoryId);
	}
	
	public int addCategory(Category category)
	{
		return categoryDao.addCategory(category);
	}
}
