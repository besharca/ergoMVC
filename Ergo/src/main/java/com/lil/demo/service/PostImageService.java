package com.lil.demo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lil.demo.model.ImageModel;
import com.lil.demo.repository.ImageRepository;

@Service
public class PostImageService {

	@Autowired
	private ImageRepository imgRepo;

	public boolean saveImageService(MultipartFile file, ImageModel img, String user) {

			//check if image
		if (checkType(file.getContentType())) {
					//format the bean ------ id autogenerated
					img.setUserName(user);
					img.setFileName(file.getOriginalFilename());
			try { 	img.setPicture(file.getBytes()); 				} catch (IOException e) { e.printStackTrace(); }

			
			// save img
			imgRepo.save(img);

			return true;
		} else {
			return false;
		}
	}

	public static boolean checkType(String type) {
		return type.contains("image");
	}

}
