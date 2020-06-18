package samplebrowserstack.Mavenjava;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@RunWith(Parallelized.class)

public class JUnitParallel {
	
	private String platform;
	  private String browserName;
	  private String browserVersion;
	  String username = System.getenv("BROWSERSTACK_USERNAME");
	  String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
	  String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
	  String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");

	  @Parameterized.Parameters
	  public static LinkedList<String[]> getEnvironments() throws Exception {
	    LinkedList<String[]> env = new LinkedList<String[]>();
	    env.add(new String[]{Platform.MAC.toString(), "chrome", "27"});
	    env.add(new String[]{Platform.MAC.toString(),"firefox","20"});
	    env.add(new String[]{Platform.MAC.toString(),"ie","9"});
	    env.add(new String[]{Platform.MAC.toString(),"opera","12.14"});

	    //add more browsers here

	    return env;
	  }


	  public JUnitParallel(String platform, String browserName, String browserVersion) {
	    this.platform = platform;
	    this.browserName = browserName;
	    this.browserVersion = browserVersion;
	  }

	  private WebDriver driver;

	  @Before
	  public void setUp() throws Exception {
	    DesiredCapabilities capability = new DesiredCapabilities();
	    capability.setCapability("platform", platform);
	    capability.setCapability("browser", browserName);
	    capability.setCapability("browserVersion", browserVersion);
	    capability.setCapability("name", "Bstack-[Java] JUnit Parallel Test");
	    capability.setCapability("build", "JUnit-Parallel");
	    capability.setCapability("os", "Windows");
	    capability.setCapability("browserstack.local", browserstackLocal);
	    capability.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);
	    driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub"), capability);
	    driver = new RemoteWebDriver(
	      new URL("https://lindafitzgerald1:vqp2L6puYj4zuN1AwaQ1@hub-cloud.browserstack.com/wd/hub"),
	      capability
	    );
	  }

	  @Test
	  public void testSimple() throws Exception {
	    driver.get("http://www.google.com");
	    String title = driver.getTitle();
	    System.out.println("Page title is: " + title);
	    WebElement element = driver.findElement(By.name("q"));
	    element.sendKeys("BrowserStack");
	    element.submit();
	    driver = new Augmenter().augment(driver);
	    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    try {
	      FileUtils.copyFile(srcFile, new File("Screenshot.png"));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	  }

}
