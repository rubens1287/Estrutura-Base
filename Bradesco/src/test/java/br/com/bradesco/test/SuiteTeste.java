package br.com.bradesco.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentTest;
import br.com.bradesco.interfaces.IExtentReportsUtils;
import br.com.bradesco.interfaces.ISeleniumUtils;

public class SuiteTeste implements ISeleniumUtils, IExtentReportsUtils{

	public WebDriver webDriver = driver;
	
	@BeforeSuite
	public void setUp(){
		this.webDriver = driver;
	}
	
	@AfterSuite
	public void TearDow(){
		this.webDriver.quit();	
	}
}