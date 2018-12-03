package com.lil.demo.model;


public class ImageBase64Model {
	private String base64Value;
	private String fileName;
	
	public ImageBase64Model() {
		super();
	}
	
	public ImageBase64Model(String base64Value, String fileName) {
		super();
		this.base64Value = base64Value;
		this.fileName = fileName;
	}
	
	public String getBase64Value() {
		return base64Value;
	}
	public void setBase64Value(String base64Value) {
		this.base64Value = base64Value;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
