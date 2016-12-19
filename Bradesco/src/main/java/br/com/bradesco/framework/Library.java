package br.com.bradesco.framework;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import br.com.bradesco.interfaces.ISeleniumUtils;


/**
 * 
 * @author AA
 * 	Framework Secure/Selenium.
 */
public class Library implements ISeleniumUtils{
//
//	private static List<WebElement> MENU_ITEMS;
//	private static WebElement element;
//	private static String EMAIL="";
//	private static String PASSWORD="";
//	private final String destination_zip_code = "04037-002";
//	private final String origin_zip_code = "01311-300";
//
//	public String getDestination_zip_code() {
//		return destination_zip_code;
//	}
//
//	public String getOrigin_zip_code() {
//		return origin_zip_code;
//	}
//
//
//
//	/**
//	 * @Login
//	 * log in and change to live mode.
//	 * @param email - Email to login onto secure
//	 * @param passWord - ...
//	 */
//	public void login(String email, String passWord){
//
//		if(!Library.EMAIL.equals(email) && !Library.PASSWORD.equals(passWord)){
//			Library.EMAIL = email;
//			Library.PASSWORD = passWord;
//
//			SeleniumUtils.DRIVER.get(url);
//
//			element = SeleniumUtils.DRIVER.findElement(By.id("email"));
//			element.sendKeys(email);
//			TestLinkUtils.notes = "EMAIL PREENCHIDO";
//
//			element = SeleniumUtils.DRIVER.findElement(By.id("password"));
//			element.sendKeys(passWord);
//			TestLinkUtils.notes = "SENHA PREENCHIDO";
//
//			element.submit();
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div[@class='growl-message ng-binding']"));
//
//			this.operacaoRealizadaComSucesso();
//
//			Assert.assertTrue(SeleniumUtils.DRIVER.findElement(By.xpath("//a[@href='/logout/']")).isDisplayed(),"logout link is not displayed");
//
//			TestLinkUtils.notes = "LOGIN REALIZADO COM SUCESSO";
//			this.turnLive(true);
//		}
//	}
//
//
//	/**
//	 * @Default
//	 * @return - check if the green object from success message is displayed.
//	 * 			 if not Red hex-code, will return true
//	 */
//	public void operacaoRealizadaComSucesso(){
//
//		SeleniumUtils.DRIVER.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
//
//		List<WebElement> msgElements = SeleniumUtils.DRIVER.findElements(By.xpath("//div[@class='growl-message ng-binding']"));
//
//		if(!msgElements.isEmpty()){
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div[@class='growl-message ng-binding']"));
//			if(this.hexCodeColor(element.getCssValue("color")).equals("#a94442")){
//				Assert.fail("Red message: "+element.getText());
//			}
//			List<WebElement> elements = driver.findElements(By.xpath("//body/div[3]/div/div/button[1]"));
//			elements.forEach(WebElement::click);
//
//			try {
//				Thread.sleep(500L);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		SeleniumUtils.DRIVER.manage().timeouts().implicitlyWait(WAIT_ELEMENT_LOAD_SECONDS,TimeUnit.SECONDS);
//	}
//
//
//	/**
//	 * @Default
//	 * @param linkName - put all letters' link name and be happy.
//	 *
//	 */
//	public void goToLink(String linkName){
//		boolean exists = false;
//		if(Library.MENU_ITEMS==null){
//			Library.MENU_ITEMS = SeleniumUtils.DRIVER.findElements(By.xpath("//div[@class='mainNavigation col-md-3']/ul/li/ul/li/a"));
//			Library.MENU_ITEMS.addAll( SeleniumUtils.DRIVER.findElements(By.xpath("//div[@class='mainNavigation col-md-3']/ul/li/a")));
//		}
//		this.turnLive(true);
//
//		for(WebElement itens: MENU_ITEMS){
//			if(this.isTextEquals(itens.getText(), linkName)){
//				itens.click();
//				this.operacaoRealizadaComSucesso();
//				exists = true;
//				TestLinkUtils.result = 1;
//				break;
//			}
//		}
//		if(!exists){
//			try {
//				Assert.fail("Invalid link text. Check the value passed at linkName var.");
//				throw new Exception("goToLinkException: Invalid link text. Check the value passed at linkName var.");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//
//	/**
//	 * @Default
//	 * @param live - True to turn Live and False to turn Test
//	 */
//	public void turnLive(boolean live){
//		String color = SeleniumUtils.DRIVER.findElement(By.xpath("//label[@class='switch__background']")).getCssValue("background-color");
//
//		if(live){
//			if(!this.hexCodeColor(color).equals("#9bbd45")){ //if not green, turn green
//				element = SeleniumUtils.DRIVER.findElement(By.xpath("//label[@class='switch__background']"));
//				element.click();
//			}
//		}else{
//			if(this.hexCodeColor(color).equals("#9bbd45")){ //if  green, turn red
//				element = SeleniumUtils.DRIVER.findElement(By.xpath("//label[@class='switch__background']"));
//				element.click();
//			}
//		}
//	}
//
//
//	/**
//	 * @CotacaoDeFrete
//	 * fills the quote page (Volume).
//	 * @param quote - quote object [use  QuoteDefault method]
//	 * @param submit - Set True to click in Calcular
//	 */
//	public void fillQuote(Quote quote, boolean submit){
//
//
//		WebElement	element = SeleniumUtils.DRIVER.findElement(By.name("origin_zip_code"));
//		element.clear();
//		element.sendKeys(quote.getOrigin_zip_code());
//
//		element = SeleniumUtils.DRIVER.findElement(By.name("destination_zip_code"));
//		element.sendKeys(quote.getDestination_zip_code());
//		int i = 1;
//		for(Volume volumes : quote.getVolume()){
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div["+i+"]/div[2]/div/input[@ng-model='volume.weight']"));
//			element.sendKeys(volumes.getWeight()); //Peso.
//
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div["+i+"]/div[3]/div/input[@ng-model='volume.length']"));
//			element.sendKeys(volumes.getLength()); // comprimento
//
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div["+i+"]/div[4]/div/input[@ng-model='volume.cost_of_goods']"));
//			element.sendKeys(volumes.getCost_of_goods()); //valor NF
//
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div["+i+"]/div[5]/div/input[@ng-model='volume.height']"));
//			element.sendKeys(volumes.getHeight()); //altura
//
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div["+i+"]/div[7]/div/input[@ng-model='volume.width']"));
//			element.sendKeys(volumes.getWidth()); //largura
//
//			this.SelectTipoCaixa(volumes.getVolume_type(),i); //tipo de caixa
//
//			if(quote.getVolume().size() != i){	// Adiciona volume se houver mais de 1.
//				element = SeleniumUtils.DRIVER.findElement(By.xpath("//div[@class='col-md-16 clear']/a"));
//				element.click();
//				i=i+1;
//			}
//		}
//		if(submit){
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//button[@type='submit']"));
//			element.click();
//		}
//		try {	Thread.sleep(500L); } catch (InterruptedException e) { e.printStackTrace(); } //wait 500 milliseconds
//
//		if(submit){
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//table[@class='transactionDetail']"));
//			Assert.assertTrue(element.isDisplayed(),"The screen object Opções de Envio wasn't displayed");
//			this.operacaoRealizadaComSucesso();
//
//		}
//		TestLinkUtils.result = 1;
//	}
//
//
//	/**
//	 * @CotacaoDeFrete
//	 * fills the quote page (Product).
//	 * @param quote - quote object
//	 * @param submit - Set True to click in Calcular
//	 */
//	public void fillQuoteByProduct(Quote quote, boolean submit){
//		WebElement	element = SeleniumUtils.DRIVER.findElement(By.xpath("//div/tabs-manager/div/ul/li[2]/a[contains(text(), 'Por produto')]"));
//		element.click();
//
//		element = SeleniumUtils.DRIVER.findElement(By.xpath("//div/div/div[2]/form/div[1]/div/div[1]/div[1]/div/input[@name='origin_zip_code']"));
//		element.sendKeys(quote.getOrigin_zip_code());
//
//		element = SeleniumUtils.DRIVER.findElement(By.xpath("//div/div/div[2]/form/div[1]/div/div[1]/div[2]/div/input[@name='destination_zip_code']"));
//		element.sendKeys(quote.getDestination_zip_code());
//		int i = 1;
//		for(Product produtos : quote.getProducts()){
//
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div["+i+"]/div[2]/div/input[@ng-model='product.quantity']"));
//			element.sendKeys(produtos.getQuantity()); //Quantidade.
//
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div["+i+"]/div[3]/div/input[@ng-model='product.sku_id']"));
//			element.sendKeys(produtos.getSku()); //sku
//
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div["+i+"]/div[4]/div/input[@ng-model='product.description']"));
//			element.sendKeys(produtos.getDescription()); //description
//
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div["+i+"]/div[5]/div/input[@ng-model='product.weight']"));
//			element.sendKeys(produtos.getWeight()); //Peso.
//
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div["+i+"]/div[6]/div/input[@ng-model='product.length']"));
//			element.sendKeys(produtos.getLength()); // comprimento
//
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div["+i+"]/div[7]/div/input[@ng-model='product.cost_of_goods']"));
//			element.sendKeys(produtos.getCostOfGoods()); //valor NF
//
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div["+i+"]/div[8]/div/input[@ng-model='product.height']"));
//			element.sendKeys(produtos.getHeight()); //altura
//
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div["+i+"]/div[9]/div/input[@ng-model='product.width']"));
//			element.sendKeys(produtos.getWidth()); //largura
//
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div["+i+"]/div[10]/div/input[@ng-init='product.can_group = false']"));
//			element.click();
//
//			if(quote.getProducts().size() != i){	// Adiciona produto se houver mais de 1.
//				element = SeleniumUtils.DRIVER.findElement(By.xpath("//div/div/div[2]/form/div[1]/div/div[3]/a"));
//				element.click();
//				i=i+1;
//			}
//		}
//		if(submit){
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//div/div/div[2]/form/div[2]/div/button[@type='submit']"));
//			element.click();
//		}
//		try {	Thread.sleep(500L); } catch (InterruptedException e) { e.printStackTrace(); } //wait 500 milliseconds
//
//		if(submit){
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//table[@class='transactionDetail']"));
//			Assert.assertTrue(element.isDisplayed(),"The screen object Opções de Envio wasn't displayed");
//			this.operacaoRealizadaComSucesso();
//
//		}
//		TestLinkUtils.result = 1;
//	}
//
//
//	/**
//	 * @CotacaoDeFrete
//	 * It'll click in the 'submit' button from quote page. Prefer using fillQuote method using @param submit = true
//	 */
//	public void submitQuotation(boolean isByProduct){
//		if(isByProduct){
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//tabs-manager/div/div/div[2]/form/div[2]/div/button[@type='submit']"));
//		}else{
//			element = SeleniumUtils.DRIVER.findElement(By.xpath("//button[@type='submit']"));
//		}
//		element.click();
//	}
//
//
//	/**
//	 * @CotacaoDeFrete
//	 * It'll click in Deletar Volume
//	 */
//	public void deleteVolume2FromQuotation(){
//		element = SeleniumUtils.DRIVER.findElement(By.xpath("//a[contains(text(), 'Deletar volume')]"));
//		element.click();
//		int i=0;
//		i=1;
//	}
//
//
//	/**
//	 * @CotacaoDeFrete
//	 * It'll click in Deletar Volume
//	 */
//	public void deleteProduct2FromQuotation(){
//		element = SeleniumUtils.DRIVER.findElement(By.xpath("//a[contains(text(), 'Deletar produto')]"));
//		element.click();
//		int i=0;
//		i=1;
//	}
//
//
//	/**
//	 * @CotacaoDeFrete
//	 * @return a quote with 1 volume
//	 */
//	public Quote quoteDefault(){
//		Quote q = new Quote();
//		q.setDestination_zip_code(this.destination_zip_code);
//		q.setOrigin_zip_code(this.origin_zip_code);
//
//		ArrayList<Volume> volume = new ArrayList<Volume>();
//			volume.add(new Volume("105.75", "1.8", "15.00", "16.00", "17.00", "CAIXA"));
////			volume.add(new Volume("105.75", "1.8", "10.00", "10.00", "10.00", "ENVELOPE"));
//		q.setVolume(volume);
//
//	return q;
//	}
//
//
//	/**
//	 * @CotacaoDeFrete
//	 * @return a quote with 1 volume
//	 */
//	public Quote quoteDefault(String volumeType){
//		Quote q = new Quote();
//		q.setDestination_zip_code(this.destination_zip_code);
//		q.setOrigin_zip_code(this.origin_zip_code);
//
//		ArrayList<Volume> volume = new ArrayList<Volume>();
//			volume.add(new Volume("101.75", "1.8", "15.00", "16.00", "17.00", volumeType));
//		q.setVolume(volume);
//
//	return q;
//	}
//
//
//	/**
//	 * @ListaDeCotacoes
//	 * @param buttonName - (Todas, CEP Origem, CEP Destino)
//	 */
//	public void searchForTheButtonAndClick(String buttonName){
//		boolean exists = false;
//
//		List<WebElement> elements = SeleniumUtils.DRIVER.findElements(By.xpath("//div/check[@class='ng-isolate-scope']"));
//		for(WebElement e : elements){
//			if(this.isTextEquals(e.getText(), buttonName)){
//				e.click();
//				this.operacaoRealizadaComSucesso();
//				exists = true;
//				TestLinkUtils.result = 1;
//				break;
//			}
//		}
//
//		if(!exists) Assert.fail("searchForTheButtonAndClick: Invalid button name. Check the value passed at buttonName var");
//	}
//
//
//	/**
//	 * @ListaDeCotacoes
//	 * fills the cep box from CEP Origem/ Cep Destino options from lista de cotações.
//	 * @param cep
//	 */
//	public void fillCepBox(String cep){
//		element = SeleniumUtils.DRIVER.findElement(By.id("search_value"));
//		element.clear();
//		element.sendKeys(cep);
//	}
//
//
//	/**
//	 * @ListaDeCotacoes
//	 * @param param - an option [Criado em, Cep de Destino, Número da Cotação, Cep de Origem]");
//	 * @param isDesc - true = Decrescente, false = Crescente.
//	 */
//	public void orderByDIV(String param, boolean isDesc){
//		boolean exists = false;
//
//		WebElement select = SeleniumUtils.DRIVER.findElement(By.id("sort_field"));
//		List<WebElement> allOptions = select.findElements(By.tagName("option"));
//		for(WebElement element: allOptions){
//			if(this.isTextEquals(element.getText(), param)){
//				element.click();
//				exists = true;
//				break;
//			}
//		}
//
//		if(!exists) Assert.fail("Invalid item text. try an available item: [Criado em, Cep de Destino, Número da Cotação, Cep de Origem]");
//
//		//CRESCENTE / DECRESCENTE
//		 select = SeleniumUtils.DRIVER.findElement(By.xpath("//div/select[@ng-model='data.config.sort_direction']"));
//		 allOptions = select.findElements(By.tagName("option"));
//
//		 if(isDesc){
//			allOptions.get(1).click();
//		}else{
//			allOptions.get(0).click();
//		}
//	}
//
//
//	/**
//	 * @ListaDeCotacoes
//	 * fills 'Exibir ultimos X dias' from Lista de Cotações.
//	 * @param xDays - quantity, ex: 20
//	 */
//	public void fillLastXDaysAndSubmit(String xDays){
//		element = SeleniumUtils.DRIVER.findElement(By.id("created_after"));
//		element.clear();
//
//		element.sendKeys(xDays);
//
//		element =  SeleniumUtils.DRIVER.findElement(By.xpath("//button[@ng-click='search()']"));
//		element.click();
//		this.operacaoRealizadaComSucesso();
//	}
//
//
//	/**
//	 * @ListaDeCotacao
//	 * @param column 1 - cep origin / 2 - cep destination
//	 * @return all data from cep Origin column
//	 * @throws Exception
//	 */
//	private ArrayList<String> cepColumn(int column){
//
//		if(column != 1 && column != 2) Assert.fail("cepColumn method, Invalid cepColumn number, try 1 (cep origin) or 2 (cep destination)");
//
//		WebElement table = SeleniumUtils.DRIVER.findElement(By.xpath("//table[@st-table='displayedCollection']"));
//
//		List<WebElement> rows = table.findElements(By.xpath("//tbody/tr"));
//
//		ArrayList<String> cep = new ArrayList<String>();
//
//		List<WebElement>	elements = null;
//
//		for(WebElement ele: rows){
//
//			elements = column == 1 ? SeleniumUtils.DRIVER.findElements(By.xpath("//td[@ng-show='column_visible.origin_zip_code']"))
//								   : SeleniumUtils.DRIVER.findElements(By.xpath("//td[@ng-show='column_visible.destination_zip_code']"));
//
//			for(WebElement e : elements){
//				cep.add(e.getText());
//			}
//
//			break;
//		}
//		return cep;
//	}
//
//
//	/**
//	 * @ListaDeCotacoes
//	 * check all CEPS from ListaDecotacoes and compare its sequence.
//	 * @param cepColumn  1 - cep origin / 2 - cep destination
//	 * @param descMode  true = validates expecting a descending value in list / false = validates expecting an ascending value in list
//	 * @throws Exception - Case invalid cepColumn number or some error in list.
//	 */
//	public void cepListValidation(int cepColumn, boolean descMode){
//
//		if(cepColumn != 1 && cepColumn != 2) Assert.fail("Invalid cepColumn number, try 1 (cep origin) or 2 (cep destination)");
//
//		ArrayList<String> cep = this.cepColumn(cepColumn);
//
//		for(int i = 3; i<=cep.size();i++){
//			if(i+1 == cep.size()) break;
//
//			boolean validator = descMode ? (this.isGreaterOrEqualNumber(cep.get(i), cep.get(i+1))) : (this.isGreaterOrEqualNumber(cep.get(i+1), cep.get(i)));
//
//			if (!validator) Assert.fail("Invalid CEP list sequence");
//
//		}
//	}
//
//
//	/**
//	 * @ListaDeCotacoes
//	 * get all datetime info from 'Criado em' column and check its sequence across isDescMode param.
//	 * @param isDescMode true = validates expecting a descending value in list / false = validates expecting an ascending value in list
//
//	 * @throws Exception
//	 */
//	public void createdAtValidation(boolean isDescMode){
//
//		boolean validator = false;
//		LocalDateTime date1 = null;
//		LocalDateTime date2 = null;
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//
//		WebElement table = SeleniumUtils.DRIVER.findElement(By.xpath("//table[@st-table='displayedCollection']"));
//
//		List<WebElement> rows = table.findElements(By.xpath("//tbody/tr"));
//
//		for(WebElement ele: rows){
//			List<WebElement> elements = SeleniumUtils.DRIVER.findElements(By.xpath("//td[@ng-show='column_visible.created']"));
//
//			for(int i = 3; i <= elements.size(); i++){
//				if(i+1 == elements.size()) break;
//
//				date1 = LocalDateTime.parse(elements.get(i).getText(), dtf);
//				date2 = LocalDateTime.parse(elements.get(i+1).getText(), dtf);
//
//				validator = isDescMode ? date1.equals(date2) || date1.isAfter(date2)
//						               : date2.equals(date1) || date2.isAfter(date1);
//
//				if (!validator){
//					System.out.println("data 1: "+date1+" data2: "+date2);
//					Assert.fail("Invalid date sequence from 'Criado em' column "
//								+"\nData primeira linha: "+date1+"\nData segunda linha: "+date2+ "DECRESCENTE = "+isDescMode);
//					break;
//				}
//			}
//			break;
//		}
//	}
//
//
//	public void quoteIdValidation(boolean isDescMode){
//
//		try {
//			Thread.sleep(250);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		boolean validator = false;
//
//		WebElement table = SeleniumUtils.DRIVER.findElement(By.xpath("//table[@st-table='displayedCollection']"));
//
//		List<WebElement> rows = table.findElements(By.xpath("//tbody/tr"));
//
//		for(WebElement ele: rows){
//			List<WebElement> elements = SeleniumUtils.DRIVER.findElements(By.xpath("//td[@ng-show='column_visible.created']"));
//
//			for(int i = 2; i <= elements.size(); i++){
//				if(i+1 == elements.size()) break;
//
//				Actions action= new Actions(SeleniumUtils.DRIVER);
//				action.contextClick(elements.get(i)).build().perform();
//
//				element = SeleniumUtils.DRIVER.findElement(By.xpath("//li/a[contains(text(), 'Abrir aqui')]"));
//				element.click();
//			}
//			break;
//		}
//
//		WebElement quoteIdtable = SeleniumUtils.DRIVER.findElement(By.id("tabs_queue"));
//		List<WebElement> ids = quoteIdtable.findElements(By.xpath("//ul/li/a[@ng-click='set_current_tab(item.id)']"));
//
//		for(int i = 3; i <= ids.size(); i++){
//			if(i+1 == ids.size()) break;
//
//			validator = isDescMode ? this.isGreaterOrEqualNumber(ids.get(i).getText(), ids.get(i+1).getText())
//								   : this.isGreaterOrEqualNumber(ids.get(i+1).getText(), ids.get(i).getText());
//
//			if (!validator) Assert.fail("Invalid quote id sequence");
//		}
//	}
//
//
//	/**
//	 * Check two numbers and return according the numbers received.
//	 *  Case smallerNumber is greater than greaterNumber, returns false. vice versa.
//	 * @ListaDeCotacoes
//	 * @param greaterNumber
//	 * @param smallerNumber
//	 * @return a dry true or false.
//	 */
//	private boolean isGreaterOrEqualNumber(String greaterNumber, String smallerNumber){
//
//		int q1 = Integer.parseInt(greaterNumber.replace("-", ""));
//		int q2 = Integer.parseInt(smallerNumber.replace("-", ""));
//
//		if(q1 > q2	||	q2 == q1){
//			return true;
//		}
//		else{
//			return false;
//		}
//	}
//
//
////_______________________________________________________________________________________________________
//
//	/**
//	 * Turn a quoting rules on
//	 * @param index - index list position. First = 1, not 0.
//	 */
//	public void putOn(int index){
//		List<WebElement> elements=	SeleniumUtils.DRIVER.findElements(By.xpath("//label[@class='switch__background']"));
//
//		try {
//			elements.get(index).click();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//
//	/**
//	 * take a screen shot from browser and save on temp folder.
//	 */
//	public void takeScreenShot(){
//		if(ITestLinkUtils.isProductionEnvironment){
//			Calendar calendar = Calendar.getInstance();
//			File print = ((TakesScreenshot) SeleniumUtils.DRIVER).getScreenshotAs(OutputType.FILE);
//
//			try {
//				FileUtils.copyFile(print, new File("./temp/"+calendar.getTimeInMillis()+".png"), true);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//
//	/**
//	 * as the name said.
//	 * @param element - WebElement to get highlighted
//	 */
//	public void highLight(WebElement element) {
//		for (int i = 0; i < 2; i++) {
//			JavascriptExecutor js = (JavascriptExecutor) SeleniumUtils.DRIVER;
//			js.executeScript(
//					"arguments[0].setAttribute('style', arguments[1]);",
//					element, "color: red; border: 3px solid red;");
//			js.executeScript(
//					"arguments[0].setAttribute('style', arguments[1]);",
//					element, "");
//		}
//	}
//
//
//	/**
//	 * Compare text1 and text2 returning true or false.
//	 * @param text1
//	 * @param text2
//	 * @return boolean
//	 */
//	private boolean isTextEquals(String text1, String text2){
//		boolean validator = false;
//		text1 = Normalizer.normalize(text1, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
//		text1 = text1.replaceAll("[^a-zA-Z]*","");
//
//		text2 = Normalizer.normalize(text2, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
//		text2 = text2.replaceAll("[^a-zA-Z]*","");
//
//		if(text2.toUpperCase().trim().equals(text1.toUpperCase().trim())){
//			validator = true;
//		}
//		return validator;
//	}
//
//
//	/**
//	 *
//	 * @param color - rgba color returned from getCssValue("color")
//	 * @return HexCode color like '#9bbd45'
//	 */
//	private String hexCodeColor(String color){
//		String[] numbers = color.replace("rgba(", "").replace(")", "").split(",");
//		int r = Integer.parseInt(numbers[0].trim());
//		int g = Integer.parseInt(numbers[1].trim());
//		int b = Integer.parseInt(numbers[2].trim());
//		String hex = "#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
//
//		return hex;
//	}
//
//
//	/**
//	 * @CotacaoDeFrete
//	 * Select an item according to text [Caixa, Envelope, ...].
//	 * @param text - text from item.
//	 */
//	private void SelectTipoCaixa(String text, int volumeNumber) {
//		WebElement select = SeleniumUtils.DRIVER.findElement(By.xpath("//div["+volumeNumber+"]/div[6]/div/select[@id='sort_field_volume']"));
//		List<WebElement> allOptions = select.findElements(By.tagName("option"));
//		for(int i =0; i < allOptions.size(); i++){
//			String compare = allOptions.get(i).getText().toUpperCase();
//			if(compare.equals(text.toUpperCase())){
//				allOptions.get(i).click();
//				break;
//			}else if(allOptions.size() == i+1){
//				System.err.println("Nenhum tipo de caixa com o nome passado.[Class:Lybrary/Method:SelectTipoCaixa]");
//			}
//
//		}
//	}

}
