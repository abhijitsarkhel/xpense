package in.indigenous.xpense.daos;

import java.util.Date;
import java.util.List;

import in.indigenous.xpense.models.AccountMoney;

public interface AccountMoneyDao {

	List<AccountMoney> getAccountMoneyList();
	int addAccountMoney(double amount);
	AccountMoney getAccountMoney(Date date);
}
