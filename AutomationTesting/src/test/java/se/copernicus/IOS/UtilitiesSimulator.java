package se.copernicus.IOS;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class UtilitiesSimulator {
	AppiumDriver driver;
	
	public void setUp() throws InterruptedException, MalformedURLException {
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
	
	public static void AcceptAlert(AppiumDriver driver)
    {
        Alert alert=driver.switchTo().alert();
        alert.accept();
    }

    public static void KeyboardDoneButton(AppiumDriver driver)
    {
        WebElement keyboardDoneButton = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAToolbar[1]/UIAButton[2]"));
        keyboardDoneButton.click();
    }

    public static void NavigationDoneButton(AppiumDriver driver)
    {
        WebElement navigationDoneButton = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]"));
        navigationDoneButton.click();
    }

    public static void ImplicitlyWait(WebDriver wd)
    {
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
    }

    public static void ExplicitlyWait(AppiumDriver driver, WebElement ele)
    {
        WebDriverWait wait=new WebDriverWait(driver, 240);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        ele.click();
    }
    
    public static void AddUserDetails(String name, String companyName, String address, AppiumDriver driver)
    {
        WebElement user=driver.findElements(By.className("UIATextField")).get(0);
        Assert.assertTrue(user.isDisplayed(),"Name field is not displayed");
        user.sendKeys(name);
        WebElement company=driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIATextField[1]"));
        company.sendKeys(companyName);
        WebElement url=driver.findElements(By.className("UIATextField")).get(2);
        url.sendKeys(address);
    }
    
    public static void Logout(AppiumDriver driver) throws InterruptedException
    {
        //Click on the bar button to select logout link
        WebElement selectMenuBar = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]"));
        Assert.assertTrue(selectMenuBar.isDisplayed(),"Select menu bar is not displayed");
        selectMenuBar.click();
        Thread.sleep(5000);
        WebElement logoutButton=driver.findElement(By.name("Logout"));
        Assert.assertTrue(logoutButton.isDisplayed(),"Logout button is not displayed");
        logoutButton.click();
        AcceptAlert(driver);

        Reporter.log("Logout tested Successfully",true);
    }
    
    public static void Login(AppiumDriver driver) throws InterruptedException
    {
		List<WebElement> clickUserLink = driver.findElements(By.className("UIAStaticText"));
        clickUserLink.get(1).click();
        Thread.sleep(3000);
        //Click on Add user link
        WebElement clickAddUserLink = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        Assert.assertTrue(clickAddUserLink.isDisplayed(), "User link is displayed");
        clickAddUserLink.click();
        AddUserDetails("10", "1000", "192.168.1.109:7070",driver);
        KeyboardDoneButton(driver);
        NavigationDoneButton(driver);
        WebElement password=driver.findElements(By.className("UIASecureTextField")).get(0);
        password.sendKeys("password");
        KeyboardDoneButton(driver);
        //click on login button
        WebElement loginButton = driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[4]/UIAStaticText[1]"));
        Assert.assertTrue(loginButton.isDisplayed(), "Login is displayed");
        loginButton.click();
        ImplicitlyWait(driver);

        Reporter.log("Login credentials entered successfully", true);
    }
}
