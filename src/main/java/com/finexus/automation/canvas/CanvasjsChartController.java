package com.finexus.automation.canvas;

import java.util.List;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 
//import com.canvasjs.chart.services.CanvasjsChartService;
//https://canvasjs.com/spring-mvc-charts/animated-chart/
@Controller
@RequestMapping("/canvasjschart")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CanvasjsChartController {
 
	@Autowired
	private CanvasjsChartService canvasjsChartService;
 
//	@RequestMapping(path = "/pieChart/{id}", method = RequestMethod.GET)
//	public ResponseEntity<List<Map<Object, Object>>> getPieChart(@PathVariable("id") Long id) {
//		Long topId = canvasjsChartService.getTopRecordId();
//		System.out.println("TopRecordID: " + topId);
//		
//		List<Map<Object, Object>> canvasjsDataList = canvasjsChartService.getCanvasjsChartData(topId);
//		return new ResponseEntity<>(canvasjsDataList, HttpStatus.OK);
//	}
	
	@RequestMapping(path = "/pieChart/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<List<Object>>> getPieChart(@PathVariable("id") Long id) {
		Long topId = canvasjsChartService.getTopRecordId();
		System.out.println("TopRecordID: " + topId);
		
		List<List<Object>> canvasjsDataList = canvasjsChartService.getCanvasjsChartData(topId);
		return new ResponseEntity<>(canvasjsDataList, HttpStatus.OK);
	}
 
	@RequestMapping(path = "/barChart", method = RequestMethod.GET)
	public ResponseEntity<List<Map<Object, Object>>> getBarChart() {
		Long topId = canvasjsChartService.getTopRecordId();
		System.out.println("TopRecordID: " + topId);
		
		List<Map<Object, Object>> canvasjsDataList = canvasjsChartService.getBarChartData(topId);
		return new ResponseEntity<>(canvasjsDataList, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/lineChart", method = RequestMethod.GET)
	public ResponseEntity<List<List<Object>>> getLineChart() {
		Long topId = canvasjsChartService.getTopRecordId();
		System.out.println("TopRecordID: " + topId);
		
		List<List<Object>> canvasjsDataList = canvasjsChartService.getLineChartData(topId);
		return new ResponseEntity<>(canvasjsDataList, HttpStatus.OK);
	}
}  
