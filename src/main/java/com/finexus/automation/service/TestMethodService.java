package com.finexus.automation.service;

import java.util.List;

import com.finexus.automation.entity.TestMethod;

public interface TestMethodService {

	List<TestMethod> findAllTestCases();
	
	TestMethod findById(int id);
	
	
}
