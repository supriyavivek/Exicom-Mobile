package se.copernicus.IOS;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.net.URL;

import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IOSSimulator {
	RemoteWebDriver driver;
	String url;
		
	@BeforeMethod
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(CapabilityType.VERSION, "7.1");
		capabilities.setCapability(CapabilityType.PLATFORM, "iOS");
		capabilities.setCapability("app", "./cTimeSheetSimulator.app");
		//capabilities.setCapability("BundleID", "se.exicom.timereport");
		//capabilities.setCapability("UDID", "b2784fc98bd0ecc5764f3b14b4c1bdc1f10daa28");
		driver= new RemoteWebDriver(new URL("http://192.168.1.84:4725/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
	}
	
	@AfterMethod
	public void tearDown() {	
		driver.quit();
	}
	
	@Test
	public void Login() throws Exception {
		//Test case to login
		LoginScript();
					
		//Test case to Add time report
		AddTimeReport();

		//Test case to Edit time report
		EditTimeReport();
			
		//Test case to Delete time report
		DeleteTimeReport();	
		
		//Test case to Logout time report
		Logout();
		
		
	}
	
	public void LoginScript()
	{
		List<WebElement> staticText = driver.findElements(By.className("UIAStaticText"));
		staticText.get(1).click();
		//Click on Add user link
		((JavascriptExecutor)driver).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 47); put("y", 141); }});
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		List<WebElement> textField=driver.findElements(By.className("UIATextField"));
		textField.get(1).sendKeys("10");
		textField.get(2).sendKeys("1000");
		textField.get(4).sendKeys("192.168.1.109:7070");
		driver.findElement(By.xpath("//UIAWindow[2]/UIAToolbar[1]/UIAButton[2]")).click();
		driver.findElement(By.xpath("//UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		WebElement password=driver.findElements(By.className("UIASecureTextField")).get(0);
		password.sendKeys("password");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[2]")).click();
		//click on login button
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
	}
	
	public void AddTimeReport()
	{
		//click on '+' symbol
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		List<WebElement> custProjActivityLink = driver.findElements(By.xpath("//UIATableCell[1]/UIAStaticText[1]"));
		custProjActivityLink.get(0).click();
		//Select the first customer
		WebElement customerName = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
		String firstCustomer = customerName.getText();
		Reporter.log(firstCustomer, true);
		customerName.click();
		WebElement task=driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
		task.click();
		//Select the first task
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		WebElement timeType=driver.findElement(By.xpath("//UIATableCell[3]/UIAStaticText[1]"));
		timeType.click();
		//Select the first time type
		driver.findElement(By.xpath("//UIATableCell[2]/UIAStaticText[1]")).click();
		//click on comment link
		driver.findElement(By.xpath("//UIATableCell[4]/UIAStaticText[1]")).click();
		WebElement comment=driver.findElements(By.className("UIATextView")).get(0);
		comment.sendKeys("comment");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		//Add hours and save report
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[6]/UIATextField[1]/UIATextField[1]")).click();
		((JavascriptExecutor)driver).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.695); put("x", 104); put("y", 519); }});
		((JavascriptExecutor)driver).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 218); put("y", 516); }});
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
	}
	
	public void EditTimeReport() throws Exception
	{
		driver.findElement(By.name("man2176/, 1090/, 2,50")).click();
		//click on comment link
		driver.findElement(By.xpath("//UIATableCell[4]/UIAStaticText[1]")).click();
		WebElement comment=driver.findElements(By.className("UIATextView")).get(0);
		comment.sendKeys("comment edited");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		//Add hours and save report
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIAStaticText[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIATextField[1]/UIATextField[1]")).click();
		((JavascriptExecutor)driver).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 104); put("y", 520); }});
		((JavascriptExecutor)driver).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 221); put("y", 494); }});
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]")).click();
		//Delayed the process so that the data gets updated
		Thread.sleep(2000);
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
	}
	
	public void DeleteTimeReport()
	{
		driver.findElement(By.name("man2176/, 1090/, 4,75")).click();
		//click on "Delete time report row" button
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIAStaticText[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIAButton[2]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAAlert[1]/UIATableView[2]/UIATableCell[1]")).click();
	}
	
	public void Logout()
	{
		//Click on the bar button to select logout link
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAAlert[1]/UIATableView[2]/UIATableCell[1]")).click();
	}
}

