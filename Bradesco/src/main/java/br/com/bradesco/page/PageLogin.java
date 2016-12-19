/**
 * 
 */
package br.com.bradesco.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.bradesco.interfaces.ISeleniumUtils;

/**
 * @author Rubens Lobo
 *
 */
public class PageLogin implements ISeleniumUtils {

	
	By inputBoxLogin=By.name("login");	
	By inputBoxPass=By.name("password");
	By btnOk =By.xpath("/html/body/table/tbody/tr[6]/td/table/tbody/tr/td[2]/form/table/tbody/tr[2]/td[2]/a");
	

	private void setInputBoxLogin(String valor) {
		driver.findElement(inputBoxLogin).sendKeys(valor);
	}
	
	private void setInputBoxPass(String valor) {
		driver.findElement(inputBoxPass).sendKeys(valor);
	}
	
	private void clickButtonOk() {
		driver.findElement(btnOk).click();
	}
	
	public void ExecutaLogin(String usario, String senha){
		this.setInputBoxLogin(usario);
		this.setInputBoxPass(senha);
		this.clickButtonOk();	
	}

		
}
