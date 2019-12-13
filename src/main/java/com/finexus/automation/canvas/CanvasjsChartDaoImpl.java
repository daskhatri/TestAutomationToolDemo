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

		List<TestngResults> testNgResultRecList = testngResultsRepository.findAll();
		List<Object[]> resultsCount = testngResultsRepository.getTotalCountOfExecutions();

		// .findById(id).get();
		Map<Object, Object> map = null;
		List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
		List<Map<Object, Object>> dataPoints1 = new ArrayList<Map<Object, Object>>();

		// "label":"Passed",
//	      "data":17,
//	      "color":"#62C1E5"
		Long failTotal = 0L ;
		Long passTotal = 0L ;
		Long skipTotal = 0L ;
		Long ignoreTotal = 0L ;
		Long total = 0L ;
		for (Object[] objects : resultsCount) {

			failTotal = (Long) objects[0];
			passTotal = (Long) objects[1];
			ignoreTotal= (Long) objects[2];
			 skipTotal = (Long) objects[3];
			total = (Long) objects[4];
		}
		map = new HashMap<Object, Object>();
		map.put("label", "Passed");
		map.put("data", passTotal); //
		map.put("color", "#62C1E5");

		dataPoints1.add(map);

		map = new HashMap<Object, Object>();
		map.put("label", "Failed");
		map.put("data", failTotal);
		map.put("color", "#1C96C5");
		dataPoints1.add(map);
//			 SUM(tr.failed) as failed, SUM(tr.passed) as passed, SUM(tr.ignored) as ignored, sum(tr.skipped)
		map = new HashMap<Object, Object>();
		map.put("label", "Skipped");
		map.put("data", skipTotal);
		map.put("color", "#20A7DB");
		dataPoints1.add(map);

		map = new HashMap<Object, Object>();
		map.put("label", "Ignored");
		map.put("data", ignoreTotal);
		map.put("color", "#A0D9EF");
		dataPoints1.add(map);

//	map = new HashMap<Object, Object>();
//	map.put("label", "Total");
//	map.put("data", total);
//	map.put("color", "C0E2FA");
//	dataPoints1.add(map);

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