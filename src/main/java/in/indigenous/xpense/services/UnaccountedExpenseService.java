package in.indigenous.xpense.services;

import java.util.Date;
import java.util.List;

import in.indigenous.xpense.models.UnaccountedExpense;

public interface UnaccountedExpenseService {

	List<UnaccountedExpense> getUnaccountedExpenseList();
	int addUnaccountedExpense(double amount);
	UnaccountedExpense getUnaccountedExpense(Date date);
}
