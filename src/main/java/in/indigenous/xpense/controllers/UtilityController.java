package in.indigenous.xpense.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.indigenous.xpense.models.FinalMonthlyExpense;
import in.indigenous.xpense.services.FinalMonthlyExpenseService;

@Controller
public class UtilityController {
	
	@Autowired
	private FinalMonthlyExpenseService finalMonthlyExpenseService;
	
	@RequestMapping(value = "utility", method = RequestMethod.GET)
	public String utilityView() {
		return "utility";
	}

	@RequestMapping(value = "finalize", method = RequestMethod.GET)
	public String finalizeView(Model model) {
		FinalMonthlyExpense finalMonthlyExpense = new FinalMonthlyExpense();
		model.addAttribute("finalMonthlyExpense", finalMonthlyExpense);
		return "finalize";
	}

	@RequestMapping(value = "finalize", method = RequestMethod.POST)
	public String finalizeAction(@ModelAttribute FinalMonthlyExpense finalMonthlyExpense, Model model) {
		finalMonthlyExpenseService.finalizeMonthlyExpense(finalMonthlyExpense);
		return finalizeView(model);
	}
}
