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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alliebooks.entities.ExpenseType;
import com.alliebooks.service.ExpenseTypeService;

@Controller
public class ConfigController {
	
	@Autowired
	private ExpenseTypeService expenseTypeService;
	
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
	
	@GetMapping(value = "/config")
    public String list(Model model){
		model.addAttribute("expenseTypes", expenseTypeService.findAll());
		return "config";
    }
	
	//TODO make a separate form for this or just find some way to make it less clunky
	@RequestMapping(value = "/config/expense-types/new")
    public String saveExpense(@RequestParam("name") String name, @RequestParam(name="active", required=false) String active, Model model){
		ExpenseType type = new ExpenseType();
		type.setName(name);
		type.setActive("on".equals(active));
        expenseTypeService.save(type);
        return "redirect:/config";
    }
	
	@RequestMapping("/config/expense-types/edit/{id}")//TODO make this modal or something
    public String editExpense(@PathVariable("id") UUID id, Model model){
		ExpenseType type = expenseTypeService.findById(id);
		model.addAttribute("expenseTypes", expenseTypeService.findAll());
		model.addAttribute("editExpenseType", type);
        return "config";
    }
	
	@RequestMapping("/config/expense-types/delete/{id}")
    public String deleteExpense(@PathVariable("id") UUID id){
		expenseTypeService.delete(id);
        return "redirect:/config";
	}
}
