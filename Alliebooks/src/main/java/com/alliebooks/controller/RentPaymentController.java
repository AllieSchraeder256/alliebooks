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
import com.alliebooks.entities.RentPayment;
import com.alliebooks.service.RentPaymentService;
import com.alliebooks.service.UnitService;

@Controller
public class RentPaymentController {
	
	@Autowired
	private RentPaymentService rentService;
	
	@Autowired
	private UnitService unitService;
	
	private int month;
	private int year;
	
	public RentPaymentController() {
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
	
	@GetMapping(value = "/rent-payments")
    public String list(Model model){
		return getRents(model);
    }
	
	@GetMapping(value = "/rent-payments/down")
    public String down(Model model){
		//TODO get current month and year values
		if (--month < 1) {
			month = 12;
			year--;
		}
		return getRents(model);
    }

	private String getRents(Model model) {
		model.addAttribute("displayDate", Utils.getDisplayForMonth(month) + " " + year);
		model.addAttribute("searchQuery", "Search");
        model.addAttribute("rentPayments", rentService.findAllByDate(month, year));
        return "rent-payments";
	}
	
	@GetMapping(value = "/rent-payments/up")
    public String up(Model model){
		//TODO get current month and year values
		if (++month >12) {
			month = 1;
			year++;
		}
		return getRents(model);
    }
	
	@RequestMapping("/rent-payments/new")
    public String newRentPayment(Model model){
		model.addAttribute("action", "Add");
		model.addAttribute("units", unitService.findAll());
		model.addAttribute("rentPayment", new RentPayment());
        return "rent-paymentsform";
    }
	
	@PostMapping(value = "/rent-payments/save")
    public String saveRentPayment(RentPayment rentPayment){
        rentService.save(rentPayment);
        return "redirect:/rent-payments";
    }
	
	@GetMapping(value = "/rent-payments/search")
    public String search(@RequestParam String query, Model model){
		if (!StringUtils.isEmpty(query)) {
			model.addAttribute("searchQuery", query);
			model.addAttribute("rentPayments", rentService.search(query));
	        return "rent-payments";
		} else {
			return getRents(model);
		}
		
    }
	
	@RequestMapping("/rent-payments/edit/{id}")
    public String editRentPayment(@PathVariable("id") UUID id, Model model){
		model.addAttribute("action", "Edit");
		model.addAttribute("units", unitService.findAll());
		model.addAttribute("rentPayment", rentService.findById(id));
        return "rent-paymentsform";
    }
	
	@RequestMapping("/rent-payments/delete/{id}")
    public String deleteRentPayment(@PathVariable("id") UUID id){
		rentService.delete(id);
        return "redirect:/rent-payments";
	}
}
