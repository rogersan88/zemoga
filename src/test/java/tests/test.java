package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.Login;
import utils.ExcelDataProvider;
import utils.ExtentTestManager;

import java.lang.reflect.Method;
import java.util.List;

public class test extends testBase {

	public WebDriver getDriver() {
		return driver;
	}

	@Test(dataProvider = "dataUserStory1", dataProviderClass = ExcelDataProvider.class)
	public void testUserStory1(String url, String duration, Method test) throws Exception {

		ExtentTestManager.startTest(test.getName(), "Test to check Sharks");

		WebDriverWait wait = new WebDriverWait(driver, 30);

		// ABRE LA PAGINA

		driver.get("https://" + url);

		// DEBIDO QUE EN ALGUNOS CASOS SE VISUALIZA UNAS MODAL DE PUBLICIDAD SE CIERRAN
		// LA CENTRAL O LA QUE ESTA A LA DERECHA

		Login.closeModal();

		// CLICK EN DURATION

		wait.until(ExpectedConditions.elementToBeClickable(By.id("cdc-durations"))).click();

		// CLICK EN EL FILTRO INGRESADO DESDE EL XLSX (EN ESTE CASO 6 A 9 DIAS)

		List<WebElement> items = driver.findElements(By.className("cdc-filter-button"));

		int count = 0;

		while (count < items.size()) {

			if (items.get(count).getText().toUpperCase().equals(duration.toUpperCase())) {

				wait.until(ExpectedConditions.elementToBeClickable(items.get(count))).click();

				count = items.size();

			} else {

				count++;

			}

		}

		// VALIDA QUE SE MUESTRE LOS RESULTADOS

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("vrg-search-unit")));

		List<WebElement> resultados = driver.findElements(By.className("vrg-search-unit"));

		if (resultados.size() == 0) {

			Assert.fail("RESULTS ARE NOT DISPLAYED");
		}

		// CLICK EN EL FILTRO PRECIO

		wait.until(ExpectedConditions.elementToBeClickable(By.id("sfn-nav-pricing"))).click();

		// VALIDA QUE EL ORDEN POR DEFECTO SE DEL MAS BARATO PRIMERO

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("rz-bar-wrapper")));

		List<WebElement> precios = driver.findElements(By.className("vrgf-price-box__price"));

		int precio1 = Integer.parseInt(precios.get(0).getText().replace("$ ", "").replace("* AVG PP", ""));

		int precio2 = Integer
				.parseInt(precios.get(precios.size() - 1).getText().replace("$ ", "").replace("* AVG PP", ""));

		if (precio1 > precio2) {

			Assert.fail("THE DEFAULT VALUE IS NOT FROM THE CHEAPEST FIRST");

		}

		// USAMOS EL FILTRO DE PRECIOS

		WebElement slider = driver.findElement(By.cssSelector(".rz-pointer-min"));

		Actions builder = new Actions(driver);
		builder.moveToElement(slider).click().dragAndDropBy(slider, 50, 0).build().perform();

		// ESPERAMOS QUE CARGUE LOS RESULTADOS Y VALIDAMOS QUE TOME LOS CAMBIOS DEL
		// NUEVO PRECIO MIN

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("vrg-search-unit")));

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("vrg-search-unit")));

		precios = driver.findElements(By.className("vrgf-price-box__price"));

		int precio3 = Integer.parseInt(precios.get(0).getText().replace("$ ", "").replace("* AVG PP", ""));

		count = 0;
		while (precio3 == precio1 && count < 30) {

			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("vrg-search-unit")));

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("vrg-search-unit")));

			precios = driver.findElements(By.className("vrgf-price-box__price"));

			precio3 = Integer.parseInt(precios.get(0).getText().replace("$ ", "").replace("* AVG PP", ""));

			count++;
		}
		if (precio1 >= precio3) {

			Assert.fail("THE PRICE FILTER IS NOT SHOWING THE CORRECT RESULTS");

		}

	}

}
