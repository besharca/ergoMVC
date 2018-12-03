package com.lil.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.servlet.ModelAndView; 


@Controller
@RequestMapping("/new")
public class ModelAttributeController {
 
	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping("/test")
	public ModelAndView test() {
		System.out.println(messageSource.getMessage("mesaj", new String[] {"ultimul", "primul"}, "Message not found!", null));
		return new ModelAndView("home/test");
	}
	
	@ModelAttribute
	public void addMessageModelAttribute(Model mv) {
		mv.addAttribute("messageModelAttribute", "functioneaza");
	}
	
	
}
