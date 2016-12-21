package br.com.bradesco.page;

import org.openqa.selenium.By;
import br.com.bradesco.interfaces.ISeleniumUtils;

public class PageFaleConosco implements ISeleniumUtils{

	By inputBoxEmail = By.name("email");
	By inputBoxTelefone	 = By.name("telefone");
	By inputBoxAssunto = By.name("assunto");
	By inputBoxMensagem = By.name("mensagem");
	By btnEnviar = By.id("enviar");
	
	
	private void inputBoxEmail(String value){
		driver.findElement(inputBoxEmail).sendKeys(value);
	}
	
	private void inputBoxTelefone(String value){
		driver.findElement(inputBoxTelefone).sendKeys(value);
	}
	
	private void inputBoxAssunto(String value){
		driver.findElement(inputBoxAssunto).sendKeys(value);
	}
	
	private void inputBoxMensagem(String value){
		driver.findElement(inputBoxMensagem).sendKeys(value);
	}
	
	private void clickBtnEnviar(){
		driver.findElement(btnEnviar).click();
	}
	
	public void executaEnvioFormularioFaleConosco(String emailUsuario, String telefone, String assunto, String mensagem){
		this.inputBoxEmail(email);
		this.inputBoxTelefone(telefone);
		this.inputBoxAssunto(assunto);
		this.inputBoxMensagem(mensagem);
		this.clickBtnEnviar();
	}
	
	
}
