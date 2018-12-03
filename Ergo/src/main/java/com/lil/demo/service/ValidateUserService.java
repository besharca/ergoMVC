package com.lil.demo.service;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lil.demo.model.UserModel;
import com.lil.demo.repository.UserRepository;

@Service
public class ValidateUserService {

	@Autowired
	private UserRepository userRepo;
 
	
	public boolean isUnique(UserModel user) {
		return userRepo.findByUsername(user.getUsername())==null;
	}
	
	public boolean isEmailRegistered(UserModel user) {
		return userRepo.findByEmail(user.getEmail())!=null;
	}
	
 

	public boolean validateUsername(UserModel user) {

		return user.getUsername().matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,}\\b");
	}
 
}
