package com.finexus.automation.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.finexus.automation.entity.TestngResults;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.JenkinsTriggerHelper;
import com.offbytwo.jenkins.model.Artifact;
import com.offbytwo.jenkins.model.BuildResult;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.MavenJobWithDetails;
import com.offbytwo.jenkins.model.TestReport;

public class JenkinsApiService {

	// To get the instance of jenkins server
	// which will be used in multiple operations
	public JenkinsServer connectToJenkins(String jenkinsUri, String user, String password) {
		JenkinsServer jenkins = null;
		try {

			jenkins = new JenkinsServer(new URI(jenkinsUri), user, password);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return jenkins;
	}

	// "http://localhost:8080"
	// "admin"
	// "admin#123"
	// jobName: "MavenDemoTest"
	public void jenkinsConnectionAndBuild(String jenkinsUri, String user, String password, String jobName, String testCaseName) throws ParserConfigurationException, SAXException {

		JenkinsServer jenkins = null;
		// get last Successful build
		BuildWithDetails details = null;
		MavenJobWithDetails mavenJob = null;

		Map<String, String> params = new HashMap<String, String>();
		boolean crumbFlag = true;

		try {

			jenkins = new JenkinsServer(new URI(jenkinsUri), user, password);

			mavenJob = jenkins.getMavenJob(jobName);
			details = mavenJob.getLastSuccessfulBuild().details();

			Map<String, Job> jobs = jenkins.getJobs();
			JobWithDetails jobDetails = jobs.get(jobName).details(); // .details();

			
			System.out.println("Job LastSuccessfulBuild: " + jobDetails.getLastSuccessfulBuild().getNumber());

			params.put("paramKey", "-Dtest");
			params.put("equalOpr", "=");
			params.put("paramValue", testCaseName);

			JenkinsTriggerHelper jobTriggerHelper = new JenkinsTriggerHelper(jenkins);

			BuildWithDetails triggeredBuildDetails = jobTriggerHelper.triggerJobAndWaitUntilFinished(jobName, params);

//			triggeredBuildDetails.getResult();
//			System.out.println("BuildResult.values: " + BuildResult.values());
	
			// TEMPORARAY BLOCKED FOR FUTURE USE
			/*
			 * String consoleOutput = triggeredBuildDetails.getConsoleOutputText(); String
			 * filePath = getTestNgResultFilePath(consoleOutput);
			 * 
			 * TestNgParser testNgParser = new TestNgParser(); TestngResults
			 * testNgResultParsed = testNgParser.parseTestNgResult(filePath);
			 */			

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// get job details
		System.out.println("Build Number: " + details.getNumber());

	}

	public boolean buildingComplete(JenkinsServer jenkins, String jobName) {

		boolean buildCompleted = false;
		boolean isBuilding = false;
		// When the build is in the queue, the nextbuild number didn't change.
		// When it changed, It might still be running.
		JobWithDetails wrkJobData = null;
		try {
			
			wrkJobData = jenkins.getJob(jobName);
			int newNextNbr = wrkJobData.getNextBuildNumber();

			System.out.println("The expected build is there");

			isBuilding = wrkJobData.getLastBuild().details().isBuilding();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!isBuilding) {
			buildCompleted = true;
		}
		return buildCompleted;
	}

	private String getTestNgResultFilePath(String consoleOutput) {

		String line;
		String filePath = null;
		try {
//			File file = new File(consoleOutput);
//			FileReader fr = new FileReader(file);
			StringReader sr = new StringReader(consoleOutput);
			BufferedReader br = new BufferedReader(sr); // creates a buffering character input stream

			StringBuffer sb = new StringBuffer(); // constructs a string buffer with no characters

			
			while ((line = br.readLine()) != null) {
				if (line.contains("Processing") && line.contains("testng-results.xml")) {
					filePath = line.split("'")[1];
					System.out.println("File path: " + filePath);
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return filePath;
	}

}
