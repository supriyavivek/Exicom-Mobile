package se.copernicus.IOS;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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


public class IOSDeviceTest {
    WebDriver wd = null;

	/*
	Precondition for test1
	1) A Event should be added in current date ["New event", "bang", "1,00"]
	*/

    @BeforeClass
    public void setUp() throws InterruptedException, MalformedURLException {
        String appdirectory = System.getProperty("user.dir")+"/src/test/resources";
        File appDir = new File(appdirectory);
        File app = new File(appDir, "cTimeSheetDevice.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "IOS");
        capabilities.setCapability("deviceName", "iPhone");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("udid", "cd5000ab8a58c931a9f24d5e1f9ad41a8f25ca7b");
        wd = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        ImplicitlyWait(wd);

        Reporter.log("App launched", true);
        Thread.sleep(3000);
    }

    @AfterClass
    public void tearDown() {
        wd.quit();
    }

    @Test
    public void LoginFieldsValidation() throws InterruptedException {
        List<WebElement> clickUserLink = wd.findElements(By.className("UIAStaticText"));
        clickUserLink.get(1).click();
        Thread.sleep(3000);
        //Click on Add user link
        WebElement clickAddUserLink = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
      //  Assert.assertTrue(clickAddUserLink.isDisplayed(), "User link is displayed");
        clickAddUserLink.click();
        AddUserDetails("10", "1000", "192.168.1.109:7070");
        KeyboardDoneButton(wd);
        NavigationDoneButton(wd);

        Reporter.log("Login credentials entered successfully", true);
    }

    @Test(dependsOnMethods = {"LoginFieldsValidation"})
    public void InvalidLogin() {
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

        Reporter.log("Invalid Login tested Successfully", true);
    }

    @Test(dependsOnMethods = {"InvalidLogin"})
    public void DeleteUser() throws InterruptedException {
        //Script followed by LoginFieldsValidation() method
        ImplicitlyWait(wd);
        List<WebElement> clickUserLink = wd.findElements(By.className("UIAStaticText"));
        clickUserLink.get(0).click();
        Thread.sleep(3000);
        //Click on Add user link
        WebElement clickAddUserLink = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
        clickAddUserLink.click();
        AddUserDetails("ind14", "1000", "192.168.1.109:8080");
        KeyboardDoneButton(wd);
        NavigationDoneButton(wd);
        WebElement clickUserLinkWithName = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        clickUserLinkWithName.click();
        WebElement ClickSelectedUser = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIAButton[1]"));
        ClickSelectedUser.click();
        WebElement resetButton=wd.findElement(By.name("Reset"));
        //Assert.assertTrue(resetButton.isEnabled(), "Reset button is enabled");
        resetButton.click();
        AcceptAlert(wd);
        WebElement deleteUser=wd.findElement(By.name("Delete user"));
        //Assert.assertTrue(deleteUser.isDisplayed(), "Delete user button is displayed");
        deleteUser.click();
        AcceptAlert(wd);
        WebElement clickExistingUser = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        clickExistingUser.click();

        Reporter.log("Delete User tested Successfully", true);
    }

    @Test(dependsOnMethods = {"DeleteUser"})
    public void InvalidPassword() throws InterruptedException {
        //Script followed by LoginFieldsValidation() method
        ImplicitlyWait(wd);
        WebElement incorrectPassword = wd.findElement(By.className("UIASecureTextField"));
        incorrectPassword.sendKeys("WrongPassword");
        KeyboardDoneButton(wd);
        WebElement clickLogin = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[4]/UIAStaticText[1]"));
        //Assert.assertTrue(clickLogin.isDisplayed(), "Login button is displayed");
        clickLogin.click();
        Thread.sleep(3000);
        //Alert pop up for wrong password
        WebElement clickOK = wd.findElement(By.name("OK"));
       // Assert.assertFalse(clickOK.isDisplayed(), "OK button is not displayed");
        ExplicitlyWait(wd, clickOK);
    }

    @Test(dependsOnMethods = {"InvalidPassword"})
    public void Login() throws InterruptedException {
        ImplicitlyWait(wd);
        //Script followed by LoginFieldsValidation() method
        WebElement password = wd.findElements(By.className("UIASecureTextField")).get(0);
        password.sendKeys("password");
        KeyboardDoneButton(wd);
        //click on login button
        WebElement loginButton = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[4]/UIAStaticText[1]"));
        //Assert.assertFalse(loginButton.isDisplayed(), "Login button is not dislayed");
        loginButton.click();
        Thread.sleep(3000);

        Reporter.log("Login successfull", true);
    }

    @Test(dependsOnMethods = {"Login"})
    public void SearchCompany() throws InterruptedException {
        //Prerequisite login script should be executed
        ImplicitlyWait(wd);
        WebElement clickOnAddTimeReport = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]"));
        //Assert.assertFalse(clickOnAddTimeReport.isDisplayed(), "Add time report is not displayed");
        clickOnAddTimeReport.click();
        List<WebElement> custProjActivityLink = wd.findElements(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        custProjActivityLink.get(0).click();
        WebElement search=wd.findElement(By.name("Search"));
       // Assert.assertFalse(search.isEnabled(),"Search option is not enabled");
        search.click();
        Thread.sleep(3000);
        WebElement searchBar = wd.findElement(By.xpath("//UIATableView[1]/UIATableGroup[1]/UIASearchBar[1]"));
        searchBar.sendKeys("man2176");
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

        Reporter.log("Search script tested Successfully", true);
    }

    @Test(dependsOnMethods = {"SearchCompany"})
    public void AddTimeReport() {
        //Prerequisite login script should be executed
        ImplicitlyWait(wd);
        WebElement clickOnAddTimeReport = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]"));
       // Assert.assertFalse(clickOnAddTimeReport.isDisplayed(), "Add time report is not displayed");
        clickOnAddTimeReport.click();
        //Select the customer project and activity
        List<WebElement> custProjActivityLink = wd.findElements(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        custProjActivityLink.get(0).click();
        WebElement customerName = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        ExplicitlyWait(wd, customerName);
        //select task
        WebElement task = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
        task.click();
        WebElement taskName = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        taskName.click();
        //select time type
        WebElement timeType = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[3]/UIAStaticText[1]"));
        timeType.click();
        WebElement timeTypeName = wd.findElement(By.xpath("//UIATableCell[2]/UIAStaticText[1]"));
        timeTypeName.click();
        //click on comment link
        WebElement commentLink = wd.findElement(By.xpath("//UIATableCell[4]/UIAStaticText[1]"));
        commentLink.click();
        WebElement comment = wd.findElements(By.className("UIATextView")).get(0);
        comment.sendKeys("comment");
        WebElement doneButton = wd.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]"));
        doneButton.click();
        //Add hours and save report
        WebElement hourButton = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[6]/UIATextField[1]"));
        hourButton.click();
        WebElement minutes = wd.findElement(By.name("0. 1 of 25"));
        minutes.sendKeys("4");
        WebElement seconds = wd.findElement(By.name("0. 1 of 4"));
        seconds.sendKeys("50");
        //click on done buttons
        WebElement hourDoneButton = wd.findElement(By.xpath("//UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]"));
        hourDoneButton.click();
        NavigationDoneButton(wd);
        //click on week view
        WebElement weekView = wd.findElement(By.name("Week"));
        weekView.click();
        WebElement nextArrow = wd.findElement(By.xpath("//UIAWindow[1]/UIAButton[2]"));
        nextArrow.click();
        //click on month view
        WebElement monthView = wd.findElement(By.name("Month"));
        monthView.click();
        wd.findElement(By.name("CalendarHide.png")).click();
        wd.findElement(By.name("CalendarShow.png")).click();
        //click on today link
        WebElement todayLink = wd.findElement(By.xpath("//UIAToolbar[1]/UIAButton[1]"));
        todayLink.click();
        //click back to day view
        WebElement dayView = wd.findElement(By.xpath("//UIASegmentedControl[1]/UIAButton[1]"));
        dayView.click();

        Reporter.log("Time Report Added Successfully", true);
    }

    @Test(dependsOnMethods = {"AddTimeReport"})
    public void EditTimeReport() throws InterruptedException{
        //Script followed by AddTimeReport
        ImplicitlyWait(wd);
        WebElement clickonTimeReport = wd.findElement(By.name("man2176/, 1090/, 4,50"));
      //  Assert.assertFalse(clickonTimeReport.isDisplayed(), "Time report is not added");
        clickonTimeReport.click();
        //click on comment link
        WebElement commentLink = wd.findElement(By.xpath("//UIATableCell[4]/UIAStaticText[1]"));
        commentLink.click();
        WebElement editComment = wd.findElements(By.className("UIATextView")).get(0);
        //Assert.assertEquals("comment", editComment.getText());
        editComment.sendKeys("comment edited");
        NavigationDoneButton(wd);
        //Edit hours and save report
        WebElement editHourButton = wd.findElement(By.name("Hours"));
        editHourButton.click();
        WebElement editMinutes = wd.findElement(By.name("4. 5 of 25"));
        editMinutes.sendKeys("7");
        WebElement editSeconds = wd.findElement(By.name("50. 3 of 4"));
        editSeconds.sendKeys("75");
        WebElement clickOnDone = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]"));
        clickOnDone.click();
        Thread.sleep(3000);
        WebElement saveEditedTimeReport = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]"));
        saveEditedTimeReport.click();
        Thread.sleep(3000);

        Reporter.log("Time Report Edited Successfully", true);
    }

    @Test(dependsOnMethods = {"EditTimeReport"})
    public void DeleteTimeReport() {
        //Depends on Add and Edit time report
        WebElement clickOnTimeReport = wd.findElement(By.name("man2176/, 1090/, 7,75"));
       // Assert.assertFalse(clickOnTimeReport.isDisplayed(), "Time report is not edited");
        clickOnTimeReport.click();
        //click on "Delete time report row" button
        WebElement contactProjManager = wd.findElement(By.name("Contact project manager"));
        contactProjManager.click();
        WebElement clickOnCancel = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAActionSheet[1]/UIAButton[2]"));
        clickOnCancel.click();
        WebElement deleteTimeReport = wd.findElement(By.name("Delete time report row"));
        deleteTimeReport.click();
        AcceptAlert(wd);
       // Assert.assertFalse(clickOnTimeReport.isDisplayed(), "Time report is not deleted");

        Reporter.log("Time Report Deleted Successfully", true);
    }

    @Test(dependsOnMethods = {"DeleteTimeReport"})
    public void AddEventTimeReport() throws InterruptedException {
        ImplicitlyWait(wd);
        WebElement clickonCalendar = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[2]"));
        clickonCalendar.click();
        WebElement okClick=wd.findElement(By.name("OK"));
        if (okClick.isDisplayed())
        {
            okClick.click();
        }
        wd.findElement(By.name("Show all calendars")).click();
        wd.findElement(By.name("Done")).click();
        WebElement eventClick = wd.findElement(By.name("New event, Bang, 1,00"));
        //Assert.assertTrue(eventClick.isDisplayed(), "Calendar event is displayed");
        eventClick.click();
        WebElement customerName = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        ExplicitlyWait(wd, customerName);
        //select task
        WebElement task = wd.findElement(By.xpath("//UIATableCell[2]/UIAStaticText[1]"));
        task.click();
        WebElement taskName = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
        taskName.click();
        //select time type
        WebElement timeType = wd.findElement(By.xpath("//UIATableCell[3]/UIAStaticText[1]"));
        timeType.click();
        WebElement timeTypeName = wd.findElement(By.xpath("//UIATableCell[2]/UIAStaticText[1]"));
        timeTypeName.click();
        WebElement navigationDoneButton = wd.findElement(By.name("Done"));
        navigationDoneButton.click();
        Thread.sleep(3000);
        WebElement clickOnEventTimeReport = wd.findElement(By.name("man2176/, 1090/, 1,00"));
      //  Assert.assertFalse(clickOnEventTimeReport.isDisplayed(), "Calendar time report is not added");
        clickOnEventTimeReport.click();
        WebElement deleteEventTimeReport = wd.findElement(By.name("Delete time report row"));
        deleteEventTimeReport.click();
        Thread.sleep(3000);
        wd.findElement(By.name("OK")).click();
       // Assert.assertFalse(clickOnEventTimeReport.isDisplayed(), "Calendar time report is not deleted");

        Reporter.log("Event Time Report Added Successfully", true);
    }

    @Test(dependsOnMethods = {"AddEventTimeReport"})
    public void ValidateMissingHoursAndSubmit() throws InterruptedException {
        ImplicitlyWait(wd);
        WebElement selectMenu = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]"));
        //Assert.assertFalse(selectMenu.isDisplayed(), "Menu option is not displayed");
        selectMenu.click();
        WebElement missingHours = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
        //Assert.assertFalse(missingHours.isEnabled(), "Missing hours is not clickable");
        missingHours.click();
        WebElement selectWeek = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]"));
        selectWeek.click();
        WebElement submitButton = wd.findElement(By.name("Submit"));
        ExplicitlyWait(wd, submitButton);
        WebElement submitWeek = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIASwitch[1]"));
        submitWeek.click();
        WebElement reportInclusive = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[2]/UIAStaticText[1]"));
        reportInclusive.click();
        WebElement selectDay = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]"));
        selectDay.click();
        WebElement submitTimeReport = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[5]/UIAStaticText[1]"));
       // Assert.assertTrue(submitTimeReport.isEnabled(), "Submit button is enabled");
        submitTimeReport.click();
        Thread.sleep(3000);

        Reporter.log("Validated Missing Hours successfully", true);
    }

    @Test(dependsOnMethods = {"ValidateMissingHoursAndSubmit"})
    public void ValidateContactStaff() throws InterruptedException {
        ImplicitlyWait(wd);
        WebElement selectMenuOption = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[1]"));
       // Assert.assertTrue(selectMenuOption.isDisplayed(), "Menu option is displayed");
        ExplicitlyWait(wd, selectMenuOption);
        WebElement contactStaff = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]"));
       // Assert.assertTrue(contactStaff.isDisplayed(), "Contact Staff is clickable");
        contactStaff.click();
        WebElement searchUser = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableGroup[1]/UIASearchBar[1]"));
        searchUser.sendKeys("G Null");
       // Assert.assertEquals("G Null", searchUser.getText());
        wd.findElement(By.name("Search")).click();
        wd.findElement(By.name("Clear text")).click();
        wd.findElement(By.name("Search")).click();
        wd.findElement(By.name("Departments")).click();
        wd.findElement(By.name("All employees")).click();
        Thread.sleep(3000);
        WebElement selectContact = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]"));
      //  Assert.assertTrue(selectContact.isDisplayed(),"Contact option is displayed");
        selectContact.click();
        WebElement backClick = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[1]"));
       // Assert.assertTrue(backClick.isDisplayed(),"Back button is displayed");
        backClick.click();


        Reporter.log("Validated Contact Staff successfully", true);
    }

    @Test(dependsOnMethods = {"ValidateContactStaff"})
    public void Logout() {
        //Click on the bar button to select logout link
        WebElement selectMenuBar = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[1]"));
        //Assert.assertFalse(selectMenuBar.isDisplayed(),"Select menu bar is not displayed");
        selectMenuBar.click();
        WebElement clickOnLogout = wd.findElement(By.name("Logout"));
       // Assert.assertFalse(clickOnLogout.isDisplayed(),"Logout button is not displayed");
        clickOnLogout.click();
        AcceptAlert(wd);

        Reporter.log("Logout tested Successfully", true);
    }

    public void AddUserDetails(String name, String companyName, String address) {
        WebElement user = wd.findElement(By.className("UIATextField"));
       // Assert.assertFalse(user.isDisplayed(),"Name field is not displayed");
        user.sendKeys(name);
        WebElement company = wd.findElements(By.className("UIATextField")).get(1);
        company.sendKeys(companyName);
        WebElement url = wd.findElements(By.className("UIATextField")).get(2);
        url.sendKeys(address);
    }

    public void AcceptAlert(WebDriver wd) {
        Alert alert = wd.switchTo().alert();
        alert.accept();
    }

    public void KeyboardDoneButton(WebDriver wd) {
        WebElement keyboardDoneButton = wd.findElement(By.xpath("//UIAWindow[2]/UIAToolbar[1]/UIAButton[2]"));
       // Assert.assertFalse(keyboardDoneButton.isDisplayed(),"Done option is not displayed");
        keyboardDoneButton.click();
    }

    public void NavigationDoneButton(WebDriver wd) {
        WebElement navigationDoneButton = wd.findElement(By.name("Done"));
       // Assert.assertFalse(navigationDoneButton.isDisplayed(),"Navigation done option is not displayed");
        navigationDoneButton.click();
    }

    public void ImplicitlyWait(WebDriver wd) {
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
    }

    public void ExplicitlyWait(WebDriver wd, WebElement ele) {
        WebDriverWait wait = new WebDriverWait(wd, 240);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        ele.click();

    }
}