package com.lil.demo.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder; 

import com.lil.demo.model.EmailRecoveryWrapper;
import com.lil.demo.model.UserModel;
import com.lil.demo.repository.UserRepository;

@Controller
@RequestMapping("/new")
public class UserRecoveryController {
 
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping(method=RequestMethod.GET, path="/recovery")
	public ModelAndView getPage() {
		ModelAndView mv = new ModelAndView("home/recovery");
		
		return mv; 
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST, path="/recovery")
	public ModelAndView doRecover(@Valid EmailRecoveryWrapper emailWrapper, BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("home/recovery");
		
		if(bindingResult.hasErrors()) { 
			mv.addObject("status", bindingResult.getAllErrors().get(0).getDefaultMessage());
			System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
			return mv;
		}
		
		UserModel user = userRepo.findByEmail(emailWrapper.getRecoveryEmail());
		
		if(user==null) {
			mv.addObject("status", "Email is not in our system");
			return mv;
		}else {
			mv.addObject("status", "Please check your email for further recovery");
			
			
			//generate new password
			final String newPassword = UUID.randomUUID().toString();
			
			try {
				//reset password
				user.setPassword(passwordEncoder.encode(newPassword));
				userRepo.save(user);
			}catch (Exception e) {
				System.out.println(e);
				mv.addObject("status", "Something went wrong");
				return mv;
			}
			
			
			
			mailSender.send(this.createEmailMessage(user,newPassword,request));;
			
			return mv;
		}

	}
	
	 private final SimpleMailMessage 
	 	createEmailMessage(final UserModel user,final String newPassword,final HttpServletRequest request) { 
	        final String recipientAddress = user.getEmail();
	        final String subject = "Account Recovery";
	        final String loginPage = ServletUriComponentsBuilder.fromContextPath(request).path("/").build()+"/new/login";
	        final String message = "These are your new credentials : ";
	        final String message2 = "Please visit the following link to login : ";
	        
	        final SimpleMailMessage email = new SimpleMailMessage();
	        email.setTo(recipientAddress);
	        email.setSubject(subject);
	        email.setText(message + " \r\n" +user.getUsername()+"\r\n"+newPassword+"\r\n"
	        					+message2+"\r\n"+loginPage +"\r\n");
	        email.setFrom("springbootak47@gmail.com");
	        return email;
	        
	    }
	
	
}
