package com.alliebooks;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

public class Utils {
	public static String getDisplayForMonth(int month) {
		switch (month) {
		case 1:
			return "January";
		case 2:
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		default:
			return "";
		}
	}
	
	public static int getCurrentMonth() {
		DateTime dt = DateTime.now();
		return dt.getMonthOfYear();
	}
	
	public static int getCurrentYear() {
		DateTime dt = DateTime.now();
		return dt.getYear();
	}
	
	public static Map<String, Object> getSearchCriteria(String query, String[] fields) {
		Map<String, Object> map = new HashMap<>();
		
		boolean contains = false;
		for (String field : fields) {
			if (query.contains(field)) {
				contains = true;
				break;
			}
		}
		if (!contains) {
			//default the entire query to the first field if the query has no parameters
			map.put(fields[0], query);
			return map;
		}
		
		for(String section : query.split(",")) {
			section = section.trim();
			
			for (String field : fields) {
				if (section.startsWith(field + ":")) {
					if ("year".equals(field)) {
						try {
							map.put(field, Integer.parseInt(section.substring(5)));
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					} else {
						map.put(field, section.substring(field.length() + 1));
					}
				}
			}
		}
		
		
		return map;
	}
}
