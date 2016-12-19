package br.com.bradesco.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

import br.com.bradesco.framework.Util;
import br.com.bradesco.interfaces.ISeleniumUtils;

public class PageAutoAtendimento implements ISeleniumUtils{

	By inputBoxNumero = By.name("numero");
	By btnEnviar = By.name("button");
	By btnImprimirCarterinha = By.xpath("/html/body/div/form/div[2]/table/tbody/tr[2]/td[4]/input");								
	By btnFechaJanelaImprimir = By.xpath("//*[@id='print-header']/div/button[2]");
	
	Util utils = new Util();
	
	private void setInputBoxNumero(String value) {
		driver.findElement(inputBoxNumero).clear();
		driver.findElement(inputBoxNumero).sendKeys(value);
	}
	
	private void clickBtnEnviar() {
		
		Util utils = new Util();
		try {
			driver.findElement(btnEnviar).click();
			utils.AguardaPoupAlertClicaOK(driver,20,true);
		} catch (Exception e) {
			
			System.out.println("Erro ao rodar o metodo 'PageAutoAtendimento.clickBtnEnviar' : " + e);
		}
		
	}
	
	private void clickBtnImprimirCarterinha(){
		driver.findElement(By.xpath("btnImprimirCarterinha")).sendKeys(Keys.ENTER);	
				
	}
	
	private void clickBtnCancelaImpressao() {
		driver.findElement(btnFechaJanelaImprimir).click();
	}
	
	public void executeClickBotaoImprimirCarterinha(){
		this.clickBtnImprimirCarterinha();
		utils.AguardaAteaSuaPresencaBy(driver, 20, btnFechaJanelaImprimir);
		this.clickBtnCancelaImpressao();
		utils.AguardaAteaSuaPresencaBy(driver, 20, By.id("idNrProtocolo"));
	}
	
	public void digitaCampoNumeroClicaBotaoEnviarConfirmaAlertJavaScript(String numero){
		this.setInputBoxNumero(numero);
		this.clickBtnEnviar();
	}
	
}
