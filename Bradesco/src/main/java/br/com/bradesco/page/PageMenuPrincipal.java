package br.com.bradesco.page;

import org.openqa.selenium.By;

import br.com.bradesco.framework.Util;
import br.com.bradesco.interfaces.ISeleniumUtils;

public class PageMenuPrincipal implements ISeleniumUtils {

	By lnkCarterinha = By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[8]/td/a");	
	By lnkSolicitarSegViaCarterinha = By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[3]/table[3]/tbody/tr[4]/td/b/i/a");
	By lnkExtratoAtendimento = By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[10]/td/a");
		
	Util utils = new Util();
	
	private void clickLnkCarterinha() {
		driver.findElement(lnkCarterinha).click();
	}
	
	private void clickLnkSolicitaSegViaCarterinha() {
		driver.findElement(lnkSolicitarSegViaCarterinha).click();
	}
	
	public void clickLnkExtratoAtendimento() {
		driver.findElement(lnkExtratoAtendimento).click();
	}
	
	public void executaClickLnkCarterinha(){
		this.clickLnkCarterinha();
	}
	
	public void executaSolicitacaoSegViaCarterinha(){
		this.clickLnkCarterinha();
		utils.AguardaAteaSuaPresencaBy(driver , 20, lnkSolicitarSegViaCarterinha);
		this.clickLnkSolicitaSegViaCarterinha();
	}
	
	
}
