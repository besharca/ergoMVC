package com.lil.demo.config;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener; 
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lil.demo.model.UserModel;
import com.lil.demo.model.ValidationToken;
import com.lil.demo.repository.ValidationTokenRepository;

@Component
public class UserRegistrationListener implements ApplicationListener<RegistrationEvent>{
	
	 @Autowired
	    private JavaMailSender mailSender;
	 
	 @Autowired
	 private ValidationTokenRepository tokenRepo; 
	 
	@Override
	public void onApplicationEvent(RegistrationEvent event) {
		final UserModel user = event.getUser();
		final String stringToken = UUID.randomUUID().toString();
		this.saveToken(stringToken, user);
		
		final SimpleMailMessage email = this.createEmailMessage(event, user, stringToken,event.getRequest());
        mailSender.send(email);
		
	}
	
	private void saveToken(final String stringToken, final UserModel user) {
		ValidationToken token = new ValidationToken(stringToken, user);
		tokenRepo.save(token);
	}
	
	 private final SimpleMailMessage 
	 	createEmailMessage(final RegistrationEvent event, final UserModel user, final String token,final HttpServletRequest request) { 
	        final String recipientAddress = user.getEmail();
	        final String subject = "Registration Confirmation";
	        final String confirmationUrl = ServletUriComponentsBuilder.fromContextPath(request).path("/").build()+"/activateAccount/" + token;
	        final String message = "Please click the following link to successfully validate your email : ";
	        final SimpleMailMessage email = new SimpleMailMessage();
	        email.setTo(recipientAddress);
	        email.setSubject(subject);
	        email.setText(message + " \r\n" + confirmationUrl +"\r\n");
	        email.setFrom("springbootak47@gmail.com");
	        return email;
	        
	    }

}
