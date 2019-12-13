package com.finexus.automation.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finexus.automation.entity.TestCase;
import com.finexus.automation.entity.TestngResults;
import com.finexus.automation.pojo.DonutDataModel;
import com.finexus.automation.service.TestngResultsService;

@RestController
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
public class TestngResultsController {

	@Autowired
	private TestngResultsService testngResultsService;

	// ************************ List ALL TEST CASES ***************
	@RequestMapping(path = "/listTestCases/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Map<Object, Object>>> getAllTestCases(@PathVariable("id") Long id) {

		List<Map<Object, Object>> list = null;
		try {
			list = testngResultsService.getAllTestCases(id);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// ****** NOT IN USE, it is json format for DONUT CHART ********
	@RequestMapping(path = "/testSuite/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<DonutDataModel>> getTestSuite(@PathVariable("id") Long id) {

		TestngResults testSuite = testngResultsService.getTestngResults(id);

		DonutDataModel dmFailed = new DonutDataModel();
		dmFailed.setLabel("Failed");
		dmFailed.setData(testSuite.getFailed());
		dmFailed.setColor("#0083b7");

		DonutDataModel dmPassed = new DonutDataModel();
		dmPassed.setLabel("Passed");
		dmPassed.setData(testSuite.getPassed());
		dmPassed.setColor("#00c0ef");

		DonutDataModel dmSkipped = new DonutDataModel();
		dmSkipped.setLabel("Skipped");
		dmSkipped.setData(testSuite.getSkipped());
		dmSkipped.setColor("#3c8dbc");

		DonutDataModel dmTotal = new DonutDataModel();
		dmTotal.setLabel("Total");
		dmTotal.setData(testSuite.getTotal());
		dmTotal.setColor("#3c8dbc");

		List<DonutDataModel> donutData = new ArrayList<DonutDataModel>();
		donutData.add(dmSkipped);
		donutData.add(dmPassed);
		donutData.add(dmFailed);

		return new ResponseEntity<>(donutData, HttpStatus.OK);
	}

	
//	public ResponseEntity<T>
}
