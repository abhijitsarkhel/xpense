package in.indigenous.xpense.services;

import java.util.Date;
import java.util.List;

import in.indigenous.xpense.models.PurseMoney;

public interface PurseMoneyService {

	List<PurseMoney> getPurseMoneyList();
	int addPurseMoney(double amount);
	PurseMoney getPurseMoney(Date date);
}
