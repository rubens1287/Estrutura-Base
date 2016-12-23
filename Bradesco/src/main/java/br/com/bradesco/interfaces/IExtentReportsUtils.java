package br.com.bradesco.interfaces;

import com.relevantcodes.extentreports.ExtentReports;

/**
 * Interface com IExtentReports
 * 
 * @author Rubens Lobo
 *
 */
public interface IExtentReportsUtils {
	
	public final ExtentReports report = new ExtentReports(".//target//surefire-reports/Report.html");
 

}
