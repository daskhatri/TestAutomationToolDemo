package com.finexus.automation.canvas;

import java.util.List;
import java.util.Map;

public interface CanvasjsChartDao {

//	List<Map<Object, Object>> getCanvasjsChartData(Long id);

	List<List<Object>> getCanvasjsChartData(Long id);

	Long getTopRecordId();

	// Bar chart
	List<Map<Object, Object>> getBarChartData(Long id);

	// Line chart
	List<List<Object>> getLineChartData(Long id);
}