package com.finexus.automation.xmlparser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class XmlToDateFormat {

	public static void main(String args[]) {

//		SimpleDateFormat format = new SimpleDateFormat(
//			    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
////		SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-d'T'HH:mm:ss'ZZZ'");
//			format.setTimeZone(TimeZone.getTimeZone("UTC"));
//			Date newDate = null;
//			try {
//				newDate = format.parse("2019-12-12 18:41:47");
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("Fomrat: " + newDate);

		String date = "2019-12-12T19:00:37 PKT";

		DateTimeFormatter aFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//		LocalDateTime localDateTime = LocalDateTime.of(2017, Month.AUGUST, 3, 12, 30);
		LocalDateTime localDateTime = LocalDateTime.of(2017, Month.AUGUST, 3, 12, 30);
		String foramttedString = localDateTime.format(aFormatter);
		// "2017-03-08 12:30"
		System.out.println("origional LocalDatetime object: " + localDateTime);
		System.out.println("generated string : " + foramttedString);

	}
}
