/**
 * 	Autor: Rubens Lobo
 * 
 *  Data de Cria��o: 23/12/2016
 * 
 * 
 * 	4.1 RF006 � Extrato de Pagamento � OdontoPrev/Bradesco/BBDental
 * 
 *  Ao digitar o login e senha (Tela01) e entrar na �rea logada no menu de autoatendimento op��o 
 *  [Extrato de Pagamento] (Tela02) o benefici�rio ser� direcionado para extrato de pagamento 
 *  (Tela03) e ao clicar no bot�o <Enviar por email>  (Tela03) ir� digitar o email (Tela04) e clicar no bot�o <Enviar>.
 *  
 *  Ao Clicar no bot�o <Enviar> e ao receber a confirma��o (Tela05) dever� seguir o item 
 *  [4.1Servi�o Gera��o de Ocorr�ncia com Protocolo de Atendimento] para gerar o protocolo de atendimento.
 *  
 *  Ao Clicar no bot�o <Imprimir> (Tela03) dever� seguir o item 
 *  [4.1Servi�o Gera��o de Ocorr�ncia com Protocolo de Atendimento] para gerar o protocolo de atendimento e mostrar na tela.
 *  
 *  Observa��o: As telas de exemplo s�o do Bradesco mas a funcionalidade a ser constru�da deve atender os 
 *  Portais da OdontoPrev, Bradesco e BBDental. 
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

public class CN006_ExtratoDePagamento extends PageLogin implements ISeleniumUtils {
	
	/**
	 * 	Autor: Rubens Lobo
	 * 
	 *  Data de Cria��o: 23/12/2016
	 * 
	 *  Detalhe do teste: Objetivo do teste � validar se ser� aprensentado o n�mero de protocolo 
	 *  				  no menu principal do sistema BradescoDental.
	 * 	
	 * Pre Condi��o: Usu�rio e Senha do portal Bradesco.
	 *				 Cadastro de um cliente ativo.
	 *				 Extrato de Pagamento Disponivel
	 *  
	 */
	@Test(priority = 1)
	public void  CT002_SolicitarExtratoPagamentoViaEmail() {
	
	//-----------------------------------------SETUP----------------------------------------------------
	//Estacia objetos
	Util utils = new Util();
	Document document = new Document();
	CabecalhoEvidencia cabecalho = new CabecalhoEvidencia();
	PageMenuPrincipal pageMenuPrincipal = new PageMenuPrincipal();
	PageAutoAtendimento pageAutoAtendimento = new PageAutoAtendimento();
	
	//---------------------------INICIALIZA��O DO CABE�ARIO DA EVIDENCIA--------------------------------
	//Monta cabe�alho do teste
	cabecalho.setNomeCasoTeste("CT002 - Solicitar extrato de pagamento via email");
	cabecalho.setNomeCenario("CN006 - Extrato de Pagamento");
	cabecalho.setNomeEmpresa("OdontoPrev");
	cabecalho.setNomeProjeto("RN395 - Protocolo de atendimento");
	cabecalho.setNomeSistema("Bradesco");
	int numStep = 1;
	String atualWindowId = null;
	String pathDataTable = "./DataTable//CN006 - Extrato de Pagamento//CT002 - Solicitar extrato de pagamento via email.xls";
	System.out.println("Inicou o Teste: " +  cabecalho.getNomeCasoTeste());
	
	//-----------------------------------------SCRIPT TEST----------------------------------------------		
	try{
		//Inicializa��o dos dados do data table
		String[] dados = utils.GetDataTable(pathDataTable, 4);
				
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
  			System.out.println("Exception :" + e);
  			Assert.fail();
  		}
  		numStep++;
  		
  		/* Step 3
		 * 
		 * #A��O: 
		 * 
		 * 		No menu 'AutoAtendimento' clicar no link 'Extrato de Pagamento' 
		 * 
		 * #RESULTADO ESPERADO:
		 * 
		 *  	Ser� apresentado uma tabela com as colunas
		 *  
		 *  	'Vencimento'  'Valor'  'Situa��o'  'Boleto' 'C�digo de Barras'
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
		 * #A��O: 
		 * 
		 * 		Clicar no bot�o 'Enviar por Email'
		 * 
		 * #RESULTADO ESPERAD0:
		 * 
		 *  	Ser� apresentado uma tabela com as colunas 
		 *		'Vencimento'; 'Valor'; 'Situa��o'
		 * 
		 * #SCRIPT
		 */
  		
  		try{
  			// janela atual do navegador
  	  		atualWindowId = driver.getWindowHandle();
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
		 * #A��O: 
		 * 
		 * 		Preencher o campo:
		 *		Email:
		 *		clicar no bot�o 'Enviar' 
		 * 
		 * #RESULTADO ESPERADO:
		 * 
		 *  	Ser� apresentado o numero do protocolo de atendimento abaixo do nome do usu�rio da aplica��o.
		 * 
		 * #SCRIPT
		 */  		
  		try{
  			pageAutoAtendimento.executeEnvioDeEmailNaJanelaDois(dados[3]);
  			
  			if(utils.AguardaTextWebElement(driver, "Email Enviado com sucesso!", 30, driver.findElement(By.cssSelector("td[class='portaltexto11'][align='center']")))){
				utils.Evidencia(document, cabecalho , driver, numStep, "Passou");
			}else{
				utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
				Assert.fail();
			}
  			
  			//falta  validar a apresenta��o doo protocolo
  			atualWindowId = driver.getWindowHandle(); 
  			driver.close();
  			utils.alteraJanelaWindows(driver, atualWindowId);
  			
  			if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.id("idNrProtocolo"))){
  	  			utils.Evidencia(document, cabecalho , driver, numStep, "Passou");
  	  		}else{
  	  			utils.Evidencia(document, cabecalho , driver, numStep, "Falhou");
  	  			Assert.fail();
  	  		}
  			
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
