package in.indigenous.xpense.services;

import java.util.List;

import in.indigenous.xpense.models.SubCategory;

public interface SubCategoryService {

	List<SubCategory> getSubCategoryList();
	List<SubCategory> getSubCategoryList(int categoryId);
	SubCategory getSubCategory(int subCategoryId);
	int addSubCategory(SubCategory subCategory);
}
