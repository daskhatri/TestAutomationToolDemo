package com.finexus.automation.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.finexus.automation.entity.TestCase;
import com.finexus.automation.entity.TestngResults;
import com.finexus.automation.repository.TestMethodRepository;
import com.finexus.automation.repository.TestngResultsRepository;
import com.finexus.automation.service.TestCaseService;
import com.finexus.automation.util.JenkinsApiService;
import com.offbytwo.jenkins.JenkinsServer;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class JenkinsBuildController {

	@Autowired
	private TestCaseService testCaseService;

	@Autowired
	private TestMethodRepository testMethodRepository;

	@Autowired
	private ScriptController scriptController;

	@Autowired
	private TestngResultsRepository testngResultsRepository;

	@RequestMapping(path = "/selectedTC/{id}", method = RequestMethod.POST)
	public void runSingleTestCase(@PathVariable("id") int id) {

		TestCase testCase = null;
		TestngResults testNgResults = null;
		String testCaseName = null;

		System.out.println("TestCase Id: " + id);
		testCase = testCaseService.findById(id);
		testCaseName = testCase.getName();
//			PREVIOUS VERSION OF EXECUTING BUILD
//			testNgResults = executeJenkinsTestCase(testCase);

		JenkinsApiService jas = new JenkinsApiService();

		// "http://localhost:8080"
		// "admin"
		// "admin#123"
		// jobName: "MavenDemoTest"
		try {

			jas.jenkinsConnectionAndBuild("http://localhost:8080", "admin", "admin#123", "MavenDemoTest", testCaseName);
			scriptController.createTestngResults();
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}

//			System.out.println("Executed testcase's generated new TestngResults id: " + testNgResults.getTestngId());

	}

	@RequestMapping(path = "/multipleCases/{multipleIds}", method = RequestMethod.POST)
	public void runMultipleTestCases(@PathVariable List<Integer> multipleIds) {

		JenkinsApiService jas = new JenkinsApiService();
		TestCase testCase = null;
		TestngResults testNgResults = null;
		String testCaseName = null;
		
		for (Integer testCaseId : multipleIds) {
			
			System.out.println("TestCase Id: " + testCaseId);
			testCase = testCaseService.findById(testCaseId);
			testCaseName = testCase.getName();
			
			
//			testNgResults = executeJenkinsTestCase(testCase);

			try {

				jas.jenkinsConnectionAndBuild("http://localhost:8080", "admin", "admin#123", "MavenDemoTest",
						testCaseName);
				scriptController.createTestngResults();
			} catch (ParserConfigurationException | SAXException e) {
				e.printStackTrace();
			}

//			System.out.println("Executed testcase's generated new TestngResults id: " + testNgResults.getTestngId());
		}

	}

	public TestngResults executeJenkinsTestCase(TestCase testCase) {

		String testCaseName = testCase.getName();
		TestngResults testCaseExecuted = null;

		TestngResults lastRecord = testngResultsRepository.findTopByOrderByTestngIdDesc();
		try {

			System.out.println("Received request for running testcase: " + testCaseName);

			URL url = new URL(
					"http://localhost:8080/job/MavenDemoTest/buildWithParameters?token=ztypZvByGW&paramKey=-Dtest&equalOpr==&paramValue="
							+ testCaseName); // Jenkins URL localhost:8080, job named 'test'
			String user = "Auto"; // username
			String pass = "1174ecedc83eddf0cb1b8fba9e5a22a2d0"; // password or API token
			String authStr = user + ":" + pass;
			String encoding = Base64.getEncoder().encodeToString(authStr.getBytes("utf-8"));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + encoding);

			InputStream content = connection.getInputStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			if ((line = in.readLine()) != null) {
				System.out.println("Response Input stream is empty");
			}

//			Thread.sleep(60000);
			// Parse generated testng.xml and save the results in database
			testCaseExecuted = scriptController.createTestngResults();

			while (testCaseExecuted == null || testCaseExecuted.getTestngId().equals(lastRecord.getTestngId())) {
				Thread.sleep(30000);
				testCaseExecuted = scriptController.createTestngResults();
			}

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return testCaseExecuted;

	}
}
