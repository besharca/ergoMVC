package com.lil.demo.service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lil.demo.hashing.FolderHashing; 

@Service
public class PostPictureService {


	public boolean savePicture(MultipartFile file, String user) {

			//check if image
		if (checkType(file.getContentType())) {
			try { 		
					// save img
					File f = new File("src/main/resources/static/"+FolderHashing.hashIt(user)+"/"+file.getOriginalFilename());
					FileUtils.writeByteArrayToFile(f, file.getBytes());
					
					
			} catch (IOException e) { e.printStackTrace(); } 

			return true;
		} else {
			return false;
		}
	}

	public static boolean checkType(String type) {
		return type.contains("image");
	}

	
}
