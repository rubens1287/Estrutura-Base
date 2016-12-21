package br.com.bradesco.page;

import org.openqa.selenium.By;

import br.com.bradesco.framework.Util;
import br.com.bradesco.interfaces.ISeleniumUtils;

public class PageMenuPrincipal implements ISeleniumUtils {

	By lnkCarterinha = By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[8]/td/a");	
	By lnkSolicitarSegViaCarterinha = By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[3]/table[3]/tbody/tr[4]/td/b/i/a");
	By lnkExtratoAtendimento = By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[10]/td/a");
	By LnkExtratoDePagamento = By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[7]/td/a");
	By LnkFaleConosco = By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[11]/td/a");
	By LnkBradescoDentaMail = By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td");
	By LnkAvisoAbertura = By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[3]/td/span/table/tbody/tr[2]/td/a");
	
	Util utils = new Util();
	
	private void clickLnkCarterinha() {
		driver.findElement(lnkCarterinha).click();
	}
	
	private void clickLnkSolicitaSegViaCarterinha() {
		driver.findElement(lnkSolicitarSegViaCarterinha).click();
	}
	
	private void clickLnkExtratoDePagamento(){
		driver.findElement(LnkExtratoDePagamento).click();
	}
	
	public void clickLnkExtratoAtendimento() {
		driver.findElement(lnkExtratoAtendimento).click();
	}
	
	private void clickLnkFaleConosco(){
		driver.findElement(LnkFaleConosco).click();
	}
	
	private void clickLnkBradescoDentaMail(){
		driver.findElement(LnkBradescoDentaMail).click();
	}
	
	private void clickLnkAvisoAbertura(){
		driver.findElement(LnkAvisoAbertura).click();
	}
		
	public void executaClickLnkCarterinha(){
		this.clickLnkCarterinha();
	}
	
	public void executaSolicitacaoSegViaCarterinha(){
		this.clickLnkCarterinha();
		utils.AguardaAteaSuaPresencaBy(driver , 20, lnkSolicitarSegViaCarterinha);
		this.clickLnkSolicitaSegViaCarterinha();
	}
	
	public void executaSolicitacaoExtratoPagamento(){
		this.clickLnkExtratoDePagamento();	
	}
	
	public void executeClickFaleConosco(){
		this.clickLnkFaleConosco();
	}
	
	public void executeClickAvisoAbertura(){
		try{
			this.clickLnkBradescoDentaMail();
			Thread.sleep(1000);
			this.clickLnkAvisoAbertura();
		}catch(Exception e){
			System.out.println("Erro no metodo 'executeClickAvisoAbertura' " + e);
		}
	}
	
	public void executaclickLnkAvisoAbertura(){
		driver.findElement(LnkAvisoAbertura).click();
	}
	
	
}
