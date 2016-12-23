/**
 * 	Autor: Rubens Lobo
 * 
 *  Data de Criação: 23/12/2016
 * 
 * 
 * 	4.1 RF003 – Imprimir Carteirinha Virtual
 * 
 *  BradescoDental, Odontoprev e BBDental
 *  Na área logada , no menu [Autoatendimento] opção [Carteirinha] Clicar em [Imprimir Carteirinha] 
 *  deverá exibir o Protocolo de Atendimento e depois mostrar o PDF da carteirinha.
 *  Ao clicar no botão <Imprimir Carteirinha> deverá seguir o item 
 *  [4.1Serviço Geração de Ocorrência com Protocolo de Atendimento] para a geração do protocolo de atendimento.
 * 
 *  
 */
package br.com.bradesco.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.bradesco.framework.CabecalhoEvidencia;
import br.com.bradesco.framework.Util;
import br.com.bradesco.interfaces.ISeleniumUtils;
import br.com.bradesco.page.PageAutoAtendimento;
import br.com.bradesco.page.PageLogin;
import br.com.bradesco.page.PageMenuPrincipal;

import com.itextpdf.text.Document;

public class CN003_ImpressaoDeCarterinhaVirtual extends PageLogin implements ISeleniumUtils {
	
	/**
	 * 	Autor: Rubens Lobo
	 * 
	 *  Data de Criação: 23/12/2016
	 * 
	 *  Detalhe do teste: Objetivo do teste é validar se será aprensentado o número de protocolo 
	 *  				  no menu principal do sistema BradescoDental.
	 * 	
	 * Pre Condição: Usuário e Senha do portal Bradesco.
	 * 				 Cadastro de um cliente ativo.
	 *  
	 */
	@Test(priority = 1)
	public void CT001_ExecutarImpressaoDeCarterinha () {
	
	//-----------------------------------------SETUP----------------------------------------------------
	//Estacia objetos
	Util utils = new Util();
	Document document = new Document();
	CabecalhoEvidencia cabecalho = new CabecalhoEvidencia();
	PageMenuPrincipal pageMenuPrincipal = new PageMenuPrincipal();
	PageAutoAtendimento pageAutoAtendimento = new PageAutoAtendimento();	
	//---------------------------INICIALIZAÇÃO DO CABEÇARIO DA EVIDENCIA--------------------------------
	//Monta cabeçalho do teste
	cabecalho.setNomeCasoTeste("CT001 - Executar impressao de carterinha");
	cabecalho.setNomeCenario("CN003 - Impressão de Carterinha Virtual");
	cabecalho.setNomeEmpresa("OdontoPrev");
	cabecalho.setNomeProjeto("RN395 - Protocolo de atendimento");
	cabecalho.setNomeSistema("Bradesco");
	int numStep = 1;
	String pathDataTable = "./DataTable//CN003 - Impressão de Carterinha Virtual//CT001 - Executar impressao de carterinha.xls";
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
			System.out.println("Exception: " + e);
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
  		try{
  			pageMenuPrincipal.executaClickLnkCarterinha();
  	  		
  	  		//Valida se o campo foi apresentado - 'Associado*:'
  	  		if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.cssSelector("td[class='portal_titulo']"))){
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
  		/* Step 4
		 * 
		 * #AÇÃO: 
		 * 
		 * 		Dentro da tabela 'Carteirinha Virtual' na coluna 'Operações' clicar no botão 'Imprimir Carteirinha'.
 		 *	
		 * #RESULTADO ESPERAD0:
		 * 
		 *  	Será apresentado o numero do protocolo no menu principal
		 *  	da aplicação abaixo do usuário
		 * 
		 * #SCRIPT
		 */
  		try{
  			pageAutoAtendimento.executeClickBotaoImprimirCarterinha();
  	  		
  	  		if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.id("idNrProtocolo"))){
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
