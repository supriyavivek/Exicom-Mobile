package se.copernicus.IOS;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login extends UtilitiesSimulator {
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
    public void test1LoginFieldsValidation() throws InterruptedException
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

        Reporter.log("Login credentials entered successfully", true);
    }

    @Test 
    public void test2InvalidLogin()
    {
        //Script followed by LoginFieldsValidation() method
        ImplicitlyWait(driver);
        WebElement clickUserName = driver.findElements(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).get(0);
        clickUserName.click();
        //Add duplicate user
        WebElement clickonAddUser = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
        clickonAddUser.click();
        AddUserDetails("10", "1000", "192.168.1.109:7070",driver);
        KeyboardDoneButton(driver);
        NavigationDoneButton(driver);
        AcceptAlert(driver);

        Reporter.log("Invalid Login tested Successfully",true);
    }

    @Test 
    public void test3DeleteUser() throws InterruptedException
    {
        //Script followed by LoginFieldsValidation() method
        ImplicitlyWait(driver);
        List<WebElement> clickUserLink = driver.findElements(By.className("UIAStaticText"));
        clickUserLink.get(0).click();
        Thread.sleep(3000);
        //Click on Add user link
        WebElement clickAddUserLink=driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
        Assert.assertTrue(clickAddUserLink.isDisplayed(), "User link is displayed");
        clickAddUserLink.click();
        AddUserDetails("10", "1000", "192.168.1.109:8080",driver);
        KeyboardDoneButton(driver);
        NavigationDoneButton(driver);
        WebElement clickUserLinkWithName=driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        clickUserLinkWithName.click();
        WebElement ClickSelectedUser=driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIAButton[1]"));
        ClickSelectedUser.click();
        WebElement resetButton=driver.findElement(By.name("Reset"));
        Assert.assertTrue(resetButton.isEnabled(), "Reset button is enabled");
        resetButton.click();
        AcceptAlert(driver);
        WebElement deleteUser=driver.findElement(By.name("Delete user"));
        Assert.assertTrue(deleteUser.isDisplayed(), "Delete user button is displayed");
        deleteUser.click();
        AcceptAlert(driver);
        WebElement clickExistingUser = driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        clickExistingUser.click();

        Reporter.log("Delete User tested Successfully",true);
    }

    @Test 
    public void test4InvalidPassword()
    {
        //Script followed by LoginFieldsValidation() method
        ImplicitlyWait(driver);
        WebElement incorrectPassword=driver.findElements(By.className("UIASecureTextField")).get(0);
        incorrectPassword.sendKeys("WrongPassword");
        KeyboardDoneButton(driver);
        WebElement clickLogin = driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[4]/UIAStaticText[1]"));
        Assert.assertTrue(clickLogin.isDisplayed(), "Login button is displayed");
        clickLogin.click();
        //Alert pop up for wrong password
        WebDriverWait wait=new WebDriverWait(driver, 240);
        wait.until(ExpectedConditions.alertIsPresent());
        AcceptAlert(driver);

        Reporter.log("Invalid Password tested Successfully",true);
    }

    @Test 
    public void test5LoginTestCase() throws InterruptedException
    {
        //Script followed by LoginFieldsValidation() method
        WebElement password=driver.findElements(By.className("UIASecureTextField")).get(0);
        password.sendKeys("password");
        KeyboardDoneButton(driver);
        //click on login button
        WebElement loginButton = driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[4]/UIAStaticText[1]"));
        Assert.assertTrue(loginButton.isDisplayed(), "Login is displayed");
        loginButton.click();
        ImplicitlyWait(driver);
        Thread.sleep(9000);
        Reporter.log("Login successfull",true);
        Logout(driver);
    }

}
