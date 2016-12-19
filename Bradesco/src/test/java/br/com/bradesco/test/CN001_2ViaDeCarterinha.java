/**
 * 
 */
package br.com.bradesco.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.itextpdf.text.Document;

import br.com.bradesco.framework.CabecalhoEvidencia;
import br.com.bradesco.framework.Util;
import br.com.bradesco.interfaces.ISeleniumUtils;
import br.com.bradesco.page.*;

/**
 * @author Rubens Lobo
 *
 */
public class CN001_2ViaDeCarterinha extends PageLogin implements ISeleniumUtils {
	
	@Test(priority = 1)
	public void CT001_ExecutarSolicitacao2viaDeCarterinha() {
	
	//-----------------------------------------SETUP----------------------------------------------------
	//Estacia objetos
	Util utils = new Util();
	Document document = new Document();
	CabecalhoEvidencia cabecalho = new CabecalhoEvidencia();
	PageMenuPrincipal pageMenuPrincipal = new PageMenuPrincipal();
	PageAutoAtendimento pageAutoAtendimento = new PageAutoAtendimento();
	
	
	//---------------------------INICIALIZAÇÃO DO CABEÇARIO DA EVIDENCIA--------------------------------
	//Monta cabeçalho do teste
	cabecalho.setNomeCasoTeste("CT001 - Executar solicitação de 2 via de carterinha");
	cabecalho.setNomeCenario("CN001 - 2 Via de Carterinha - Bradesco Pós-Vendas");
	cabecalho.setNomeEmpresa("OdontoPrev");
	cabecalho.setNomeProjeto("RN395 - Protocolo de atendimento");
	cabecalho.setNomeSistema("Bradesco");
	int numStep = 1;
	String pathDataTable = "./DataTable//CN001 - 2 Via de Carterinha - Bradesco Pós-Vendas//CT001 - Executar solicitação de 2 via de carterinha.xls";
	System.out.println("Inicou o Teste: " +  cabecalho.getNomeCasoTeste());
	
	//-----------------------------------------SCRIPT TEST----------------------------------------------		
	try{
		//Inicialização dos dados do data table
		String[] dados = utils.GetDataTable(pathDataTable, 3);
				
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
				
		driver.get(dados[0]);
  		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  		  			
  		if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.name("login"))==true){
  			utils.Evidencia(document, cabecalho , driver, numStep, "Passou");
  		}else{
  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
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
  		
  		this.ExecutaLogin(dados[1],dados[2]);
  		  				
  		if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.name("logout"))==true){
  			utils.Evidencia(document, cabecalho , driver, numStep, "Passou");
  		}else{
  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
  			Assert.fail();
  		}
  		numStep++;
  		
  		/* Step 3
		 * 
		 * #AÇÃO: 
		 * 
		 * 		No menu 'AutoAtendimento' clicar no link 'Carterinha' 
		 * 
		 * #RESULTADO ESPERADO:
		 * 
		 *  	Será apresentado uma tabela com as colunas
		 *  
		 *  	'Nome' 'Titular' 'Empresa' 'Operações'
		 * 
		 * #SCRIPT
		 */
  		
  		pageMenuPrincipal.executaSolicitacaoSegViaCarterinha();
  		
  		//Valida se o campo foi apresentado - 'Associado*:'
  		if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.name("dependente"))){
  			utils.Evidencia(document, cabecalho , driver, numStep, "Passou");
  		}else{
  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
  			Assert.fail();
  		}
  		numStep++;
  		  		
  		/* Step 4
		 * 
		 * #AÇÃO: 
		 * 
		 * 		Click no link '- Solicitar Segunda via Carterinha -' 
		 * 
		 * #RESULTADO ESPERAD0:
		 * 
		 *  	Será apresentado o numero do protocolo no menu principal
		 *  	da aplicação abaixo do usuário
		 * 
		 * #SCRIPT
		 */
  		
  		pageAutoAtendimento.digitaCampoNumeroClicaBotaoEnviarConfirmaAlertJavaScript("10");
  		
  		if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.id("idNrProtocolo"))==true){
  			utils.Evidencia(document, cabecalho , driver, numStep, "Passou");
  		}else{
  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
  			Assert.fail();
  		}
  		
  		
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
