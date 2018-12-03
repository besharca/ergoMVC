package com.lil.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMail;
	
	
	public void sendMessage(String message, String toEmail) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(toEmail); 
		mail.setSubject("Hi there!");
		mail.setText(message);
		
		
		javaMail.send(mail);
	}
	
}
