package br.com.bradesco.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import br.com.bradesco.interfaces.ISeleniumUtils;

public class PageCanaisDeAtendimento implements ISeleniumUtils{
	
	By inputBoxDtInicio = By.id("dtInicial");
	By inputBoxDtFinal = By.id("dtFinal");
	By btnBuscar = By.id("buscar");
	
	private void setDataIncial(String value){
		driver.findElement(inputBoxDtInicio).sendKeys(value);
	}
	
	private void setDataFim(String value){
		driver.findElement(inputBoxDtFinal).sendKeys(value);
		driver.findElement(inputBoxDtFinal).sendKeys(Keys.TAB);
	}
	
	private void clickBtnBuscar() {
		driver.findElement(btnBuscar).sendKeys(Keys.ENTER);
	}
	
	public void ExecutaBuscaExtratoDeAtendimento(String dtInicio, String dtFinal){	
		try {
			this.setDataIncial(dtInicio);
			this.setDataFim(dtFinal);
			Thread.sleep(1000);
			this.clickBtnBuscar();
			
		} catch (Exception e) {
			System.out.println("Erro ao rodar metodo 'ExecutaBuscaExtratoDeAtendimento': " + e);
		}
		
	}
	
	
}
