package com.lil.demo.model;

import javax.validation.constraints.Email;

public class EmailRecoveryWrapper {
	
	@Email(message="Invalid email format")
	private String recoveryEmail;

	public String getRecoveryEmail() {
		return recoveryEmail;
	}

	public void setRecoveryEmail(String recoveryEmail) {
		this.recoveryEmail = recoveryEmail;
	}
	
	
	
	
}
