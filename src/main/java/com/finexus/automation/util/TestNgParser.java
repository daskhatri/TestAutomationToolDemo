package com.finexus.automation.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.finexus.automation.entity.ExceptionsNode;
import com.finexus.automation.entity.Suite;
import com.finexus.automation.entity.Test;
import com.finexus.automation.entity.TestCase;
import com.finexus.automation.entity.TestMethod;
import com.finexus.automation.entity.TestngResults;

public class TestNgParser {

	public TestngResults parseTestNgResult(String filePath)throws ParserConfigurationException, SAXException, IOException { 
		TestngResults testResults = null;
		// Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		// Load the input XML document, parse it and return an instance of the
		// Document class.
		Document document = builder.parse(new File(
				filePath));

		NodeList nodeList = document.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			System.out.println(node.getNodeName());

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				testResults = new TestngResults();

				// get node name and value
				System.out.println("\nNode Name =" + node.getNodeName() + " [OPEN]");
				// System.out.println("Node Value =" + tempNode.getTextContent());

				// ****************** TEST-RESULT NODE'S ATTRIBUTES
				if (node.hasAttributes()) { // first node i.e testng-results
					// get attributes names and values
					NamedNodeMap nodeMap = node.getAttributes();

					// TestResults testResults = new TestResults();
					for (int index = 0; index < nodeMap.getLength(); index++) {

						Node innerNode = nodeMap.item(index);

						int value = Integer.valueOf(innerNode.getNodeValue());

						if (innerNode.getNodeName().equals("skipped"))
							testResults.setSkipped(value);
						else if (innerNode.getNodeName().equals("failed"))
							testResults.setFailed(value);
						else if (innerNode.getNodeName().equals("ignored"))
							testResults.setIgnored(value);
						else if (innerNode.getNodeName().equals("total"))
							testResults.setTotal(value);
						else if (innerNode.getNodeName().equals("passed"))
							testResults.setPassed(value);
					}
				}
				// ****************** TEST-RESULT NODE'S CHILD NODES
				if (node.hasChildNodes()) { // child nodes of testng-results node.
					List<Suite> suitesList = new ArrayList<Suite>();
					NodeList testRChildNodes = node.getChildNodes();

					for (int indexTRC = 0; indexTRC < testRChildNodes.getLength(); indexTRC++) {
						Node trcNode = testRChildNodes.item(indexTRC);
						if (trcNode.getNodeType() == Node.ELEMENT_NODE) {
							Suite suite = new Suite();

							// ****************** TEST-RESULT NODE'S ATTRIBUTES
							if (trcNode.hasAttributes()) {

								NamedNodeMap trcNodeMap = trcNode.getAttributes();
								for (int nmIndex = 0; nmIndex < trcNodeMap.getLength(); nmIndex++) {
									Node attNode = trcNodeMap.item(nmIndex);

									if (attNode.getNodeName().equalsIgnoreCase("name"))
										suite.setName(attNode.getNodeValue());
									else if (attNode.getNodeName().equalsIgnoreCase("duration-ms"))
										suite.setDuration_ms(Integer.valueOf(attNode.getNodeValue()));
									else if (attNode.getNodeName().equalsIgnoreCase("started-at"))
										suite.setStarted_at(attNode.getNodeValue());
									else if (attNode.getNodeName().equalsIgnoreCase("finished-at")) {
										suite.setFinished_at(attNode.getNodeValue());
										System.out.println("Date Format finished at: " + attNode.getNodeValue());
									}

								} // end of for loop nmIndex
							} // end of if trcNode

							// ****************** TEST-RESULT NODE'S CHILD NODES
							if (trcNode.hasChildNodes()) {
								List<Test> testList = new ArrayList<Test>();
								NodeList testsNodes = trcNode.getChildNodes();
								for (int testsIndex = 0; testsIndex < testsNodes.getLength(); testsIndex++) {
									Node testsNode = testsNodes.item(testsIndex);
									if (testsNode.getNodeType() == Node.ELEMENT_NODE) {

										Test test = new Test();
										// this node value is null so we are not intertaining it.
										// ****************** TEST NODE'S ATTRIBUTES
										if (testsNode.hasAttributes()) {
											NamedNodeMap testsNodeMap = testsNode.getAttributes();
											for (int tnmIndex = 0; tnmIndex < testsNodeMap.getLength(); tnmIndex++) {
												Node tnmAttNode = testsNodeMap.item(tnmIndex);

												if (tnmAttNode.getNodeName().equalsIgnoreCase("name"))
													test.setName(tnmAttNode.getNodeValue());
												else if (tnmAttNode.getNodeName().equalsIgnoreCase("duration-ms"))
													test.setDuration_ms(Integer.valueOf(tnmAttNode.getNodeValue()));
												else if (tnmAttNode.getNodeName().equalsIgnoreCase("started-at"))
													test.setStarted_at(tnmAttNode.getNodeValue());
												else if (tnmAttNode.getNodeName().equalsIgnoreCase("finished-at"))
													test.setFinished_at(tnmAttNode.getNodeValue());
											}
										} // end of if hasattributes

										// ****************** TEST NODE'S CHILD NODES i.e class node
										if (testsNode.hasChildNodes()) {
											List<TestCase> classesList = new ArrayList<TestCase>();
											NodeList classNodes = testsNode.getChildNodes();

											for (int cIndex = 0; cIndex < classNodes.getLength(); cIndex++) {
												Node classNode = classNodes.item(cIndex);
												if (classNode.getNodeType() == Node.ELEMENT_NODE) {
													TestCase classes = new TestCase();

													if (classNode.hasAttributes()) {
														NamedNodeMap classNodeMap = classNode.getAttributes();

														for (int cnmIndedx = 0; cnmIndedx < classNodeMap
																.getLength(); cnmIndedx++) {
															Node classAttNode = classNodeMap.item(cnmIndedx);
															if (classAttNode.getNodeName().equalsIgnoreCase("name"))
																classes.setName(classAttNode.getNodeValue());

														} // end of loop on classnode attributes
													} // end of classNode has attributes

													// ****************** CLASS NODE'S CHILD NODES, i.e test-method
													if (classNode.hasChildNodes()) {
														List<TestMethod> testMethodList = new ArrayList<TestMethod>();
														NodeList testMethodNodeList = classNode.getChildNodes();

														for (int tmnIndex = 0; tmnIndex < testMethodNodeList
																.getLength(); tmnIndex++) {
															Node testMethodNode = testMethodNodeList.item(tmnIndex);
															if (testMethodNode.getNodeType() == node.ELEMENT_NODE) {
																TestMethod testMethod = new TestMethod();

																if (testMethodNode.hasAttributes()) {
																	NamedNodeMap tmAttNodemap = testMethodNode
																			.getAttributes();

																	for (int tmIndex = 0; tmIndex < tmAttNodemap
																			.getLength(); tmIndex++) {
																		Node tmAttNode = tmAttNodemap.item(tmIndex);

																		if (tmAttNode.getNodeName()
																				.equalsIgnoreCase("name"))
																			testMethod
																					.setName(tmAttNode.getNodeValue());
																		else if (tmAttNode.getNodeName()
																				.equalsIgnoreCase("status"))
																			testMethod.setStatus(
																					tmAttNode.getNodeValue());
																		else if (tmAttNode.getNodeName()
																				.equalsIgnoreCase("signature"))
																			testMethod.setSignature(
																					tmAttNode.getNodeValue());
																		else if (tmAttNode.getNodeName()
																				.equalsIgnoreCase("duration-ms"))
																			testMethod.setSignature(
																					tmAttNode.getNodeValue());
																		else if (tmAttNode.getNodeName()
																				.equalsIgnoreCase("started-at"))
																			testMethod.setStarted_at(
																					tmAttNode.getNodeValue());
																		else if (tmAttNode.getNodeName()
																				.equalsIgnoreCase("finished-at"))
																			testMethod.setFinished_at(
																					tmAttNode.getNodeValue());

																	} // end of for loop tmIndex
																} // end of if testMethodNode has attributes

																if (testMethodNode.hasChildNodes()) {
																	List<ExceptionsNode> exceptionList = new ArrayList<ExceptionsNode>();
																	NodeList exceptionNodes = testMethodNode
																			.getChildNodes();
																	for (int eIndex = 0; eIndex < exceptionNodes
																			.getLength(); eIndex++) {
																		Node exceptionNode = exceptionNodes
																				.item(eIndex);
																		// if(exceptionNode.getNodeType() ==
																		// Node.ELEMENT_NODE) {

																		ExceptionsNode exceptionOccured = new ExceptionsNode();
																		if (exceptionNode.hasAttributes()) {

																			NamedNodeMap exceptionNodemap = exceptionNode
																					.getAttributes();

																			for (int enmIndex = 0; enmIndex < exceptionNodemap
																					.getLength(); enmIndex++) {

																				Node exceptionAttNode = exceptionNodemap
																						.item(enmIndex);
																				System.out.println(
																						"exceptionAttNode Name: "
																								+ exceptionAttNode
																										.getNodeName());
																				System.out.println(
																						"exceptionAttNode Value: "
																								+ exceptionAttNode
																										.getNodeValue());

																				if (exceptionAttNode.getNodeName()
																						.equalsIgnoreCase("class"))
																					exceptionOccured.setExceptionClass(
																							exceptionAttNode
																									.getNodeValue());
																				else if (exceptionAttNode.getNodeName()
																						.equalsIgnoreCase("message"))
																					exceptionOccured
																							.setExceptionMessage(
																									exceptionAttNode
																											.getNodeValue());
																				else if (exceptionAttNode.getNodeName()
																						.equalsIgnoreCase(
																								"full-stacktrace"))
																					exceptionOccured.setFullStackTrace(
																							exceptionAttNode
																									.getNodeValue());
																			}
																		}
																		exceptionList.add(exceptionOccured);
																		testMethod.setExceptionsList(exceptionList);
//																					if(exceptionNode.hasChildNodes()) {
//																						NodeList exceptionChildNodes = exceptionNode.getChildNodes();
//																						for (int ecIndex = 0; ecIndex < exceptionChildNodes.getLength(); ecIndex++) {
//																							
//																							Node exceptionChildNode = exceptionChildNodes.item(ecIndex);
//																							
//																							System.out.println("exceptionChildNode Name: " + exceptionChildNode.getNodeName());
//																							System.out.println("exceptionChildNode Value: " + exceptionChildNode.getNodeValue());
//																							if(exceptionChildNode.hasAttributes()) {
//																								NamedNodeMap excChildAttNodeMap =  exceptionChildNode.getAttributes();
//																								for (int ecaIndex = 0; ecaIndex < excChildAttNodeMap.getLength(); ecaIndex++) {
//																									Node messageNode = excChildAttNodeMap.item(ecaIndex);
//																									System.out.println("messageNode Name: " + messageNode.getNodeName());
//																									System.out.println("messageNode Value: " + messageNode.getNodeValue());
//																								}
//																							}
//																						}
//																					}

																		// }// end of if exceptionNode check type
																	} // end of for loop to iterate exceptionNodes
																} // end of if testMethodNode has child nodes
																testMethodList.add(testMethod);

															} // end of if testMthodnode type checking
														} // end of for loop testMethodnode list iterating
														classes.setTestMethodsList(testMethodList);

													} // end of if classNode hasChildNode
													classesList.add(classes);

												} // end of classNOde check Type
											} // end of for loop cIndex
											test.setTestCaseList(classesList);

										} // end of if testsNode hasChildNodes
										testList.add(test);

									} // end of if getNodeType
								}
								suite.setTestList(testList);

							}
							suitesList.add(suite);

						}
					} // end of for indexTRC
					testResults.setSuiteList(suitesList);
				} // end test results has child notes

			} // test results
		} // end attributes for test results
		return testResults;		
	}

}
