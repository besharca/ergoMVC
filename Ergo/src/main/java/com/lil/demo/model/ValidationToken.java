package com.lil.demo.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ValidationToken {
	private static final int EXPIRATION =60*24;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String token;
	
	private String email;
	
	@OneToOne(targetEntity=UserModel.class, fetch=FetchType.EAGER)
	@JoinColumn(nullable=false, name="id")
	private UserModel user;
	
	private Date expirationDate;
	

	
   public ValidationToken() {
        super();
        this.expirationDate = calculateExpirationDate(EXPIRATION);
    }

    public ValidationToken(final String token) {
        super();
        
        this.token = token;
        this.expirationDate = calculateExpirationDate(EXPIRATION);
    }

    public ValidationToken(final String token, final UserModel user) {
        super();

        this.token = token;
        this.user = user;
        this.expirationDate = calculateExpirationDate(EXPIRATION);
        this.email = user.getEmail();
    }
    
	
	private Date calculateExpirationDate(int expirationDateInMinutes) {
		final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expirationDateInMinutes);
        return new Date(cal.getTime().getTime()); 
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
	
}
