package in.indigenous.xpense.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.indigenous.xpense.models.CategorisedExpense;
import in.indigenous.xpense.models.Category;
import in.indigenous.xpense.models.ExpenseLineItem;
import in.indigenous.xpense.models.SubCategory;
import in.indigenous.xpense.services.CategorisedExpenseService;
import in.indigenous.xpense.services.CategoryService;
import in.indigenous.xpense.services.ExpenseService;
import in.indigenous.xpense.services.SubCategoryService;

@Controller
public class ExpenseController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubCategoryService subCategoryService;

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private CategorisedExpenseService categorisedExpenseService;

	@RequestMapping(value = "addExpense", method = RequestMethod.GET)
	public String addExpenseView(Model model) {
		ExpenseLineItem expense = new ExpenseLineItem();
		model.addAttribute("expense", expense);
		List<Category> categoryList = categoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);
		List<SubCategory> subCategoryList = Collections.emptyList();
		if (!categoryList.isEmpty()) {
			subCategoryList = subCategoryService.getSubCategoryList(categoryList.get(0).getCategoryId());
		}
		model.addAttribute("subCategoryList", subCategoryList);
		return "addExpense";
	}

	@RequestMapping(value = "addExpense", method = RequestMethod.POST)
	public String addExpenseAction(@ModelAttribute ExpenseLineItem expense, Model model) {
		int categoryId = -1;
		if (!StringUtils.isEmpty(expense.getNewCategory())) {
			Category category = new Category();
			category.setCategoryName(expense.getNewCategory());
			categoryId = categoryService.addCategory(category);
		} else {
			categoryId = expense.getCategory();
		}
		int subCategoryId = -1;
		if (!StringUtils.isEmpty(expense.getNewSubCategory())) {
			SubCategory subCategory = new SubCategory();
			subCategory.setSubCategoryName(expense.getNewSubCategory());
			subCategory.setCategoryId(categoryId);
			subCategoryId = subCategoryService.addSubCategory(subCategory);
		} else {
			subCategoryId = expense.getSubCategory();
		}
		ExpenseLineItem newExpense = new ExpenseLineItem();
		newExpense.setCategory(categoryId);
		newExpense.setSubCategory(subCategoryId);
		newExpense.setAmount(expense.getAmount());
		expenseService.addExpense(newExpense);
		return addExpenseView(model);
	}

	@RequestMapping(value = "subCategories", method = RequestMethod.GET)
	public @ResponseBody List<SubCategory> getSubCategoriesForCategory(
			@RequestParam(value = "categoryId", required = true) int categoryId) {
		List<SubCategory> list = this.subCategoryService.getSubCategoryList(categoryId);
		return list;
	}

	@RequestMapping(value = "viewExpense", method = RequestMethod.GET)
	public String viewExpense(Model model) {
		List<CategorisedExpense> categorisedExpenses = categorisedExpenseService.getCategorisedExpenseList();
		model.addAttribute("categorisedExpenses", categorisedExpenses);
		return "viewExpense";
	}

	@RequestMapping(value = "viewExpenseDetails", method = RequestMethod.GET)
	public String viewExpenseDetails(@RequestParam(name = "index") int index, Model model) {
		List<CategorisedExpense> categorisedExpenses = categorisedExpenseService.getCategorisedExpenseList();
		List<ExpenseLineItem> expenses = categorisedExpenses.get(index).getExpenses();
		for (ExpenseLineItem expense : expenses) {
			expense.setCategoryName(categoryService.getCategory(expense.getCategory()).getCategoryName());
			expense.setSubCategoryName(
					subCategoryService.getSubCategory(expense.getSubCategory()).getSubCategoryName());
		}
		model.addAttribute("expenses", expenseService.getAssortedExpense(expenses));
		return "viewExpenseDetails";
	}
}
