/**
 * 	Autor: Rubens Lobo
 * 
 *  Data de Criação: 23/12/2016
 * 
 * 
 * 	RF001 - 2ª Via de Carteirinha (Física) – Bradesco Pós-Venda
 * 
 * Ao digitar o login e senha (Tela01) e entrar na área logada no menu de autoatendimento opção [Carteirinha] 
 * (Tela02) o beneficiário será direcionado para a Carteirinha Virtual onde existe um link [Solicitar Segunda via Carteirinha] 
 * (Tela03) que ao clicar irá direcionar para o preenchimento dos dados para envio da carteirinha física (Tela04).
 *  O preenchimento dos dados irá atualizar o endereço de entrega do beneficiário no DCMS.
 *  
 *  Ao Clicar no botão <Enviar> (Tela05), ao receber a confirmação deverá seguir o item 
 *  [4.1Serviço Geração de Ocorrência com Protocolo de Atendimento] para gerar o protocolo de atendimento.
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

public class CN001_2ViaDeCarterinha extends PageLogin implements ISeleniumUtils {
	
	/**
	 * 	Autor: Rubens Lobo
	 * 
	 *  Data de Criação: 23/12/2016
	 * 
	 *  Detalhe do teste: Objetivo do teste é validar se será aprensentado o 
	 *  				  número de protocolo no menu principal do sistema BradescoDental.
	 * 	
	 *  Pre Condição: Usuário e Senha do portal Bradesco.
	 *  			  Cadastro de um cliente ativo.
	 * 				
	 *  
	 */
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
  		try{
  			pageAutoAtendimento.digitaCampoNumeroClicaBotaoEnviarConfirmaAlertJavaScript("10");
  	  		
  	  		if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.id("idNrProtocolo"))==true){
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
