package se.copernicus.IOS;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class IOS_Simulator_Test
{
	WebDriver wd=null;
    	 
	/*
	Precondition for test1
	1) A Event should be added in current date ["New event", "bang", "1,00"]
	*/

    @BeforeMethod
	public void setUp() 
	{
	try {
		System.out.println("Launching App");
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
			capabilities.setCapability(CapabilityType.VERSION, "7.1");
			capabilities.setCapability(CapabilityType.PLATFORM, "iOS");
			capabilities.setCapability("app", "./cTimeSheetSimulator.app");
			capabilities.setCapability("udid", "80644d3a823bb12fe012ea3e54223e00feef8097");
			capabilities.setCapability("bundleid", "com.example.appiumiphonetest");
			wd= new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
			
		System.out.println("App launched");
		Thread.sleep(3000);
		
		} catch(Exception e) {
			System.out.println("App launch failed");
		}
	}
	
	@AfterMethod
	public void tearDown() 
	{	
		//wd.quit();
	}

	@Test
	public void TestScript1() {
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
			
		//Test case to Delete time report
		DeleteTimeReport();	
		
		//Test case to add event time report
		AddEventTimeReport();
		
		//Test case to check missing hours and submit
		ValidateMissingHoursAndSubmit();
		
		//Test case to check contact staff details
		ValidateContactStaff();
		
		//Test case to Logout time report
		Logout();
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

    public void LoginFieldsValidation()
	{
		try {
			Reporter.log("Login script");
			
				List<WebElement> clickUserLink = wd.findElements(By.className("UIAStaticText"));
				clickUserLink.get(1).click();
				Thread.sleep(3000);
				//Click on Add user link
				WebElement clickAddUserLink = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
				clickAddUserLink.click();
				WebElement user=wd.findElements(By.className("UIATextField")).get(1);
				user.sendKeys("10");
				WebElement company=wd.findElements(By.className("UIATextField")).get(2);
				company.sendKeys("1000");
				WebElement url=wd.findElements(By.className("UIATextField")).get(4);
				url.sendKeys("192.168.1.109:7070");
				List<WebElement> keyboardDoneButton = wd.findElements(By.name("Done"));
				keyboardDoneButton.get(1).click();
				List<WebElement> navigationDoneButton = wd.findElements(By.name("Done"));
				navigationDoneButton.get(0).click();

            Reporter.log("Login successfull");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
		}
			
	}
	
	public void InvalidLogin()
	{
		try {
            Reporter.log("Invalid Login script");
				
				//Script followed by LoginFieldsValidation() method
				wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
				WebElement clickUserName = wd.findElements(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).get(0);
				clickUserName.click();
				//Add duplicate user
				WebElement clickonAddUser = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
				clickonAddUser.click();
				WebElement user=wd.findElements(By.className("UIATextField")).get(1);
				user.sendKeys("10");
				WebElement company=wd.findElements(By.className("UIATextField")).get(2);
				company.sendKeys("1000");
				WebElement url=wd.findElements(By.className("UIATextField")).get(4);
				url.sendKeys("192.168.1.109:7070");
				List<WebElement> keyDoneButton = wd.findElements(By.name("Done"));
				keyDoneButton.get(1).click();
				List<WebElement> navigationDoneButton = wd.findElements(By.name("Done"));
				navigationDoneButton.get(0).click();
                AcceptAlert(wd);

            Reporter.log("Invalid Login tested Successfully");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void DeleteUser()
	{
		try {
            Reporter.log("Delete User script");
			
				//Script followed by LoginFieldsValidation() method
				wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
				List<WebElement> clickUserLink = wd.findElements(By.className("UIAStaticText"));
				clickUserLink.get(0).click();
				Thread.sleep(3000);
				//Click on Add user link
				WebElement clickAddUserLink=wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
				clickAddUserLink.click();
				WebElement user=wd.findElements(By.className("UIATextField")).get(1);
				user.sendKeys("10");
				WebElement company=wd.findElements(By.className("UIATextField")).get(2);
				company.sendKeys("1000");
				WebElement url=wd.findElements(By.className("UIATextField")).get(4);
				url.sendKeys("192.168.1.109:8080");
				List<WebElement> keyDoneButton = wd.findElements(By.name("Done"));
				keyDoneButton.get(1).click();
				List<WebElement> navigationDoneButton = wd.findElements(By.name("Done"));
				navigationDoneButton.get(0).click();
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

            Reporter.log("Delete User tested Successfully");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
		
	public void InvalidPassword()
	{
		try {
            Reporter.log("Invalid Password script");
			
				//Script followed by LoginFieldsValidation() method
				wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
				WebElement incorrectPassword=wd.findElements(By.className("UIASecureTextField")).get(0);
				incorrectPassword.sendKeys("WrongPassword");
				List<WebElement> keyDoneButton = wd.findElements(By.name("Done"));
				keyDoneButton.get(0).click();
				WebElement clickLogin = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[4]/UIAStaticText[1]"));
				clickLogin.click();
				//Alert pop up for wrong password
				WebDriverWait wait=new WebDriverWait(wd, 240);
				wait.until(ExpectedConditions.alertIsPresent());
                AcceptAlert(wd);

            Reporter.log("Invalid Password tested Successfully");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void Login()
	{
		try {
            Reporter.log("Login By entering correct password");
			
				//Script followed by LoginFieldsValidation() method
				WebElement password=wd.findElements(By.className("UIASecureTextField")).get(0);
				password.sendKeys("password");
				WebElement keyboardDoneButton = wd.findElement(By.name("Done"));
				keyboardDoneButton.click();
				//click on login button
				WebElement loginButton = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[4]/UIAStaticText[1]"));
				Assert.assertTrue(loginButton.isDisplayed(), "Login");
				loginButton.click();

            Reporter.log("Login successfull");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void SearchCompany() 
	{
		try {
            Reporter.log("Search script");
			
				//Prerequisite login script should be executed
				wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
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

            Reporter.log("Search script tested Successfully");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void AddTimeReport() 
	{
		try {
            Reporter.log("Add Time Report script");
			
				//Prerequisite login script should be executed
				wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
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
				List<WebElement> navigationDoneButton = wd.findElements(By.name("Done"));
				navigationDoneButton.get(0).click();
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

            Reporter.log("Time Report Added Successfully");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void EditTimeReport() 
	{
		try {
            Reporter.log("Edit Time Report script");
				
				//Script followed by AddTimeReport
				wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
				WebElement clickonTimeReport = wd.findElement(By.name("man2176/, 1090/, 4,50"));
				clickonTimeReport.click();
				//click on comment link
				WebElement commentLink = wd.findElement(By.xpath("//UIATableCell[4]/UIAStaticText[1]"));
				commentLink.click();
				WebElement editComment=wd.findElements(By.className("UIATextView")).get(0);
				editComment.sendKeys("comment edited");
				WebElement clickDone = wd.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]"));
				clickDone.click();
				//Edit hours and save report
				WebElement editHour=wd.findElements(By.xpath("//UIATableCell[5]/UIATextField[1]/UIATextField[1]")).get(0);
				editHour.click();
				WebElement editMinutes=wd.findElements(By.name("4. 5 of 25")).get(0);
				editMinutes.sendKeys("7");
				WebElement editSeconds=wd.findElements(By.name("50. 3 of 4")).get(0);
				editSeconds.sendKeys("75");
				WebElement clickonDone = wd.findElement(By.xpath("//UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]"));
				clickonDone.click();
				WebElement saveEditedTimeReport = wd.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]"));
				saveEditedTimeReport.click();

            Reporter.log("Time Report Edited Successfully");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void DeleteTimeReport()
	{
		try {
            Reporter.log("Delete Time Report script");
			
				//Depends on Add and Edit time report
				wd.findElement(By.name("man2176/, 1090/, 7,75")).click();
				//click on "Delete time report row" button
				wd.findElement(By.name("Contact project manager")).click();
                wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]/UIAActionSheet[1]/UIAButton[2]")).click();
				wd.findElement(By.name("Delete time report row")).click();
                AcceptAlert(wd);

            Reporter.log("Time Report Deleted Successfully");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void AddEventTimeReport() 
	{
		try {
            Reporter.log("Add Event Time Report script");
			
				WebElement clickomCalendar = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAToolbar[1]/UIAButton[2]"));
				clickomCalendar.click();
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
				List<WebElement> navigationDoneButton = wd.findElements(By.name("Done"));
				navigationDoneButton.get(0).click();
				wd.findElement(By.name("man2176/, 1090/, 1,00")).click();
				wd.findElement(By.name("Delete time report row")).click();
				wd.findElement(By.name("OK")).click();

            Reporter.log("Event Time Report Added Successfully");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/* public void AddTimeReportWithSettings() {
		try {
            Reporter.log("Add Time Report script");
			
				//click on '+' symbol
				wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
				wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
				List<WebElement> custProjActivityLink = wd.findElements(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
				custProjActivityLink.get(0).click();
				//search();
				//Select the first customer
				WebElement customerNameWithoutNo = wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[3]/UIAStaticText[1]"));
				customerNameWithoutNo.click();
				WebElement task=wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));
				task.click();
				//Select the first task
				wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
				WebElement timeType=wd.findElement(By.xpath("//UIATableView[1]/UIATableCell[3]/UIAStaticText[1]"));
				timeType.click();
				//Select the first time type
				wd.findElement(By.xpath("//UIATableCell[2]/UIAStaticText[1]")).click();
				//click on comment link
				wd.findElement(By.xpath("//UIATableCell[4]/UIAStaticText[1]")).click();
				WebElement comment=wd.findElements(By.className("UIATextView")).get(0);
				comment.sendKeys("comment");
				wd.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]")).click();
				//click on private link
				wd.findElement(By.xpath("//UIATableCell[5]/UIAStaticText[1]")).click();
				WebElement privateComment=wd.findElements(By.className("UIATextView")).get(0);
				privateComment.sendKeys("private comment");
				wd.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]")).click();
				//click on adjusted hours
				wd.findElement(By.xpath("//UIATableCell[6]/UIATextField[1]/UIATextField[1]")).click();
				WebElement minutes=wd.findElements(By.name("0. 1 of 25")).get(0);
				minutes.sendKeys("4");
				WebElement seconds=wd.findElements(By.name("0. 1 of 20")).get(0);
				seconds.sendKeys("50");
				//click on done buttons
				wd.findElement(By.xpath("//UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]")).click();
				//click on Price deviation
				WebElement priceDeviation = wd.findElement(By.xpath("//UIATableCell[7]/UIATextField[1]/UIATextField[1]"));
				priceDeviation.sendKeys("2.50");
				wd.findElement(By.name("Return")).click();
				wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[9]/UIATextField[1]/UIATextField[1]")).click();
				WebElement pickerWheelMinutes=wd.findElements(By.name("0. 1 of 25")).get(0);
				pickerWheelMinutes.sendKeys("8");
				WebElement pickerWheelseconds=wd.findElements(By.name("0. 1 of 20")).get(0);
				pickerWheelseconds.sendKeys("50");
				//click on done buttons
				wd.findElement(By.xpath("//UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]")).click();
				List<WebElement> navigationDoneButton = wd.findElements(By.name("Done"));
				navigationDoneButton.get(0).click();

            Reporter.log("Time Report With App Settings Added Successfully");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void EditTimeReportWithSettings() 
	{
		try {
            Reporter.log("Edit Time Report script");
			
				wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
				wd.findElement(By.name("A&M R/, A&M Records, 8,50")).click();
				//click on comment link
				wd.findElement(By.xpath("//UIATableCell[5]/UIAStaticText[1]")).click();
				WebElement comment=wd.findElements(By.className("UIATextView")).get(0);
				comment.sendKeys("private comment edited");
				wd.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]")).click();
				//Add hours and save report
				WebElement hour=wd.findElements(By.xpath("//UIATableCell[8]/UIATextField[1]/UIATextField[1]")).get(0);
				hour.click();
				WebElement minutes=wd.findElements(By.name("8. 9 of 25")).get(0);
				minutes.sendKeys("4");
				WebElement seconds=wd.findElements(By.name("50. 11 of 20")).get(0);
				seconds.sendKeys("5");
				wd.findElement(By.xpath("//UIAWindow[2]/UIANavigationBar[1]/UIAButton[3]")).click();
				//Delayed the process so that the data gets updated
				wd.findElement(By.xpath("//UIANavigationBar[1]/UIAButton[3]")).click();

            Reporter.log("Time Report Edited Successfully");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void DeleteTimeReportWithSettings()
	{
		try {
            Reporter.log("Delete Time Report script");
			
				wd.findElement(By.name("A&M R/, A&M Records, 4,05")).click();
				//click on "Delete time report row" button
				wd.findElement(By.name("Delete time report row")).click();
				wd.findElement(By.name("OK")).click();

            Reporter.log("Time Report Deleted Successfully");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	*/
	public void ValidateMissingHoursAndSubmit()
	{
		try {
            Reporter.log("Validate Missing Hours and Submit time report");

				WebElement selectMenu = wd.findElement(By.xpath("//UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]"));
				selectMenu.click();
				wd.findElement(By.name("Missing hours")).click();
				WebElement selectWeek = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]"));
				selectWeek.click();
				Thread.sleep(3000);
				wd.findElement(By.name("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[3]")).click();
				wd.findElement(By.name("Submit part of week")).click();
				wd.findElement(By.name("Report inclusive")).click();
				WebElement selectDay = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIAStaticText[1]"));
				selectDay.click();
				wd.findElement(By.name("Submit time report")).click();
				if (ExpectedConditions.alertIsPresent() != null)
				{
                AcceptAlert(wd);
				wd.findElement(By.name("Cancel")).click();	
				}

            Reporter.log("Validated Missing Hours successfully");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void ValidateContactStaff()
	{
		try {
            Reporter.log("Validate Contact Staff");

				WebElement selectMenuOption=wd.findElement(By.xpath("//UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]"));
				selectMenuOption.click();
				wd.findElement(By.name("Contact staff")).click();
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

            Reporter.log("Validated Contact Staff successfully");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void Logout()
	{
		try {
            Reporter.log("Logout script");
			
				//Click on the bar button to select logout link
				WebElement selectMenuBar = wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[1]"));
				selectMenuBar.click();
				wd.findElement(By.name("Logout")).click();
				AcceptAlert(wd);

            Reporter.log("Logout tested Successfully");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

    public void AcceptAlert(WebDriver driver)
    {
        Alert alert=wd.switchTo().alert();
        alert.accept();
    }
	
}

