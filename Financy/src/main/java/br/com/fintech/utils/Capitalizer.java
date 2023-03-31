package br.com.fintech.utils;

public class Capitalizer {
	
	public static String capitalize(String string) {
		
		String string1 = "";
		
		String[] words = string.split("_");
		
		for(String word : words) {
			string1 += " " + word.substring(0,1) + word.substring(1).toLowerCase();
		}
		
		string1 = string1.substring(1);
		
		return string1;			
	}
	
}
