package com.finexus.automation.xmlparser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class JenkinsApiExperiments {

	private static String JenkinsAuthenticationUserName = "admin";
	private static String JenkinsAuthenticationPassword = "admin#123";

	public static void main(String args[]) {

		String testCaseName = "MavFDTest1";
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(
					"http://localhost:8080/job/MavenDemoTest/buildWithParameters?token=ztypZvByGW&paramKey=-Dtest&equalOpr==&paramValue="
							+ testCaseName);

			UsernamePasswordCredentials creds = new UsernamePasswordCredentials(JenkinsAuthenticationUserName,
					JenkinsAuthenticationPassword);

			httpPost.addHeader(new BasicScheme().authenticate(creds, httpPost, null));
			httpPost.addHeader("Content-type", "application/json");
			httpPost.addHeader("Accept", "application/json");
			
			System.out.println("posting: " + httpPost);
			
			CloseableHttpResponse response = client.execute(httpPost);
			
			System.out.println((response.getStatusLine().getStatusCode()));
			System.out.println("Response: " + response);
			
			client.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Jenkins URL localhost:8080, job named 'test'
		catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
