package tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class testBase {
	
	public static WebDriver driver;
	public static String fecha;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest reporte;
	
	
	
	@BeforeSuite()
	public void suite(ITestContext context) throws Exception {

		// CON LA FECHA GENERA PARTE DEL NOMBRE DEL REPORTE DE LA SUITE
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String requiredDate = df.format(new Date()).toString();
		// *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"
		fecha = requiredDate.replace(":", "-").replace(" ", "_");

		System.out.println("fecha " + fecha);

	}

	@AfterMethod()
	public void afterTest() throws Exception {

		driver.quit();

	}

	@Parameters({ "Navegador" })
	@BeforeMethod()
	public void beforeTest(String navegador) throws Exception {

		switch (navegador) {

		case ("Chrome"):

			String projectPath = System.getProperty("user.dir");

			System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/chromedriver");
			driver = new ChromeDriver();
			break;

		default:
			break;

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

	}


}
