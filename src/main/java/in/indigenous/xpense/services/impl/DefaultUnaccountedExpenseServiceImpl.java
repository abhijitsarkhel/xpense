package in.indigenous.xpense.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.indigenous.xpense.daos.UnaccountedExpenseDao;
import in.indigenous.xpense.models.UnaccountedExpense;
import in.indigenous.xpense.services.UnaccountedExpenseService;

@Component("unaccountedExpenseService")
public class DefaultUnaccountedExpenseServiceImpl implements UnaccountedExpenseService {

	@Autowired
	private UnaccountedExpenseDao unaccountedExpenseDao;
	
	public List<UnaccountedExpense> getUnaccountedExpenseList()
	{
		return unaccountedExpenseDao.getUnaccountedExpenseList();
	}
	
	public int addUnaccountedExpense(double amount)
	{
		return unaccountedExpenseDao.addUnaccountedExpense(amount);
	}
	
	public UnaccountedExpense getUnaccountedExpense(Date date) {
		return unaccountedExpenseDao.getUnaccountedExpense(date);
	}
}
