package automation.fluent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

//Make sure that application is up and running on localhost:8090

@RunWith(JUnit4.class)
public class TestReservationPageFluent extends FluentTest {
	
	private WebDriver driver;
	
	@Override
	public WebDriver getDefaultDriver(){
		//driver = getChromeDriver();  this will launch chrome and test in it
		driver = getHeadlessBrowserDriver();
		return driver;
	}
	
	@Test
	public void testReservationsPage() throws InterruptedException {
		goTo("http://localhost:8090/reserve/");
		Thread.sleep(800L);
		System.err.println(driver.getPageSource());				//Print the HTML page
		assertTrue(title().contains("Reservations"));
		String nameToAdd = "Narendra Modi";
		FluentList<FluentWebElement> tableColList = $("table#data-table tr td");
		//assertEquals(tableColList.get(0).getElement().getText(),"There are no items...");
		assertEquals(tableColList.get(3).getElement().getText(),"Ramit");
		$("#nameBox").get(0).getElement().sendKeys(nameToAdd);  //Add value to text field
		$(".addBtn").get(0).getElement().click();				//Click submit button
		
		Thread.sleep(800L);
		
		tableColList = $("table#data-table tr td");
		assertEquals(tableColList.get(tableColList.size()-1).getElement().getText(),nameToAdd);
	}
	
	private WebDriver getHeadlessBrowserDriver(){
		WebDriver driver = new HtmlUnitDriver(true); //'true' to enable javacript, else js actions like buuton click wont have any effect
	    return driver;
	}
	
	private WebDriver getChromeDriver(){
		//Set Chrome as the default driver
		System.setProperty("webdriver.chrome.driver", "C:/Java practice/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
	    return driver;
	}
}
