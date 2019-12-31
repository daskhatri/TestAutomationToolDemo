package com.finexus.automation.canvas;

import java.util.List;
import java.util.Map;
 
public interface CanvasjsChartService {
 
//	List<Map<Object, Object>> getCanvasjsChartData(Long id);
	
	List<List<Object>> getCanvasjsChartData(Long id);
	Long getTopRecordId();

	
	// bar chart
	List<Map<Object, Object>> getBarChartData(Long id);
	
	// Line Chart
	List<List<Object>> getLineChartData(Long id);
 
	
}
