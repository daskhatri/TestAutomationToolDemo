package com.finexus.automation.service;

import java.util.List;
import java.util.Map;

import com.finexus.automation.entity.TestMethod;

public interface TestMethodService {

	List<TestMethod> findAllTestCases();
	
	TestMethod findById(int id);

	List<Map<Object, Object>> allTestCaseMethods();
	
	
}
