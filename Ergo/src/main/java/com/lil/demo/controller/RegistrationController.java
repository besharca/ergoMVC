package com.lil.demo.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lil.demo.model.UserModel;
import com.lil.demo.service.ProcessRegistrationService; 

@Controller
@RequestMapping("/new")
public class RegistrationController {
	

	
	@Autowired
	private ProcessRegistrationService process;

	@GetMapping("/register")
	public ModelAndView getRegistrationForm(HttpServletRequest request) { 
		return new ModelAndView("home/register");
		
	}
	
	@PostMapping("/register")
	public ModelAndView doRegister(@Valid @ModelAttribute("user") UserModel user,  BindingResult bindingResult, HttpServletRequest request) {
				
		ModelAndView mv = new ModelAndView("home/register");
		
		if(bindingResult.hasErrors()) {
			mv.addObject("userReturn", user);
			mv.addObject("status", bindingResult.getAllErrors().get(0).getDefaultMessage()); 
			return mv;
		}
		
		
		return process.doProcess(mv, user, request);
	}
	
}
