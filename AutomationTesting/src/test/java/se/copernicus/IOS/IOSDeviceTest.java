package se.copernicus.IOS;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
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

public class IOSDeviceTest
{
        WebDriver wd = null;

	/*
	Precondition for test1
	1) A Event should be added in current date ["New event", "bang", "1,00"]
	*/

        @BeforeMethod
        public void setUp() {
            try {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(CapabilityType.VERSION, "7.1");
                capabilities.setCapability(CapabilityType.PLATFORM, "iOS");
                capabilities.setCapability("app", "./cTimeSheetDevice.app");
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

        @Test
        public void AutomationTestScripts() {
            //Test case to login
            LoginFieldsValidation();
            InvalidLogin();
            DeleteUser();
            InvalidPassword();
            Login();

            //Test case to test search functionality
            SearchCompany();

            //Test case to Add time report
            AddTimeReport();

            //Test case to Edit time report
            EditTimeReport();

//            //Test case to Delete time report
//            DeleteTimeReport();
//
//            //Test case to add event time report
//            AddEventTimeReport();
//
//            //Test case to check missing hours and submit
//            ValidateMissingHoursAndSubmit();
//
//            //Test case to check contact staff details
//            ValidateContactStaff();
//
//            //Test case to Logout time report
//            Logout();
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

        public void LoginFieldsValidation() {
            try {
                List<WebElement> clickUserLink = wd.findElements(By.className("UIAStaticText"));
                clickUserLink.get(1).click();
                Thread.sleep(3000);
                //Click on Add user link
                WebElement clickAddUserLink = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
                clickAddUserLink.click();
                AddUserDetails("10","1000","192.168.1.109:7070");
                KeyboardDoneButton(wd);
                NavigationDoneButton(wd);

                Reporter.log("Login credentials entered successfully", true);
            } catch (Exception e) {
                FailureMessage();
            }

        }

        public void InvalidLogin() {
            try {
                //Script followed by LoginFieldsValidation() method
                ImplicitlyWait(wd);
                WebElement clickUserName = wd.findElements(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).get(0);
                clickUserName.click();
                //Add duplicate user
                WebElement clickonAddUser = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
                clickonAddUser.click();
                AddUserDetails("10","1000","192.168.1.109:7070");
                KeyboardDoneButton(wd);
                NavigationDoneButton(wd);
                AcceptAlert(wd);

                Reporter.log("Invalid Login tested Successfully", true);
            } catch (Exception e) {
                FailureMessage();
            }
        }

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
                AddUserDetails("ind14","1000","192.168.1.109:8080");
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

                Reporter.log("Delete User tested Successfully",true);
            } catch (Exception e) {
                FailureMessage();
            }
        }

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

                if (ExpectedConditions.alertIsPresent() != null) {
                    AcceptAlert(wd);
                }

                Reporter.log("Invalid Password tested Successfully",true);
            } catch (Exception e) {
                FailureMessage();
            }
        }

        public void Login() {
            try {
                //Script followed by LoginFieldsValidation() method
                WebElement password = wd.findElements(By.className("UIASecureTextField")).get(0);
                password.sendKeys("password");
                KeyboardDoneButton(wd);
                //click on login button
                WebElement loginButton = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[4]/UIAStaticText[1]"));
                Assert.assertTrue(loginButton.isDisplayed(), "Login");
                loginButton.click();

                Reporter.log("Login successfull",true);
            } catch (Exception e) {
                FailureMessage();
            }
        }

        public void SearchCompany() {
            try {
                //Prerequisite login script should be executed
                ImplicitlyWait(wd);
                WebElement clickOnAddTimeReport = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]"));
                clickOnAddTimeReport.click();
                List<WebElement> custProjActivityLink = wd.findElements(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
                custProjActivityLink.get(0).click();
                wd.findElement(By.name("Search")).click();
                WebElement searchBar = wd.findElement(By.className("UIASearchBar"));
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
            } catch (Exception e) {
                FailureMessage();
            }
        }

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
                WebElement nextArrow=wd.findElement(By.xpath("//UIAWindow[1]/UIAButton[2]"));
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

                Reporter.log("Time Report Added Successfully",true);
            } catch (Exception e) {
                FailureMessage();
            }
        }

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
                NavigationDoneButton(wd);
                WebElement saveEditedTimeReport = wd.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]"));
                saveEditedTimeReport.click();

                Reporter.log("Time Report Edited Successfully",true);
            } catch (Exception e) {
                FailureMessage();
            }
        }

//        public void DeleteTimeReport() {
//            try {
//                //Depends on Add and Edit time report
//                wd.findElement(By.name("man2176/, 1090/, 7,75")).click();
//                //click on "Delete time report row" button
//                wd.findElement(By.name("Contact project manager")).click();
//                wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAActionSheet[1]/UIAButton[2]")).click();
//                wd.findElement(By.name("Delete time report row")).click();
//                AcceptAlert(wd);
//
//                Reporter.log("Time Report Deleted Successfully",true);
//            } catch (Exception e) {
//                FailureMessage();
//            }
//        }
//
//        public void AddEventTimeReport() {
//            try {
//                ImplicitlyWait(wd);
//                WebElement clickonCalendar = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[2]"));
//                clickonCalendar.click();
//                wd.findElement(By.name("OK")).click();
//                wd.findElement(By.name("Show all calendars")).click();
//                wd.findElement(By.name("Done")).click();
//                wd.findElement(By.name("New event, bang, 1,00")).click();
//                WebElement selectCustomer = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
//                selectCustomer.click();
//                WebElement selectTask = wd.findElement(By.name("Task"));
//                selectTask.click();
//                WebElement taskName = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
//                taskName.click();
//                WebElement selectTimeType = wd.findElement(By.name("Timetype"));
//                selectTimeType.click();
//                WebElement timeTypeName = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
//                timeTypeName.click();
//                NavigationDoneButton(wd);
//                wd.findElement(By.name("man2176/, 1090/, 1,00")).click();
//                wd.findElement(By.name("Delete time report row")).click();
//                wd.findElement(By.name("OK")).click();
//                Thread.sleep(3000);
//
//                Reporter.log("Event Time Report Added Successfully",true);
//            } catch (Exception e) {
//                FailureMessage();
//            }
//        }
//
//        public void ValidateMissingHoursAndSubmit() {
//            try {
//                ImplicitlyWait(wd);
//                WebElement selectMenu = wd.findElement(By.xpath("//UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]"));
//                selectMenu.click();
//                WebElement missingHours = wd.findElement(By.name("Missing hours"));
//                ExplicitlyWait(wd, missingHours);
//                WebElement selectWeek = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]"));
//                selectWeek.click();
//                WebElement submitButton = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[3]"));
//                ExplicitlyWait(wd, submitButton);
//                WebElement submitWeek = wd.findElement(By.name("Submit part of week"));
//                submitWeek.click();
//                wd.findElement(By.name("Report inclusive")).click();
//                WebElement selectDay = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]"));
//                selectDay.click();
//                wd.findElement(By.name("Submit time report")).click();
//                if (ExpectedConditions.alertIsPresent() != null) {
//                    AcceptAlert(wd);
//                    wd.findElement(By.name("Cancel")).click();
//                }
//
//                Reporter.log("Validated Missing Hours successfully",true);
//            } catch (Exception e) {
//                FailureMessage();
//
//            }
//        }
//
//        public void ValidateContactStaff() {
//            try {
//                ImplicitlyWait(wd);
//                WebElement selectMenuOption = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[1]"));
//                selectMenuOption.click();
//                WebElement contactStaff = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]"));
//                contactStaff.click();
//                WebElement searchUser = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableGroup[1]/UIASearchBar[1]/UIASearchBar[1]"));
//                searchUser.sendKeys("Supriya User");
//                wd.findElement(By.name("Search")).click();
//                wd.findElement(By.name("Clear text")).click();
//                wd.findElement(By.name("Search")).click();
//                wd.findElement(By.name("Departments")).click();
//                wd.findElement(By.name("All employees")).click();
//                Thread.sleep(3000);
//                WebElement selectContact = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]"));
//                selectContact.click();
//                wd.findElement(By.name("OK")).click();
//                Thread.sleep(2000);
//                wd.findElement(By.name("Back")).click();
//
//                Reporter.log("Validated Contact Staff successfully",true);
//            } catch (Exception e) {
//                FailureMessage();
//            }
//        }
//
//        public void Logout() {
//            try {
//                //Click on the bar button to select logout link
//                WebElement selectMenuBar = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[1]"));
//                selectMenuBar.click();
//                wd.findElement(By.name("Logout")).click();
//                AcceptAlert(wd);
//
//                Reporter.log("Logout tested Successfully",true);
//            } catch (Exception e) {
//                FailureMessage();
//            }
//        }

        public void AddUserDetails(String name, String companyName, String address)
        {
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
            System.out.println("Failure");
            getScreenShot(wd, "/Users/indpro/Documents/CopernicusMobileWorkspace/AutomationTesting/target");
        }

        public void getScreenShot(WebDriver wd, String imgPath) {
            EventFiringWebDriver efDriver = new EventFiringWebDriver(wd);
            File srcFile = efDriver.getScreenshotAs(OutputType.FILE);
            File destFile = new File(imgPath);
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