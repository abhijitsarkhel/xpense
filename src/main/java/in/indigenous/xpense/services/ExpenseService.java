package in.indigenous.xpense.services;

import java.util.Date;
import java.util.List;

import in.indigenous.xpense.models.ExpenseLine;
import in.indigenous.xpense.models.ExpenseLineItem;

public interface ExpenseService {

	List<ExpenseLineItem> getExpenseList();
	List<ExpenseLineItem> getExpenseListBetweenDates(Date date1, Date date2);
	int addExpense(ExpenseLineItem expense);
	List<ExpenseLine> getAssortedExpense(List<ExpenseLineItem> expenseLineItems);
}
