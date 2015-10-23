package in.indigenous.xpense.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.indigenous.xpense.models.AccountMoney;
import in.indigenous.xpense.models.CategorisedExpense;
import in.indigenous.xpense.models.ExpenseLineItem;
import in.indigenous.xpense.services.AccountMoneyService;
import in.indigenous.xpense.services.CategorisedExpenseService;
import in.indigenous.xpense.services.ExpenseService;
import in.indigenous.xpense.services.PurseMoneyService;
import in.indigenous.xpense.services.UnaccountedExpenseService;

@Component("categorisedExpenseService")
public class DefaultCategorisedExpenseServiceImpl implements CategorisedExpenseService {

	@Autowired
	private AccountMoneyService accountMoneyService; 
	@Autowired
	private PurseMoneyService purseMoneyService;
	@Autowired
	private UnaccountedExpenseService unaccountedExpenseService;
	@Autowired
	private ExpenseService expenseService;
	
	public List<CategorisedExpense> getCategorisedExpenseList() {
		List<CategorisedExpense> expenseList = new ArrayList<CategorisedExpense>();
		List<AccountMoney> accountMoneyList = accountMoneyService.getAccountMoneyList();
		Collections.sort(accountMoneyList, new Comparator<AccountMoney>() {
			public int compare(AccountMoney o1, AccountMoney o2) {
				return o2.getCreated().compareTo(o1.getCreated());
			}
		});
		Date date2 = new Date();
		Date date1 = accountMoneyList.get(0).getCreated();
		CategorisedExpense expense = new CategorisedExpense();
		expense.setAccountBalance(accountMoneyList.get(0).getAmount());
		List<ExpenseLineItem> expenses = expenseService.getExpenseListBetweenDates(date1, date2);
		expense.setExpenses(expenses);
		expense.setPurseMoney(purseMoneyService.getPurseMoney(date1).getAmount());
		expense.setUnaccountedExpense(0);
		expense.setFrom(date1);
		expense.setTo(date2);
		expense.setCategory("Non Finalized expense from " + date1 + " to " + date2);
		expenseList.add(expense);
		for(int i=0; i<accountMoneyList.size()-1; i++) {
			date2 = accountMoneyList.get(i).getCreated();
			date1 = accountMoneyList.get(i + 1).getCreated();
			expense = new CategorisedExpense();
			expense.setAccountBalance(accountMoneyList.get(i+1).getAmount());
			expenses = expenseService.getExpenseListBetweenDates(date1, date2);
			expense.setExpenses(expenses);
			expense.setPurseMoney(purseMoneyService.getPurseMoney(date1).getAmount());
			expense.setUnaccountedExpense(unaccountedExpenseService.getUnaccountedExpense(date2).getAmount());
			expense.setFrom(date1);
			expense.setTo(date2);
			expense.setCategory("Finalized expense from " + date1 + " to " + date2);
			expenseList.add(expense);
		}
		return expenseList;
	}
}
