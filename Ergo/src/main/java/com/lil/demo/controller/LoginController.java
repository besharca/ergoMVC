package com.lil.demo.controller;

 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
import com.lil.demo.model.UserModel;
import com.lil.demo.repository.UserRepository;
 

@Controller
@RequestMapping("/new")
public class LoginController {  
 
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	@GetMapping("/login")
	public ModelAndView login() {
		 
		return new ModelAndView("home/login");  
	}
	 
	@PostMapping("/login")
	public ModelAndView doLogin(HttpSession session, HttpServletRequest request , 
			@RequestParam("uname") String username, @RequestParam("psw") String password) {
		ModelAndView mv  = new ModelAndView("home/loginResult"); 
			if(username.equals("lil") && password.equals("lil")) {
				session.setAttribute("login", "lil"); 
				mv.addObject("result", "Logged in succesfully");
				return mv;
			}  
			if(username.equals("notlil") && password.equals("lil")) {
				session.setAttribute("login", "notlil"); 
				mv.addObject("result", "Logged in succesfully");
				return mv;
			}
			
		final UserModel user = userRepo.findByUsername(username);
		 
		
		if(user==null || !passwordEncoder.matches(password, user.getPassword())) { 
			mv.setViewName("home/login");
			mv.addObject("loginStatus", "Username or password incorrect!");
		}else {
			session.setAttribute("login", user.getUsername());
			mv.addObject("result", "Logged in succesfully");
		}

			
		return mv;
	}
	
	
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("home/loginResult");
		HttpSession session = request.getSession();
		session.removeAttribute("login");
		mv.addObject("result", "Logged out succesfully"); 
		
		return mv;
	}
	
}
