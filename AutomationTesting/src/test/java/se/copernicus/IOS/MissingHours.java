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

public class MissingHours extends UtilitiesSimulator{
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
    public void test1ValidateMissingHoursAndSubmit() throws Exception
    {
		Login(driver);
        ImplicitlyWait(driver);
        WebElement selectMenu = driver.findElement(By.xpath("//UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]"));
        Assert.assertTrue(selectMenu.isDisplayed(), "Menu option is not displayed");
        selectMenu.click();
        Thread.sleep(3000);
        List<WebElement> missingHours = driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell"));
//        WebElement missingHours=driver.findElement(By.name("Missing hours"));
//        int count = missingHours.size();
        missingHours.get(1).click();
//        ExplicitlyWait(driver, missingHours);
        WebElement selectWeek = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]"));
        selectWeek.click();
        WebElement submitButton=driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[3]"));
        ExplicitlyWait(driver, submitButton);
        WebElement submitWeek=driver.findElement(By.name("Submit part of week"));
        submitWeek.click();
        driver.findElement(By.name("Report inclusive")).click();
        WebElement selectDay = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]"));
        selectDay.click();
        driver.findElement(By.name("Submit time report")).click();
        Thread.sleep(2000);
//        if ((driver.findElement(By.name("OK")).isDisplayed())) {
//            driver.findElement(By.name("OK")).click();
//        } 
//            else {
//            driver.findElement(By.name("Cancel")).click();
//        }

        Reporter.log("Validated Missing Hours successfully",true);
        LogoutTestCase();
    }
	
	public void LogoutTestCase() throws InterruptedException
    {
        //Click on the bar button to select logout link
        WebElement selectMenuBar = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[1]"));
        Assert.assertTrue(selectMenuBar.isDisplayed(),"Select menu bar is not displayed");
        selectMenuBar.click();
        Thread.sleep(5000);
        WebElement logoutButton=driver.findElement(By.name("Logout"));
        Assert.assertTrue(logoutButton.isDisplayed(),"Logout button is not displayed");
        logoutButton.click();
        AcceptAlert(driver);

        Reporter.log("Logout tested Successfully",true);
    }
}
