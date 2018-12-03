package com.lil.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ImageModel {
	
	@Id
	@GeneratedValue
	private int id;
	private String fileName;
	@Column(columnDefinition="MEDIUMBLOB")
	private byte[] picture;
	private String userName;
	
	public ImageModel() {
		super();
	}
	

	public ImageModel(int id, String fileName, byte[] picture, String userName) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.picture = picture;
		this.userName = userName;
	}

	
	
	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
 
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
	
}
