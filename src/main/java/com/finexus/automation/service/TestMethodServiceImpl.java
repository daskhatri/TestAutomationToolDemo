package com.finexus.automation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finexus.automation.entity.TestMethod;
import com.finexus.automation.repository.TestMethodRepository;

@Service
public class TestMethodServiceImpl implements TestMethodService  {

	@Autowired
	private TestMethodRepository testMethodRepository;
	
	@Override
	public List<TestMethod> findAllTestCases() {
		List<TestMethod> dbTestMethodList =  testMethodRepository.findAll();
		return dbTestMethodList;
	}

	@Override
	public TestMethod findById(int id) {
		TestMethod testMethod = testMethodRepository.findById(id).get();
		
		return testMethod;
	}
	
}
