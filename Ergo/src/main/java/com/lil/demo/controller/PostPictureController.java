package com.lil.demo.controller;
 
import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.lil.demo.hashing.FolderHashing;
import com.lil.demo.service.PostPictureService; 

@Controller
@RequestMapping("/new")
public class PostPictureController {

	@Autowired
	PostPictureService postPicture;
	
	@GetMapping("/pictures")
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView mv =  new ModelAndView("home/pictures"); 
		loadPictures(mv,request);
		return mv;
	}
	

	@PostMapping("/pictures")
	public ModelAndView addPicture(MultipartHttpServletRequest request) {
		ModelAndView mv =  new ModelAndView("home/pictures");
		MultipartFile file = request.getFile("pikture");
		String user = request.getSession().getAttribute("login").toString();
		//save pic
		boolean b = postPicture.savePicture(file, user);
		if(b) {
			mv.addObject("uploadStatus", "Uploaded successfully.");
		}else {
			mv.addObject("uploadStatus", "File type not supported");
		}
		
		loadPictures(mv,request);
		
		return mv;
	}
	
	
	public static ModelAndView loadPictures(ModelAndView mv, HttpServletRequest request ) {
		
		if(request.getSession().getAttribute("login")!=null) {
			String user = request.getSession().getAttribute("login").toString();
			ArrayList<String> imageTitleList = new ArrayList<>();
			File folder = new File("./src/main/resources/static/"+FolderHashing.hashIt(user));
				if(folder.exists()) {
			        for( File f : folder.listFiles()){
			        	if(f.isFile()) 
			        		imageTitleList.add(request.getContextPath()+"/local/"+FolderHashing.hashIt(user)+"/"+f.getName()); 
			        }
				}
			
	        mv.addObject("imageList", imageTitleList);
		}
		return mv;
	}
		
}
