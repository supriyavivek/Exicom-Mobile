package se.copernicus.IOS;

import io.appium.java_client.AppiumDriver;

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

import se.copernicus.Android.Settings;

public class IOSSimulator {
	AppiumDriver wd;
	String url;
		
	@BeforeMethod
	public void setUp() throws Exception {
		Settings.exicom_iPad(wd);
	}
	
	@AfterMethod	
	public void tearDown() {	
		wd.quit();
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
		List<WebElement> staticText = wd.findElements(By.className("UIAStaticText"));
		staticText.get(1).click();
		//Click on Add user link
		Thread.sleep(2000);
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		List<WebElement> textField=wd.findElements(By.className("UIATextField"));
		textField.get(1).sendKeys("10");
		textField.get(2).sendKeys("1000");
		textField.get(4).sendKeys("192.168.1.109:7070");
		wd.findElement(By.xpath("//UIAWindow[2]/UIAToolbar[1]/UIAButton[2]")).click();
		wd.findElement(By.xpath("//UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		InvalidLogin();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		Thread.sleep(2000);
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		InvalidPassword();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIASecureTextField[1]/UIAStaticText[1]")).click();
		wd.findElement(By.name("Clear text")).click();
		WebElement password=wd.findElements(By.className("UIASecureTextField")).get(0);
		password.sendKeys("password");
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[2]")).click();
		//click on login button
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
	}
	
	public void InvalidLogin()
	{
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		//Add duplicate user
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")).click();
		List<WebElement> textField=wd.findElements(By.className("UIATextField"));
		textField.get(1).sendKeys("10");
		textField.get(2).sendKeys("1000");
		textField.get(4).sendKeys("192.168.1.109:7070");
		wd.findElement(By.xpath("//UIAWindow[2]/UIAToolbar[1]/UIAButton[2]")).click();
		wd.findElement(By.xpath("//UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAAlert[1]/UIATableView[1]/UIATableCell[1]")).click();
	}
	
	public void InvalidPassword()
	{
		WebElement password=wd.findElements(By.className("UIASecureTextField")).get(0);
		password.sendKeys("WrongPassword");
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[2]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		//Alert pop up for wrong password
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAAlert[1]/UIATableView[1]/UIATableCell[1]")).click();
	}
	
	public void AddTimeReport()
	{
		//click on '+' symbol
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		List<WebElement> custProjActivityLink = wd.findElements(By.xpath("//UIATableCell[1]/UIAStaticText[1]"));
		custProjActivityLink.get(0).click();
		//Select the first customer
		WebElement customerName = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
		customerName.click();
		WebElement task=wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
		task.click();
		//Select the first task
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		WebElement timeType=wd.findElement(By.xpath("//UIATableCell[3]/UIAStaticText[1]"));
		timeType.click();
		//Select the first time type
		wd.findElement(By.xpath("//UIATableCell[2]/UIAStaticText[1]")).click();
		//click on comment link
		wd.findElement(By.xpath("//UIATableCell[4]/UIAStaticText[1]")).click();
		WebElement comment=wd.findElements(By.className("UIATextView")).get(0);
		comment.sendKeys("comment");
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		//Add hours and save report
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[6]/UIATextField[1]/UIATextField[1]")).click();
		wd.findElement(By.name("0. 1 of 25")).sendKeys("4");
		wd.findElement(By.name("0. 1 of 4")).sendKeys("50");
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
	}
	
	public void EditTimeReport() throws Exception
	{
		wd.findElement(By.name("man2176/, 1090/, 4,50")).click();
		//click on comment link
		wd.findElement(By.xpath("//UIATableCell[4]/UIAStaticText[1]")).click();
		WebElement comment=wd.findElements(By.className("UIATextView")).get(0);
		comment.sendKeys("comment edited");
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		//Add hours and save report
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIAStaticText[1]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIATextField[1]/UIATextField[1]")).click();
		wd.findElement(By.name("4. 5 of 25")).sendKeys("7");
		wd.findElement(By.name("50. 3 of 4")).sendKeys("75");
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]")).click();
		//Delayed the process so that the data gets updated
		Thread.sleep(2000);
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
	}
	
	public void DeleteTimeReport()
	{
		wd.findElement(By.name("man2176/, 1090/, 7,75")).click();
		//click on "Delete time report row" button
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIAStaticText[1]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIAButton[2]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAAlert[1]/UIATableView[2]/UIATableCell[1]")).click();
	}
	
	public void Logout()
	{
		//Click on the bar button to select logout link
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAAlert[1]/UIATableView[2]/UIATableCell[1]")).click();
	}
}

