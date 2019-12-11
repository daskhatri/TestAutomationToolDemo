package com.finexus.automation.canvas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finexus.automation.entity.TestngResults;
import com.finexus.automation.repository.TestngResultsRepository;
 
//import com.canvasjs.chart.data.CanvasjsChartData;
@Service
public class CanvasjsChartDaoImpl implements CanvasjsChartDao {
 
	@Autowired
	private TestngResultsRepository testngResultsRepository;
	
	@Override
	public List<Map<Object, Object>> getCanvasjsChartData(Long id) {
		
		TestngResults testNgResultRec = testngResultsRepository.findById(id).get();
		Map<Object, Object> map = null;
		List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
		List<Map<Object, Object>> dataPoints1 = new ArrayList<Map<Object, Object>>();
		
		map = new HashMap<Object, Object>();
		map.put("label", "Passed");
		map.put("y", testNgResultRec.getPassed());
		dataPoints1.add(map);
		
		map = new HashMap<Object, Object>();
		map.put("label", "Failed");
		map.put("y", testNgResultRec.getFailed());
		dataPoints1.add(map);
		
		map = new HashMap<Object, Object>();
		map.put("label", "Skipped");
		map.put("y", testNgResultRec.getSkipped());
		dataPoints1.add(map);
		
		map = new HashMap<Object, Object>();
		map.put("label", "Ignored");
		map.put("y", testNgResultRec.getIgnored());
		dataPoints1.add(map);
		
		map = new HashMap<Object, Object>();
		map.put("label", "Total");
		map.put("y", testNgResultRec.getTotal());
		dataPoints1.add(map);
		
		
//		list.add(dataPoints1);
		
		return dataPoints1;
		
	}

	@Override
	public Long getTopRecordId() {
		TestngResults topRecord = testngResultsRepository.findTopByOrderByTestngIdDesc();		
		return topRecord.getTestngId();
	}
	
//	public static List<List<Map<Object, Object>>> getCanvasjsDataList() {
//		return list;
//	}

 
}                  