package com.lil.demo.interceptors;
 

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TimeLog extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception { 
		System.out.println();
		System.out.println("Before controller "+new SimpleDateFormat("HH:mm:ss.SSSZ").format(Calendar.getInstance().getTime()));
		return true;
	}
	
	@Override
	public void postHandle(	HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("Before view "+new SimpleDateFormat("HH:mm:ss.SSSZ").format(Calendar.getInstance().getTime()));
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {
		System.out.println("After completion "+new SimpleDateFormat("HH:mm:ss.SSSZ").format(Calendar.getInstance().getTime()));
	}
}
