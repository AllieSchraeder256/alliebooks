package com.alliebooks.controller;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alliebooks.Utils;
import com.alliebooks.entities.Expense;
import com.alliebooks.service.ExpenseService;
import com.alliebooks.service.ExpenseTypeService;
import com.alliebooks.service.PropertyService;

@Controller
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private ExpenseTypeService expenseTypeService;
	
	private int month;
	private int year;
	
	public ExpenseController() {
		month = Utils.getCurrentMonth();
		year = Utils.getCurrentYear();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	    binder.registerCustomEditor(UUID.class, new PropertyEditorSupport() {
	        @Override
	        public void setAsText(String text) {
	        	if (StringUtils.isEmpty(text)) { 
	        		setValue(null);
	        	} else {
	        		setValue(UUID.fromString(text));
	        	}
	        }
			@Override
			public String getAsText (){
				UUID u = (UUID) getValue () ;
				if (u!=null) {
					return u.toString () ;
				}
				return "";
			}
	    });
	}
	
	@GetMapping(value = "/expenses")
    public String list(Model model){
		return getExpenses(model);
    }
	
	@GetMapping(value = "/expenses/down")
    public String down(Model model){
		if (--month < 1) {//TODO I don't like how this works
			month = 12;
			year--;
		}
		return getExpenses(model);
    }

	private String getExpenses(Model model) {
		model.addAttribute("displayDate", Utils.getDisplayForMonth(month) + " " + year);
		model.addAttribute("searchQuery", "Search");
        Iterable<Expense> ex = expenseService.findAllByDate(month, year);
		model.addAttribute("expenses", ex);
        return "expenses";
	}
	
	@GetMapping(value = "/expenses/up")
    public String up(Model model){
		if (++month >12) {
			month = 1;
			year++;
		}
		return getExpenses(model);
    }
	
	@RequestMapping("/expenses/new")
    public String newExpense(Model model){
		model.addAttribute("action", "Add");
		model.addAttribute("properties", propertyService.findAll());
		model.addAttribute("expenseTypes", expenseTypeService.findAll());
		model.addAttribute("expense", new Expense());
        return "expenseform";
    }
	
	@PostMapping(value = "/expenses/save")
    public String saveExpense(Expense expense){
        expenseService.saveExpense(expense);
        return "redirect:/expenses";
    }
	
	@GetMapping(value = "/expenses/search")
    public String search(@RequestParam String query, Model model){
		//TODO don't show date selector thing in search results
		if (!StringUtils.isEmpty(query)) {
			model.addAttribute("searchQuery", query);
			model.addAttribute("expenses", expenseService.search(query));
			return "expenses";
		} else {
			return getExpenses(model);
		}
        
    }
	
	@RequestMapping("/expenses/edit/{id}")
    public String editExpense(@PathVariable("id") UUID id, Model model){
		model.addAttribute("action", "Edit");
		model.addAttribute("properties", propertyService.findAll());
		model.addAttribute("expenseTypes", expenseTypeService.findAll());
		model.addAttribute("expense", expenseService.getExpenseById(id));
        return "expenseform";
    }
	
	@RequestMapping("/expenses/delete/{id}")
    public String deleteExpense(@PathVariable("id") UUID id){
		expenseService.deleteExpense(id);
        return "redirect:/expenses";
	}
}
