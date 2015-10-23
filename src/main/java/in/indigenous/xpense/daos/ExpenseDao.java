package in.indigenous.xpense.daos;

import java.util.Date;
import java.util.List;

import in.indigenous.xpense.models.ExpenseLineItem;

public interface ExpenseDao {

	List<ExpenseLineItem> getExpenseList();
	List<ExpenseLineItem> getExpenseListBetweenDates(Date date1, Date date2);
	int addExpense(ExpenseLineItem expense);
}
