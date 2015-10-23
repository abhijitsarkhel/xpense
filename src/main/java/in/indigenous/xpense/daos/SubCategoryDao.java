package in.indigenous.xpense.daos;

import java.util.List;

import in.indigenous.xpense.models.SubCategory;

public interface SubCategoryDao {

	List<SubCategory> getSubCategories();
	List<SubCategory> getSubCategories(int categoryId);
	SubCategory getSubCategory(int subCategoryId);
	int addSubCategory(SubCategory subCategory);
}
