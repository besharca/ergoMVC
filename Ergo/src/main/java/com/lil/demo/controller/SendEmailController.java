package com.lil.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lil.demo.service.MailService;

@Controller
@RequestMapping("/new")
public class SendEmailController {

	@Autowired
	private MailService mailService;
	
	@GetMapping("/send")
	public ModelAndView viewPage() {
		return new ModelAndView("home/emailForm");
	}
	
	@PostMapping("/send")
	public ModelAndView sendEmail(@RequestParam String receiver, @RequestParam String content) {
		ModelAndView mv = new ModelAndView("home/emailForm");
		mailService.sendMessage(content, receiver);
		
		mv.addObject("status", "Email sent to : "+receiver);
		
		return mv;
	}
	
}
