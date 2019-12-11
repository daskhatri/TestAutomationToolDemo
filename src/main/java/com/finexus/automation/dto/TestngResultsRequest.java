package com.finexus.automation.dto;

import com.finexus.automation.entity.TestngResults;


public class TestngResultsRequest {
	
	private TestngResults testngResults;

	
	public TestngResultsRequest(TestngResults testngResults) {
		
		this.testngResults = testngResults;
	}
	
	public TestngResultsRequest() {
		
	}
	
	public TestngResults getTestngResults() {
		return testngResults;
	}

	public void setTestngResults(TestngResults testngResults) {
		this.testngResults = testngResults;
	}

	@Override
	public String toString() {
		return "TestngResultsRequest [testngResults=" + testngResults + "]";
	}

	
	
}
