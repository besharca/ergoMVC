package com.lil.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lil.demo.model.Post;
import com.lil.demo.repository.PostsRepository; 

@Controller
@RequestMapping("/new")
public class PostController {
	
	@Autowired	
	private PostsRepository posts;
	
	@GetMapping("/main")
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView mv =  new ModelAndView("home/main"); 
		
		//populate comment section
		List<Post> comments =  posts.findAll();
		mv.addObject("comments", comments);
 
		
		return mv;
	}
	

	@PostMapping("/main")
	public ModelAndView addComment(HttpServletRequest request, Post p) {
		System.out.println(request.getParameter("content")); 
		
		ModelAndView mv =  new ModelAndView("home/main");
		
		if(request.getSession().getAttribute("login")==null) {
			mv.addObject("comment", request.getParameter("content"));
		}else { 
			if(p.getContent().length()<1000) {
				p.setTimeStamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm  dd MMMM, YYYY")));
				p.setUsername(request.getSession().getAttribute("login").toString());
				posts.save(p);
				mv.addObject("status", "posted");
			}else {
				mv.addObject("status", "notposted");
			}
		}
		
		//populate comment section
		List<Post> comments =  posts.findAll();
		mv.addObject("comments", comments);
	
		return mv;
	}
	
}
