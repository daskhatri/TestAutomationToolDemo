package com.finexus.automation.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finexus.automation.entity.ExceptionsNode;
import com.finexus.automation.entity.TestCase;
import com.finexus.automation.entity.TestMethod;
import com.finexus.automation.entity.TestngResults;
import com.finexus.automation.pojo.DonutDataModel;
import com.finexus.automation.pojo.TestCasesPojo;
import com.finexus.automation.repository.TestCaseRepository;
import com.finexus.automation.repository.TestngResultsRepository;

@Service
public class TestngResultsServiceImpl implements TestngResultsService {

	@Autowired
	private TestngResultsRepository testngResultsRepository;

	@Autowired
	private TestCaseRepository testCaseRepository;

//	@Override
//	public TestsSuite getSuite(Long testng_id) {
//		TestngResults testsSuite = testngResultsRepository.findById(testng_id).get();
//	
//		return testsSuite;
////		Map<String, Object> map = new HashMap<String, Object>();
////        try {
////        	
//////        	[
//////        	{"label":"Skipped","data":1,"color":"#3c8dbc"},
//////        	{"label":"Failed","data":3,"color":"#0083b7"},
//////        	{"label":"Ignored","data":5,"color":"#01c0ef"},
//////        	{"label":"Passed","data":7,"color":"#00c0ef"}
//////        	]
//////        
//////        	
//////        	  map.put("label", "Skipped");
//////        	  map.put("data", testsSuite.getSkipped());
//////        	  map.put("color", "#3c8dbc");
////        	  
////              map.put("label", "Failed");
////              map.put("data", testsSuite.getFailed());
////              map.put("color", "#0083b7");
////			/*
////			 * map.put("label","Ignored", testsSuite.getIgnored()); "data"
////			 * map.put("label","Total", testsSuite.getTotal()); "data"
////			 * map.put("label","Passed", testsSuite.getPassed()); "data"
////			 */
////              
//////            map.put("Skipped", testsSuite.getSkipped());            
//////            map.put("Failed", testsSuite.getFailed());
//////            map.put("Ignored", testsSuite.getIgnored());
//////            map.put("Total", testsSuite.getTotal());
//////            map.put("Passed", testsSuite.getPassed());
////            
////            
////            
////
////            
////        } catch (Exception e) {
//////            map.clear();
////            e.printStackTrace();
////            
////        }
////		return map;
//	}

	@Override
	public TestngResults getTestngResults(Long id) {
		TestngResults testsSuite = testngResultsRepository.findById(id).get();
//		List<DonutDataModel> testsSuiteList = ArrayList<DonutDataModel>();
		return testsSuite;
	}

	@Override
	public List<Map<Object, Object>> getAllTestCases(Long id) {

//		TestngResults testngResults = testngResultsRepository.findById(id).get();
//		TestCasesPojo tcPojo = new TestCasesPojo();

		List<TestCase> testCasesList = testCaseRepository.findAll();

		List<TestCase> sortedList = testCasesList.stream()
				.sorted(Comparator.comparingInt(TestCase::getId)
						.reversed())
						.collect(Collectors.toList());
		
		Map<Object, Object> map = null;
//		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		List<Map<Object, Object>> allTestCases = new ArrayList<Map<Object, Object>>();

		for (TestCase testCase : sortedList) {
			TestMethod  testMethod = testCase.getTestMethodsList().get(1);
			ExceptionsNode exceptionNode = testMethod.getExceptionsList().get(1);
			map = new HashMap<Object, Object>();
			String testCaseName = testCase.getName();
			
			map.put("A", testCase.getId());
			map.put("B", testCaseName.substring(18, testCaseName.length()));
			map.put("E", testMethod.getStatus());
			map.put("D", testMethod.getFinished_at());
			map.put("C", exceptionNode.getExceptionClass());
			
//			map.put("Aid", testCase.getId());
//			map.put("Bname", testCaseName.substring(18, testCaseName.length()));
//			map.put("Cstatus", testMethod.getStatus());
//			map.put("DlastRun", testMethod.getFinished_at());
//			map.put("Ereason", exceptionNode.getExceptionClass());
			allTestCases.add(map);						
		}
		
		
		return allTestCases;
	}

}
