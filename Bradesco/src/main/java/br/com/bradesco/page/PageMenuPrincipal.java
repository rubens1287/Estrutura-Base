package br.com.bradesco.page;

import org.openqa.selenium.By;

import br.com.bradesco.framework.Util;
import br.com.bradesco.interfaces.ISeleniumUtils;

public class PageMenuPrincipal implements ISeleniumUtils {

	By lnkCarterinha 				= By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[8]/td/a");	
	By lnkSolicitarSegViaCarterinha = By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[3]/table[3]/tbody/tr[4]/td/b/i/a");
	By lnkExtratoAtendimento 		= By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[10]/td/a");
	By lnkExtratoDePagamento 		= By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[7]/td/a");
	By lnkFaleConosco 				= By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[11]/td/a");
	By lnkBradescoDentaMail 		= By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td");
	By lnkAvisoAbertura 			= By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[3]/td/span/table/tbody/tr[2]/td/a");
	By lnkInformativoIR 			= By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[4]/td/table/tbody/tr/td/a");
	By lnkExtratoDeReembolso 		= By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[4]/td/span/table/tbody/tr[1]/td/a");
	By lnkProtuarioVirtual			= By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/a");
	By lnkExtratoUtilizacaoServico  = By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[2]/td/span/table/tbody/tr[1]/td/a");
	
	Util utils = new Util();
	
	private void clickLnkCarterinha() {
		driver.findElement(lnkCarterinha).click();
	}
	
	private void clickLnkSolicitaSegViaCarterinha() {
		driver.findElement(lnkSolicitarSegViaCarterinha).click();
	}
	
	private void clickLnkExtratoDePagamento(){
		driver.findElement(lnkExtratoDePagamento).click();
	}
	
	public void clickLnkExtratoAtendimento() {
		driver.findElement(lnkExtratoAtendimento).click();
	}
	
	private void clickLnkFaleConosco(){
		driver.findElement(lnkFaleConosco).click();
	}
	
	private void clickLnkBradescoDentaMail(){
		driver.findElement(lnkBradescoDentaMail).click();
	}
	
	private void clickLnkAvisoAbertura(){
		driver.findElement(lnkAvisoAbertura).click();
	}
		
	public void executaClickLnkCarterinha(){
		this.clickLnkCarterinha();
	}
	
	private void clickLnkInformativoIR(){
		driver.findElement(lnkInformativoIR).click();
	}
	
	private void clickLnkExtratoDeReembolso(){
		driver.findElement(lnkExtratoDeReembolso).click();
	}
	
	private void clickLnkProtuarioVirtual(){
		driver.findElement(lnkProtuarioVirtual).click();
	}
	
	private void clickLnkExtratoUtilizacaoServico(){
		driver.findElement(lnkExtratoUtilizacaoServico).click();
	}
	
	public void executeClickExtratoUtilizacaoServico(){
		try{
			this.clickLnkProtuarioVirtual();
			Thread.sleep(1000);
			this.clickLnkExtratoUtilizacaoServico();
		}catch(Exception e){
			System.out.println("Erro ao rodar o metodo 'executeClickExtratoDeReembolso' : " + e);
		}
	}
	
	public void executeClickExtratoDeReembolso(){
		try{
			this.clickLnkInformativoIR();
			Thread.sleep(1000);
			this.clickLnkExtratoDeReembolso();
		}catch(Exception e){
			System.out.println("Erro ao rodar o metodo 'executeClickExtratoDeReembolso' : " + e);
		}
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
		driver.findElement(lnkAvisoAbertura).click();
	}
	
	
	
	
}
