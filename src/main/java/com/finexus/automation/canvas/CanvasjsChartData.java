package com.finexus.automation.canvas;

//CanvasjsChartData.java
//package com.canvasjs.chart.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanvasjsChartData {

	static Map<Object, Object> map = null;
	static List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
	static List<Map<Object, Object>> dataPoints1 = new ArrayList<Map<Object, Object>>();
	
	static {
		map = new HashMap<Object, Object>();
		map.put("label", "Accomodation");
		map.put("y", 30);
		dataPoints1.add(map);
		
		map = new HashMap<Object, Object>();
		map.put("label", "Food & Groceries");
		map.put("y", 25);
		dataPoints1.add(map);
		
		map = new HashMap<Object, Object>();
		map.put("label", "Utilities");
		map.put("y", 5);
		dataPoints1.add(map);
		
		map = new HashMap<Object, Object>();
		map.put("label", "Entertainment & Fun");
		map.put("y", 20);
		dataPoints1.add(map);
		
		map = new HashMap<Object, Object>();
		map.put("label", "Savings");
		map.put("y", 10);
		dataPoints1.add(map);
		
		map = new HashMap<Object, Object>();
		map.put("label", "Cellphone & Internet");
		map.put("y", 10);
		dataPoints1.add(map);

		list.add(dataPoints1);
	}

	public static List<List<Map<Object, Object>>> getCanvasjsDataList() {
		return list;
	}
}