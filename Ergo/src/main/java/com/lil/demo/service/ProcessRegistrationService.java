package com.lil.demo.service;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.lil.demo.config.RegistrationEvent;
import com.lil.demo.model.UserModel;
import com.lil.demo.repository.UserRepository;

@Service
public class ProcessRegistrationService {
	
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ValidateUserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	public ModelAndView doProcess(ModelAndView mv,  UserModel user, HttpServletRequest request) {

		// VALIDATE PASSWORD 
		if(user.getPassword().length()<6 ||
				!user.getPassword().equals(user.getConfirmPassword())) { 
			mv.addObject("userReturn", user); 
			mv.addObject("status", "Password does not match!");
			return mv;
		}
		 
 	
		//VALIDATE USERNAME
		if(!userService.isUnique(user)) { 
			user.setUsername("");
			mv.addObject("userReturn", user);  
			mv.addObject("status", "Username already taken!");
			return mv;
		}
 
		//VALIDATE EMAIL ALREADY TAKEN
		if(userService.isEmailRegistered(user)) { 
			user.setEmail("");
			mv.addObject("userReturn", user);  
			mv.addObject("status", "Email Already Registered");
			return mv;
		}
 
 	
		user.setPassword(passwordEncoder.encode(user.getPassword())); 
		
		
		if(this.persist(user, request)) {
			mv.addObject("status", "Succesfully registered. Please verify your email.");
		}else {
			mv.addObject("status", "Something went wrong..");
		}
		
		return mv;
	}
	
	public boolean persist(UserModel user, HttpServletRequest request) {
		
		try {
			userRepo.save(user); 
		}catch(HibernateException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
		
		//send confirmation email
		eventPublisher.publishEvent(new RegistrationEvent(user, request.getLocale(), request.getContextPath(), request));
		return true;
	}
	
}
