package se.copernicus.IOS;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class IOSSimulatorTest
{
    WebDriver wd=null;

	/*
	Precondition for test1
	1) A Event should be added in current date ["New event", "bang", "1,00"]
	*/

    @BeforeClass
    public void setUp() throws InterruptedException, MalformedURLException
    {
        String appdirectory = System.getProperty("user.dir")+"/Users/indpro/Desktop/iPhone Simulator.app";
        File appDir = new File(appdirectory);
        File app=new File(appDir, "cTimeSheetSimulator.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "ios");
        capabilities.setCapability("deviceName", "iPhone");
        capabilities.setCapability("app", app.getAbsolutePath());
        wd= new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        ImplicitlyWait(wd);

        Reporter.log("App launched",true);
        Thread.sleep(3000);
    }

    @AfterClass
    public void tearDown()
    {
        wd.quit();
    }

    @Test
    public void LoginFieldsValidation() throws InterruptedException
    {
        List<WebElement> clickUserLink = wd.findElements(By.className("UIAStaticText"));
        clickUserLink.get(1).click();
        Thread.sleep(3000);
        //Click on Add user link
        WebElement clickAddUserLink = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        clickAddUserLink.click();
        AddUserDetails("10", "1000", "192.168.1.109:7070");
        KeyboardDoneButton(wd);
        NavigationDoneButton(wd);

        Reporter.log("Login credentials entered successfully", true);
    }

    @Test (dependsOnMethods = {"LoginFieldsValidation"})
    public void InvalidLogin()
    {
        //Script followed by LoginFieldsValidation() method
        ImplicitlyWait(wd);
        WebElement clickUserName = wd.findElements(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).get(0);
        clickUserName.click();
        //Add duplicate user
        WebElement clickonAddUser = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
        clickonAddUser.click();
        AddUserDetails("10", "1000", "192.168.1.109:7070");
        KeyboardDoneButton(wd);
        NavigationDoneButton(wd);
        AcceptAlert(wd);

        Reporter.log("Invalid Login tested Successfully",true);
    }

    @Test (dependsOnMethods = {"InvalidLogin"})
    public void DeleteUser() throws InterruptedException
    {
        //Script followed by LoginFieldsValidation() method
        ImplicitlyWait(wd);
        List<WebElement> clickUserLink = wd.findElements(By.className("UIAStaticText"));
        clickUserLink.get(0).click();
        Thread.sleep(3000);
        //Click on Add user link
        WebElement clickAddUserLink=wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
        clickAddUserLink.click();
        AddUserDetails("10", "1000", "192.168.1.109:8080");
        KeyboardDoneButton(wd);
        NavigationDoneButton(wd);
        WebElement clickUserLinkWithName=wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        clickUserLinkWithName.click();
        WebElement ClickSelectedUser=wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIAButton[1]"));
        ClickSelectedUser.click();
        wd.findElement(By.name("Reset")).click();
        AcceptAlert(wd);
        wd.findElement(By.name("Delete user")).click();
        AcceptAlert(wd);
        WebElement clickExistingUser = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        clickExistingUser.click();

        Reporter.log("Delete User tested Successfully",true);
    }

    @Test (dependsOnMethods = {"DeleteUser"})
    public void InvalidPassword()
    {
        //Script followed by LoginFieldsValidation() method
        ImplicitlyWait(wd);
        WebElement incorrectPassword=wd.findElements(By.className("UIASecureTextField")).get(0);
        incorrectPassword.sendKeys("WrongPassword");
        KeyboardDoneButton(wd);
        WebElement clickLogin = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[4]/UIAStaticText[1]"));
        clickLogin.click();
        //Alert pop up for wrong password
        if (ExpectedConditions.alertIsPresent()!=null) {
            AcceptAlert(wd);
        }

        Reporter.log("Invalid Password tested Successfully",true);
    }

    @Test (dependsOnMethods = {"InvalidPassword"})
    public void Login()
    {
        //Script followed by LoginFieldsValidation() method
        WebElement password=wd.findElements(By.className("UIASecureTextField")).get(0);
        password.sendKeys("password");
        KeyboardDoneButton(wd);
        //click on login button
        WebElement loginButton = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[4]/UIAStaticText[1]"));
        Assert.assertTrue(loginButton.isDisplayed(), "Login");
        loginButton.click();

        Reporter.log("Login successfull",true);
    }

    @Test (dependsOnMethods = {"Login"})
    public void SearchCompany() throws  InterruptedException
    {
        //Prerequisite login script should be executed
        ImplicitlyWait(wd);
        WebElement clickOnAddTimeReport = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]"));
        clickOnAddTimeReport.click();
        List<WebElement> custProjActivityLink = wd.findElements(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        custProjActivityLink.get(0).click();
        wd.findElement(By.name("Search")).click();
        WebElement searchBar = wd.findElement(By.xpath("//UIATableView[1]/UIATableGroup[1]/UIASearchBar[1]/UIASearchBar[1]"));
        searchBar.sendKeys("man2176/");
        WebElement keyboardSearchButton = wd.findElement(By.xpath("//UIAWindow[2]/UIAKeyboard[1]/UIAButton[4]"));
        keyboardSearchButton.click();
        wd.findElement(By.name("Clear text")).click();
        //search button on keyboard
        WebElement searchButton = wd.findElement(By.xpath("//UIAWindow[2]/UIAKeyboard[1]/UIAButton[4]"));
        searchButton.click();
        //select the first value and click
        WebElement selectFirstCustomer = wd.findElement(By.name("10054/"));
        selectFirstCustomer.click();
        WebElement navigateBack = wd.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[1]"));
        navigateBack.click();
        //navigating on all tabs
        wd.findElement(By.name("Customer")).click();
        Thread.sleep(2000);
        wd.findElement(By.name("Project")).click();
        Thread.sleep(2000);
        wd.findElement(By.name("Activity")).click();
        wd.findElement(By.name("Customers")).click();
        wd.findElement(By.name("Cancel")).click();
        WebElement cancelButton = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]"));
        cancelButton.click();

        Reporter.log("Search script tested Successfully",true);
    }

    @Test (dependsOnMethods = {"SearchCompany"})
    public void AddTimeReport()
    {
        //Prerequisite login script should be executed
        ImplicitlyWait(wd);
        WebElement clickOnAddTimeReport = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]"));
        clickOnAddTimeReport.click();
        //Select the customer project and activity
        List<WebElement> custProjActivityLink = wd.findElements(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        custProjActivityLink.get(0).click();
        WebElement customerName = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        customerName.click();
        //select task
        WebElement task=wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
        task.click();
        WebElement taskName=wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        taskName.click();
        //select time type
        WebElement timeType=wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[3]/UIAStaticText[1]"));
        timeType.click();
        WebElement timeTypeName = wd.findElement(By.xpath("//UIATableCell[2]/UIAStaticText[1]"));
        timeTypeName.click();
        //click on comment link
        WebElement commentLink = wd.findElement(By.xpath("//UIATableCell[4]/UIAStaticText[1]"));
        commentLink.click();
        WebElement comment=wd.findElements(By.className("UIATextView")).get(0);
        comment.sendKeys("comment");
        WebElement doneButton = wd.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]"));
        doneButton.click();
        //Add hours and save report
        WebElement hourButton = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[6]/UIATextField[1]/UIATextField[1]"));
        hourButton.click();
        WebElement minutes=wd.findElements(By.name("0. 1 of 25")).get(0);
        minutes.sendKeys("4");
        WebElement seconds=wd.findElements(By.name("0. 1 of 4")).get(0);
        seconds.sendKeys("50");
        //click on done buttons
        WebElement hourDoneButton = wd.findElement(By.xpath("//UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]"));
        hourDoneButton.click();
        NavigationDoneButton(wd);
        //click on week view
        WebElement weekView = wd.findElement(By.xpath("//UIASegmentedControl[1]/UIAButton[2]"));
        weekView .click();
        wd.findElement(By.name("NextArrow")).click();
        //click on month view
        WebElement monthView = wd.findElement(By.xpath("//UIASegmentedControl[1]/UIAButton[3]"));
        monthView.click();
        wd.findElement(By.name("CalendarHide.png")).click();
        wd.findElement(By.name("CalendarShow.png")).click();
        //click on today link
        WebElement todayLink = wd.findElement(By.xpath("//UIAToolbar[1]/UIAButton[1]"));
        todayLink.click();
        //click back to day view
        WebElement dayView = wd.findElement(By.xpath("//UIASegmentedControl[1]/UIAButton[1]"));
        dayView.click();

        Reporter.log("Time Report Added Successfully",true);
    }

    @Test (dependsOnMethods = {"AddTimeReport"})
    public void EditTimeReport()
    {
        //Script followed by AddTimeReport
        ImplicitlyWait(wd);
        WebElement clickonTimeReport = wd.findElement(By.name("man2176/, 1090/, 4,50"));
        clickonTimeReport.click();
        //click on comment link
        WebElement commentLink = wd.findElement(By.xpath("//UIATableCell[4]/UIAStaticText[1]"));
        commentLink.click();
        WebElement editComment=wd.findElements(By.className("UIATextView")).get(0);
        editComment.sendKeys("comment edited");
        NavigationDoneButton(wd);
        //Edit hours and save report
        WebElement editHour=wd.findElements(By.xpath("//UIATableCell[5]/UIATextField[1]/UIATextField[1]")).get(0);
        editHour.click();
        WebElement editMinutes=wd.findElements(By.name("4. 5 of 25")).get(0);
        editMinutes.sendKeys("7");
        WebElement editSeconds=wd.findElements(By.name("50. 3 of 4")).get(0);
        editSeconds.sendKeys("75");
        NavigationDoneButton(wd);
        WebElement saveEditedTimeReport = wd.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]"));
        saveEditedTimeReport.click();

        Reporter.log("Time Report Edited Successfully",true);
    }

    @Test (dependsOnMethods = {"EditTimeReport"})
    public void DeleteTimeReport()
    {
        //Depends on Add and Edit time report
        wd.findElement(By.name("man2176/, 1090/, 7,75")).click();
        //click on "Delete time report row" button
        wd.findElement(By.name("Contact project manager")).click();
        wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAActionSheet[1]/UIAButton[2]")).click();
        wd.findElement(By.name("Delete time report row")).click();
        AcceptAlert(wd);

        Reporter.log("Time Report Deleted Successfully",true);
    }

    @Test (dependsOnMethods = {"DeleteTimeReport"})
    public void AddEventTimeReport() throws InterruptedException
    {
        ImplicitlyWait(wd);
        WebElement clickonCalendar = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[2]"));
        clickonCalendar.click();
        wd.findElement(By.name("OK")).click();
        wd.findElement(By.name("Show all calendars")).click();
        wd.findElement(By.name("Done")).click();
        wd.findElement(By.name("New event, bang, 1,00")).click();
        WebElement selectCustomer = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        selectCustomer.click();
        WebElement selectTask=wd.findElement(By.name("Task"));
        selectTask.click();
        WebElement taskName = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        taskName.click();
        WebElement selectTimeType=wd.findElement(By.name("Timetype"));
        selectTimeType.click();
        WebElement timeTypeName = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
        timeTypeName.click();
        NavigationDoneButton(wd);
        wd.findElement(By.name("man2176/, 1090/, 1,00")).click();
        wd.findElement(By.name("Delete time report row")).click();
        wd.findElement(By.name("OK")).click();
        Thread.sleep(3000);

        Reporter.log("Event Time Report Added Successfully",true);
    }



    @Test (dependsOnMethods = {"AddEventTimeReport"})
    public void ValidateMissingHoursAndSubmit()
    {
        ImplicitlyWait(wd);
        WebElement selectMenu = wd.findElement(By.xpath("//UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]"));
        selectMenu.click();
        WebElement missingHours=wd.findElement(By.name("Missing hours"));
        ExplicitlyWait(wd, missingHours);
        WebElement selectWeek = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]"));
        selectWeek.click();
        WebElement submitButton=wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[3]"));
        ExplicitlyWait(wd, submitButton);
        WebElement submitWeek=wd.findElement(By.name("Submit part of week"));
        submitWeek.click();
        wd.findElement(By.name("Report inclusive")).click();
        WebElement selectDay = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]"));
        selectDay.click();
        wd.findElement(By.name("Submit time report")).click();
        if (ExpectedConditions.alertIsPresent() != null) {
            AcceptAlert(wd);
            wd.findElement(By.name("Cancel")).click();
        }

        Reporter.log("Validated Missing Hours successfully",true);
    }

    @Test (dependsOnMethods = {"ValidateMissingHoursAndSubmit"})
    public void ValidateContactStaff() throws InterruptedException
    {
        ImplicitlyWait(wd);
        WebElement selectMenuOption=wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[1]"));
        selectMenuOption.click();
        WebElement contactStaff=wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]"));
        contactStaff.click();
        WebElement searchUser = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableGroup[1]/UIASearchBar[1]/UIASearchBar[1]"));
        searchUser.sendKeys("Supriya User");
        wd.findElement(By.name("Search")).click();
        wd.findElement(By.name("Clear text")).click();
        wd.findElement(By.name("Search")).click();
        wd.findElement(By.name("Departments")).click();
        wd.findElement(By.name("All employees")).click();
        Thread.sleep(3000);
        WebElement selectContact = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]"));
        selectContact.click();
        wd.findElement(By.name("OK")).click();
        Thread.sleep(2000);
        wd.findElement(By.name("Back")).click();

        Reporter.log("Validated Contact Staff successfully",true);
    }

    @Test (dependsOnMethods = {"ValidateContactStaff"})
    public void Logout()
    {
        //Click on the bar button to select logout link
        WebElement selectMenuBar = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[1]"));
        selectMenuBar.click();
        wd.findElement(By.name("Logout")).click();
        AcceptAlert(wd);

        Reporter.log("Logout tested Successfully",true);
    }

    public void AddUserDetails(String name, String companyName, String address)
    {
        WebElement user=wd.findElements(By.className("UIATextField")).get(1);
        user.sendKeys(name);
        WebElement company=wd.findElements(By.className("UIATextField")).get(2);
        company.sendKeys(companyName);
        WebElement url=wd.findElements(By.className("UIATextField")).get(4);
        url.sendKeys(address);
    }

    public void AcceptAlert(WebDriver wd)
    {
        Alert alert=wd.switchTo().alert();
        alert.accept();
    }

    public void KeyboardDoneButton(WebDriver wd)
    {
        WebElement keyboardDoneButton = wd.findElement(By.name("Done"));
        keyboardDoneButton.click();
    }

    public void NavigationDoneButton(WebDriver wd)
    {
        WebElement navigationDoneButton = wd.findElement(By.name("Done"));
        navigationDoneButton.click();
    }

    public void ImplicitlyWait(WebDriver wd)
    {
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
    }

    public void ExplicitlyWait(WebDriver wd, WebElement ele)
    {
        WebDriverWait wait=new WebDriverWait(wd, 240);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        ele.click();
    }
}


