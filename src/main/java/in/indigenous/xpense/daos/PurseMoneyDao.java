package in.indigenous.xpense.daos;

import java.util.Date;
import java.util.List;

import in.indigenous.xpense.models.PurseMoney;

public interface PurseMoneyDao {

	List<PurseMoney> getPurseMoneyList();
	int addPurseMoney(double amount);
	PurseMoney getPurseMoney(Date date);
}
