package in.indigenous.xpense.controllers;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.indigenous.xpense.models.Income;
import in.indigenous.xpense.services.IncomeService;

@Controller
public class IncomeController {

	@Autowired
	private IncomeService incomeService;

	@RequestMapping(value = "addIncome", method = RequestMethod.GET)
	public String addIncomeView(Model model) {
		Income income = new Income();
		model.addAttribute("income", income);
		return "addIncome";
	}

	@RequestMapping(value = "addIncome", method = RequestMethod.POST)
	public String addIncomeAction(@ModelAttribute Income income, Model model) {
		if ("salary".equals(income.getIncomeOption())) {
			List<Income> incomeList = incomeService.getIncomeList();
			for (Income incomeIns : incomeList) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(incomeIns.getCreated());
				LocalDateTime now = LocalDateTime.now();
				if (cal.get(Calendar.MONTH) + 1 == now.getMonthValue() && cal.get(Calendar.YEAR) == now.getYear()) {
					String errorMsg = "You have already entered salary for this month.";
					model.addAttribute("errorMsg", errorMsg);
					return "addIncome";
				}
			}
		}
		incomeService.addIncome(income);
		return addIncomeView(model);
	}
}
