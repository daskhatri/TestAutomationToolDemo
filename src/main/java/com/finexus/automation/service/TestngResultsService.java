package com.finexus.automation.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.finexus.automation.entity.TestngResults;

public interface TestngResultsService {
	
	
	TestngResults getTestngResults(Long id) ;
	
	List<Map<Object, Object>> getAllTestCases(Long id) throws ParseException;
	
//	List<> getResults();
}
