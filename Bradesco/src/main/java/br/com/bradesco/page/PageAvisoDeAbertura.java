package br.com.bradesco.page;

import org.openqa.selenium.By;

import br.com.bradesco.interfaces.ISeleniumUtils;

public class PageAvisoDeAbertura implements ISeleniumUtils{

	By lnkCliqueAqui = By.cssSelector("a[class='linkCinza']");
	
	
	private void clickLnkCliqueAqui(){
		driver.findElement(lnkCliqueAqui).click();
	}
	
	public void executeCliqueAqui(){
		this.clickLnkCliqueAqui();
	}
}
