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

public class ContactStaff extends UtilitiesSimulator {
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
    public void test1ValidateContactStaff() throws Exception
    {
		Login(driver);
        ImplicitlyWait(driver);
        Thread.sleep(5000);
        WebElement selectMenuOption=driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]"));
        Assert.assertTrue(selectMenuOption.isDisplayed(), "Menu option is not displayed");
        selectMenuOption.click();
        Thread.sleep(3000);
        List<WebElement> contactStaff = driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell"));
//        WebElement contactStaff=driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]"));
        int count = contactStaff.size();
        System.out.println(count);
//        contactStaff.get(count-2).click();
        contactStaff.get(2).click();
        
//        Assert.assertTrue(contactStaff.isDisplayed(), "Contact Staff is not clickable");
//        contactStaff.click();
        WebElement searchUser = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableGroup[1]/UIASearchBar[1]/UIASearchBar[1]"));
//        Assert.assertTrue(searchUser.isDisplayed(), "Search user option is not displayed");
        searchUser.sendKeys("Supriya User");
        Assert.assertEquals("Supriya User", searchUser.getText());
        driver.findElement(By.name("Search")).click();
        driver.findElement(By.name("Clear text")).click();
        driver.findElement(By.name("Search")).click();
        driver.findElement(By.name("Departments")).click();
        driver.findElement(By.name("All employees")).click();
        Thread.sleep(3000);
        WebElement selectContact = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]"));
        Assert.assertTrue(selectContact.isDisplayed(),"Contact option is not displayed");
        selectContact.click();
        if ((driver.findElement(By.name("OK"))).isDisplayed()) {
            driver.findElement(By.name("OK")).click();
        }
        WebElement backClick=driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[1]"));
        Thread.sleep(3000);
        backClick.click();
        Thread.sleep(3000);
        Assert.assertFalse(backClick.isDisplayed(), "Back button is displayed");

        Reporter.log("Validated Contact Staff successfully",true);
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
