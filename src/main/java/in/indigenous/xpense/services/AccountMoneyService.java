package in.indigenous.xpense.services;

import java.util.Date;
import java.util.List;

import in.indigenous.xpense.models.AccountMoney;

public interface AccountMoneyService {

	List<AccountMoney> getAccountMoneyList();
	int addAccountMoney(double amount);
	AccountMoney getAccountMoney(Date date);
}
