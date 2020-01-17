package com.finexus.automation.xmlparser;

public class StringOpr {

	public static void main(String[] args) {
		String sentence = "Check this answer and you can find the keyword with this code";
		String search  = "complete";

		if ( sentence.toLowerCase().indexOf(search.toLowerCase()) == -1 ) {

		   System.out.println("I found the keyword");

		} else {

		   System.out.println("not found");

		}

	}

}
