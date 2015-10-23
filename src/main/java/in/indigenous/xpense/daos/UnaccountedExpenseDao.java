package in.indigenous.xpense.daos;

import java.util.Date;
import java.util.List;

import in.indigenous.xpense.models.UnaccountedExpense;

public interface UnaccountedExpenseDao {

	List<UnaccountedExpense> getUnaccountedExpenseList();
	int addUnaccountedExpense(double amount);
	UnaccountedExpense getUnaccountedExpense(Date date);
}
