package se.copernicus.IOS;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
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
		capabilities.setCapability("app", "./cTimeSheetDevice.app");
		capabilities.setCapability("BundleID", "se.exicom.timereport");
		capabilities.setCapability("UDID", "b2784fc98bd0ecc5764f3b14b4c1bdc1f10daa28");
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
	
	public void LoginScript() throws Exception
	{
		List<WebElement> staticText = driver.findElements(By.className("UIAStaticText"));
		staticText.get(1).click();
		//Click on Add user link
		Thread.sleep(2000);
		driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		WebElement user=driver.findElements(By.className("UIATextField")).get(0);
		user.sendKeys("10");
		WebElement company=driver.findElements(By.className("UIATextField")).get(1);
		company.sendKeys("1000");
		WebElement url=driver.findElements(By.className("UIATextField")).get(2);
		url.sendKeys("192.168.1.109:7070");
		driver.findElement(By.xpath("//UIAWindow[2]/UIAToolbar[1]/UIAButton[2]")).click();
		driver.findElement(By.xpath("//UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		InvalidLogin();
		DeleteUser();
		InvalidPassword();
		WebElement password=driver.findElements(By.className("UIASecureTextField")).get(0);
		password.sendKeys("password");
		driver.findElement(By.xpath("//UIAWindow[2]/UIAToolbar[1]/UIAButton[2]")).click();
		//click on login button
		driver.findElement(By.xpath("//UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
	}
	
	public void InvalidLogin()
	{
		driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		//Add duplicate user
		driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")).click();
		WebElement user=driver.findElements(By.className("UIATextField")).get(0);
		user.sendKeys("10");
		WebElement company=driver.findElements(By.className("UIATextField")).get(1);
		company.sendKeys("1000");
		WebElement url=driver.findElements(By.className("UIATextField")).get(2);
		url.sendKeys("192.168.1.109:7070");
		driver.findElement(By.xpath("//UIAWindow[2]/UIAToolbar[1]/UIAButton[2]")).click();
		driver.findElement(By.xpath("//UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		driver.findElement(By.xpath("//UIAWindow[3]/UIAAlert[1]/UIATableView[1]/UIATableCell[1]")).click();
	}
	
	public void DeleteUser()
	{
		driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		//Delete user
		driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")).click();
		WebElement user=driver.findElements(By.className("UIATextField")).get(0);
		user.sendKeys("10");
		WebElement company=driver.findElements(By.className("UIATextField")).get(1);
		company.sendKeys("1000");
		WebElement url=driver.findElements(By.className("UIATextField")).get(2);
		url.sendKeys("192.168.1.109:8080");
		driver.findElement(By.xpath("//UIAWindow[2]/UIAToolbar[1]/UIAButton[2]")).click();
		driver.findElement(By.xpath("//UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIAButton[1]")).click();
		driver.findElement(By.xpath("//UIATableView[1]/UIAButton[1]")).click();
		driver.findElement(By.xpath("//UIAWindow[3]/UIAAlert[1]/UIATableView[2]")).click();
		driver.findElement(By.xpath("//UIATableView[1]/UIAButton[2]")).click();	
		driver.findElement(By.xpath("//UIAWindow[3]/UIAAlert[1]/UIATableView[2]")).click();
		driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
	}
	
	public void InvalidPassword()
	{
		WebElement password=driver.findElements(By.className("UIASecureTextField")).get(0);
		password.sendKeys("WrongPassword");
		driver.findElement(By.xpath("//UIAWindow[2]/UIAToolbar[1]/UIAButton[2]")).click();
		driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		//Alert pop up for wrong password
		driver.findElement(By.xpath("//UIAWindow[3]/UIAAlert[1]/UIATableView[1]/UIATableCell[1]")).click();
		driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIASecureTextField[1]/UIAStaticText[1]")).click();
		driver.findElement(By.name("Clear text")).click();
	}
	
	public void AddTimeReport()
	{
		//click on '+' symbol
		driver.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]")).click();
		List<WebElement> custProjActivityLink = driver.findElements(By.xpath("//UIATableCell[1]/UIAStaticText[1]"));
		custProjActivityLink.get(0).click();
		//Select the first customer
		WebElement customerName = driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
		customerName.click();
		WebElement task=driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
		task.click();
		//Select the first task
		driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		WebElement timeType=driver.findElement(By.xpath("//UIATableCell[3]/UIAStaticText[1]"));
		timeType.click();
		//Select the first time type
		driver.findElement(By.xpath("//UIATableCell[2]/UIAStaticText[1]")).click();
		//click on comment link
		driver.findElement(By.xpath("//UIATableCell[4]/UIAStaticText[1]")).click();
		WebElement comment=driver.findElements(By.className("UIATextView")).get(0);
		comment.sendKeys("comment");
		driver.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]")).click();
		//Add hours and save report
		WebElement hour=driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[6]/UIAStaticText[1]")).get(0);
		hour.click();
		WebElement minutes=driver.findElements(By.name("0. 1 of 25")).get(0);
		minutes.sendKeys("4");
		WebElement seconds=driver.findElements(By.name("0. 1 of 4")).get(1);
		seconds.sendKeys("50");
		driver.findElement(By.xpath("//UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]")).click();
		driver.findElement(By.xpath("//UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		driver.findElement(By.xpath("//UIASegmentedControl[1]/UIAButton[2]")).click();
		driver.findElement(By.name("NextArrow")).click();
		driver.findElement(By.xpath("//UIASegmentedControl[1]/UIAButton[3]")).click();
		driver.findElement(By.xpath("//UIAToolbar[1]/UIAButton[1]")).click();
		driver.findElement(By.xpath("//UIASegmentedControl[1]/UIAButton[1]")).click();
	}
	
	public void EditTimeReport() throws Exception
	{
		driver.findElement(By.name("man2176/, 1090/, 4,50")).click();
		//click on comment link
		driver.findElement(By.xpath("//UIATableCell[4]/UIAStaticText[1]")).click();
		WebElement comment=driver.findElements(By.className("UIATextView")).get(0);
		comment.sendKeys("comment edited");
		driver.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]")).click();
		//Add hours and save report
		driver.findElement(By.xpath("//UIATableCell[5]/UIAStaticText[1]")).click();
		WebElement hour=driver.findElements(By.xpath("//UIATableCell[5]/UIATextField[1]/UIATextField[1]")).get(0);
		hour.click();
		WebElement minutes=driver.findElements(By.name("4. 5 of 25")).get(0);
		minutes.sendKeys("7");
		WebElement seconds=driver.findElements(By.name("50. 3 of 4")).get(1);
		seconds.sendKeys("75");
		driver.findElement(By.xpath("//UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]")).click();
		//Delayed the process so that the data gets updated
		Thread.sleep(2000);
		driver.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]")).click();
	}
	
	public void DeleteTimeReport()
	{
		driver.findElement(By.name("man2176/, 1090/, 7,75")).click();
		//click on "Delete time report row" button
		driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[5]/UIAStaticText[1]")).click();
		driver.findElement(By.xpath("//UIATableView[1]/UIAButton[2]")).click();
		driver.findElement(By.xpath("//UIAWindow[3]/UIAAlert[1]/UIATableView[2]/UIATableCell[1]")).click();
	}
	
	public void Logout()
	{
		//Click on the bar button to select logout link
		driver.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[1]")).click();
		driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		driver.findElement(By.xpath("//UIAWindow[3]/UIAAlert[1]/UIATableView[2]/UIATableCell[1]")).click();
	}
}

