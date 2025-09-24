package com.example.demo.service;

public class removeExtension {

	public static String clearExtension(String FileName) {
		int lastDotIndex = FileName.lastIndexOf(".");
		if (lastDotIndex != -1) {
			return FileName.substring(0, lastDotIndex);
		}
		return FileName;
	}
}
