package StepDefinition;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class Steps {

	WebDriver driver;

	@Given("^Open the app$")
	public void openApp() throws MalformedURLException, InterruptedException {
		//setting the property of the browser
//		System.setProperty("webdriver.chrome.driver",
//				"src/test/resources/chromedriver.exe");
		
		Capabilities chromeCapabilities = DesiredCapabilities.chrome();
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeCapabilities);
		
		//creating a new instance of chrome driver
//		driver = new ChromeDriver();
		//maximizing the chrome 
//		driver.manage().window().maximize();
		//accessing the app
		driver.get("http://172.21.207.49:3000/");
	}

	@When("^Sort table$")
	public void sortTable() throws Throwable {
		//find an element by Id in our HTML page
		WebElement app = driver.findElement(By.id("app"));
		//find an element by tagName in our div with id=app		
		WebElement ul = app.findElement(By.tagName("ul"));
		//creating a list of WebElement's
		List<WebElement> orderedList = new ArrayList<WebElement>();
		//creating a secundary list of WebElement's
		List<WebElement> originalList = new ArrayList<WebElement>();

		//verifying if the WebElement ul isn't null
		if (ul != null) {
			// searching within the UL a list of WebElements
			orderedList = ul.findElements(By.tagName("li"));
			// searching within the UL a list of WebElements
			originalList = ul.findElements(By.tagName("li"));
			// sorting the list through the HTML's attribute textContent
			orderedList.sort(Comparator.comparing(a -> a.getAttribute("textContent")));
			
			for (int i = 0; i < orderedList.size(); i++) {
				// creating a new Action
				Actions act = new Actions(driver);
				// searching in the originalList, the index of the actual element
				int of = originalList.indexOf(orderedList.get(i));
				// moving the element to the correct position
				act.dragAndDrop(originalList.get(of), originalList.get(i)).build().perform();
				// sorting the orderedList again
				orderedList.sort(Comparator.comparing(a -> a.getAttribute("textContent")));
			}
			//closing the browser
			driver.close();
		}
	}
}
