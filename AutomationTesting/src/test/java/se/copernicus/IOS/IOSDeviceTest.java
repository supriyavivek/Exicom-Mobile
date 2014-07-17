package se.copernicus.IOS;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class IOSDeviceTest {
    WebDriver wd = null;

	/*
	Precondition for test1
	1) A Event should be added in current date ["New event", "bang", "1,00"]
	*/

    @BeforeMethod
    public void setUp() {
        try {
            Reporter.log("App launched", true);
//            File appDir = new File("/Users/Shared/Jenkins");
            String appdirectory = System.getProperty("user.dir")+"/src/test/resources";
            System.out.println(appdirectory);
            File appDir = new File(appdirectory);
            System.out.println(appDir);
            File app = new File(appDir, "cTimeSheetDevice.app");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "ios");
            capabilities.setCapability("deviceName", "iPhone");
            capabilities.setCapability("app", app.getAbsolutePath());
            capabilities.setCapability("udid", "b2784fc98bd0ecc5764f3b14b4c1bdc1f10daa28");
            wd = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            ImplicitlyWait(wd);

            Reporter.log("App launched", true);
            Thread.sleep(3000);

        } catch (Exception e) {
            FailureMessage();
        }
    }

    @AfterMethod
    public void tearDown() {
        // wd.quit();
    }

    /*
	Precondition for test2
	1) Goto settings by pressing "windows+shift+h" and clicking on settings app
	2) Find cTimeSheet and click on it.
	3) On "Private comment", "Adjusted hours", and "Price deviation".
	4) Change "Time factor" value to 5 by clicking on it.
	5) Off "Show number".
	6) Click on "calendar search" and select Location.
	@Test
	public void TestScript2() {
	//Test case to login and verify fields
	LoginValidation();
	Login();

	//Test case to Add time report with cTimeSheet settings
	AddTimeReportWithSettings();

	//Test case to Edit time report
	EditTimeReportWithSettings();

	//Test case to Delete time report
	DeleteTimeReportWithSettings();

	//Test case to Logout time report
	Logout();
	}
	*/
    @Test
    public void LoginFieldsValidation() {
        try {
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
        } catch (Exception e) {
            FailureMessage();
        }

    }

    @Test(dependsOnMethods = {"LoginFieldsValidation"})
    public void InvalidLogin() {
        try {
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
        } catch (Exception e) {
            FailureMessage();
        }
    }

    @Test(dependsOnMethods = {"InvalidLogin"})
    public void DeleteUser() {
        try {
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
            wd.findElement(By.name("Reset")).click();
            AcceptAlert(wd);
            wd.findElement(By.name("Delete user")).click();
            AcceptAlert(wd);
            WebElement clickExistingUser = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
            clickExistingUser.click();

            Reporter.log("Delete User tested Successfully", true);
        } catch (Exception e) {
            FailureMessage();
        }
    }

    @Test(dependsOnMethods = {"DeleteUser"})
    public void InvalidPassword() {
        try {
            //Script followed by LoginFieldsValidation() method
            ImplicitlyWait(wd);
            WebElement incorrectPassword = wd.findElement(By.className("UIASecureTextField"));
            incorrectPassword.sendKeys("WrongPassword");
            KeyboardDoneButton(wd);
            WebElement clickLogin = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[4]/UIAStaticText[1]"));
            clickLogin.click();
            Thread.sleep(3000);
            //Alert pop up for wrong password
            WebElement clickOK = wd.findElement(By.name("OK"));
            ExplicitlyWait(wd, clickOK);
            Reporter.log("Invalid Password tested Successfully", true);
        } catch (Exception e) {
            FailureMessage();
        }
    }

    @Test(dependsOnMethods = {"InvalidPassword"})
    public void Login() {
        try {
            ImplicitlyWait(wd);
            //Script followed by LoginFieldsValidation() method
            WebElement password = wd.findElements(By.className("UIASecureTextField")).get(0);
            password.sendKeys("password");
            KeyboardDoneButton(wd);
            //click on login button
            WebElement loginButton = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[4]/UIAStaticText[1]"));
            Assert.assertTrue(loginButton.isDisplayed(), "Login");
            loginButton.click();
            Thread.sleep(3000);

            Reporter.log("Login successfull", true);
        } catch (Exception e) {
            FailureMessage();
        }
    }

    @Test(dependsOnMethods = {"Login"})
    public void SearchCompany() {
        try {
            //Prerequisite login script should be executed
            ImplicitlyWait(wd);
            WebElement clickOnAddTimeReport = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]"));
            clickOnAddTimeReport.click();
            List<WebElement> custProjActivityLink = wd.findElements(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
            custProjActivityLink.get(0).click();
            wd.findElement(By.name("Search")).click();
            Thread.sleep(3000);
            try {
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
            } catch (Exception e) {
                System.out.println(e);
                wd.findElement(By.name("Customers")).click();
                wd.findElement(By.name("Cancel")).click();
                WebElement cancelButton = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]"));
                cancelButton.click();
            }

            Reporter.log("Search script tested Successfully", true);
        } catch (Exception e) {
            FailureMessage();
        }
    }

    @Test(dependsOnMethods = {"SearchCompany"})
    public void AddTimeReport() {
        try {
            //Prerequisite login script should be executed
            ImplicitlyWait(wd);
            WebElement clickOnAddTimeReport = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]"));
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
        } catch (Exception e) {
            FailureMessage();
        }
    }

    @Test(dependsOnMethods = {"AddTimeReport"})
    public void EditTimeReport() {
        try {
            //Script followed by AddTimeReport
            ImplicitlyWait(wd);
            WebElement clickonTimeReport = wd.findElement(By.name("man2176/, 1090/, 4,50"));
            clickonTimeReport.click();
            //click on comment link
            WebElement commentLink = wd.findElement(By.xpath("//UIATableCell[4]/UIAStaticText[1]"));
            commentLink.click();
            WebElement editComment = wd.findElements(By.className("UIATextView")).get(0);
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
        } catch (Exception e) {
            FailureMessage();
        }
    }

    @Test(dependsOnMethods = {"EditTimeReport"})
    public void DeleteTimeReport() {
        try {
            //Depends on Add and Edit time report
            WebElement clickOnTimeReport = wd.findElement(By.name("man2176/, 1090/, 7,75"));
            clickOnTimeReport.click();
            //click on "Delete time report row" button
            WebElement contactProjManager = wd.findElement(By.name("Contact project manager"));
            contactProjManager.click();
            WebElement clickOnCancel = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAActionSheet[1]/UIAButton[2]"));
            clickOnCancel.click();
            WebElement deleteTimeReport = wd.findElement(By.name("Delete time report row"));
            deleteTimeReport.click();
            AcceptAlert(wd);

            Reporter.log("Time Report Deleted Successfully", true);
        } catch (Exception e) {
            FailureMessage();
        }
    }

    @Test(dependsOnMethods = {"DeleteTimeReport"})
    public void AddEventTimeReport() {
        try {
            ImplicitlyWait(wd);
            WebElement clickonCalendar = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[2]"));
            clickonCalendar.click();
            wd.findElement(By.name("Show all calendars")).click();
            wd.findElement(By.name("Done")).click();
            WebElement eventClick = wd.findElement(By.name("New event, Bang, 1,00"));
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
            clickOnEventTimeReport.click();
            WebElement deleteEventTimeReport = wd.findElement(By.name("Delete time report row"));
            deleteEventTimeReport.click();
            Thread.sleep(3000);
            wd.findElement(By.name("OK")).click();

            Reporter.log("Event Time Report Added Successfully", true);
        } catch (Exception e) {
            FailureMessage();
        }
    }

    @Test(dependsOnMethods = {"AddEventTimeReport"})
    public void ValidateMissingHoursAndSubmit() {
        try {
            ImplicitlyWait(wd);
            WebElement selectMenu = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]"));
            selectMenu.click();
            WebElement missingHours = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
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
            submitTimeReport.click();
            Thread.sleep(3000);

            Reporter.log("Validated Missing Hours successfully", true);
        } catch (Exception e) {
            System.out.println(e);
            FailureMessage();

        }
    }

    @Test(dependsOnMethods = {"ValidateMissingHoursAndSubmit"})
    public void ValidateContactStaff() {
        try {
            ImplicitlyWait(wd);
            WebElement selectMenuOption = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[1]"));
            ExplicitlyWait(wd, selectMenuOption);
            WebElement contactStaff = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]"));
            contactStaff.click();
            WebElement searchUser = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableGroup[1]/UIASearchBar[1]"));
            searchUser.sendKeys("G Null");
            wd.findElement(By.name("Search")).click();
            wd.findElement(By.name("Clear text")).click();
            wd.findElement(By.name("Search")).click();
            wd.findElement(By.name("Departments")).click();
            wd.findElement(By.name("All employees")).click();
            Thread.sleep(3000);
            WebElement selectContact = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]"));
            selectContact.click();
            WebElement backClick = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[1]"));
            backClick.click();


            Reporter.log("Validated Contact Staff successfully", true);
        } catch (Exception e) {
            FailureMessage();
        }
    }

    @Test(dependsOnMethods = {"ValidateContactStaff"})
    public void Logout() {
        try {
            //Click on the bar button to select logout link
            WebElement selectMenuBar = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[1]"));
            selectMenuBar.click();
            WebElement clickOnLogout = wd.findElement(By.name("Logout"));
            clickOnLogout.click();
            AcceptAlert(wd);

            Reporter.log("Logout tested Successfully", true);
        } catch (Exception e) {
            FailureMessage();
        }
    }

    public void AddUserDetails(String name, String companyName, String address) {
        WebElement user = wd.findElement(By.className("UIATextField"));
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

    public void FailureMessage() {
        Reporter.log("Test Case Failed");
    }

    public void KeyboardDoneButton(WebDriver wd) {
        WebElement keyboardDoneButton = wd.findElement(By.xpath("//UIAWindow[2]/UIAToolbar[1]/UIAButton[2]"));
        keyboardDoneButton.click();
    }

    public void NavigationDoneButton(WebDriver wd) {
        WebElement navigationDoneButton = wd.findElement(By.name("Done"));
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