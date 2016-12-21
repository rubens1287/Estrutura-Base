package br.com.bradesco.test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import br.com.bradesco.framework.CabecalhoEvidencia;
import br.com.bradesco.framework.Util;
import br.com.bradesco.interfaces.ISeleniumUtils;
import br.com.bradesco.page.PageCanaisDeAtendimento;
import br.com.bradesco.page.PageLogin;
import br.com.bradesco.page.PageMenuPrincipal;
import com.itextpdf.text.Document;

public class CN013_ExtratoDeAtendimentoAreaLogada extends PageLogin implements ISeleniumUtils {

	@Test(priority = 1)
	public void CT001_ExecutarConsultaDeProtocolo() {
	
	//-----------------------------------------SETUP----------------------------------------------------
	//Estacia objetos
	Util utils = new Util();
	Document document = new Document();
	CabecalhoEvidencia cabecalho = new CabecalhoEvidencia();
	PageMenuPrincipal pageMenuPrincipal = new PageMenuPrincipal();
	PageCanaisDeAtendimento pageCanaisDeAtendimento = new PageCanaisDeAtendimento();
	
	
	//---------------------------INICIALIZA��O DO CABE�ARIO DA EVIDENCIA--------------------------------
	//Monta cabe�alho do teste
	cabecalho.setNomeCasoTeste("CT001 - Executar consulta de protocolo - OdontoPrev ");
	cabecalho.setNomeCenario("CN013 - Extrato de Atendimento na �rea Logada");
	cabecalho.setNomeEmpresa("OdontoPrev");
	cabecalho.setNomeProjeto("RN395 - Protocolo de atendimento");
	cabecalho.setNomeSistema("Bradesco");
	int numStep = 1;		
	String pathDataTable = "./DataTable//CN013 - Extrato de Atendimento na �rea Logada//CT002 - Executar consulta de protocolo.xls";
	System.out.println("Inicou o Teste: " +  cabecalho.getNomeCasoTeste());
	
	//-----------------------------------------SCRIPT TEST----------------------------------------------		
	try{
		//Inicializa��o dos dados do data table
		String[] dados = utils.GetDataTable(pathDataTable, 5); 
				
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
		 * 		No menu 'CENTRAL DE ATENDIMENTO' clicar no link 'Extrato de Atendimento'.
		 * 
		 * #RESULTADO ESPERADO:
		 * 
		 *  	Ser� apresentado os campos:
		 *	
		 *      'Data Inicial:'
	 	 *		
	 	 *		'Data Final:'
	 	 *
	 	 *       e um bot�o 'Buscar'
		 * 
		 * #SCRIPT
		 */
  		try{
  			pageMenuPrincipal.clickLnkExtratoAtendimento();
  	  		
  	  		//Valida se o campo foi apresentado - 'Associado*:'
  	  		if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.id("buscar"))){
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
		 * 		Preencher os campos
		 * 		'Data Inicial:'
		 *		'Data Final:'
		 *		e clicar no bot�o 'Buscar'
		 * 
		 * #RESULTADO ESPERAD0:
		 * 
		 *  	Ser� apresentado uma tabela com as colunas
	 	 *		
	 	 *		'Protocolo' ; 'Data de Abertura' ; 'Assunto' ; 'Status' ; 'Canal de Atendimento'
	 	 *
	 	 *		Os registros da tabela dever� ser apresentado em
	 	 *		ordem decrescente pelo coluna 'data'
	 	 *
		 * #SCRIPT
		 */
  		try{
  			pageCanaisDeAtendimento.ExecutaBuscaExtratoDeAtendimento(dados[3],dados[4]);  		
		  		
  	  		if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.xpath("//*[@id='idTabelaContent']/tbody/tr[1]/td[1]"))){
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
