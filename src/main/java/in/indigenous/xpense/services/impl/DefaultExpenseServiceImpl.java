package in.indigenous.xpense.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.indigenous.xpense.daos.ExpenseDao;
import in.indigenous.xpense.models.ExpenseLine;
import in.indigenous.xpense.models.ExpenseLineItem;
import in.indigenous.xpense.services.ExpenseService;

@Component(value = "expenseService")
public class DefaultExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseDao expenseDao;

	public List<ExpenseLineItem> getExpenseList() {
		return expenseDao.getExpenseList();
	}

	public List<ExpenseLineItem> getExpenseListBetweenDates(Date date1, Date date2) {
		return expenseDao.getExpenseListBetweenDates(date1, date2);
	}

	public int addExpense(ExpenseLineItem expense) {
		return expenseDao.addExpense(expense);
	}

	public List<ExpenseLine> getAssortedExpense(List<ExpenseLineItem> expenseLineItems) {
		final Map<String, ExpenseLine> expenseLineMap = new HashMap<String, ExpenseLine>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		for (ExpenseLineItem expenseLineItem : expenseLineItems) {
			Date expenseDate = getWashedDate(expenseLineItem.getCreated());
			ExpenseLine expenseLine = expenseLineMap.get(sdf.format(expenseDate));
			if (expenseLine == null) {
				expenseLine = new ExpenseLine();
				expenseLine.setDate(sdf.format(expenseDate));
				expenseLine.getExpenseLineItems().add(expenseLineItem);
				expenseLineMap.put(sdf.format(expenseDate), expenseLine);
			} else {
				expenseLine.getExpenseLineItems().add(expenseLineItem);
			}
		}
		Collection<ExpenseLine> expenseLines = expenseLineMap.values();
		List<ExpenseLine> expenseLineList = new ArrayList<ExpenseLine>();
		expenseLineList.addAll(expenseLines);
		Collections.sort(expenseLineList, new Comparator<ExpenseLine>() {
			public int compare(ExpenseLine o1, ExpenseLine o2) {
				return o2.getDate().compareTo(o1.getDate());
			}
		});
		return expenseLineList;
	}

	private Date getWashedDate(Date date) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		int dat = cal1.get(Calendar.DATE);
		int month = cal1.get(Calendar.MONTH);
		int year = cal1.get(Calendar.YEAR);
		Calendar cal2 = Calendar.getInstance();
		cal2.set(year, month, dat, 0, 0, 0);
		return cal2.getTime();
	}

}
