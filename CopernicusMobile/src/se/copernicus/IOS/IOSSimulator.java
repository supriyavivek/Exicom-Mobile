package se.copernicus.IOS;

import java.util.concurrent.TimeUnit;
import java.net.URL;

import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IOSSimulator {
	RemoteWebDriver wd;
	String url;
	
	@BeforeMethod
	public void setUp() throws Exception {
	//File appDir=new File(System.getProperty("users.dir"), "./Users/indpro/Documents/cTimeSheetBuild");
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
	capabilities.setCapability(CapabilityType.VERSION, "7.1");
	capabilities.setCapability(CapabilityType.PLATFORM, "iOS");
	capabilities.setCapability("device", "iPhone");
	capabilities.setCapability("app", "/Users/indpro/Desktop/cTimeSheet/cTimeSheet Device/cTimeSheet.app");
	capabilities.setCapability("BundleID", "se.exicom.timereport");
	capabilities.setCapability("UDID", "b2784fc98bd0ecc5764f3b14b4c1bdc1f10daa28");
	wd= new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
	wd.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
	}
	
	@AfterMethod
	public void tearDown() {	
		//wd.quit();
	}
	
	@Test
	public void addTimeReport() throws Exception {
		//Test case to login and add time report
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		((JavascriptExecutor)wd).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 302); put("y", 141); }});
		((JavascriptExecutor)wd).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 233); put("y", 123); }});
		WebElement user=wd.findElements(By.className("UIATextField")).get(0);
		user.sendKeys("10");
		WebElement company=wd.findElements(By.className("UIATextField")).get(1);
		company.sendKeys("1000");
		WebElement url=wd.findElements(By.className("UIATextField")).get(2);
		url.sendKeys("192.168.1.109:7070");
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[2]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		
		//Verify duplicate login
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")).click();
		((JavascriptExecutor)wd).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 302); put("y", 141); }});
		((JavascriptExecutor)wd).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 233); put("y", 123); }});
		WebElement same_user=wd.findElements(By.className("UIATextField")).get(0);
		same_user.sendKeys("10");
		WebElement same_company=wd.findElements(By.className("UIATextField")).get(1);
		same_company.sendKeys("1000");
		WebElement same_url=wd.findElements(By.className("UIATextField")).get(2);
		same_url.sendKeys("192.168.1.109:7070");
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[2]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		wd.findElement(By.name("OK")).click();
	   
		//Login
		WebElement password=wd.findElements(By.className("UIASecureTextField")).get(0);
		password.sendKeys("password");
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[2]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		
		//Add time report and logout
		wd.findElement(By.name("Add")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		WebElement custProjAct=wd.findElements(By.className("UIAStaticText")).get(0);
		custProjAct.click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")).click();
		WebElement task=wd.findElements(By.className("UIAStaticText")).get(0);
		task.click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]")).click();
		WebElement timeType=wd.findElements(By.className("UIAStaticText")).get(0);
		timeType.click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		WebElement comment=wd.findElements(By.className("UIATextView")).get(0);
		comment.sendKeys("comment");
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[6]/UIATextField[1]/UIATextField[1]")).click();
		((JavascriptExecutor)wd).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.695); put("x", 104); put("y", 519); }});
		((JavascriptExecutor)wd).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 218); put("y", 516); }});
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]")).click();
		wd.findElement(By.name("Done")).click();
		Thread.sleep(2000);
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		wd.findElement(By.name("OK")).click();
		
		//Test case to login, edit time report and logout
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIASecureTextField[1]/UIASecureTextField[1]")).sendKeys("password");
		wd.findElement(By.name("Done")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		wd.findElement(By.name("man2176/GG, 1091/, 2,50")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATextView[1]")).sendKeys("comment edited");
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIATextField[1]/UIATextField[1]")).click();
		((JavascriptExecutor)wd).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 102); put("y", 517); }});
		((JavascriptExecutor)wd).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 214); put("y", 494); }});
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		Thread.sleep(3000);
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAAlert[1]/UIATableView[2]")).click();	
		
		//Test case to login, delete time report and logout
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIASecureTextField[1]/UIASecureTextField[1]")).sendKeys("password");
		wd.findElement(By.name("Done")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		wd.findElement(By.name("man2061/A&M R/, K1007/A&M Records, 4,75")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[5]/UIAStaticText[1]")).click();
		wd.findElement(By.name("Delete time report row")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAAlert[1]/UIATableView[2]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click();
		wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")).click();
		wd.findElement(By.name("OK")).click();
//		WebElement text_field=wd.findElements(By.className("UIATextField")).get(1);
//		String default_value=text_field.getAttribute("value");
//		String rnd_string=RandomStringUtils.randomAlphabetic(6);
//		text_field.sendKeys(rnd_string);
//		String rnd_string2 = RandomStringUtils.randomAlphanumeric(6);
//		Actions swipe = new Actions(wd).sendKeys(rnd_string2);
//		swipe.perform();
//		text_field.clear();
	}
}

