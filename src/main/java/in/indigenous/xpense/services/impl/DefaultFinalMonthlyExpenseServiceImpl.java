package in.indigenous.xpense.services.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.indigenous.xpense.models.AccountMoney;
import in.indigenous.xpense.models.ExpenseLineItem;
import in.indigenous.xpense.models.FinalMonthlyExpense;
import in.indigenous.xpense.models.Income;
import in.indigenous.xpense.models.PurseMoney;
import in.indigenous.xpense.services.AccountMoneyService;
import in.indigenous.xpense.services.ExpenseService;
import in.indigenous.xpense.services.FinalMonthlyExpenseService;
import in.indigenous.xpense.services.IncomeService;
import in.indigenous.xpense.services.PurseMoneyService;
import in.indigenous.xpense.services.UnaccountedExpenseService;

@Component("finalMonthlyExpenseService")
public class DefaultFinalMonthlyExpenseServiceImpl implements FinalMonthlyExpenseService {

	@Autowired
	private AccountMoneyService accountMoneyService;
	@Autowired
	private PurseMoneyService purseMoneyService;
	@Autowired
	private UnaccountedExpenseService unaccountedExpenseService;
	@Autowired
	private ExpenseService expenseService;
	@Autowired
	private IncomeService incomeService;

	public void finalizeMonthlyExpense(FinalMonthlyExpense finalMonthlyExpense) {
		double prevAccountMoney = getPreviousAccountBalance();
		double prevPurseMoney = getPreviousPurseMoney();
		Date date1 = getLatestDate();
		double totalExpenses = getTotalExpense(date1);
		double totalIncome = getTotalIncome(date1);
		double unAccountedExpense = prevAccountMoney + prevPurseMoney + totalIncome
				- finalMonthlyExpense.getAccountMoney() - finalMonthlyExpense.getPurseMoney() - totalExpenses;
		if(prevAccountMoney==0  && prevPurseMoney == 0) {
			unAccountedExpense = 0;
		}
		accountMoneyService.addAccountMoney(finalMonthlyExpense.getAccountMoney());
		purseMoneyService.addPurseMoney(finalMonthlyExpense.getPurseMoney());
		unaccountedExpenseService.addUnaccountedExpense(unAccountedExpense);
	}

	private double getPreviousAccountBalance() {
		List<AccountMoney> accountMoneyList = accountMoneyService.getAccountMoneyList();
		Collections.sort(accountMoneyList, new Comparator<AccountMoney>() {
			public int compare(AccountMoney o1, AccountMoney o2) {
				return o2.getCreated().compareTo(o1.getCreated());
			}
		});
		if (!accountMoneyList.isEmpty()) {
			return accountMoneyList.get(0).getAmount();
		}
		return 0;
	}

	private Date getLatestDate() {
		List<AccountMoney> accountMoneyList = accountMoneyService.getAccountMoneyList();
		Collections.sort(accountMoneyList, new Comparator<AccountMoney>() {
			public int compare(AccountMoney o1, AccountMoney o2) {
				return o2.getCreated().compareTo(o1.getCreated());
			}
		});
		if (!accountMoneyList.isEmpty()) {
			return accountMoneyList.get(0).getCreated();
		}
		return null;
	}

	private double getPreviousPurseMoney() {
		List<PurseMoney> purseMoneyList = purseMoneyService.getPurseMoneyList();
		Collections.sort(purseMoneyList, new Comparator<PurseMoney>() {
			public int compare(PurseMoney o1, PurseMoney o2) {
				return o2.getCreated().compareTo(o1.getCreated());
			}
		});
		if (!purseMoneyList.isEmpty()) {
			return purseMoneyList.get(0).getAmount();
		}
		return 0;
	}

	private double getTotalExpense(Date previousFinalizationDate) {
		Date date2 = new Date();
		List<ExpenseLineItem> expenseList = null;
		if (previousFinalizationDate == null) {
			expenseList = expenseService.getExpenseList();
		} else {
			expenseList = expenseService.getExpenseListBetweenDates(previousFinalizationDate, date2);
		}
		double totalExpenses = 0;
		for (ExpenseLineItem expense : expenseList) {
			totalExpenses += expense.getAmount();
		}
		return totalExpenses;
	}

	private double getTotalIncome(Date previousFinalizationDate) {
		Date date2 = new Date();
		List<Income> incomeList = null;
		if (previousFinalizationDate == null) {
			incomeList = incomeService.getIncomeList();
		} else {
			incomeList = incomeService.getIncomeListBetweenDates(previousFinalizationDate, date2);
		}
		double totalIncome = 0;
		for (Income income : incomeList) {
			totalIncome += income.getAmount();
		}
		return totalIncome;
	}
}
