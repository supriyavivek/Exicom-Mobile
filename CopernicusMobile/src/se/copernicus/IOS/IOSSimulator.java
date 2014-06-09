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
		//driver.quit();
	}
	
	@Test
	public void Login() throws Exception {
		//Test case to login
		List<WebElement> staticText = driver.findElements(By.xpath("//UIATableCell[1]/UIAStaticText[1]"));
		staticText.get(0).click();
		((JavascriptExecutor)driver).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 51); put("y", 144); }});
		((JavascriptExecutor)driver).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 26); put("y", 123); }});
		List<WebElement> textField=driver.findElements(By.className("UIATextField"));
		textField.get(1).sendKeys("10");
		textField.get(2).sendKeys("1000");
		textField.get(4).sendKeys("192.168.1.109:7070");
		driver.findElement(By.xpath("//UIAWindow[2]/UIAToolbar[1]/UIAButton[2]")).click();
		driver.findElement(By.xpath("//UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		WebElement password=driver.findElements(By.className("UIASecureTextField")).get(0);
		password.sendKeys("password");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[2]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
					
		//Add time report and logout
		driver.findElement(By.name("Add")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		WebElement custProjAct=driver.findElements(By.className("UIAStaticText")).get(0);
		custProjAct.click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")).click();
		WebElement task=driver.findElements(By.className("UIAStaticText")).get(0);
		task.click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]")).click();
		WebElement timeType=driver.findElements(By.className("UIAStaticText")).get(0);
		timeType.click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		WebElement comment=driver.findElements(By.className("UIATextView")).get(0);
		comment.sendKeys("comment");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[6]/UIATextField[1]/UIATextField[1]")).click();
		((JavascriptExecutor)driver).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.695); put("x", 104); put("y", 519); }});
		((JavascriptExecutor)driver).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 218); put("y", 516); }});
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]")).click();
		driver.findElement(By.name("Done")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		driver.findElement(By.name("OK")).click();
		
		//Test case to login, edit time report and logout
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIASecureTextField[1]/UIASecureTextField[1]")).sendKeys("password");
		driver.findElement(By.name("Done")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		driver.findElement(By.name("man2176/GG, 1091/, 2,50")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATextView[1]")).sendKeys("comment edited");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIATextField[1]/UIATextField[1]")).click();
		((JavascriptExecutor)driver).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 102); put("y", 517); }});
		((JavascriptExecutor)driver).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 214); put("y", 494); }});
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAAlert[1]/UIATableView[2]")).click();	
		
		//Test case to login, delete time report and logout
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIASecureTextField[1]/UIASecureTextField[1]")).sendKeys("password");
		driver.findElement(By.name("Done")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		driver.findElement(By.name("man2061/A&M R/, K1007/A&M Records, 4,75")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIAStaticText[1]")).click();
		driver.findElement(By.name("Delete time report row")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAAlert[1]/UIATableView[2]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		driver.findElement(By.name("OK")).click();
	}
}

