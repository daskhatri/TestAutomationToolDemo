//package com.finexus.automation;
//
//public class FileWriting {
//
//	public static void main(String[] args) {
//		String = "import java.util.regex.Pattern;\r\n" + 
//				"import java.util.concurrent.TimeUnit;\r\n" + 
//				"import org.junit.*;\r\n" + 
//				"import static org.junit.Assert.*;\r\n" + 
//				"import static org.hamcrest.CoreMatchers.*;\r\n" + 
//				"import org.openqa.selenium.*;\r\n" + 
//				"import org.openqa.selenium.firefox.FirefoxDriver;\r\n" + 
//				"import org.openqa.selenium.support.ui.Select;\r\n" + 
//				"\r\n" + 
//				"public class TestHRM {\r\n" + 
//				"  private WebDriver driver;\r\n" + 
//				"  private String baseUrl;\r\n" + 
//				"  private boolean acceptNextAlert = true;\r\n" + 
//				"  private StringBuffer verificationErrors = new StringBuffer();\r\n" + 
//				"\r\n" + 
//				"  @Before\r\n" + 
//				"  public void setUp() throws Exception {\r\n" + 
//				"    driver = new FirefoxDriver();\r\n" + 
//				"    baseUrl = \"https://www.katalon.com/\";\r\n" + 
//				"    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);\r\n" + 
//				"  }\r\n" + 
//				"\r\n" + 
//				"  @Test\r\n" + 
//				"  public void testHRM() throws Exception {\r\n" + 
//				"    driver.get(\"https://opensource-demo.orangehrmlive.com/\");\r\n" + 
//				"    driver.findElement(By.xpath(\"(.//*[normalize-space(text()) and normalize-space(.)='LOGIN Panel'])[1]/following::span[1]\")).click();\r\n" + 
//				"    driver.findElement(By.id(\"txtUsername\")).clear();\r\n" + 
//				"    driver.findElement(By.id(\"txtUsername\")).sendKeys(\"admin\");\r\n" + 
//				"    driver.findElement(By.id(\"txtPassword\")).clear();\r\n" + 
//				"    driver.findElement(By.id(\"txtPassword\")).sendKeys(\"admin123\");\r\n" + 
//				"    driver.findElement(By.id(\"btnLogin\")).click();\r\n" + 
//				"    driver.findElement(By.xpath(\"(.//*[normalize-space(text()) and normalize-space(.)='Directory'])[1]/following::b[1]\")).click();\r\n" + 
//				"    driver.findElement(By.id(\"confirm_password\")).click();\r\n" + 
//				"    driver.findElement(By.id(\"confirm_password\")).clear();\r\n" + 
//				"    driver.findElement(By.id(\"confirm_password\")).sendKeys(\"admin123\");\r\n" + 
//				"    driver.findElement(By.xpath(\"(.//*[normalize-space(text()) and normalize-space(.)='*'])[1]/following::input[4]\")).click();\r\n" + 
//				"    driver.findElement(By.id(\"welcome\")).click();\r\n" + 
//				"    driver.findElement(By.linkText(\"Logout\")).click();\r\n" + 
//				"  }\r\n" + 
//				"\r\n" + 
//				"  @After\r\n" + 
//				"  public void tearDown() throws Exception {\r\n" + 
//				"    driver.quit();\r\n" + 
//				"    String verificationErrorString = verificationErrors.toString();\r\n" + 
//				"    if (!\"\".equals(verificationErrorString)) {\r\n" + 
//				"      fail(verificationErrorString);\r\n" + 
//				"    }\r\n" + 
//				"  }\r\n" + 
//				"\r\n" + 
//				"  private boolean isElementPresent(By by) {\r\n" + 
//				"    try {\r\n" + 
//				"      driver.findElement(by);\r\n" + 
//				"      return true;\r\n" + 
//				"    } catch (NoSuchElementException e) {\r\n" + 
//				"      return false;\r\n" + 
//				"    }\r\n" + 
//				"  }\r\n" + 
//				"\r\n" + 
//				"  private boolean isAlertPresent() {\r\n" + 
//				"    try {\r\n" + 
//				"      driver.switchTo().alert();\r\n" + 
//				"      return true;\r\n" + 
//				"    } catch (NoAlertPresentException e) {\r\n" + 
//				"      return false;\r\n" + 
//				"    }\r\n" + 
//				"  }"
//
//	}
//
//}
