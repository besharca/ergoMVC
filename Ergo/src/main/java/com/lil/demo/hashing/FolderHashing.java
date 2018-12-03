package com.lil.demo.hashing;

public class FolderHashing {
	public static String hashIt(String folder) {
		String hashedFolder = null;
		hashedFolder =  Integer.toHexString(folder.hashCode()*15+6);
		
		return hashedFolder;
	}
}
