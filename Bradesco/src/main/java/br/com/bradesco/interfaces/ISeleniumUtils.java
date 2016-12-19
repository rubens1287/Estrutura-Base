package br.com.bradesco.interfaces;

import org.openqa.selenium.WebDriver;
import br.com.bradesco.framework.SeleniumUtils;


public interface ISeleniumUtils {
		
	/* 1 = Internt explorer
	 * 2 = Chrome
	 * 3 = Firefox
	 */
	public final int browserNumber = 2;
	
	public final boolean isProductionEnvironment = true;
	public static int WAIT_ELEMENT_LOAD_SECONDS = 30;
	public final String email = "qa01@rubens.com.br";
	public final String passWord = "123456";
	public final String url = new SeleniumUtils().Url(isProductionEnvironment);
	public final WebDriver driver = new SeleniumUtils().setUpDriver(
			browserNumber, 30, WAIT_ELEMENT_LOAD_SECONDS);
	
}
