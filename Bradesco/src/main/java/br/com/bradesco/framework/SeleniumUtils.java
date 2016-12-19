package br.com.bradesco.framework;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Classe de configura��o utilizada na instancia��o do objeto Webdriver
 * 
 * @author Rubens Lobo
 *
 */
public class SeleniumUtils {

	public static WebDriver DRIVER;

	// public final String profileFirefoxPath =
	// "/home/user/.mozilla/firefox/aw8hg7o6.default";

	/**
	 * Create a Protected static WebDriver Object.
	 * 
	 * @author 
	 * @param browserNumber
	 *            <p>
	 *            3 - Firefox - DEFAULT
	 *            <p>
	 *            2 - Chrome &nbsp; - Windows S.O ONLY - REMOVED
	 *            <p>
	 *            1 - Internet Explorer - Windows S.O ONLY (of course) - REMOVED
	 *            <p>
	 * 
	 * @param waitPageSeconds
	 *            &nbsp; Page Load timeout.
	 *            <p>
	 * 
	 * @param waitElementSeconds
	 *            &nbsp; Elements from page timeout
	 *            <p>
	 * 
	 * @return A static object to SeleniumUtils.DRIVER protected var.

	 */
	public WebDriver setUpDriver(int browserNumber, int waitPageSeconds, int waitElementSeconds) {
		
		DesiredCapabilities cap = null;
		//Colocar o ip e porta criada na maquina node. esse dados encontra-se tambem no endere�o http://localhost:4444/grid/console
		//String nodeUrl = "http://192.168.100.173:5556/wd/hub";
		String nodeUrl = "http://192.168.100.173:5556/wd/hub";
		
		if (SeleniumUtils.DRIVER == null) {
			WebDriver driver = null;
			if (browserNumber == 1) {
				 
				System.setProperty("webdriver.ie.driver","./src/test/resources/IEDriverServer.exe");		
				driver = new InternetExplorerDriver();
				
				//cap = DesiredCapabilities.internetExplorer();
				//cap.setBrowserName("internet explorer");
				//cap.setPlatform(Platform.WINDOWS);			
				//try {
				//	driver = new RemoteWebDriver(new URL(nodeUrl),cap); 
				//} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				//}

			} else if (browserNumber == 2) {
		
				
								
				System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver.exe");
				driver = new ChromeDriver();
				//cap = DesiredCapabilities.chrome();
				//cap.setBrowserName("chrome");
				//cap.setPlatform(Platform.VISTA);
				// try {
				//	driver = new RemoteWebDriver(new URL(nodeUrl),cap);
				//} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
			//	}
				
			} else if (browserNumber == 3) {
				 FirefoxProfile profile = new FirefoxProfile();	
				
				// cap = DesiredCapabilities.firefox();
				// cap.setBrowserName("firefox");
				// cap.setPlatform(Platform.VISTA);	
				 driver = new FirefoxDriver(profile);
				// try {
				//	driver = new RemoteWebDriver(new URL(nodeUrl),cap); 
				// } catch (MalformedURLException e) {
				//	// TODO Auto-generated catch block
				//	e.printStackTrace();
				//}
				
			}
			driver.manage().window().maximize();
			SeleniumUtils.DRIVER = driver;
		}
		this.WaitPageLoad(waitPageSeconds);
		this.WaitElementLoad(waitElementSeconds);
		return (RemoteWebDriver) SeleniumUtils.DRIVER;
	}

	/**
	 * Configura o tempo maximo de espera do carregamento da pagina.
	 * 
	 * @author Rubens Lobo
	 * @param seconds
	 *            &nbsp; Tempo de espera do carregamento da pagina em segundos.
	 */
	private final void WaitPageLoad(int seconds) {
		SeleniumUtils.DRIVER.manage().timeouts()
				.pageLoadTimeout(seconds, TimeUnit.SECONDS);
	}

	/**
	 * Configura o tempo maximo de espera de carregamento do elemento que
	 * comp�e a pagina.
	 * 
	 * @author Rubens Lobo
	 * @param seconds
	 *            &nbsp; Tempo de espera do carregamento do elemento da pagina.
	 */
	private final void WaitElementLoad(int seconds) {
		SeleniumUtils.DRIVER.manage().timeouts()
				.implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	/**
	 * 
	 * @param isProductionEnvironment
	 *            - true for secure.rubens / false for secure-testing.rubens
	 * @return Url String
	 */
	public String Url(boolean isProductionEnvironment) {
		String url = null;
		if (isProductionEnvironment) {
			url = "http://192.168.100.173:8080/webcom/login.do";
		} else {
			url = "https://secure-testing.rubens.com.br/login/";
		}
		return url;
	}
}
