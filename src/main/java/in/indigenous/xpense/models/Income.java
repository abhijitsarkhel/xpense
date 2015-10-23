package in.indigenous.xpense.models;

import java.io.Serializable;
import java.util.Date;

public class Income implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String incomeOption;
	private double amount;
	private Date created;
	private String remarks;

	public String getIncomeOption() {
		return incomeOption;
	}

	public void setIncomeOption(String incomeOption) {
		this.incomeOption = incomeOption;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
