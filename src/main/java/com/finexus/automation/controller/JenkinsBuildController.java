package com.finexus.automation.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finexus.automation.entity.TestCase;
import com.finexus.automation.entity.TestMethod;
import com.finexus.automation.repository.TestMethodRepository;
import com.finexus.automation.service.TestCaseService;

@RestController
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
public class JenkinsBuildController {

	@Autowired
	private TestCaseService testCaseService;

	@Autowired
	private TestMethodRepository testMethodRepository;
	
	@Autowired
	private ScriptController scriptController;

	@RequestMapping(path = "/selectedTC/{id}", method = RequestMethod.POST)
	public void runSingleTestCase(@PathVariable("id") int id) {

		TestMethod testMethodRec= testMethodRepository.findById(id).get();
		TestCase testCase = testCaseService.findById();

		String testCaseName = testCase.getName();

		String command = "curl -X POST http://Auto:1174ecedc83eddf0cb1b8fba9e5a22a2d0@localhost:8080/job/MavenDemoTest/buildWithParameters?token=ztypZvByGW&paramKey=-Dtest&equalOpr==&paramValue="
				+ testCaseName;
		try {
//			Process process = Runtime.getRuntime().exec(command);			
//			int termination = process.waitFor();			
////			http://localhost/jenkins/job/<jobName>/lastBuild/api/xml				
//			if (termination == 0) {
//				System.out.println("its completed smoothy");
//			} else
//				System.out.println("Process of execution is interupted!");
//		

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

			Thread.sleep(30000);

			scriptController.createTestngResults();
			System.out.println("Calling parse and save is done");

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
}
