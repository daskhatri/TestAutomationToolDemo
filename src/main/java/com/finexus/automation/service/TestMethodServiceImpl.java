package com.finexus.automation.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finexus.automation.entity.ExceptionsNode;
import com.finexus.automation.entity.TestCase;
import com.finexus.automation.entity.TestMethod;
import com.finexus.automation.pojo.TestMethodPojo;
import com.finexus.automation.repository.TestMethodRepository;

@Service
public class TestMethodServiceImpl implements TestMethodService {

	@Autowired
	private TestMethodRepository testMethodRepository;

	@Override
	public List<TestMethod> findAllTestCases() {
		List<TestMethod> dbTestMethodList = testMethodRepository.findAll();
		return dbTestMethodList;
	}

	@Override
	public TestMethod findById(int id) {
		TestMethod testMethod = testMethodRepository.findById(id).get();

		return testMethod;
	}

	@Override
	public List<Map<Object, Object>> allTestCaseMethods() {

		List<TestMethodPojo> dbLastUpdatedRecords = testMethodRepository.getAllTestMethods();

		Map<Object, Object> map = null;
//		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		List<Map<Object, Object>> lastUpdatedTestMethods = new ArrayList<Map<Object, Object>>();

		for (TestMethodPojo testMethodPojo : dbLastUpdatedRecords) {
			map = new HashMap<Object, Object>();
			// "A->id",
			// "B->name",
			// "C->status",
			// "D->lastRun",
			// "E->reason",
			
			map.put("A", testMethodPojo.getId());
			map.put("B", testMethodPojo.getFinishedAt());
			map.put("E", testMethodPojo.getStatus());
			map.put("D", testMethodPojo.getName());
			map.put("C", "ExceptionFound");
			
			lastUpdatedTestMethods.add(map);
		}
//		for (TestCase testCase : sortedList) {
//			TestMethod testMethod = testCase.getTestMethodsList().get(1);
//			ExceptionsNode exceptionNode = testMethod.getExceptionsList().get(1);
//			map = new HashMap<Object, Object>();
//			String testCaseName = testCase.getName();
//			// "A->id",
//			// "B->name",
//			// "C->status",
//			// "D->lastRun",
//			// "E->reason",
//
//			map.put("A", testCase.getId());
//			map.put("B", testCaseName.substring(18, testCaseName.length()));
//			map.put("E", testMethod.getStatus());
//			map.put("D", testMethod.getFinished_at());
////			map.put("D", formatterDate.parse(testMethod.getFinished_at()));
//			map.put("C", exceptionNode.getExceptionClass());
//			allTestCases.add(map);
//		}
//
////		} catch (ParseException e) {
////			e.printStackTrace();
////		}
//		return allTestCases;

		return lastUpdatedTestMethods;
	}

}
