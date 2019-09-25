package com.alliebooks.controller;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import com.alliebooks.Utils;
import com.alliebooks.service.ReportService;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	private int year;
	
	public ReportController() {
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
	            setValue(UUID.fromString(text));
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
	
	@GetMapping(value = "/reports")
    public String list(Model model){
		return getReports(model);
    }
	
	@GetMapping(value = "/reports/down")
    public String down(Model model){
		year--;
		return getReports(model);
    }

	@GetMapping(value = "/reports/up")
    public String up(Model model){
		year++;
		return getReports(model);
    }
	
	private String getReports(Model model) {
		model.addAttribute("displayDate", year);
        model.addAttribute("revenueAndLoss", reportService.buildRevenueAndLossByProperty(year));
        model.addAttribute("expenseByProperty", reportService.buildExpenseByProperty(year));
        return "reports";
	}
}
