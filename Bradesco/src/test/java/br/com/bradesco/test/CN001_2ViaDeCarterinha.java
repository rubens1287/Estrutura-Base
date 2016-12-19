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
	
	
	//---------------------------INICIALIZA��O DO CABE�ARIO DA EVIDENCIA--------------------------------
	//Monta cabe�alho do teste
	cabecalho.setNomeCasoTeste("CT001 - Executar solicita��o de 2 via de carterinha");
	cabecalho.setNomeCenario("CN001 - 2 Via de Carterinha - Bradesco P�s-Vendas");
	cabecalho.setNomeEmpresa("OdontoPrev");
	cabecalho.setNomeProjeto("RN395 - Protocolo de atendimento");
	cabecalho.setNomeSistema("Bradesco");
	int numStep = 1;
	String pathDataTable = "./DataTable//CN001 - 2 Via de Carterinha - Bradesco P�s-Vendas//CT001 - Executar solicita��o de 2 via de carterinha.xls";
	System.out.println("Inicou o Teste: " +  cabecalho.getNomeCasoTeste());
	
	//-----------------------------------------SCRIPT TEST----------------------------------------------		
	try{
		//Inicializa��o dos dados do data table
		String[] dados = utils.GetDataTable(pathDataTable, 3);
				
		/* Step 1
		 * 
		 * #A��O:
		 * 
		 * 		Abrir navegador firefox e inserir a URL http://172.18.203.40/bdpf-prj/ 'Enter'
		 * 
		 * 
		 * #RESULTADO ESPERADO: 
		 * 		
		 * 		Ser� apresentado o Portal Bradesco Dental com os campos: 'login:' e 'Senha:' 
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
		 * #A��O: 
		 * 
		 * 		Preencher os campos:
		 * 
		 * 		'Login' e 'Senha'
		 * 
		 * 		 Clicar no bot�o 'OK'
		 * 
		 * 
		 * #RESULTADO ESPERADO:
		 * 
		 *  	Ser� apresentado o menu principal da aplica��o Bradesco
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
		 * #A��O: 
		 * 
		 * 		No menu 'AutoAtendimento' clicar no link 'Carterinha' 
		 * 
		 * #RESULTADO ESPERADO:
		 * 
		 *  	Ser� apresentado uma tabela com as colunas
		 *  
		 *  	'Nome' 'Titular' 'Empresa' 'Opera��es'
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
		 * #A��O: 
		 * 
		 * 		Click no link '- Solicitar Segunda via Carterinha -' 
		 * 
		 * #RESULTADO ESPERAD0:
		 * 
		 *  	Ser� apresentado o numero do protocolo no menu principal
		 *  	da aplica��o abaixo do usu�rio
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
