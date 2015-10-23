package in.indigenous.xpense.models;

import java.io.Serializable;
import java.util.Date;

public class ExpenseLineItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int category;
	private String newCategory;
	private int subCategory;
	private String newSubCategory;
	private double amount;
	private Date created;
	private String remarks;
	private String categoryName;
	private String subCategoryName;

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getNewCategory() {
		return newCategory;
	}

	public void setNewCategory(String newCategory) {
		this.newCategory = newCategory;
	}

	public int getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(int subCategory) {
		this.subCategory = subCategory;
	}

	public String getNewSubCategory() {
		return newSubCategory;
	}

	public void setNewSubCategory(String newSubCategory) {
		this.newSubCategory = newSubCategory;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
}
