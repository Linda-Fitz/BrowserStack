package samplebrowserstack.Mavenjava;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class JavaSample {
	
		// TODO Auto-generated method stub
		public static final String USERNAME = "lindafitzgerald1";
		public static final String AUTOMATE_KEY = "vqp2L6puYj4zuN1AwaQ1";
		public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

		public static void main(String[] args) throws Exception {

		    DesiredCapabilities caps = new DesiredCapabilities();
		    caps.setCapability("browser", "Chrome");
		    caps.setCapability("browser_version", "83.0");
		    caps.setCapability("os", "OS X");
		    caps.setCapability("os_version", "Mojave");
		    caps.setCapability("resolution", "1024x768");
		    caps.setCapability("name", "Bstack-[Java] Sample Test");

		    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
		    driver.get("http://www.google.com");
		    WebElement element = driver.findElement(By.name("q"));

		    element.sendKeys("BrowserStack");
		    element.submit();

		    System.out.println(driver.getTitle());
		    driver.quit();

	}

}
