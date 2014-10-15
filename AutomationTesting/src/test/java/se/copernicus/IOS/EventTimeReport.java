package se.copernicus.IOS;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EventTimeReport extends UtilitiesSimulator {
	AppiumDriver driver;
	
	@Override
	@BeforeClass
    public void setUp() throws InterruptedException, MalformedURLException
    {
        String appdirectory = System.getProperty("user.dir")+"/src/test/resources";
        File appDir = new File(appdirectory);
        File app=new File(appDir, "cTimeSheetSimulator.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "ios");
        capabilities.setCapability("deviceName", "iPhone");
        capabilities.setCapability("app", app.getAbsolutePath());
        driver= new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        ImplicitlyWait(driver);

        Reporter.log("App launched",true);
        Thread.sleep(3000);
    }
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test 
    public void test1AddEventTimeReport() throws InterruptedException
    {
		Login(driver);
        ImplicitlyWait(driver);
        WebElement clickonCalendar = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[2]"));
        Assert.assertTrue(clickonCalendar.isDisplayed(), "Calendar option is not displayed");
        clickonCalendar.click();
        if ((driver.findElement(By.name("OK"))).isDisplayed()) {
            driver.findElement(By.name("OK")).click();
        }
        driver.findElement(By.name("Show all calendars")).click();
        driver.findElement(By.name("Done")).click();
        Thread.sleep(3000);
        WebElement calendarEvent=driver.findElement(By.name("New event, bang, 1,00"));
        Assert.assertTrue(calendarEvent.isDisplayed(), "Calendar event is displayed");
        calendarEvent.click();
        WebElement selectCustomer = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        selectCustomer.click();
        WebElement selectTask=driver.findElement(By.name("Task"));
        selectTask.click();
        WebElement taskName = driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        taskName.click();
        WebElement selectTimeType=driver.findElement(By.name("Timetype"));
        selectTimeType.click();
        WebElement timeTypeName = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
        timeTypeName.click();
        NavigationDoneButton(driver);
        Thread.sleep(3000);
//        WebElement clickOnEventTimeReport=driver.findElement(By.name("man2176/, 1090/, 1,00"));
//        Assert.assertTrue(clickOnEventTimeReport.isDisplayed(), "Calendar time report is added");
//        clickOnEventTimeReport.click();
        List<WebElement> allReports = driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell"));
        int count = allReports.size();
        allReports.get(count-1).click();
        driver.findElement(By.name("Delete time report row")).click();
        WebElement deleteTimeReport=driver.findElement(By.name("OK"));
        deleteTimeReport.click();
        Thread.sleep(3000);
//        Assert.assertFalse(clickOnEventTimeReport.isDisplayed(), "Calendar time report is not deleted");

        Reporter.log("Event Time Report Added Successfully",true);
        Logout(driver);
    }
}
