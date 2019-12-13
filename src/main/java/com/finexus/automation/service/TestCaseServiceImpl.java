package com.finexus.automation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finexus.automation.entity.TestCase;
import com.finexus.automation.repository.TestCaseRepository;

@Service
public class TestCaseServiceImpl implements TestCaseService {

	@Autowired
	private TestCaseRepository testCaseRepository;
	
	@Override
	public TestCase findById(int id) {
		
		TestCase dbTestCase = testCaseRepository.findById(id).get();
				
		return dbTestCase;
	}

	@Override
	public List<TestCase> updaterLiveData() {
//		findTopByOrderByTestngIdDesc
		
//		testCaseRepository.findTopByOrderByTestngIdDesc
		return null;
	}

	
}
