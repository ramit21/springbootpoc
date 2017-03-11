package automation.fluent;

import static org.junit.Assert.*;

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
	
	@Override
	public WebDriver getDefaultDriver(){
		//return getChromeDriver();  this will launch chrome and test in it
		return getHeadlessBrowserDriver();
	}
	
	@Test
	public void testReservationsPage() throws InterruptedException {
		goTo("http://localhost:8090/reserve/");
		Thread.sleep(2000L);
		
		assertTrue(title().contains("Reservations"));
		
		FluentList<FluentWebElement> tableColList = $("table#data-table tr td");
		assertEquals(tableColList.get(0).getElement().getText(),"There are no items...");
		
		//$(".addBtn").click();
	}
	
	private WebDriver getHeadlessBrowserDriver(){
		WebDriver driver = new HtmlUnitDriver();
	    return driver;
	}
	
	private WebDriver getChromeDriver(){
		//Set Chrome as the default driver
		System.setProperty("webdriver.chrome.driver", "C:/Java practice/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
	    return driver;
	}
}
