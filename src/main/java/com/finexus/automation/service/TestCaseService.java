package com.finexus.automation.service;

import java.util.List;

import com.finexus.automation.entity.TestCase;

public interface TestCaseService {

	TestCase findById(int id);
	
	List<TestCase> updaterLiveData();
	
}
