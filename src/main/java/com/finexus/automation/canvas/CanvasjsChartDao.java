package com.finexus.automation.canvas;

import java.util.List;
import java.util.Map;
 
public interface CanvasjsChartDao {
 
	List<Map<Object, Object>> getCanvasjsChartData(Long id);

	Long getTopRecordId();
 
}