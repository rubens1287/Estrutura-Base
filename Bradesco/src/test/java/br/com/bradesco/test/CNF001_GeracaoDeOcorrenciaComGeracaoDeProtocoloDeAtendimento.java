/**
 * 	Autor: Rubens Lobo
 * 
 *  Data de Cria��o: 23/12/2016
 * 
 * 
 * 	RNF001 Servi�o Gera��o de Ocorr�ncia com Protocolo de Atendimento
 * 
 * Em todos os locais dentro dos portais que necessite da gera��o do Protocolo de Atendimento, 
 * ser� chamado o servi�o de gerar protocolo ANS j� desenvolvido pela equipe de Sistemas Internos para gerar o protocolo.
 * 
 * Caso o protocolo j� tenha sido gerado para alguma outra funcionalidade, o mesmo n�mero deve ser 
 * utilizado para gerar as novas ocorr�ncias.
 * 
 * Chamar o servi�o de gera��o de ocorr�ncias passando o n�mero de protocolo retornado pelo servi�o de gerar protocolo,
 * as informa��es abaixo tamb�m devem ser informadas na chamada do servi�o:
 * 
 *  
 */
package br.com.bradesco.test;

import java.util.concurrent.TimeUnit;

import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString;

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

public class CNF001_GeracaoDeOcorrenciaComGeracaoDeProtocoloDeAtendimento extends PageLogin implements ISeleniumUtils {
	
	/**
	 * 	Autor: Rubens Lobo
	 * 
	 *  Data de Cria��o: 23/12/2016
	 * 
	 *  Detalhe do teste: Objetivo do teste � validar se o sistema aprensentar� o mesmo n�mero de protocolo para duas solicita��es 
	 * 					  diferentes com usu�rio logado na mesma sess�o.
	 * 	
	 * Pre Condi��o: Usu�rio e Senha do portal Bradesco.
	 *  
	 */
	@Test(priority = 1)
	public void  CT001_ExecutarDoisServicosQueGeremProtocoloNaMesmaSess�oAbertaPorUmUsuario() {
	
	//-----------------------------------------SETUP----------------------------------------------------
	//Estacia objetos
	Util utils = new Util();
	Document document = new Document();
	CabecalhoEvidencia cabecalho = new CabecalhoEvidencia();
	PageMenuPrincipal pageMenuPrincipal = new PageMenuPrincipal();
	PageAutoAtendimento pageAutoAtendimento = new PageAutoAtendimento();
	String numeroProtocolo1 = null;
	
	//---------------------------INICIALIZA��O DO CABE�ARIO DA EVIDENCIA--------------------------------
	//Monta cabe�alho do teste
	cabecalho.setNomeCasoTeste("CT001 - Executar dois servi�os que gerem protocolo na mesma sess�o aberta por um usu�rio");
	cabecalho.setNomeCenario("CNF001 - Geracao de ocorrencia com geracao de protocolo de Atendimento");
	cabecalho.setNomeEmpresa("OdontoPrev");
	cabecalho.setNomeProjeto("RN395 - Protocolo de atendimento");
	cabecalho.setNomeSistema("Bradesco");
	int numStep = 1;
	String pathDataTable = "./DataTable//CNF001 - Geracao de ocorrencia com geracao de protocolo de Atendimento//CT001 - Executar dois servi�os que gerem protocolo na mesma sess�o aberta por um usu�rio.xls";
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
		try{
			driver.get(dados[0]);
	  		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  		  			
	  		if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.name("login"))){
	  			utils.Evidencia(document, cabecalho , driver, numStep, "Passou");
	  		}else{
	  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
	  			Assert.fail();
	  		}
		}catch(Exception e){
			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
			System.out.println("Exception: " + e);
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
			System.out.println("Exception: " + e);
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
  		try{
  			pageMenuPrincipal.executaSolicitacaoSegViaCarterinha();
  	  		//Valida se o campo foi apresentado - 'Associado*:'
  	  		if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.name("dependente"))){
  	  			utils.Evidencia(document, cabecalho , driver, numStep, "Passou");
  	  		}else{
  	  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
  	  			Assert.fail();
  	  		}
  		}catch(Exception e){
  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
			System.out.println("Exception: " + e);
  			Assert.fail();
  		}
  		numStep++;		
  		/* Step 4 e Step 5
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
  		try{
  			pageAutoAtendimento.digitaCampoNumeroClicaBotaoEnviarConfirmaAlertJavaScript("10");
  	  		
  	  		if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.id("idNrProtocolo"))){
  	  			utils.Evidencia(document, cabecalho , driver, numStep, "Passou");
  	  		}else{
  	  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
  	  			Assert.fail();
  	  		}
  	  		numeroProtocolo1 =  new String(driver.findElement(By.id("idNrProtocolo")).getText());
  		}catch(Exception e){
  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
			System.out.println("Exception: " + e);
  			Assert.fail();
  		}
  		numStep++;
  		/* Step 6
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
  		try{
  			pageMenuPrincipal.executaClickLnkCarterinha();
  			 
  	  		//Valida se o campo foi apresentado - 'Associado*:'
  	  		if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[3]/table[3]/tbody/tr[4]/td/b/i/a"))){
  	  			utils.Evidencia(document, cabecalho , driver, numStep, "Passou");
  	  		}else{
  	  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
  	  			Assert.fail();
  	  		}
  		}catch(Exception e){
  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
			System.out.println("Exception: " + e);
  			Assert.fail();
  		}
  		numStep++;
  		/* Step 7
		 * 
		 * #A��O: 
		 *		
		 *		Dentro da tabela 'Carteirinha Virtual' na coluna 
		 *		'Opera��es' clicar no bot�o 'Imprimir Carteirinha'
		 * 
		 * #RESULTADO ESPERADO:
		 * 
		 *  	Ser� apresentado o numero do protocolo no menu principal da 
		 *  	aplica��o abaixo do nome do usu�rio.
		 *
		 * #SCRIPT
		 */
  		try{
  			pageAutoAtendimento.executeClickBotaoImprimirCarterinha();		
  			
  	  		String numeroProtocolo2 = new String(driver.findElement(By.id("idNrProtocolo")).getText());	
  	  		
  	  		//Valida se o campo foi apresentado - 'Associado*:'
  	  		if(numeroProtocolo1.substring(11).equals(numeroProtocolo2.substring(11))){
  	  			utils.Evidencia(document, cabecalho , driver, numStep, "Passou");
  	  		}else{
  	  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
  	  			Assert.fail();
  	  		}
  		}catch(Exception e){
  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
			System.out.println("Exception: " + e);
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
