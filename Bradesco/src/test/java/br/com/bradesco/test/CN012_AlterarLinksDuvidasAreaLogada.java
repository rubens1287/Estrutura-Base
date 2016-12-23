/**
 * 	Autor: Rubens Lobo
 * 
 *  Data de Cria��o: 23/12/2016
 * 
 * 
 * 	4.1 RF012 � Alterar Links de D�vidas dentro da �rea Logada
 * 
 * Ser�o retirados os links de d�vidas dentro da �rea logada dos Portais nos locais abaixo mencionados, 
 * j� que teremos no menu [Central de Atendimento] o [Fale Conosco]
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
import br.com.bradesco.page.PageAvisoDeAbertura;
import br.com.bradesco.page.PageLogin;
import br.com.bradesco.page.PageMenuPrincipal;
import com.itextpdf.text.Document;

public class CN012_AlterarLinksDuvidasAreaLogada extends PageLogin implements ISeleniumUtils {
	
	/**
	 * 	Autor: Rubens Lobo
	 * 
	 *  Data de Cria��o: 23/12/2016
	 * 
	 *  Detalhe do teste: Objetivo do teste � validar se ser� aprensentado a mensagem 
	 *  				  'D�vidas sobre envio de abertura de tratamento por e-mail ? Acesse Fale Conosco'
	 * 	
	 * Pre Condi��o: Usu�rio e Senha
	 *  
	 */
	@Test(priority = 1)
	public void CN019_ValidarAlteracaoLinkDuvidasSobreEnvioDeAberturaDeTratamentoPorEmail() {
	
	//-----------------------------------------SETUP----------------------------------------------------
	//Estacia objetos
	Util utils = new Util();
	Document document = new Document();
	CabecalhoEvidencia cabecalho = new CabecalhoEvidencia();
	PageMenuPrincipal pageMenuPrincipal = new PageMenuPrincipal();
	
	//---------------------------INICIALIZA��O DO CABE�ARIO DA EVIDENCIA--------------------------------
	//Monta cabe�alho do teste
	cabecalho.setNomeCasoTeste("CT019 - Validar alteracao do link - Duvidas sobre envio de abertura de tratamento por e-mail");
	cabecalho.setNomeCenario("CN012 - Alterar Links de Duvidas dentro da Area Logada");
	cabecalho.setNomeEmpresa("OdontoPrev");
	cabecalho.setNomeProjeto("RN395 - Protocolo de atendimento");
	cabecalho.setNomeSistema("Bradesco");
	int numStep = 1;		
	String pathDataTable = "./DataTable//CN012 - Alterar Links de Duvidas dentro da Area Logada//CT019 - Validar alteracao do link - Duvidas sobre envio de abertura de tratamento por e-mail.xls";
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
		 * 		Clicar no Menu 'Bradesco Dental' e 'Aviso de Abertura'
		 * 
		 * #RESULTADO ESPERADO:
		 * 
		 *  	 Ser� apresentado a mensagem 'Receba por e-mail o seu extrato de abertura de tratamento. Clique Aqui '
		 * 
		 * #SCRIPT
		 */
  		try{
  			pageMenuPrincipal.executeClickAvisoAbertura();
  	  		//Valida se o campo foi apresentado - 'Associado*:'
  			if(utils.AguardaTextWebElement(driver, "D�vidas sobre envio de extrato de Tratamentos por e-mail ? Acesse Fale Conosco.", 
  	  				30, driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table[3]/tbody/tr[2]/td[3]/form/table/tbody/tr/td[2]/table[2]/tbody/tr/td")))){
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
	
	/**
	 * 	Autor: Rubens Lobo
	 * 
	 *  Data de Cria��o: 23/12/2016
	 * 
	 *  Detalhe do teste: Objetivo do teste � validar se ser� aprensentado a mensagem 
	 *  				  'D�vida Extrato de Rede ? Acesse Fale Conosco'
	 * 	
	 * Pre Condi��o: Usu�rio e Senha
	 *  
	 */
	@Test(priority = 2)
	public void  CT021_ValidarAltera��oDo_LinkProtu�rioVirtual_DuvidaExtratoDeRede(){
	
	//-----------------------------------------SETUP----------------------------------------------------
	//Estacia objetos
	Util utils = new Util();
	Document document = new Document();
	CabecalhoEvidencia cabecalho = new CabecalhoEvidencia();
	PageMenuPrincipal pageMenuPrincipal = new PageMenuPrincipal();
	
	//---------------------------INICIALIZA��O DO CABE�ARIO DA EVIDENCIA--------------------------------
	//Monta cabe�alho do teste
	cabecalho.setNomeCasoTeste("CT021 - Validar altera��o do link Protu�rio Virtual - Duvida Extrato de Rede");
	cabecalho.setNomeCenario("CN012 - Alterar Links de Duvidas dentro da Area Logada");
	cabecalho.setNomeEmpresa("OdontoPrev");
	cabecalho.setNomeProjeto("RN395 - Protocolo de atendimento");
	cabecalho.setNomeSistema("Bradesco");
	int numStep = 1;		
	String pathDataTable = "./DataTable//CN012 - Alterar Links de Duvidas dentro da Area Logada//"
			+ "CT021 - Validar altera��o do link Protu�rio Virtual - Duvida Extrato de Rede.xls";
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
		 * 		Clicar no Menu 'Bradesco Dental' e 'Aviso de Abertura'
		 * 
		 * #RESULTADO ESPERADO:
		 * 
		 *  	 Ser� apresentado a mensagem 'Receba por e-mail o seu extrato de abertura de tratamento. Clique Aqui '
		 * 
		 * #SCRIPT
		 */
  		try{
  			
  			pageMenuPrincipal.executeClickExtratoUtilizacaoServico();
  	  		//Valida se o campo foi apresentado - 'Associado*:'
  			
  	  		if(utils.AguardaTextWebElement(driver, "D�vida Extrato de Rede ? Acesse Fale Conosco.", 30, driver.findElement(By.cssSelector("td[class='portaltexto11'][align='center']")))){
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
	
	/**
	 * 	Autor: Rubens Lobo
	 * 
	 *  Data de Cria��o: 23/12/2016
	 * 
	 *  Detalhe do teste: Objetivo do teste � validar se ser� aprensentado a mensagem 
	 *  				  'D�vida Extrato de Reembolso ? Acesse Fale Conosco'
	 * 	
	 * Pre Condi��o: Usu�rio e Senha
	 *  
	 */
	@Test(priority = 3)
	public void  CT020_ValidarAltera��oDoLink_ImpostoDeRendaReembolsos_DuvidaExtratoReembolso(){
	
	//-----------------------------------------SETUP----------------------------------------------------
	//Estacia objetos
	Util utils = new Util();
	Document document = new Document();
	CabecalhoEvidencia cabecalho = new CabecalhoEvidencia();
	PageMenuPrincipal pageMenuPrincipal = new PageMenuPrincipal();
	
	//---------------------------INICIALIZA��O DO CABE�ARIO DA EVIDENCIA--------------------------------
	//Monta cabe�alho do teste
	cabecalho.setNomeCasoTeste("CT020 - Validar altera��o do link Imposto de Renda Reembolsos Duvida Extrato Reembolso");
	cabecalho.setNomeCenario("CN012 - Alterar Links de Duvidas dentro da Area Logada");
	cabecalho.setNomeEmpresa("OdontoPrev");
	cabecalho.setNomeProjeto("RN395 - Protocolo de atendimento");
	cabecalho.setNomeSistema("Bradesco");
	int numStep = 1;		
	String pathDataTable = "./DataTable//CN012 - Alterar Links de Duvidas dentro da Area Logada//"
			+ "CT020 - Validar altera��o do link Imposto de Renda Reembolsos Duvida Extrato Reembolso.xls";
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
		 * 		Clicar no Menu 'Bradesco Dental' e 'Aviso de Abertura'
		 * 
		 * #RESULTADO ESPERADO:
		 * 
		 *  	 Ser� apresentado a mensagem 'Receba por e-mail o seu extrato de abertura de tratamento. Clique Aqui '
		 * 
		 * #SCRIPT
		 */
  		try{
  			
  			pageMenuPrincipal.executeClickExtratoDeReembolso();
  	  		//Valida se o campo foi apresentado - 'Associado*:'
  			
  	  		if(utils.AguardaTextWebElement(driver, "D�vida Extrato de Reembolso ? Acesse Fale Conosco.", 30, driver.findElement(By.cssSelector("td[class='portaltexto11'][align='center']")))){
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
	
	/**
	 * 	Autor: Rubens Lobo
	 * 
	 *  Data de Cria��o: 23/12/2016
	 * 
	 *  Detalhe do teste: Objetivo do teste � validar se ser� aprensentado a mensagem 
	 *  				  'D�vidas sobre Altera��o de E-mail ? Acesse Fale Conosco'
	 * 	
	 * Pre Condi��o: Usu�rio e Senha
	 *  
	 */
	@Test(priority = 4)
	public void CT022_ValidarAltera��oLinkDuvidasSobreAltera��oEmail(){
	
	//-----------------------------------------SETUP----------------------------------------------------
	//Estacia objetos
	Util utils = new Util();
	Document document = new Document();
	CabecalhoEvidencia cabecalho = new CabecalhoEvidencia();
	PageMenuPrincipal pageMenuPrincipal = new PageMenuPrincipal();
	PageAvisoDeAbertura pageavisodeabertura = new PageAvisoDeAbertura();
	
	//---------------------------INICIALIZA��O DO CABE�ARIO DA EVIDENCIA--------------------------------
	//Monta cabe�alho do teste
	cabecalho.setNomeCasoTeste("CT022 - Validar alteracao do link - Duvidas sobre Alteracao de E-mail");
	cabecalho.setNomeCenario("CN012 - Alterar Links de Duvidas dentro da Area Logada");
	cabecalho.setNomeEmpresa("OdontoPrev");
	cabecalho.setNomeProjeto("RN395 - Protocolo de atendimento");
	cabecalho.setNomeSistema("Bradesco");
	int numStep = 1;		
	String pathDataTable = "./DataTable//CN012 - Alterar Links de Duvidas dentro da Area Logada//CT022 - Validar altera��o do link - Duvidas sobre Altera��o de E-mail.xls";
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
		 * 		Clicar no Menu 'Bradesco Dental' e 'Aviso de Abertura'
		 * 
		 * #RESULTADO ESPERADO:
		 * 
		 *  	 Ser� apresentado a mensagem 'Receba por e-mail o seu extrato de abertura de tratamento. Clique Aqui '
		 * 
		 * #SCRIPT
		 */
  		try{
  			pageMenuPrincipal.executaclickLnkAvisoAbertura();
  	  		//Valida se o campo foi apresentado - 'Associado*:'
  	  		if(utils.AguardaAteaSuaPresencaBy(driver, 20, By.cssSelector("a[class='linkCinza']"))){
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
		 * 		 Clicar no link 'Clique Aqui' na mensagem estatica 'Receba por e-mail 
		 * 		 o seu extrato de abertura de tratamento.Clique Aqui'
		 * 
		 * #RESULTADO ESPERAD0:
		 * 
		 *  	Ser� apresentado uma mensagem estatica 
		 *  	'D�vidas sobre envio de abertura de tratamento por e-mail ? Acesse Fale Conosco'
	 	 *
		 * #SCRIPT
		 */
  		try{
  			pageavisodeabertura.executeCliqueAqui();	
		  		
  	  		if(utils.AguardaTextWebElement(driver, "D�vidas sobre Altera��o de E-mail ? Acesse Fale Conosco.", 
  	  				30, driver.findElement(By.cssSelector("td[class='textopreto10'][align='center']")))){
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