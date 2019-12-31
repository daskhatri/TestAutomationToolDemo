package com.finexus.automation.canvas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finexus.automation.entity.TestngResults;
import com.finexus.automation.repository.TestMethodRepository;
import com.finexus.automation.repository.TestngResultsRepository;

//import com.canvasjs.chart.data.CanvasjsChartData;
@Service
public class CanvasjsChartDaoImpl implements CanvasjsChartDao {

	@Autowired
	private TestngResultsRepository testngResultsRepository;
	
	@Autowired
	private TestMethodRepository testMethodRepository;

	@Override
	public List<List<Object>> getCanvasjsChartData(Long id) {

		List<Object[]> resultsCount = testngResultsRepository.getTotalCountOfExecutions();

		List<Object> list = null;
		List<List<Object>> listOfListObjects = new ArrayList<List<Object>>();

		Long failTotal = 0L;
		Long passTotal = 0L;
		Long skipTotal = 0L;
		Long ignoreTotal = 0L;
		Long total = 0L;
		for (Object[] objects : resultsCount) {
			failTotal = (Long) objects[0];
			passTotal = (Long) objects[1];
			ignoreTotal = (Long) objects[2];
			skipTotal = (Long) objects[3];
			total = (Long) objects[4];
		}

		list = new ArrayList<Object>();
		list.add("Passed");
		list.add(passTotal);

		listOfListObjects.add(list);

		list = new ArrayList<Object>();
		list.add("Failed");
		list.add(failTotal);
		listOfListObjects.add(list);

		list = new ArrayList<Object>();
		list.add("Skipped");
		list.add(skipTotal);

		listOfListObjects.add(list);

		list = new ArrayList<Object>();
		list.add("Ignored");
		list.add(ignoreTotal);
		listOfListObjects.add(list);

		return listOfListObjects;

	}

	/*
	 * @Override public List<Map<Object, Object>> getCanvasjsChartData(Long id) {
	 * 
	 * List<TestngResults> testNgResultRecList = testngResultsRepository.findAll();
	 * List<Object[]> resultsCount =
	 * testngResultsRepository.getTotalCountOfExecutions();
	 * 
	 * // .findById(id).get(); Map<Object, Object> map = null; List<List<Map<Object,
	 * Object>>> list = new ArrayList<List<Map<Object, Object>>>(); List<Map<Object,
	 * Object>> listOfMapObjects = new ArrayList<Map<Object, Object>>();
	 * 
	 * // "label":"Passed", // "data":17, // "color":"#62C1E5" Long failTotal = 0L ;
	 * Long passTotal = 0L ; Long skipTotal = 0L ; Long ignoreTotal = 0L ; Long
	 * total = 0L ; for (Object[] objects : resultsCount) { failTotal = (Long)
	 * objects[0]; passTotal = (Long) objects[1]; ignoreTotal= (Long) objects[2];
	 * skipTotal = (Long) objects[3]; total = (Long) objects[4]; }
	 * 
	 * 
	 * 
	 * 
	 * map = new HashMap<Object, Object>(); map.put("label", "Passed");
	 * map.put("data", passTotal); // map.put("color", "#62C1E5");
	 * 
	 * listOfMapObjects.add(map);
	 * 
	 * map = new HashMap<Object, Object>(); map.put("label", "Failed");
	 * map.put("data", failTotal); map.put("color", "#1C96C5");
	 * listOfMapObjects.add(map); // SUM(tr.failed) as failed, SUM(tr.passed) as
	 * passed, SUM(tr.ignored) as ignored, sum(tr.skipped) map = new HashMap<Object,
	 * Object>(); map.put("label", "Skipped"); map.put("data", skipTotal);
	 * map.put("color", "#20A7DB"); listOfMapObjects.add(map);
	 * 
	 * map = new HashMap<Object, Object>(); map.put("label", "Ignored");
	 * map.put("data", ignoreTotal); map.put("color", "#A0D9EF");
	 * listOfMapObjects.add(map);
	 * 
	 * // map = new HashMap<Object, Object>(); // map.put("label", "Total"); //
	 * map.put("data", total); // map.put("color", "C0E2FA"); //
	 * dataPoints1.add(map);
	 * 
	 * // list.add(dataPoints1);
	 * 
	 * return listOfMapObjects;
	 * 
	 * }
	 */
	@Override
	public Long getTopRecordId() {
		TestngResults topRecord = testngResultsRepository.findTopByOrderByTestngIdDesc();
		return topRecord.getTestngId();
	}

	@Override
	public List<Map<Object, Object>> getBarChartData(Long id) {

		List<TestngResults> testNgResultRecList = testngResultsRepository.findAll();
		List<Object[]> resultsCount = testngResultsRepository.getTotalCountOfExecutions();

		// .findById(id).get();
		Map<Object, Object> map = null;
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		List<Map<Object, Object>> listOfMapObjects = new ArrayList<Map<Object, Object>>();

		// "label":"Passed", // "data":17, // "color":"#62C1E5"
		Long failTotal = 0L;
		Long passTotal = 0L;
		Long skipTotal = 0L;
		Long ignoreTotal = 0L;
		Long total = 0L;

		for (Object[] objects : resultsCount) {

			failTotal = (Long) objects[0];
			passTotal = (Long) objects[1];
			ignoreTotal = (Long) objects[2];
			skipTotal = (Long) objects[3];
			total = (Long) objects[4];

		}

		map = new HashMap<Object, Object>();
		map.put("x", "Group1");
		map.put("value", passTotal); //

		listOfMapObjects.add(map);

		map = new HashMap<Object, Object>();
		map.put("x", "Group2");
		map.put("value", failTotal);
		listOfMapObjects.add(map);
		// SUM(tr.failed) as failed, SUM(tr.passed) as passed, SUM(tr.ignored) as
		// ignored, sum(tr.skipped)

		map = new HashMap<Object, Object>();
		map.put("x", "Group3");
		map.put("value", skipTotal);
		listOfMapObjects.add(map);

		map = new HashMap<Object, Object>();
		map.put("x", "Group4");
		map.put("value", ignoreTotal);
		listOfMapObjects.add(map);

		map = new HashMap<Object, Object>();
		map.put("x", "Group5");
		map.put("value", total);
		listOfMapObjects.add(map);

//		list.add(listOfMapObjects);

		return listOfMapObjects;

	}

	@Override
	public List<List<Object>> getLineChartData(Long id) {

		List<Object[]> resultsCount = testMethodRepository.getNoOfTestCaseGroupByDate();
		

		List<Object> list = null;
		List<List<Object>> listOfListObjects = new ArrayList<List<Object>>();
		
		
		List<Object> dateList = new ArrayList<Object>();
		List<Object> noOfTestCasesList = new ArrayList<Object>();
		int weekDaysCounter = 0;
		for (Object[] objects : resultsCount) {
			if(weekDaysCounter < 7) {
				noOfTestCasesList.add(objects[0]);
				dateList.add(objects[1]);				
			}		
		}

		list = new ArrayList<Object>();
		list.add(dateList.get(0));
		list.add(noOfTestCasesList.get(0));
		listOfListObjects.add(list);

		list = new ArrayList<Object>();
		list.add(dateList.get(1));
		list.add(noOfTestCasesList.get(1));
		listOfListObjects.add(list);

		list = new ArrayList<Object>();
		list.add(dateList.get(2));
		list.add(noOfTestCasesList.get(2));

		listOfListObjects.add(list);

		list = new ArrayList<Object>();
		list.add(dateList.get(3));
		list.add(noOfTestCasesList.get(3));
		listOfListObjects.add(list);

		
		list = new ArrayList<Object>();
		list.add(dateList.get(4));
		list.add(noOfTestCasesList.get(4));
		listOfListObjects.add(list);

		
		list = new ArrayList<Object>();
		list.add(dateList.get(5));
		list.add(noOfTestCasesList.get(5));
		listOfListObjects.add(list);

		list = new ArrayList<Object>();
		list.add(dateList.get(6));
		list.add(noOfTestCasesList.get(6));
		listOfListObjects.add(list);

		return listOfListObjects;

	}

}