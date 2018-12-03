package com.lil.demo.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationEvent; 

import com.lil.demo.model.UserModel;
 
public class RegistrationEvent extends ApplicationEvent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserModel user;
	private Locale locale;
	private String appUrl;
	HttpServletRequest request;
 
	public RegistrationEvent(UserModel user, Locale locale, String appUrl, HttpServletRequest request) {
		super(user);
		this.user = user;
		this.locale = locale;
		this.appUrl = appUrl;
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
 

}
