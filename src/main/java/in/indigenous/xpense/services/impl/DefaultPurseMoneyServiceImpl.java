package in.indigenous.xpense.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.indigenous.xpense.daos.PurseMoneyDao;
import in.indigenous.xpense.models.PurseMoney;
import in.indigenous.xpense.services.PurseMoneyService;

@Component("purseMoneyService")
public class DefaultPurseMoneyServiceImpl implements PurseMoneyService {

	@Autowired
	private PurseMoneyDao purseMoneyDao;
	
	public List<PurseMoney> getPurseMoneyList()
	{
		return purseMoneyDao.getPurseMoneyList();
	}
	
	public int addPurseMoney(double amount)
	{
		return purseMoneyDao.addPurseMoney(amount);
	}
	
	public PurseMoney getPurseMoney(Date date) {
		return purseMoneyDao.getPurseMoney(date);
	}
}
