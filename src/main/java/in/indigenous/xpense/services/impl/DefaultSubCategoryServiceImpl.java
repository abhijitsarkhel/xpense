package in.indigenous.xpense.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.indigenous.xpense.daos.SubCategoryDao;
import in.indigenous.xpense.models.SubCategory;
import in.indigenous.xpense.services.SubCategoryService;

@Component(value="subCategoryService")
public class DefaultSubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryDao subCategoryDao;
	
	public List<SubCategory> getSubCategoryList()
	{
		return subCategoryDao.getSubCategories();
	}
	
	public List<SubCategory> getSubCategoryList(int categoryId)
	{
		return subCategoryDao.getSubCategories(categoryId);
	}
	
	public SubCategory getSubCategory(int subCategoryId)
	{
		return subCategoryDao.getSubCategory(subCategoryId);
	}
	
	public int addSubCategory(SubCategory subCategory)
	{
		return subCategoryDao.addSubCategory(subCategory);
	}
}
