package in.indigenous.xpense.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExpenseLine implements Serializable {

	private static final long serialVersionUID = 1L;
	private String date;
	private List<ExpenseLineItem> expenseLineItems = new ArrayList<ExpenseLineItem>();

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<ExpenseLineItem> getExpenseLineItems() {
		return expenseLineItems;
	}

	public void setExpenseLineItems(List<ExpenseLineItem> expenseLineItems) {
		this.expenseLineItems = expenseLineItems;
	}

	public double getTotal()
	{
		double total = 0;
		for(ExpenseLineItem expenseLineItem: expenseLineItems)
		{
			total += expenseLineItem.getAmount();
		}
		return total;
	}
}
