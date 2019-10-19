package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Login extends tests.test {

	public static void closeModal() throws Exception {

		List<WebElement> modal = driver.findElements(By.className("close-28847"));

		List<WebElement> modal1 = driver.findElements(By.className("vifp-sweeps-modal-close"));

		try {

			if (modal1.size() > 0) {

				System.out.println("CLICK MODAL CENTRAL " + modal1.size());

				modal1.get(0).click();

			}

			if (modal.size() > 0) {

				System.out.println("CLICK MODAL RIGHT " + modal.size());

				modal.get(0).click();

			}
		} catch (Exception ex) {

		}

	}

}
