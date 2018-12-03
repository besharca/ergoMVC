package com.lil.demo.service;

import java.util.Base64;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lil.demo.model.ImageBase64Model;
import com.lil.demo.model.ImageModel;
import com.lil.demo.repository.ImageRepository;

@Service
public class LoadImageService {

	@Autowired
	private ImageRepository imgRepo;

	
	public TreeMap<Integer, String> loadImages(String user){
		List<ImageModel> dbList = imgRepo.findAllByUserName(user);
		TreeMap<Integer, String> resultList = new TreeMap<Integer, String>();

		
		for(ImageModel img : dbList) {
			resultList.put(img.getId(), img.getFileName()); 
		}
		
		return resultList;
	}
	
	public ImageBase64Model findImage(int id, String user) {
		ImageModel img = imgRepo.findById(id).orElse(null);
		
		
		if(img!=null && user.equals(img.getUserName())) {
			ImageBase64Model img64 =  new ImageBase64Model();
			
			img64.setFileName(img.getFileName());
			img64.setBase64Value(Base64.getEncoder().encodeToString(img.getPicture()));
			return img64;
		}else {
			ImageBase64Model img64 = new ImageBase64Model("","FileNotFound");
			return img64;
		}
		
	}
}
