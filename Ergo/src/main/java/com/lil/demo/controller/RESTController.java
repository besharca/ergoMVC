package com.lil.demo.controller;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.lil.demo.hashing.FolderHashing;
import com.lil.demo.model.UserModel;
import com.lil.demo.model.ValidationToken;
import com.lil.demo.repository.UserRepository;
import com.lil.demo.repository.ValidationTokenRepository;
 

@Controller
public class RESTController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ValidationTokenRepository tokenRepo;
	
	
	@GetMapping("/local/{folder}/{fileName}")
	public void getImage(HttpServletRequest request,HttpServletResponse response,@PathVariable String folder, @PathVariable String fileName ) throws IOException{
		try {
			
			if(request.getSession().getAttribute("login")!=null &&
					FolderHashing.hashIt(request.getSession().getAttribute("login").toString()).equals(folder)) { 
				FileInputStream fis = 
						new FileInputStream("./src/main/resources/static/"+folder+"/"+fileName);
				response.setContentType(MediaType.IMAGE_JPEG_VALUE);
				response.setContentType(MediaType.IMAGE_PNG_VALUE);
				IOUtils.copy(fis, response.getOutputStream());
			}else {
				response.setContentType("text/html");
				response.getWriter().println("NOT ALLOWED!");
			}
		}catch(FileNotFoundException notFound) {
			response.setContentType("text/html");
			response.getWriter().println("Resource missing!");
			System.out.println(notFound.getMessage());
		}
	}
	
	
	@GetMapping("/activateAccount/{stringToken}") 
	@Transactional
	public ModelAndView activateUserEmail(@PathVariable String stringToken) {
		final ValidationToken token = tokenRepo.findByToken(stringToken);
		ModelAndView mv = new ModelAndView("home/activateEmailResponse");
		
		if(token==null) {
			mv.addObject("mailResponse","Invalid Link");
			return mv;
		} 
		 
		
		final UserModel user = userRepo.findByEmail(token.getEmail());
		
		if(user==null) {
			mv.addObject("mailResponse","Email is missing from our database.");  
			return mv;
		}
		
		if(user.isEnabled()) {
			mv.addObject("mailResponse","Email is already activated!");
			return mv;
		}else {
			userRepo.enableAccount(user.getEmail());
			mv.addObject("mailResponse","You have verified your email successfully"); 
			return mv;
		}
		
	}
}
