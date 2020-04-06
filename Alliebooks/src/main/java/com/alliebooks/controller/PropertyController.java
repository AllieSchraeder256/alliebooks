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

import com.alliebooks.entities.Property;
import com.alliebooks.entities.RoiReport;
import com.alliebooks.service.PropertyService;
import com.alliebooks.service.ReportService;

@Controller
public class PropertyController {
	
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private ReportService reportService;
	
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
				if (u != null) {
					return u.toString () ;
				}
				return "";
			}
	    });
	}
	
	@GetMapping(value = "/properties/{monthsBack}")
    public String list(Model model, @PathVariable("monthsBack") Integer monthsBack){
		Iterable<Property> properties = propertyService.findAll();
		reportService.setPropertyRoiReports(properties, monthsBack);
		
        model.addAttribute("properties", properties);
        return "properties";
    }
	
	@GetMapping(value = "/properties")
    public String list(Model model){
        return list(model, 12);
    }
	
	//TODO this is hard coded for 2 units, make it less shitty
	
	@RequestMapping("/properties/new")
    public String newProperty(Model model){
		model.addAttribute("action", "Add");
        model.addAttribute("property", new Property());
        return "propertyform";
    }
	
	@PostMapping(value = "/properties/save")
    public String saveProperty(Property property){
        propertyService.save(property);
        return "redirect:/properties";
    }
	
	@RequestMapping("/properties/edit/{id}")
    public String editExpense(@PathVariable("id") UUID id, Model model){
		model.addAttribute("action", "Edit");
		model.addAttribute("property", propertyService.find(id));
        return "propertyform";//TODO image is lost on edit
    }
	
	@RequestMapping("/properties/delete/{id}")
    public String deleteExpense(@PathVariable("id") UUID id){
		propertyService.delete(id);
        return "redirect:/properties";
    }
}
