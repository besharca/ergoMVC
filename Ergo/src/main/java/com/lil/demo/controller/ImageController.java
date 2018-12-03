package com.lil.demo.controller;
 
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.lil.demo.model.ImageBase64Model;
import com.lil.demo.model.ImageModel;
import com.lil.demo.service.LoadImageService;
import com.lil.demo.service.PostImageService;

@Controller
@RequestMapping("new")
public class ImageController {

	@Autowired
	private PostImageService postImgService;
	@Autowired
	private LoadImageService loadImgService;
	
	
	@GetMapping("/images")
	public ModelAndView doImages(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("home/img"); 
		
		//populate with content
		if(request.getSession().getAttribute("login")!=null) {
			TreeMap<Integer, String> imgList = loadImgService.loadImages(request.getSession().getAttribute("login").toString());
			mv.addObject("pics", imgList);
		}
		
		return mv;
	}
	
	
	@PostMapping("/images")
	public ModelAndView postImages(MultipartHttpServletRequest request, ImageModel img) {
		ModelAndView mv = new ModelAndView("home/img");
		
			MultipartFile file = request.getFile("pic");
			String user = request.getSession().getAttribute("login").toString();
		
		///////////// save and check if saved	
		boolean b = postImgService.saveImageService(file, img, user);
		if(b) {
			mv.addObject("uploadStatus", "Uploaded successfully.");
		}else {
			mv.addObject("uploadStatus", "File type not supported");
		}
		
		//populate with content
		TreeMap<Integer, String> imgList = loadImgService.loadImages(user);
		mv.addObject("pics", imgList);
		
		return mv;
	}
	
	@GetMapping("/images/{id}")
	public ModelAndView getImage(@PathVariable int id, HttpSession session) {
		ModelAndView mv = new ModelAndView("home/img");
		
		if(session.getAttribute("login")!=null) {
			ImageBase64Model img = loadImgService.findImage(id, session.getAttribute("login").toString());
			mv.addObject("onePic", img); 
		}
		
		return mv;
	}
	
}
