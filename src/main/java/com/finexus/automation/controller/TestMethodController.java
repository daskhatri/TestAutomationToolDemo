package com.finexus.automation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finexus.automation.entity.TestMethod;
import com.finexus.automation.pojo.TestMethodPojo;
import com.finexus.automation.service.TestMethodService;

@RestController
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
public class TestMethodController {
	
	@Autowired
	private TestMethodService testMethodService;
	
	
	@RequestMapping( path = "/allTests", method = RequestMethod.GET, produces = "application/json")
	
	public List<TestMethod> getAllTests(){
		
		List<TestMethod> testMethodsList = testMethodService.findAllTestCases();
		for (TestMethod testMethod : testMethodsList) {
			System.out.println("TestName: " + testMethod.getName());
			System.out.println("Test Status: " + testMethod.getStatus());
		}		
		return testMethodsList;
	}
	
	
	@RequestMapping(path = "/test/{id}", method = RequestMethod.GET, produces = "application/json")
	
	public TestMethod getTest(@PathVariable("id") int id){
		
		TestMethod test = testMethodService.findById(id);		
		return test;
	}
	
	@RequestMapping(path = "/testMethods", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Map<Object, Object>>> getAllTestMethods(){
		
		List<Map<Object, Object>> allTestMethods = testMethodService.allTestCaseMethods();
		return new ResponseEntity<> (allTestMethods, HttpStatus.OK);
	}
	
	
}
