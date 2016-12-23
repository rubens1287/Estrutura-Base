package br.com.bradesco.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.swing.text.MaskFormatter;

/**
 * Classe contendo metodos para ser utilizado para qualquer tipo de automação de teste front-end webdriver
 * 
 * @author Rubens Lobo
 *
 */
public class Util {
	
	HSSFWorkbook workBook;
	HSSFSheet sheet;
	private ArrayList<Integer> listaAleatoria = new ArrayList<Integer>();
	private ArrayList<Integer> listaNumMultiplicados = null;
	
	public void DataTableConfig(String PathXls) throws IOException {
		
		try{
			FileInputStream excel = new FileInputStream(new File(PathXls));			
			workBook = new HSSFWorkbook(excel);			
			sheet = workBook.getSheetAt(0);
		}catch(Exception e){
			System.out.println("Erro ao tentar abrir o DataTable: " + e);
			Assert.fail();
		}
		
	}
	
	public String[] GetDataTable(String PathDataTable, int colunas) throws IOException{
		
		String Dados[] = new String[colunas];	
		DataTableConfig(PathDataTable);
		for (int i = 0; i < colunas;i++){
			Dados[i] = sheet.getRow(1).getCell(i).getStringCellValue();
		}	
		return Dados;
	}
	
	public void killProcess(String processo) {  
        try {  
            String line;  
            Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");  
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));  
            while ((line = input.readLine()) != null) {  
                if (!line.trim().equals("")) {  
                    if (line.substring(1, line.indexOf("\"", 1)).equalsIgnoreCase(processo)) {  
                        Runtime.getRuntime().exec("taskkill /F /IM " + line.substring(1, line.indexOf("\"", 1)));   
                    }  
                }  
            }  
            input.close();  
        } catch (Exception err) {  
            err.printStackTrace();  
        }   
    }  
	
	/**
	 * Método aguarda string de um objeto WebElemnet, caso não apresente o metodo falha o teste
	 * 
	 * @author Rubens Lobo
	 * @param     
	 *            <b>newDriver</b> - utilizado para reconhecer a estancia de drive utilizado no teste.
	 *            <p>
	 *            <b>element</b> - objeto que representa um elemento web de uma pagina HTML
	 *            <p>
	 * 			  <b>timeOutExpected</b> - Valor de time-Out para para validação
	 *            <p>
	 *            <b>textoEsperado</b> - Valor do texto a ser procurando, considerando case sensitive
	 *            <p>
	 */
	public boolean AguardaTextWebElement(WebDriver newDriver, String textoEsperado , int timeOutExpected, WebElement element){
		try{
			int timeout = 0;
			while(!(element.getText().trim().equals(textoEsperado)) &&  (timeout <= timeOutExpected)){
				Thread.sleep(1000);
				if(timeout == timeOutExpected){
					return false;
				}
				timeout ++;
			}
		}catch(Exception e){
			System.out.println("Erro ao rodar o metodo 'AguardaTextWebElement': " + e);
		}
		return true;
	}
	/**
	 * Método aguarda a presença de um objeto WebElemnet, caso não apresente o metodo falha o teste
	 * 
	 * @author Rubens Lobo
	 * @param     
	 *            <b>newDriver</b> - utilizado para reconhecer a estancia de drive utilizado no teste.
	 *            <p>
	 *            <b>by</b> - objeto que representa um elemento web de uma pagina HTML
	 *            <p>
	 *            <b>nameEvidencia</b> - Valor contendo o nome da evidencia
	 *            <p>
	 *            <b>mensagemFail</b> - Enviar mensagem de erro para o Console
	 *            <p>
	 *            <b>path</b> - pasta onde vai ser salva a evidencia no projeto
	 * 			  <p>
	 */
	public boolean AguardaAteaSuaPresencaBy(WebDriver newDriver, int timeOut , By by){			
		try{
			
			WebDriverWait wait = new WebDriverWait(newDriver,timeOut);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
					
		}catch(Exception e){
			
			System.out.println("Erro ao rodar o metodo 'AguardaAteaSuaPresencaBy': " + e);
			return false;
			
		}			
	}
	/**
	 * Método aguarda string de um objeto retornado por uma AJAX, caso não retorne o metodo falha.
	 * 
	 * @author Rubens Lobo
	 * @param     
	 *            <b>newDriver</b> - utilizado para reconhecer a estancia de drive utilizado no teste.
	 *            <p>
	 *            <b>by</b> - objeto que representa um elemento web de uma pagina HTML
	 *            <p>
	 * 			  <b>timeOut</b> - Valor de time-Out para para validação
	 *            <p>
	 *            <b>pollig</b> - Laço de repetição
	 *            <p>
	 *            <b>nameEvidencia</b> - Valor contendo o nome da evidencia
	 *            <p>
	 *            <b>mensagemFail</b> - Enviar mensagem de erro para o Console
	 *            <p>
	 *            <b>path</b> - pasta onde vai ser salva a evidencia no projeto
	 * 			  <p>
	 */
	public boolean AguardaAtePresencaDeValorRerotnadoPorAjaxa(WebDriver newDriver, int timeOut , int pollig){
		try{				
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Wait<WebDriver> wait = new FluentWait(newDriver)
		    	.withTimeout(timeOut, TimeUnit.SECONDS)
		    	.pollingEvery(pollig, TimeUnit.SECONDS)
		    	.ignoring(NoSuchElementException.class)
		    	.ignoring(StaleElementReferenceException.class);
					
		}catch(Exception e){
			
			return false;
		}
		
		return true;
	}
	   
    public String GetDate(int formatacao){
    	
    	if (formatacao == 0){
	    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    	Date date = new Date();
	    	return dateFormat.format(date).replace("/", "");
    	}else{
    		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    	Date date = new Date();
	    	return dateFormat.format(date);
    	}
    }
    
    public String GetTime(int formatacao){
    	
    	if(formatacao == 0){
    		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        	Date date = new Date();
        	return dateFormat.format(date).replace(":", "");
    	}else{
    		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        	Date date = new Date();
        	return dateFormat.format(date);
    	}
    	
    }
    
 // Metodo para geracao de um numero aleatorio entre 0 e 9
 	public int geraNumAleatorio() {
 		// Note que foi preciso fazer um cast para int, ja que Math.random()
 		// retorna um double
 		int numero = (int) (Math.random() * 10);

 		return numero;
 	}

 	// Metodo para geracao de parte do nosso CPF (aqui geramos apenas os 9
 	// primeiros digitos)
 	public ArrayList<Integer> geraCPFParcial() {
 		for (int i = 0; i < 9; i++) {
 			listaAleatoria.add(geraNumAleatorio());
 		}

 		return listaAleatoria;
 	}

 	// Metodo para geracao do primeiro digito verificador (para isso nos
 	// baseamos nos 9 digitos aleatorios gerados anteriormente)
 	public ArrayList<Integer> geraPrimeiroDigito() {
 		listaNumMultiplicados = new ArrayList<Integer>();
 		int primeiroDigito;
 		int totalSomatoria = 0;
 		int restoDivisao;
 		int peso = 10;

 		// Para cada item na lista multiplicamos seu valor pelo seu peso
 		for (int item : listaAleatoria) {
 			listaNumMultiplicados.add(item * peso);

 			peso--;
 		}

 		// Agora somamos todos os itens que foram multiplicados
 		for (int item : listaNumMultiplicados) {
 			totalSomatoria += item;
 		}

 		restoDivisao = (totalSomatoria % 11);

 		// Se o resto da divisao for menor que 2 o primeiro digito sera 0, senao
 		// subtraimos o numero 11 pelo resto da divisao
 		if (restoDivisao < 2) {
 			primeiroDigito = 0;
 		} else {
 			primeiroDigito = 11 - restoDivisao;
 		}

 		// Apos gerar o primeiro digito o adicionamos a lista
 		listaAleatoria.add(primeiroDigito);

 		return listaAleatoria;
 	}

 	// Metodo para geracao do segundo digito verificador (para isso nos baseamos
 	// nos 9 digitos aleatorios + o primeiro digito verificador)
 	public ArrayList<Integer> geraSegundoDigito() {
 		listaNumMultiplicados = new ArrayList<Integer>();
 		int segundoDigito;
 		int totalSomatoria = 0;
 		int restoDivisao;
 		int peso = 11;

 		// Para cada item na lista multiplicamos seu valor pelo seu peso
 		// (observe que com o aumento da lista o peso tambem aumenta)
 		for (int item : listaAleatoria) {
 			listaNumMultiplicados.add(item * peso);

 			peso--;
 		}

 		// Agora somamos todos os itens que foram multiplicados
 		for (int item : listaNumMultiplicados) {
 			totalSomatoria += item;
 		}

 		restoDivisao = (totalSomatoria % 11);

 		// Se o resto da divisao for menor que 2 o segundo digito sera 0, senao
 		// subtraimos o numero 11 pelo resto da divisao
 		if (restoDivisao < 2) {
 			segundoDigito = 0;
 		} else {
 			segundoDigito = 11 - restoDivisao;
 		}

 		// Apos gerar o segundo digito o adicionamos a lista
 		listaAleatoria.add(segundoDigito);

 		return listaAleatoria;
 	}

 	// Agora que temos nossa lista com todos os digitos que precisamos vamos
 	// formatar os valores de acordo com a mascara do CPF
 	public String GeradorCPF() {
 		// Primeiro executamos os metodos de geracao
 		geraCPFParcial();
 		geraPrimeiroDigito();
 		geraSegundoDigito();

 		String cpf = "";
 		String texto = "";

 		/*
 		 * Aqui vamos concatenar todos os valores da lista em uma string Por que
 		 * isso? Porque a formatacao que o ArrayList gera me impossibilitaria de
 		 * usar a mascara, pois junto com os numeros gerados ele tambem gera
 		 * caracteres especias. Ex.: lista com inteiros (de 1 a 5) [1 , 2 , 3 ,
 		 * 4 , 5] Dessa forma o sistema geraria a excecao ParseException
 		 */
 		for (int item : listaAleatoria) {
 			texto += item;
 		}

 		// Dentro do bloco try.. catch.. tentaremos adicionar uma mascara ao
 		// nosso CPF
 		try {
 			MaskFormatter mf = new MaskFormatter("###.###.###-##");
 			mf.setValueContainsLiteralCharacters(false);
 			cpf = mf.valueToString(texto);
 		} catch (Exception ex) {
 			ex.printStackTrace();
 		}

 		return cpf;
 	}
 	
 	public String CaptureScreenshot(WebDriver driver ,String path, String screensName){
 		
 		try{
 			TakesScreenshot ts=(TakesScreenshot)driver;
 			this.AguardaPoupAlertClicaOK(driver, 5, true);
 			File source=ts.getScreenshotAs(OutputType.FILE);
 			
 			this.criarDiretorio(".\\ScreensShot");
 			
 			if(path == ""){
 				String dest = ".\\ScreensShot\\" + screensName + "_"+ this.GetDate(0) + "_" + this.GetTime(0) +".png";
 				File destination = new File(dest);
 	 			FileUtils.copyFile(source,destination); 
 	 			return dest;
 			}else{
 				String dest = ".\\ScreensShot\\"+ path +"\\" + screensName + "_"+ this.GetDate(0) + "_" + this.GetTime(0) +".png";
 				File destination = new File(dest);
 	 			FileUtils.copyFile(source,destination);
 	 			return dest;
 			}
 			 			
 		}catch(Exception e){ 
 			
 			System.out.println("Erro ao Capturou a evidencia" + e.getMessage());
 			return e.getMessage();
 		}
 	}
 	
	public boolean aguardaAteQueValorMude(WebDriver newDriver, final WebElement element, int timeOutExpected)  
 	{
		try{
			int timeout = 0;
			while(timeout <= timeOutExpected){
				Thread.sleep(1000);
				if(timeout == timeOutExpected){
					return false;
				}else if(!element.getText().equals("")){
					break;
				}
				timeout ++;
			}
		}catch(Exception e){
			System.out.println("Erro ao rodar o metodo 'aguardaAteQueValorMude': " + e);
		}
		return true;
 	}
	
	public void Evidencia(Document document, CabecalhoEvidencia oCabecalho , WebDriver newDriver, int numStep, String StatusStep){
		
		try {
		
			final Font fonteCabecalho = new Font(Font.FontFamily.COURIER, 16, Font.BOLD,  BaseColor.RED);
			final Font fontePadrao = new Font(Font.FontFamily.COURIER, 12);
			final Font fonteVermelha = new Font(Font.FontFamily.TIMES_ROMAN,12, Font.NORMAL, BaseColor.RED);
			final Font negritoPequena = new Font(Font.FontFamily.HELVETICA,	10, Font.BOLD);
			  
			if(numStep == 1){
				
				 this.criarDiretorio(".\\Evidencia");
				 File diretorio = new File(".\\Evidencia\\"+oCabecalho.getNomeCenario()+"\\");
		         diretorio.mkdir();
				//Cria documento na pasta especificada	
				PdfWriter.getInstance(document, new FileOutputStream(".\\Evidencia\\"+oCabecalho.getNomeCenario()+"\\"+ oCabecalho.getNomeCasoTeste() +"_"+ this.GetDate(0)+"_"+this.GetTime(0)+".pdf"));
				//Abri documento PDF
				document.open(); 
				
				// Anexa imagem logo na primeira pagina 
				Image img = Image.getInstance(".//Logo//Logo.jpg");
				img.scaleAbsolute(300f, 400f);
				img.setAlignment(Element.ALIGN_CENTER);
				document.add(img);
				 
				//Configura fonte inserida na primeira pagina
				Paragraph p = new Paragraph("", fonteCabecalho );
				Paragraph p1 = new Paragraph("", fontePadrao );
				Paragraph p2 = new Paragraph("", fontePadrao );
				Paragraph p3 = new Paragraph("", fontePadrao);
				p3.add(" ");
				p.setAlignment(Element.ALIGN_CENTER);
				p.add("TESTE AUTOMATIZADO");
				p1.setAlignment(Element.ALIGN_CENTER);
				p1.add(oCabecalho.getNomeEmpresa());
				p2.setAlignment(Element.ALIGN_CENTER);
				p2.add(oCabecalho.getNomeProjeto());
				document.add(p);
				document.add(p1);
				document.add(p2);
				
				//Posiona a tabela do dados de teste mais abaixo
				for(int i=1; i<=7; i++){
				  document.add(p3); 
				}
				  
				// Inseri Tabela com 2 colunas e preenche os campos
				PdfPTable table = new PdfPTable(new float[] { 0.080f, 0.2f });	  
				table.setHeaderRows(1);
				table.setWidthPercentage(100.0f);
				PdfPCell header = new PdfPCell(new Paragraph("                                                         DADOS DO TESTE"));
				header.setColspan(2);
				header.getPaddingLeft();
				table.addCell(header);
				table.addCell("Nome do Sistema: "); table.addCell(oCabecalho.getNomeSistema());
				table.addCell("Cenário de teste: ");  table.addCell(oCabecalho.getNomeCenario());
				table.addCell("Caso de teste: ");  table.addCell(oCabecalho.getNomeCasoTeste());
				table.addCell("Execução: ");  table.addCell("Automatico");		
				table.addCell("Data: ");  table.addCell(this.GetDate(1));
				table.addCell("Hora: ");  table.addCell(this.GetTime(1));
				document.add(table);
					  
				//adiciona uma nova página
				document.newPage();
			}
			document.newPage();
			
			//Prenche informaçãos do Step ; data ; hora ; Status do Step
			Paragraph p4 = new Paragraph("");
			Paragraph p5 = new Paragraph("");
			p5.clear();
			p5.add(" ");
			document.add(p5);
			p4.add("Step "+numStep+" - Evidência      Data: " + this.GetDate(1)+" Hora: "+this.GetTime(1) +" Status: "+StatusStep);
			document.add(p4);
			document.add(p5);
			
			//Anexa evidencia no documento
			String dest = this.CaptureScreenshot(newDriver, "", "Step "+numStep );
			Image imgEvidecia = Image.getInstance(dest);			
			imgEvidecia.scaleAbsolute(500, 500);
			imgEvidecia.setAlignment(Element.ALIGN_CENTER);
			imgEvidecia.setSpacingAfter(3);
			document.add(imgEvidecia);
			document.add(p5);
			
			//deleta a imagem gerada pela metodo CaptureScreensShot
			File f = new File(dest);
			f.delete();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Step "+numStep+ " Status do step: " + StatusStep );
			        		
		
	}
	
	public void AguardaPoupAlertClicaOK(WebDriver newDriver, int timeOut, boolean aceitar){
		
		try{
			WebDriverWait wait = new WebDriverWait(newDriver, timeOut);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			
			if (alert != null){
				if(aceitar==true){
					alert.accept();
					newDriver.switchTo().defaultContent();
				}else{
					alert.dismiss();
					newDriver.switchTo().defaultContent();
				}
			}
		}catch(Exception e){
			
		}
		
	}
	
	public void alteraJanelaWindows(WebDriver newDriver, String AtualWindowId){
		//logica para selecionar a janela 2 aberta no processo do navegador
  		Set<String> windons = newDriver.getWindowHandles();	
  		Iterator iterator = windons.iterator();
  		while(iterator.hasNext()){
  			String widowsId = iterator.next().toString();
  			
  			if(!widowsId.equals(AtualWindowId)){
  				newDriver.switchTo().window(widowsId);
  			}
  		}
	}
	
	public void criarDiretorio(String path) {
        try {
            File diretorio = new File(path);
            diretorio.mkdir();
        } catch (Exception e) {
            System.out.println("Erro ao criar o diretorio metodo 'criarDiretorio': " + e);
        }
    }
	
}