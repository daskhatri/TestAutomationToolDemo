package com.finexus.automation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.finexus.automation.entity.TestCase;
import com.finexus.automation.repository.TestMethodRepository;

public class TestCaseController {

	@Autowired
	private TestMethodRepository testMethodRepository;
	
	// to get the last updated record by checking last finished_at time factor.
	public ResponseEntity<TestCase> testCaseLiveUpdater(String requestDate){
		
		
		return new ResponseEntity<TestCase>(HttpStatus.OK);
	}
}
