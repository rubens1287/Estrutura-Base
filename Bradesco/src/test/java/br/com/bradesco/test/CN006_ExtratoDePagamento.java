package br.com.bradesco.test;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.bradesco.framework.CabecalhoEvidencia;
import br.com.bradesco.framework.Util;
import br.com.bradesco.interfaces.ISeleniumUtils;
import br.com.bradesco.page.PageAutoAtendimento;
import br.com.bradesco.page.PageLogin;
import br.com.bradesco.page.PageMenuPrincipal;

import com.itextpdf.text.Document;

public class CN006_ExtratoDePagamento extends PageLogin implements ISeleniumUtils {
	
	@Test(priority = 1)
	public void  CT002_SolicitarExtratoPagamentoViaEmail() {
	
	//-----------------------------------------SETUP----------------------------------------------------
	//Estacia objetos
	Util utils = new Util();
	Document document = new Document();
	CabecalhoEvidencia cabecalho = new CabecalhoEvidencia();
	PageMenuPrincipal pageMenuPrincipal = new PageMenuPrincipal();
	PageAutoAtendimento pageAutoAtendimento = new PageAutoAtendimento();
	
	//---------------------------INICIALIZAÇÃO DO CABEÇARIO DA EVIDENCIA--------------------------------
	//Monta cabeçalho do teste
	cabecalho.setNomeCasoTeste("CT002 - Solicitar extrato de pagamento via email");
	cabecalho.setNomeCenario("CN006 - Extrato de Pagamento");
	cabecalho.setNomeEmpresa("OdontoPrev");
	cabecalho.setNomeProjeto("RN395 - Protocolo de atendimento");
	cabecalho.setNomeSistema("Bradesco");
	int numStep = 1;
	String pathDataTable = "./DataTable//CN006 - Extrato de Pagamento//CT002 - Solicitar extrato de pagamento via email.xls";
	System.out.println("Inicou o Teste: " +  cabecalho.getNomeCasoTeste());
	
	//-----------------------------------------SCRIPT TEST----------------------------------------------		
	try{
		//Inicialização dos dados do data table
		String[] dados = utils.GetDataTable(pathDataTable, 4);
				
		/* Step 1
		 * 
		 * #AÇÃO:
		 * 
		 * 		Abrir navegador firefox e inserir a URL http://172.18.203.40/bdpf-prj/ 'Enter'
		 * 
		 * 
		 * #RESULTADO ESPERADO: 
		 * 		
		 * 		Será apresentado o Portal Bradesco Dental com os campos: 'login:' e 'Senha:' 
		 * 
		 * #SCRIPT
		 */
		
		try{
			driver.get(dados[0]);
			driver.manage().timeouts().setScriptTimeout(30 , TimeUnit.SECONDS);
			if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.name("login"))){
				utils.Evidencia(document, cabecalho , driver, numStep, "Passou");
			}else{
				utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
				Assert.fail();
			}
		}catch(Exception e){
			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
			System.out.println("Exception :" + e);
  			Assert.fail();
		}
  		numStep++;
  				
  		/* Step 2
		 * 
		 * #AÇÃO: 
		 * 
		 * 		Preencher os campos:
		 * 
		 * 		'Login' e 'Senha'
		 * 
		 * 		 Clicar no botão 'OK'
		 * 
		 * 
		 * #RESULTADO ESPERADO:
		 * 
		 *  	Será apresentado o menu principal da aplicação Bradesco
		 *  	Dental com o link de Logout do lado direito superior
		 * 
		 * #SCRIPT
		 */
  		
  		try{
  			this.ExecutaLogin(dados[1],dados[2]);
  			if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.name("logout"))){
				utils.Evidencia(document, cabecalho , driver, numStep, "Passou");
			}else{
				utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
				Assert.fail();
			}
  		}catch(Exception e){
  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
  			System.out.println("Exception :" + e);
  			Assert.fail();
  		}
  		numStep++;
  		
  		/* Step 3
		 * 
		 * #AÇÃO: 
		 * 
		 * 		No menu 'AutoAtendimento' clicar no link 'Extrato de Pagamento' 
		 * 
		 * #RESULTADO ESPERADO:
		 * 
		 *  	Será apresentado uma tabela com as colunas
		 *  
		 *  	'Vencimento'  'Valor'  'Situação'  'Boleto' 'Código de Barras'
		 * 
		 * #SCRIPT
		 */
  		
  		try{
  			pageMenuPrincipal.executaSolicitacaoExtratoPagamento();
  			if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[3]/table[4]/tbody/tr[2]/td/center/button[2]"))){
				utils.Evidencia(document, cabecalho , driver, numStep, "Passou");
			}else{
				utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
				Assert.fail();
			}
  		}catch(Exception e){
  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
  			System.out.println("Exception :" + e);
  			Assert.fail();
  		}
  		numStep++;
  		  		
  		/* Step 4 
		 * 
		 * #AÇÃO: 
		 * 
		 * 		Clicar no botão 'Enviar por Email'
		 * 
		 * #RESULTADO ESPERAD0:
		 * 
		 *  	Será apresentado uma tabela com as colunas 
		 *		'Vencimento'; 'Valor'; 'Situação'
		 * 
		 * #SCRIPT
		 */
  		
  		try{
  			// janela atual do navegador
  	  		String atualWindowId = driver.getWindowHandle();
  	  		pageAutoAtendimento.executeClickBtnEviarPorEmail();
  	  		utils.alteraJanelaWindows(driver, atualWindowId); 		
	  	  	if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.name("email"))){
				utils.Evidencia(document, cabecalho , driver, numStep, "Passou");
			}else{
				utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
				Assert.fail();
			}
  		}catch(Exception e){
  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
  			System.out.println("Exception :" + e);
  			Assert.fail();
  		}
  		numStep++;
  		
  		/* Step 5
		 * 
		 * #AÇÃO: 
		 * 
		 * 		Preencher o campo:
		 *		Email:
		 *		clicar no botão 'Enviar' 
		 * 
		 * #RESULTADO ESPERADO:
		 * 
		 *  	Será apresentado o numero do protocolo de atendimento abaixo do nome do usuário da aplicação.
		 * 
		 * #SCRIPT
		 */  		
  		try{
  			pageAutoAtendimento.executeEnvioDeEmailNaJanelaDois(dados[3]);
  			
  			if(utils.AguardaTextWebElement(driver, "Email Enviado com sucesso!", 30, driver.findElement(By.xpath("/html/body/table/tbody/tr[5]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td")))){
				utils.Evidencia(document, cabecalho , driver, numStep, "Passou");
			}else{
				utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
				Assert.fail();
			}
  			
  			//falta  validar a apresentação doo protocolo
  		}catch(Exception e){
  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
  			System.out.println("Exception :" + e);
  			Assert.fail();
  		};
  		numStep++;
  		  		
	}catch(Exception e){
		
		System.out.println("Erro ao rodar o teste :" + cabecalho.getNomeCasoTeste()); 
		System.out.println("Exception :" + e);
		Assert.fail();
		
	}finally{
		
		document.close();
		System.out.println("Total de passos: " + numStep);
		System.out.println("Finalizou o Teste: " +  cabecalho.getNomeCasoTeste());
	}
  		
  }

}
