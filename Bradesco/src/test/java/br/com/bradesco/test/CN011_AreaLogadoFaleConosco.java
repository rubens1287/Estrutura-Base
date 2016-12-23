/**
 * 	Autor: Rubens Lobo
 * 
 *  Data de Cria��o: 23/12/2016
 * 
 * 
 * 	4.1 RF011 � �rea Logada � Fale Conosco dos Portais
 * 
 * Na �rea logada o [Fale Conosco] ao clicar abrir� o formul�rio com os campos para preenchimento 
 * (Tela01). O processo de envio (Tela02) desse formul�rio para o e-mail do Disque ter� algumas particularidades descritas abaixo:
 * 
 * 1)      Os Portais a serem ajustados s�o os que constam na Premissa;
 * 2)      Esse formul�rio ser� enviado para o Disque em forma de e-mail;
 * 3)      Dever� enviar junto com os dados do formul�rio, o site, c�digo do benefici�rio e Nome, facilitando a 
 *         identifica��o pelo atendente do Disque analisar e verificar se tem a necessidade de gerar o protocolo de atendimento;
 * 4)      Layout exemplo do email a ser enviado ao Disque (os dados mudam para cada Portal:
 * 
 * 		Mensagem recebida pelo site: www.odontoprev.com.br
 *      C�digo do benefici�rio: 123456789
 *      Nome: Fulano de Tal
 *      email: fulanodetal@gmail.com
 *      Telefone: (11) 988112233
 *      Assunto: Reclama��o
 *      Mensagem:Texto referente a reclama��o do benefici�rio com todas as suas d�vidas 
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
import br.com.bradesco.page.PageCanaisDeAtendimento;
import br.com.bradesco.page.PageFaleConosco;
import br.com.bradesco.page.PageLogin;
import br.com.bradesco.page.PageMenuPrincipal;

import com.itextpdf.text.Document;

public class CN011_AreaLogadoFaleConosco extends PageLogin implements ISeleniumUtils {
	
	/**
	 * 	Autor: Rubens Lobo
	 * 
	 *  Data de Cria��o: 23/12/2016
	 * 
	 *  Detalhe do teste: Objetivo do teste � validar se ser� aprensentado a mensagem 
	 *  				  'Sua demanda foi enviado com sucesso, caso seja uma solicita��o assistencial, 
	 *  				   aguarde o n�mero do protocolo por e-mail.'
	 * 	
	 * Pre Condi��o: Usu�rio e Senha 
	 *  
	 */
	@Test(priority = 1)
	public void CT002_EnviarFormulario_FaleConosco() {
	
	//-----------------------------------------SETUP----------------------------------------------------
	//Estacia objetos
	Util utils = new Util();
	Document document = new Document();
	CabecalhoEvidencia cabecalho = new CabecalhoEvidencia();
	PageMenuPrincipal pageMenuPrincipal = new PageMenuPrincipal();
	PageFaleConosco pagefaleconosco = new PageFaleConosco();
	
	//---------------------------INICIALIZA��O DO CABE�ARIO DA EVIDENCIA--------------------------------
	//Monta cabe�alho do teste
	cabecalho.setNomeCasoTeste("CT002 - Enviar formulario - Fale Conosco");
	cabecalho.setNomeCenario("CN011 - Area logado - Fale Conosco");
	cabecalho.setNomeEmpresa("OdontoPrev");
	cabecalho.setNomeProjeto("RN395 - Protocolo de atendimento");
	cabecalho.setNomeSistema("Bradesco");
	int numStep = 1;		
	String pathDataTable = "./DataTable//CN011 - Area logado - Fale Conosco//CT002 - Enviar formulario - Fale Conosco - Bradesco.xls";
	System.out.println("Inicou o Teste: " +  cabecalho.getNomeCasoTeste());
	
	//-----------------------------------------SCRIPT TEST----------------------------------------------		
	try{
		//Inicializa��o dos dados do data table
		String[] dados = utils.GetDataTable(pathDataTable, 7); 
				
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
		 * 		No menu clicar no link 'Fale Conosco'.
		 * 
		 * #RESULTADO ESPERADO:
		 * 
		 *  	 Ser� apresentado um formularios com os campos;
		 *  	'Email:'
		 *  	'Telefone:'
		 *  	'Assunto:'
		 *  	'Mensagem:'
		 *  	e um bot�o 'Enviar'
		 * 
		 * #SCRIPT
		 */
  		try{
  			pageMenuPrincipal.executeClickFaleConosco();
  	  		
  	  		//Valida se o campo foi apresentado - 'Associado*:'
  	  		if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.id("enviar"))){
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
		 * #A��O: 
		 * 
		 * 		 Preencher os campos;
		 * 		'Email:'
		 * 		'Telefone:'
		 * 		'Assunto:'
		 * 		'Mensagem:'
		 * 		e clicar no bot�o 'Enviar'
		 * 
		 * #RESULTADO ESPERAD0:
		 * 
		 *  	Ser� apresentado uma mensagem abaixo do campo 'Mensagem:' e do bot�o 'Enviar'
		 *  	'Sua demanda foi enviado com sucesso, caso seja uma solicita��o assistencial, 
		 *  	aguarde o n�mero do protocolo por e-mail.'
	 	 *
		 * #SCRIPT
		 */
  		try{
  			pagefaleconosco.executaEnvioFormularioFaleConosco(dados[3], dados[4], dados[5], dados[6]);	
		  		
  	  		if(utils.AguardaTextWebElement(driver, "Sua demanda foi enviado com sucesso, caso seja uma solicita��o assistencial, aguarde o n�mero do protocolo por e-mail.", 
  	  				30, driver.findElement(By.cssSelector("td[class='textopreto10'][height='20']")))){
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
