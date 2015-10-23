package in.indigenous.xpense.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.indigenous.xpense.daos.IncomeDao;
import in.indigenous.xpense.models.Income;
import in.indigenous.xpense.services.IncomeService;

@Component("incomeService")
public class DefaultIncomeServiceImpl implements IncomeService {

	@Autowired
	private IncomeDao incomeDao;
	
	public int addIncome(Income income) {
		return incomeDao.addIncome(income);
	}
	
	public List<Income> getIncomeList() {
		return incomeDao.getIncomeList();
	}
	
	public List<Income> getIncomeListBetweenDates(Date date1, Date date2)
	{
		return incomeDao.getIncomeListBetweenDates(date1, date2);
	}
}
