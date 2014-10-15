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

public class TimeReport extends UtilitiesSimulator {
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
    public void test1SearchCompany() throws  InterruptedException
    {
        //Prerequisite login script should be executed
		Login(driver);
        ImplicitlyWait(driver);
        WebElement clickOnAddTimeReport = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]"));
        Assert.assertTrue(clickOnAddTimeReport.isDisplayed(), "Add time report is not displayed");
        clickOnAddTimeReport.click();
        List<WebElement> custProjActivityLink = driver.findElements(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        custProjActivityLink.get(0).click();
        WebElement searchOption=driver.findElement(By.name("Search"));
        Assert.assertTrue(searchOption.isEnabled(),"Search option is not enabled");
        searchOption.click();
        WebElement searchBar = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableGroup[1]/UIASearchBar[1]/UIASearchBar[1]"));
        searchBar.sendKeys("man2176");
        WebElement keyboardSearchButton = driver.findElement(By.xpath("//UIAWindow[2]/UIAKeyboard[1]/UIAButton[4]"));
        keyboardSearchButton.click();
        driver.findElement(By.name("Clear text")).click();
        //search button on keyboard
        WebElement searchButton = driver.findElement(By.xpath("//UIAWindow[2]/UIAKeyboard[1]/UIAButton[4]"));
        searchButton.click();
        //select the first value and click
        WebElement selectFirstCustomer = driver.findElement(By.name("10054/"));
        selectFirstCustomer.click();
        WebElement navigateBack = driver.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[1]"));
        navigateBack.click();
        //navigating on all tabs
        driver.findElement(By.name("Customer")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("Project")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("Activity")).click();
        driver.findElement(By.name("Customers")).click();
        driver.findElement(By.name("Cancel")).click();
        WebElement cancelButton = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]"));
        cancelButton.click();

        Reporter.log("Search script tested Successfully",true);
    }
	
	@Test 
    public void test2AddTimeReport()
    {
        //Prerequisite login script should be executed
        ImplicitlyWait(driver);
        WebElement clickOnAddTimeReport = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]"));
        Assert.assertTrue(clickOnAddTimeReport.isDisplayed(), "Add time report is not displayed");
        clickOnAddTimeReport.click();
        //Select the customer project and activity
        List<WebElement> custProjActivityLink = driver.findElements(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        custProjActivityLink.get(0).click();
        WebElement customerName = driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        customerName.click();
        //select task
        WebElement task=driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
        task.click();
        WebElement taskName=driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        taskName.click();
        //select time type
        WebElement timeType=driver.findElement(By.xpath("//UIATableView[1]/UIATableCell[3]/UIAStaticText[1]"));
        timeType.click();
        WebElement timeTypeName = driver.findElement(By.xpath("//UIATableCell[2]/UIAStaticText[1]"));
        timeTypeName.click();
        //click on comment link
        WebElement commentLink = driver.findElement(By.xpath("//UIATableCell[4]/UIAStaticText[1]"));
        commentLink.click();
        WebElement comment=driver.findElements(By.className("UIATextView")).get(0);
        comment.sendKeys("comment");
        WebElement doneButton = driver.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]"));
        doneButton.click();
        //Add hours and save report
        WebElement hourButton = driver.findElement(By.name("Hours"));
        hourButton.click();
        WebElement minutes=driver.findElements(By.name("0. 1 of 25")).get(0);
        minutes.sendKeys("4");
        WebElement seconds=driver.findElements(By.name("0. 1 of 4")).get(0);
        seconds.sendKeys("50");
        //click on done buttons
        WebElement hourDoneButton = driver.findElement(By.xpath("//UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]"));
        hourDoneButton.click();
        NavigationDoneButton(driver);
        //click on week view
        WebElement weekView = driver.findElement(By.xpath("//UIASegmentedControl[1]/UIAButton[2]"));
        weekView .click();
        driver.findElement(By.name("NextArrow")).click();
        //click on month view
        WebElement monthView = driver.findElement(By.xpath("//UIASegmentedControl[1]/UIAButton[3]"));
        monthView.click();
        driver.findElement(By.name("CalendarHide.png")).click();
        driver.findElement(By.name("CalendarShow.png")).click();
        //click on today link
        WebElement todayLink = driver.findElement(By.xpath("//UIAToolbar[1]/UIAButton[1]"));
        todayLink.click();
        //click back to day view
        WebElement dayView = driver.findElement(By.xpath("//UIASegmentedControl[1]/UIAButton[1]"));
        dayView.click();

        Reporter.log("Time Report Added Successfully",true);
    }

    @Test 
    public void test3EditTimeReport() throws Exception
    {
        //Script followed by AddTimeReport
        ImplicitlyWait(driver);
        List<WebElement> allReports = driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell"));
        int count = allReports.size();
        allReports.get(count-1).click();
//        WebElement clickonTimeReport = driver.findElement(By.partialLinkText("4,50"));
//        Assert.assertTrue(clickonTimeReport.isDisplayed(), "Time report is not added");
//        clickonTimeReport.click();
        //click on comment link
        WebElement commentLink = driver.findElement(By.xpath("//UIATableCell[4]/UIAStaticText[1]"));
        commentLink.click();
        WebElement editComment=driver.findElements(By.className("UIATextView")).get(0);
        Assert.assertEquals("comment", editComment.getText());
        editComment.sendKeys("comment edited");
        NavigationDoneButton(driver);
        //Edit hours and save report
        WebElement editHour=driver.findElement(By.name("Hours"));
        editHour.click();
        WebElement editMinutes=driver.findElements(By.name("4. 5 of 25")).get(0);
        editMinutes.sendKeys("7");
        WebElement editSeconds=driver.findElements(By.name("50. 3 of 4")).get(0);
        editSeconds.sendKeys("75");
        WebElement hourDoneButton=driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]"));
        hourDoneButton.click();
        Thread.sleep(3000);
        WebElement saveEditedTimeReport = driver.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]"));
        saveEditedTimeReport.click();
        Thread.sleep(3000);

        Reporter.log("Time Report Edited Successfully",true);
    }

    @Test 
    public void test4DeleteTimeReport() throws InterruptedException
    {
        //Depends on Add and Edit time report
//        WebElement clickOnTimeReport=driver.findElement(By.name("man2176/, 1090/, 7,75"));
//        Assert.assertTrue(clickOnTimeReport.isDisplayed(), "Time report is not edited");
//        clickOnTimeReport.click();
    	List<WebElement> allReports = driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell"));
        int count = allReports.size();
        allReports.get(count-1).click();
        //click on "Delete time report row" button
        driver.findElement(By.name("Contact project manager")).click();
        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAActionSheet[1]/UIAButton[2]")).click();
        driver.findElement(By.name("Delete time report row")).click();
        AcceptAlert(driver);
//        Assert.assertFalse(clickOnTimeReport.isDisplayed(), "Time report is not deleted");

        Reporter.log("Time Report Deleted Successfully",true);
        Logout(driver);
        Thread.sleep(5000);
    }

}
