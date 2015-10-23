package in.indigenous.xpense.services;

import java.util.Date;
import java.util.List;

import in.indigenous.xpense.models.Income;

public interface IncomeService {

	int addIncome(Income income);
	List<Income> getIncomeList();
	List<Income> getIncomeListBetweenDates(Date date1, Date date2);
}
