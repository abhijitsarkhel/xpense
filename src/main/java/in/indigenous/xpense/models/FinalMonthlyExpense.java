package in.indigenous.xpense.models;

import java.io.Serializable;

public class FinalMonthlyExpense implements Serializable {

	private static final long serialVersionUID = 1L;
	private double purseMoney;
	private double accountMoney;
	private double unaccountedExpense;

	public double getPurseMoney() {
		return purseMoney;
	}

	public void setPurseMoney(double purseMoney) {
		this.purseMoney = purseMoney;
	}

	public double getAccountMoney() {
		return accountMoney;
	}

	public void setAccountMoney(double accountMoney) {
		this.accountMoney = accountMoney;
	}

	public double getUnaccountedExpense() {
		return unaccountedExpense;
	}

	public void setUnaccountedExpense(double unaccountedExpense) {
		this.unaccountedExpense = unaccountedExpense;
	}

}
