package in.indigenous.xpense.models;

import java.io.Serializable;
import java.util.Date;

public class UnaccountedExpense implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private double amount;
	private Date created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
