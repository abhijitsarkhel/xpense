package in.indigenous.xpense.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.indigenous.xpense.daos.AccountMoneyDao;
import in.indigenous.xpense.models.AccountMoney;
import in.indigenous.xpense.services.AccountMoneyService;

@Component("accountMoneyService")
public class DefaultAccountMoneyServiceImpl implements AccountMoneyService {

	@Autowired
	private AccountMoneyDao accountMoneyDao;
	
	public List<AccountMoney> getAccountMoneyList()
	{
		return accountMoneyDao.getAccountMoneyList();
	}
	
	public int addAccountMoney(double amount)
	{
		return accountMoneyDao.addAccountMoney(amount);
	}
	
	public AccountMoney getAccountMoney(Date date) {
		return accountMoneyDao.getAccountMoney(date);
	}
}
