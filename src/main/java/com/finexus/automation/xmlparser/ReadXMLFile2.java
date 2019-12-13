package com.finexus.automation.xmlparser;
//package com.finexus.automation.saxparser;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NamedNodeMap;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import com.finexus.automation.entity.Classes;
//import com.finexus.automation.entity.ExceptionOccured;
//import com.finexus.automation.entity.Suit;
//import com.finexus.automation.entity.Test;
//import com.finexus.automation.entity.TestMethod;
//import com.finexus.automation.entity.TestngResults;
//
//public class ReadXMLFile2 {
//
//	public static void XXmain(String[] args) {
//
//		try {
//
//			File file = new File(
//					"C:/Program Files (x86)/Jenkins/workspace/MavenDemoTest/target/surefire-reports/testng-results.xml");
//
//			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//
//			Document doc = dBuilder.parse(file);
//
//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
//
//			if (doc.hasChildNodes()) {
//
//				printNote(doc.getChildNodes());
//
//			}
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//	}
//
//	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
//
//		// Get Document Builder
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder = factory.newDocumentBuilder();
//
//		// Load the input XML document, parse it and return an instance of the
//		// Document class.
//		Document document = builder.parse(new File("testng-results.xml"));
//
//		NodeList nodeList = document.getChildNodes();
//		for (int i = 0; i < nodeList.getLength(); i++) {
//			Node node = nodeList.item(i);
//			System.out.println(node.getNodeName());
//
//			if (node.getNodeType() == Node.ELEMENT_NODE) {
//				TestngResults testResults = new TestngResults();
//
//				// get node name and value
//				System.out.println("\nNode Name =" + node.getNodeName() + " [OPEN]");
//				// System.out.println("Node Value =" + tempNode.getTextContent());
//				
//				// ****************** TEST-RESULT NODE'S ATTRIBUTES
//				if (node.hasAttributes()) { // first node i.e testng-results
//					// get attributes names and values
//					NamedNodeMap nodeMap = node.getAttributes();
//
//					// TestResults testResults = new TestResults();
//					for (int index = 0; index < nodeMap.getLength(); index++) {
//
//						Node innerNode = nodeMap.item(index);
//
//						int value = Integer.valueOf(innerNode.getNodeValue());
//
//						if (innerNode.getNodeName().equals("skipped"))
//							testResults.setSkipped(value);
//						else if (innerNode.getNodeName().equals("failed"))
//							testResults.setFailed(value);
//						else if (innerNode.getNodeName().equals("ignored"))
//							testResults.setIgnored(value);
//						else if (innerNode.getNodeName().equals("total"))
//							testResults.setTotal(value);
//						else if (innerNode.getNodeName().equals("passed"))
//							testResults.setPassed(value);
//					}
//				}
//				// ****************** TEST-RESULT NODE'S CHILD NODES
//				if (node.hasChildNodes()) { // child nodes of testng-results node.
//					List<Suit> suitesList = new ArrayList<Suit>();
//					NodeList testRChildNodes = node.getChildNodes();
//					
//					for(int indexTRC =0; indexTRC < testRChildNodes.getLength(); indexTRC++) {
//						Node trcNode = testRChildNodes.item(indexTRC);
//						if(trcNode.getNodeType() == Node.ELEMENT_NODE) {
//							Suit suit = new Suit();
//							
//							// ****************** TEST-RESULT NODE'S ATTRIBUTES
//							if(trcNode.hasAttributes()) {
//								
//								NamedNodeMap trcNodeMap = trcNode.getAttributes();
//								for (int nmIndex = 0; nmIndex < trcNodeMap.getLength(); nmIndex++) {
//									Node attNode = trcNodeMap.item(nmIndex);
//									
//									if(attNode.getNodeName().equalsIgnoreCase("name"))
//										suit.setName(attNode.getNodeValue());
//									else if(attNode.getNodeName().equalsIgnoreCase("duration-ms"))
//										suit.setDuration_ms(Integer.valueOf(attNode.getNodeValue()));
//									// TO Do finished-at and started-at
//								}// end of for loop nmIndex
//							}//end of if trcNode
//
//							// ****************** TEST-RESULT NODE'S CHILD NODES 
//							if(trcNode.hasChildNodes()) {
//								List<Test> testList = new ArrayList<Test>();
//								NodeList testsNodes = trcNode.getChildNodes();
//								for (int testsIndex = 0; testsIndex < testsNodes.getLength(); testsIndex++) {
//									Node testsNode = testsNodes.item(testsIndex);
//									if(testsNode.getNodeType() == Node.ELEMENT_NODE) {
//										
//										Test test = new Test();
//										// this node value is null so we are not intertaining it.										
//										// ****************** TEST NODE'S ATTRIBUTES
//										if(testsNode.hasAttributes()) {
//											NamedNodeMap testsNodeMap = testsNode.getAttributes();
//											for (int tnmIndex = 0; tnmIndex < testsNodeMap.getLength(); tnmIndex++) {
//												Node tnmAttNode = testsNodeMap.item(tnmIndex);
//												
//												if(tnmAttNode.getNodeName().equalsIgnoreCase("name"))
//													test.setName(tnmAttNode.getNodeValue());
//												else if(tnmAttNode.getNodeName().equalsIgnoreCase("duration-ms"))
//													test.setDuration_ms(Integer.valueOf(tnmAttNode.getNodeValue()));
//												// TO Do finished-at and started-at
//											}
//										}// end of if hasattributes
//
//										// ****************** TEST NODE'S CHILD NODES
//										if(testsNode.hasChildNodes()) {
//											List<Classes> classesList = new ArrayList<Classes>();
//											NodeList classNodes = testsNode.getChildNodes();
//											
//											for (int cIndex = 0; cIndex < classNodes.getLength(); cIndex++) {
//												Node classNode = classNodes.item(cIndex);
//												if(classNode.getNodeType() == Node.ELEMENT_NODE) {
//													Classes classes = new Classes();
//													
//													if(classNode.hasAttributes()) {
//														NamedNodeMap classNodeMap = classNode.getAttributes();
//														
//														for (int cnmIndedx = 0; cnmIndedx < classNodeMap.getLength(); cnmIndedx++) {
//															Node classAttNode = classNodeMap.item(cnmIndedx);
//															if(classAttNode.getNodeName().equalsIgnoreCase("name"))
//																classes.setName(classAttNode.getNodeValue());											
//															
//														}// end of loop on classnode attributes
//													}// end of classNode has attributes
//													
//													// ****************** CLASS NODE'S CHILD NODES
//													if(classNode.hasChildNodes()) {
//														List<TestMethod> testMethodList = new ArrayList<TestMethod>();
//														NodeList testMethodNodeList = classNode.getChildNodes();
//														
//														for (int tmnIndex = 0; tmnIndex < testMethodNodeList.getLength(); tmnIndex++) {
//															Node testMethodNode = testMethodNodeList.item(tmnIndex);
//															if(testMethodNode.getNodeType() == node.ELEMENT_NODE) {
//																TestMethod testMethod = new TestMethod();
//																
//																if(testMethodNode.hasAttributes()) {
//																	NamedNodeMap tmAttNodemap = testMethodNode.getAttributes();
//																	
//																	for (int tmIndex = 0; tmIndex < tmAttNodemap.getLength(); tmIndex++) {
//																		Node tmAttNode = tmAttNodemap.item(tmIndex);
//																	
//																		if(tmAttNode.getNodeName().equalsIgnoreCase("name"))
//																			testMethod.setName(tmAttNode.getNodeValue());
//																		else if(tmAttNode.getNodeName().equalsIgnoreCase("status"))
//																			testMethod.setStatus(tmAttNode.getNodeValue());
//																		else if(tmAttNode.getNodeName().equalsIgnoreCase("signature"))
//																			testMethod.setSignature(tmAttNode.getNodeValue());
//																		else if(tmAttNode.getNodeName().equalsIgnoreCase("duration-ms"))
//																			testMethod.setSignature(tmAttNode.getNodeValue());
//																		
//																		//TO DO started-at and finished-at
//																		
//																	}// end of for loop tmIndex 
//																}// end of if testMethodNode has attributes
//																
//																if(testMethodNode.hasChildNodes()) {
//																	List<ExceptionOccured> exceptionList = new ArrayList<ExceptionOccured>();
//																	NodeList exceptionNodes = testMethodNode.getChildNodes();
//																	for (int eIndex = 0; eIndex < exceptionNodes.getLength(); eIndex++) {
//																		Node exceptionNode = exceptionNodes.item(eIndex);
//																	//	if(exceptionNode.getNodeType() == Node.ELEMENT_NODE) {
//																			
//																		ExceptionOccured exceptionOccured = new ExceptionOccured();
//																			if(exceptionNode.hasAttributes()) {
//																				
//																				NamedNodeMap exceptionNodemap = exceptionNode.getAttributes();
//																				
//																				for (int enmIndex = 0; enmIndex < exceptionNodemap.getLength(); enmIndex++) {
//																					
//																					Node exceptionAttNode = exceptionNodemap.item(enmIndex);
//																					System.out.println("exceptionAttNode Name: " + exceptionAttNode.getNodeName());
//																					System.out.println("exceptionAttNode Value: " + exceptionAttNode.getNodeValue());
//																					
//																					if(exceptionAttNode.getNodeName().equalsIgnoreCase("class"))
//																						exceptionOccured.setExceptionClass(exceptionAttNode.getNodeValue());
//																					else if(exceptionAttNode.getNodeName().equalsIgnoreCase("message"))
//																						exceptionOccured.setExceptionMessage(exceptionAttNode.getNodeValue());
//																					else if(exceptionAttNode.getNodeName().equalsIgnoreCase("full-stacktrace"))
//																						exceptionOccured.setFullStackTrace(exceptionAttNode.getNodeValue());
//																				}
//																			}
//																			exceptionList.add(exceptionOccured);
//																			testMethod.setExceptionList(exceptionList);
////																			if(exceptionNode.hasChildNodes()) {
////																				NodeList exceptionChildNodes = exceptionNode.getChildNodes();
////																				for (int ecIndex = 0; ecIndex < exceptionChildNodes.getLength(); ecIndex++) {
////																					
////																					Node exceptionChildNode = exceptionChildNodes.item(ecIndex);
////																					
////																					System.out.println("exceptionChildNode Name: " + exceptionChildNode.getNodeName());
////																					System.out.println("exceptionChildNode Value: " + exceptionChildNode.getNodeValue());
////																					if(exceptionChildNode.hasAttributes()) {
////																						NamedNodeMap excChildAttNodeMap =  exceptionChildNode.getAttributes();
////																						for (int ecaIndex = 0; ecaIndex < excChildAttNodeMap.getLength(); ecaIndex++) {
////																							Node messageNode = excChildAttNodeMap.item(ecaIndex);
////																							System.out.println("messageNode Name: " + messageNode.getNodeName());
////																							System.out.println("messageNode Value: " + messageNode.getNodeValue());
////																						}
////																					}
////																				}
////																			}
//																			
//																	//	}// end of if exceptionNode check type
//																	}// end of for loop to iterate exceptionNodes
//																}// end of if testMethodNode has child nodes
//																testMethodList.add(testMethod);
//																
//															}// end of if testMthodnode type checking
//														}// end of for loop testMethodnode list iterating
//														classes.setTestMethodList(testMethodList);
//														
//													}// end of if classNode hasChildNode
//												classesList.add(classes);
//												
//												}// end of classNOde check Type
//											}// end of for loop cIndex
//											test.setClassesList(classesList);
//											
//										}// end of if testsNode hasChildNodes
//										testList.add(test);
//										
//									}//end of if getNodeType
//								}
//								suit.setTestList(testList);
//								
//							}
//							suitesList.add(suit);
//							
//						}
//					}// end of for indexTRC
//					testResults.setSuitList(suitesList);
//					
//				} // end test results has child notes
//				
////				recordUtil(testResults);
//				
//
//			} // test results
//		} // end attributes for test results
//	}
//
//	private static void printNote(NodeList nodeList) {
//
//		for (int count = 0; count < nodeList.getLength(); count++) {
//
//			Node tempNode = nodeList.item(count);
//
//			// make sure it's element node.
//			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
//
//				// get node name and value
//				System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
//				System.out.println("Node Value =" + tempNode.getTextContent());
//
//				if (tempNode.hasAttributes()) {
//
//					// get attributes names and values
//					NamedNodeMap nodeMap = tempNode.getAttributes();
//
//					for (int i = 0; i < nodeMap.getLength(); i++) {
//						Node node = nodeMap.item(i);
//						System.out.println("attr name : " + node.getNodeName());
//						System.out.println("attr value : " + node.getNodeValue());
//
//					}
//
//				}
//
//				if (tempNode.hasChildNodes()) {
//					// loop again if has child nodes
//					printNote(tempNode.getChildNodes());
////			System.out.println("*** 3rd L End --- ");
//				}
//
//				System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
//
//			}
//
//		}
//
//	}
//
//	public void testCaseForRecordUtil() {
//		TestngResults testResults = new TestngResults();
//		testResults.setPassed(100);
//		recordUtil(testResults);
//
//		Suit suit = new Suit();
//		suit.setName("MySuite1");
//		recordUtil(suit);
//
//		Test test = new Test();
//		test.setName("MyTest1");
//		recordUtil(test);
//
//		Classes classes = new Classes();
//		classes.setName("TestClass1");
//		recordUtil(classes);
//
//		TestMethod testMethod1 = new TestMethod();
//		testMethod1.setName("TestMetho3()");
//		recordUtil(testMethod1);
//
//		TestMethod testMethod2 = new TestMethod();
//		testMethod2.setName("TestMetho3()");
//		recordUtil(testMethod2);
//
//		TestMethod testMethod3 = new TestMethod();
//		testMethod3.setName("TestMetho3()");
//		recordUtil(testMethod3);
//
//		Classes classes2 = new Classes();
//		classes2.setName("TestClass1");
//		recordUtil(classes2);
//
//		testMethod1 = new TestMethod();
//		testMethod1.setName("TestMetho3()");
//		recordUtil(testMethod1);
//
//		testMethod2 = new TestMethod();
//		testMethod2.setName("TestMetho3()");
//		recordUtil(testMethod2);
//
//		// ============= [ LOGS ] ================
//		System.out.println(testResults.getPassed());
//		// System.out.println(testResults.getSuitList().size());
//		System.out.println(testResults.getSuitList().get(0).getTestList().get(0).getClassesList().size());
//		System.out.println(testResults.getSuitList().get(0).getTestList().get(0).getClassesList().get(0)
//				.getTestMethodList().size());
//		System.out.println(testResults.getSuitList().get(0).getTestList().get(0).getClassesList().get(1)
//				.getTestMethodList().size());
//
//	}
//
//	public static TestngResults testResults = null;
//
//	static void recordUtil(Object obj) {
//		if (obj instanceof TestngResults) {
//			testResults = (TestngResults) obj;
//		}
//		if (obj instanceof Suit) {
//			Suit suit = (Suit) obj;
//			List<Suit> suitList = testResults.getSuitList();
//			suitList.add(suit);
//			testResults.setSuitList(suitList);
//		}
//		if (obj instanceof Test) {
//			Test test = (Test) obj;
//			int suiteListCurrentIndex = testResults.getSuitList().size() - 1;
//			List<Test> testList = testResults.getSuitList().get(suiteListCurrentIndex).getTestList();
//			testList.add(test);
//			testResults.getSuitList().get(suiteListCurrentIndex).setTestList(testList);
//		}
//		if (obj instanceof Classes) {
//			Classes classes = (Classes) obj;
//			int suiteListCurrentIndex = testResults.getSuitList().size() - 1;
//			int testListCurrentIndex = testResults.getSuitList().get(suiteListCurrentIndex).getTestList().size() - 1;
//			List<Classes> classesList = testResults.getSuitList().get(suiteListCurrentIndex).getTestList()
//					.get(testListCurrentIndex).getClassesList();
//			classesList.add(classes);
//			testResults.getSuitList().get(suiteListCurrentIndex).getTestList().get(testListCurrentIndex)
//					.setClassesList(classesList);
//		}
//		if (obj instanceof TestMethod) {
//			TestMethod testMethod = (TestMethod) obj;
//
//			int suiteListCurrentIndex = testResults.getSuitList().size() - 1;
//			int testListCurrentIndex = testResults.getSuitList().get(suiteListCurrentIndex).getTestList().size() - 1;
//			int classListCurrentIndex = testResults.getSuitList().get(suiteListCurrentIndex).getTestList()
//					.get(testListCurrentIndex).getClassesList().size() - 1;
//
//			List<TestMethod> testMethodList = testResults.getSuitList().get(suiteListCurrentIndex).getTestList()
//					.get(testListCurrentIndex).getClassesList().get(classListCurrentIndex).getTestMethodList();
//
//			testMethodList.add(testMethod);
//			testResults.getSuitList().get(suiteListCurrentIndex).getTestList().get(testListCurrentIndex)
//					.getClassesList().get(classListCurrentIndex).setTestMethodList(testMethodList);
//			;
//		}
//	}
//
//}
