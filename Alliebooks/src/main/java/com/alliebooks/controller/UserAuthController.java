package com.alliebooks.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import com.alliebooks.dto.UserRegistrationDto;
import com.alliebooks.entities.User;
import com.alliebooks.service.UserService;

@Controller
public class UserAuthController {

	@Autowired private UserService userService;

    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserRegistrationDto userDto = new UserRegistrationDto();
        model.addAttribute("user", userDto);
        return "registration";
    }
    
	@PostMapping("/user/registration")
	public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, 
			HttpServletRequest request, Errors errors, Model model) {
	    
	    try {
	        User registered = userService.registerNewUserAccount(userDto);
	        model.addAttribute("user", registered);
	    } catch (Exception e) {
	        model.addAttribute("error", e.getMessage());
	    	return "registration";
	    }
	    
	    return "/";
	}
	
	@GetMapping("/login")
    public String login(WebRequest request, Model model) {
        return "login";
    }
}
