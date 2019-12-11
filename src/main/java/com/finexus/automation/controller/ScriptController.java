package com.finexus.automation.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.IndexDiff.StageState;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.finexus.automation.entity.Classes;
import com.finexus.automation.entity.ExceptionsNode;
import com.finexus.automation.entity.Suite;
import com.finexus.automation.entity.Test;
import com.finexus.automation.entity.TestCase;
import com.finexus.automation.entity.TestMethod;
import com.finexus.automation.entity.TestngResults;
import com.finexus.automation.repository.TestngResultsRepository;

@Controller
@RequestMapping(value = "/api")
public class ScriptController {

	
	@Autowired	
	private TestngResultsRepository testngResultRep;
	
//	@ResponseBody 
	@RequestMapping(value = "/search/api/getSearchResult/{fileName}", method = RequestMethod.POST)

	public String getSearchResultViaAjax(@RequestBody String content,
			@Valid @PathVariable(value = "fileName") String fileName) {
		System.out.println("content from server: " + content);
		System.out.println("filname from server: " + fileName);
		try {
			// **************** UPLOADING NEW TEST CASE, FROM EXTENSION TO PROJECT DIRECTORY  ***************
			writeUsingOutputStream(content, fileName);

			// ********** PUSH THE Test case file to Github *********
			pushOverRepository(fileName);
			
			Thread.sleep(60000);
			
			createTestngResults();
			
		} catch (IOException | NoWorkTreeException | GitAPIException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "successful";
	}

	private void pushOverRepository(String fileName) throws IOException, NoWorkTreeException, GitAPIException {

//		Git git = Git.cloneRepository().setURI("https://github.com/daskhatri/FormProject").call();
//		Git git = Git.cloneRepository().setURI("https://github.com/daskhatri/MavenFinalDemo.git").call();
		// Open an existing repository
//		Repository existingRepo = new FileRepositoryBuilder().setGitDir(new File("FormProject/.git")).build();
//		if(existingRepo.equals(true)) {
//		}
		
		if (new File("D:/mirtalk-ws-eclipse/TestAutomationServer/MavenFinalDemo/.git").exists()) {
			Git git = Git.open(new File("D:/mirtalk-ws-eclipse/TestAutomationServer/MavenFinalDemo/.git"));
			// Status status = git.status().call();
			Status status = git.status().call();

			if (!status.isClean()) {
				git.add().addFilepattern(".").call();
				git.commit().setMessage("Commit to add the file: " + fileName).call();

				CredentialsProvider cp = new UsernamePasswordCredentialsProvider("daskhatri", "github#786");
				git.push().setCredentialsProvider(cp).call();
			}

			Set<String> conflicting = status.getConflicting();
			for (String conflict : conflicting) {
				System.out.println("Conflicting: " + conflict);
			}
			Set<String> added = status.getAdded();
			for (String add : added) {
				System.out.println("Added: " + add);
			}
			Set<String> changed = status.getChanged();
			for (String change : changed) {
				System.out.println("Change: " + change);
			}
			Set<String> missing = status.getMissing();
			for (String miss : missing) {
				System.out.println("Missing: " + miss);
			}
			Set<String> modified = status.getModified();
			for (String modify : modified) {
				System.out.println("Modification: " + modify);
			}
			Set<String> removed = status.getRemoved();
			for (String remove : removed) {
				System.out.println("Removed: " + remove);
			}
			Set<String> uncommittedChanges = status.getUncommittedChanges();
			for (String uncommitted : uncommittedChanges) {
				System.out.println("Uncommitted: " + uncommitted);
			}
			Set<String> untracked = status.getUntracked();
			for (String untrack : untracked) {
				System.out.println("Untracked: " + untrack);
			}
			Set<String> untrackedFolders = status.getUntrackedFolders();
			for (String untrack : untrackedFolders) {
				System.out.println("Untracked Folder: " + untrack);
			}
			Map<String, StageState> conflictingStageState = status.getConflictingStageState();
			for (Map.Entry<String, StageState> entry : conflictingStageState.entrySet()) {
				System.out.println("ConflictingState: " + entry);
			}
		}else {
			System.out.println("File directory does not exist!");
		}
	}

	/*
	 * public static void stringToDom(String xmlSource, String fileName) throws
	 * SAXException, ParserConfigurationException, IOException, TransformerException
	 * { // Parse the given input DocumentBuilderFactory factory =
	 * DocumentBuilderFactory.newInstance(); DocumentBuilder builder =
	 * factory.newDocumentBuilder(); Document doc = builder.parse(new
	 * InputSource(new StringReader(xmlSource)));
	 * 
	 * // Write the parsed document to an xml file TransformerFactory
	 * transformerFactory = TransformerFactory.newInstance(); Transformer
	 * transformer = transformerFactory.newTransformer(); DOMSource source = new
	 * DOMSource(doc);
	 * 
	 * StreamResult result = new StreamResult(new
	 * File("d:/mirtalk-ws-eclipse/TestAutomationServer/code/" + fileName));
	 * transformer.transform(source, result);
	 * 
	 * }
	 */
	private static void writeUsingOutputStream(String data, String fileName) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File("D:/mirtalk-ws-eclipse/TestAutomationServer/MavenFinalDemo/src/test/java/com/finexus/tests/" + fileName));

			os.write(data.getBytes(), 0, data.length());
			
			 Path path = Paths.get("D:/mirtalk-ws-eclipse/TestAutomationServer/MavenFinalDemo/src/test/java/com/finexus/tests/" + fileName);
	            Stream <String> lines = Files.lines(path);
	            
	            List <String> replaced = lines.map(line -> line.replaceAll("FirefoxDriver", "ChromeDriver").replaceAll(".firefox", ".chrome").replaceAll("package com.example.tests;", "package com.finexus.tests;")).collect(Collectors.toList());
	            Files.write(path, replaced);
	            lines.close();
			System.out.println("Writing done");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	@RequestMapping(value = "/testApi/saveTestngResults", method = RequestMethod.POST)
	public void createTestngResults() {
		
		try {
			TestngResults testNgObj = parseTheXml();
			
			TestngResults savedEntity = testngResultRep.save(testNgObj);
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@RequestMapping(value = "/search/api/parseTheXml", method = RequestMethod.POST)
	private TestngResults parseTheXml() throws ParserConfigurationException, SAXException, IOException{
		TestngResults testResults = null;
		// Get Document Builder
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();

				// Load the input XML document, parse it and return an instance of the
				// Document class.
				Document document = builder.parse(new File("C:/Program Files (x86)/Jenkins/workspace/MavenDemoTest/target/surefire-reports/testng-results.xml"));

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
							
							for(int indexTRC =0; indexTRC < testRChildNodes.getLength(); indexTRC++) {
								Node trcNode = testRChildNodes.item(indexTRC);
								if(trcNode.getNodeType() == Node.ELEMENT_NODE) {
									Suite suite = new Suite();
									
									// ****************** TEST-RESULT NODE'S ATTRIBUTES
									if(trcNode.hasAttributes()) {
										
										NamedNodeMap trcNodeMap = trcNode.getAttributes();
										for (int nmIndex = 0; nmIndex < trcNodeMap.getLength(); nmIndex++) {
											Node attNode = trcNodeMap.item(nmIndex);
											
											if(attNode.getNodeName().equalsIgnoreCase("name"))
												suite.setName(attNode.getNodeValue());
											else if(attNode.getNodeName().equalsIgnoreCase("duration-ms"))
												suite.setDuration_ms(Integer.valueOf(attNode.getNodeValue()));
											else if(attNode.getNodeName().equalsIgnoreCase("started-at"))
												suite.setStarted_at(attNode.getNodeValue());
											else if(attNode.getNodeName().equalsIgnoreCase("finished-at"))
												suite.setFinished_at(attNode.getNodeValue());
										}// end of for loop nmIndex
									}//end of if trcNode

									// ****************** TEST-RESULT NODE'S CHILD NODES 
									if(trcNode.hasChildNodes()) {
										List<Test> testList = new ArrayList<Test>();
										NodeList testsNodes = trcNode.getChildNodes();
										for (int testsIndex = 0; testsIndex < testsNodes.getLength(); testsIndex++) {
											Node testsNode = testsNodes.item(testsIndex);
											if(testsNode.getNodeType() == Node.ELEMENT_NODE) {
												
												Test test = new Test();
												// this node value is null so we are not intertaining it.										
												// ****************** TEST NODE'S ATTRIBUTES
												if(testsNode.hasAttributes()) {
													NamedNodeMap testsNodeMap = testsNode.getAttributes();
													for (int tnmIndex = 0; tnmIndex < testsNodeMap.getLength(); tnmIndex++) {
														Node tnmAttNode = testsNodeMap.item(tnmIndex);
														
														if(tnmAttNode.getNodeName().equalsIgnoreCase("name"))
															test.setName(tnmAttNode.getNodeValue());
														else if(tnmAttNode.getNodeName().equalsIgnoreCase("duration-ms"))
															test.setDuration_ms(Integer.valueOf(tnmAttNode.getNodeValue()));
														else if(tnmAttNode.getNodeName().equalsIgnoreCase("started-at"))
															test.setStarted_at(tnmAttNode.getNodeValue());
														else if(tnmAttNode.getNodeName().equalsIgnoreCase("finished-at"))
															test.setFinished_at(tnmAttNode.getNodeValue());
													}
												}// end of if hasattributes

												// ****************** TEST NODE'S CHILD NODES i.e class node
												if(testsNode.hasChildNodes()) {
													List<TestCase> classesList = new ArrayList<TestCase>();
													NodeList classNodes = testsNode.getChildNodes();
													
													for (int cIndex = 0; cIndex < classNodes.getLength(); cIndex++) {
														Node classNode = classNodes.item(cIndex);
														if(classNode.getNodeType() == Node.ELEMENT_NODE) {
															TestCase classes = new TestCase();
															
															if(classNode.hasAttributes()) {
																NamedNodeMap classNodeMap = classNode.getAttributes();
																
																for (int cnmIndedx = 0; cnmIndedx < classNodeMap.getLength(); cnmIndedx++) {
																	Node classAttNode = classNodeMap.item(cnmIndedx);
																	if(classAttNode.getNodeName().equalsIgnoreCase("name"))
																		classes.setName(classAttNode.getNodeValue());											
																	
																}// end of loop on classnode attributes
															}// end of classNode has attributes
															
															// ****************** CLASS NODE'S CHILD NODES, i.e test-method
															if(classNode.hasChildNodes()) {
																List<TestMethod> testMethodList = new ArrayList<TestMethod>();
																NodeList testMethodNodeList = classNode.getChildNodes();
																
																for (int tmnIndex = 0; tmnIndex < testMethodNodeList.getLength(); tmnIndex++) {
																	Node testMethodNode = testMethodNodeList.item(tmnIndex);
																	if(testMethodNode.getNodeType() == node.ELEMENT_NODE) {
																		TestMethod testMethod = new TestMethod();
																		
																		if(testMethodNode.hasAttributes()) {
																			NamedNodeMap tmAttNodemap = testMethodNode.getAttributes();
																			
																			for (int tmIndex = 0; tmIndex < tmAttNodemap.getLength(); tmIndex++) {
																				Node tmAttNode = tmAttNodemap.item(tmIndex);
																				
																				if(tmAttNode.getNodeName().equalsIgnoreCase("name"))
																					testMethod.setName(tmAttNode.getNodeValue());
																				else if(tmAttNode.getNodeName().equalsIgnoreCase("status"))
																					testMethod.setStatus(tmAttNode.getNodeValue());
																				else if(tmAttNode.getNodeName().equalsIgnoreCase("signature"))
																					testMethod.setSignature(tmAttNode.getNodeValue());
																				else if(tmAttNode.getNodeName().equalsIgnoreCase("duration-ms"))
																					testMethod.setSignature(tmAttNode.getNodeValue());
																				else if(tmAttNode.getNodeName().equalsIgnoreCase("started-at"))
																					testMethod.setStarted_at(tmAttNode.getNodeValue());
																				else if(tmAttNode.getNodeName().equalsIgnoreCase("finished-at"))
																					testMethod.setFinished_at(tmAttNode.getNodeValue());
																				
																			}// end of for loop tmIndex 
																		}// end of if testMethodNode has attributes
																		
																		if(testMethodNode.hasChildNodes()) {
																			List<ExceptionsNode> exceptionList = new ArrayList<ExceptionsNode>();
																			NodeList exceptionNodes = testMethodNode.getChildNodes();
																			for (int eIndex = 0; eIndex < exceptionNodes.getLength(); eIndex++) {
																				Node exceptionNode = exceptionNodes.item(eIndex);
																			//	if(exceptionNode.getNodeType() == Node.ELEMENT_NODE) {
																					
																				ExceptionsNode exceptionOccured = new ExceptionsNode();
																					if(exceptionNode.hasAttributes()) {
																						
																						NamedNodeMap exceptionNodemap = exceptionNode.getAttributes();
																						
																						for (int enmIndex = 0; enmIndex < exceptionNodemap.getLength(); enmIndex++) {
																							
																							Node exceptionAttNode = exceptionNodemap.item(enmIndex);
																							System.out.println("exceptionAttNode Name: " + exceptionAttNode.getNodeName());
																							System.out.println("exceptionAttNode Value: " + exceptionAttNode.getNodeValue());
																							
																							if(exceptionAttNode.getNodeName().equalsIgnoreCase("class"))
																								exceptionOccured.setExceptionClass(exceptionAttNode.getNodeValue());
																							else if(exceptionAttNode.getNodeName().equalsIgnoreCase("message"))
																								exceptionOccured.setExceptionMessage(exceptionAttNode.getNodeValue());
																							else if(exceptionAttNode.getNodeName().equalsIgnoreCase("full-stacktrace"))
																								exceptionOccured.setFullStackTrace(exceptionAttNode.getNodeValue());
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
																					
																			//	}// end of if exceptionNode check type
																			}// end of for loop to iterate exceptionNodes
																		}// end of if testMethodNode has child nodes
																		testMethodList.add(testMethod);
																		
																	}// end of if testMthodnode type checking
																}// end of for loop testMethodnode list iterating
																classes.setTestMethodsList(testMethodList);
																
															}// end of if classNode hasChildNode
														classesList.add(classes);
														
														}// end of classNOde check Type
													}// end of for loop cIndex
													test.setTestCaseList(classesList);
													
												}// end of if testsNode hasChildNodes
												testList.add(test);
												
											}//end of if getNodeType
										}
										suite.setTestList(testList);
										
									}
									suitesList.add(suite);
									
								}
							}// end of for indexTRC
							testResults.setSuiteList(suitesList);
						} // end test results has child notes
						
					} // test results
				} // end attributes for test results
				return testResults;

	}
	
}
