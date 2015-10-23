package in.indigenous.xpense.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CategorisedExpense implements Serializable {

	private static final long serialVersionUID = 1L;
	private String category;
	private Date from;
	private Date to;
	private double accountBalance;
	private double purseMoney;
	private List<ExpenseLineItem> expenses;
	private double unaccountedExpense;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public double getPurseMoney() {
		return purseMoney;
	}

	public void setPurseMoney(double purseMoney) {
		this.purseMoney = purseMoney;
	}

	public List<ExpenseLineItem> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<ExpenseLineItem> expenses) {
		this.expenses = expenses;
	}

	public double getUnaccountedExpense() {
		return unaccountedExpense;
	}

	public void setUnaccountedExpense(double unaccountedExpense) {
		this.unaccountedExpense = unaccountedExpense;
	}

	public double getTotalExpense() {
		double totalExpense = 0;
		for(ExpenseLineItem expense: expenses)
		{
			totalExpense += expense.getAmount();
		}
		return totalExpense;
	}
}
