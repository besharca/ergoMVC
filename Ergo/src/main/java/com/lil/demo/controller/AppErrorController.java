package com.lil.demo.controller;

 

 
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView; 

@Controller
public class AppErrorController implements org.springframework.boot.web.servlet.error.ErrorController{
	
	private ErrorAttributes  errorAttributes; 
 
	//constructor
	public AppErrorController(ErrorAttributes errorAttributes) {
		super();
		this.errorAttributes = errorAttributes;
	}

	@RequestMapping(value = "/error" , produces = "text/html")
	public ModelAndView errorHtml(HttpServletRequest request) {
		return new ModelAndView("home/error", this.getErrorAttributes(request, true));
	}
	
	private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
		WebRequest webRequest = new ServletWebRequest(request);
		return this.errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
		
	}
	
	
	

	@Override
	public String getErrorPath() {
		
		return "/error";
	}
	
}
