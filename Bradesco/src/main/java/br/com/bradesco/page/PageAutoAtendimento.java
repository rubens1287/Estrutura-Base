package br.com.bradesco.page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

import br.com.bradesco.framework.Util;
import br.com.bradesco.interfaces.ISeleniumUtils;

public class PageAutoAtendimento implements ISeleniumUtils{

	By inputBoxNumero = By.name("numero");
	By btnEnviar = By.name("button");
	By btnImprimirCarterinha = By.cssSelector("td[class='portal_titulo']");								
	By btnEnviarPorEmail = By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[3]/table[4]/tbody/tr[2]/td/center/button[2]");
	//Janela dois
	By btnEnviarEmail = By.xpath("/html/body/table[2]/tbody/tr[5]/td/table/tbody/tr/td[2]/form/input[3]");
	By inputBoxEmail = By.name("email");
	
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
	
	private void clickBtnImprimirCarterinha() {
		try {
			Robot robot = new Robot();
			driver.findElement(btnImprimirCarterinha).click();
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_ENTER);
			
		} catch (Exception e) {
			System.out.println("Erro metodo 'clickBtnImprimirCarterinha' : " + e);
		}
		
	}
		
	private void clickBtnEnviarPorEmail(){
		driver.findElement(btnEnviarPorEmail).click();
	}
	
	private void clickBtnEviarEmail(){
		driver.findElement(btnEnviarEmail).sendKeys(Keys.ENTER);
	}
	
	private void setInputBoxEmail(String value){
		driver.findElement(inputBoxEmail).clear();
		driver.findElement(inputBoxEmail).sendKeys(value);
	}
	
	public void executeClickBotaoImprimirCarterinha(){
		this.clickBtnImprimirCarterinha();
		utils.AguardaAteaSuaPresencaBy(driver, 20, By.id("idNrProtocolo"));
	}
	
	public void digitaCampoNumeroClicaBotaoEnviarConfirmaAlertJavaScript(String numero){
		this.setInputBoxNumero(numero);
		this.clickBtnEnviar();
	}
	
	public void executeClickBtnEviarPorEmail(){
		this.clickBtnEnviarPorEmail();
	}
	
	public void executeEnvioDeEmailNaJanelaDois(String email){
		try{
			this.setInputBoxEmail(email);
			Thread.sleep(1000);
			this.clickBtnEviarEmail();
		}catch(Exception e){
			System.out.println("Erro ao rodar o metodo 'PageAutoAtendimento.executeEnvioDeEmailNaJanelaDois' : " + e);
		}
		
	}
	
}
