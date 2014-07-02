package se.copernicus.Android;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AndroidDeviceTest {
	WebDriver wd=null;
		
		@BeforeMethod
		public void SetUp() throws Exception {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("app", "./CTimeSheet_v2.0.2.apk");
            capabilities.setCapability("appPackage", "se.copernicus");
            capabilities.setCapability("appActivity", ".SplashScreenPage");
            wd= new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
		
		@AfterMethod
		public void TearDown()
        {
			//wd.quit();
		}
		
		@Test
		public void Login() throws Exception {

			//Require Field Validation method
			RequiredFieldValidation();


			//continue login with incorrect data value
			VerifyIncorrectData();
			     		
     		//Edit User Details method
    		EditUserDetailsAndLogin();

			//Add Time Report
			AddTimeReport();

			//Edit Time Report
			EditTimeReport();

            //Missing Time
            MissingTime();

            // App Settings
            Settings();

			//Delete Time Report
			//DeleteTimeReport();
			
			//Logout
			Logout();
		}

       	public void RequiredFieldValidation()
		{
            try {
                Reporter.log("Require Field Validation", true);
                wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
                WebElement clickUserName = wd.findElement(By.className("android.widget.ImageView"));
                clickUserName.click();
                WebElement addNewUser = wd.findElement(By.className("android.widget.ImageView"));
                addNewUser.click();
                WebElement userName = wd.findElements(By.className("android.widget.EditText")).get(0);
                userName.sendKeys("10");
                wd.navigate().back();
                WebElement saveButton = wd.findElement(By.className("android.widget.Button"));
                saveButton.click();
                AcceptAlert();
                Reporter.log("Require Field Validation Successful", true);
            } catch (Exception e) {
                System.out.println(e);
                Failure("Unsuccessful validation");
            }
		}
		
		public void VerifyIncorrectData()
		{
            try {
                Reporter.log("Verification of Incorrect Data", true);
                WebElement incorrectCompanyId = wd.findElements(By.className("android.widget.EditText")).get(1);
                incorrectCompanyId.sendKeys("123");
                WebElement address = wd.findElements(By.className("android.widget.EditText")).get(2);
                address.sendKeys("192.168.1.109:7070");
                wd.navigate().back();
                WebElement saveButton = wd.findElement(By.className("android.widget.Button"));
                saveButton.click();
                WebElement passwordLink = wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]"));
                passwordLink.click();
                WebElement password = wd.findElement(By.className("android.widget.EditText"));
                password.sendKeys("WrongPassword");
                WebElement clickOk = wd.findElement(By.className("android.widget.Button"));
                clickOk.click();
                WebElement login = wd.findElement(By.className("android.widget.Button"));
                login.click();
                WebDriverWait wait = new WebDriverWait(wd, 240);
                WebElement okButton = wd.findElement(By.className("android.widget.Button"));
                wait.until(ExpectedConditions.elementToBeClickable(okButton));
                okButton.click();
                Reporter.log("Verification of Incorrect Data Successful", true);
            } catch (Exception e) {
                System.out.println(e);
                Failure("Unsuccessful verification");
            }
		}
		
		public void EditUserDetailsAndLogin()
		{
            try {
                Reporter.log("Edit User Details and Login", true);
                //Code to change user detail and verify warning message
                wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
                WebElement clickUserName=wd.findElement(By.className("android.widget.ImageView"));
                clickUserName.click();
                WebElement clickExistingUser =wd.findElement(By.className("android.widget.ImageView"));
                clickExistingUser.click();
                WebElement correctCompanyId =wd.findElements(By.className("android.widget.EditText")).get(1);
                correctCompanyId.sendKeys("1000");
                wd.navigate().back();
                WebElement clickSaveButton=wd.findElement(By.className("android.widget.Button"));
                clickSaveButton.click();
                AcceptAlert();
                WebElement passwordLink=wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]"));
                passwordLink.click();
                WebElement password=wd.findElement(By.className("android.widget.EditText"));
                password.sendKeys("password");
                WebElement clickOk=wd.findElement(By.className("android.widget.Button"));
                clickOk.click();
                WebElement saveButton=wd.findElement(By.className("android.widget.Button"));
                saveButton.click();
                Reporter.log("Edit User Details and Login Successful", true);
            } catch (Exception e) {
                System.out.println(e);
                Failure("Unsuccessful Login");
            }
   		}

		public void AddTimeReport()
		{
            try {
                Reporter.log("Add Time Report", true);
                WebDriverWait wait=new WebDriverWait(wd, 240);
                //click on "+" symbol to add time report
                WebElement addTimeReport=wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.view.View[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.TabHost[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.ImageView[1]"));
                wait.until(ExpectedConditions.elementToBeClickable(addTimeReport));
                addTimeReport.click();
                //click on "customer,Project and Activity"
                WebElement clickOnCustomerProjAndActivity=wd.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]"));
                clickOnCustomerProjAndActivity.click();
                WebElement customer=wd.findElements(By.className("android.widget.RadioButton")).get(0);
                customer.click();
                //click on "task"
                WebElement task = wd.findElements(By.className("android.widget.ImageView")).get(0);
                task.click();
                WebElement taskName =wd.findElements(By.className("android.widget.RadioButton")).get(1);
                taskName.click();
                //click on "TimeType" text
                WebElement timeType = wd.findElements(By.className("android.widget.ImageView")).get(1);
                timeType.click();
                WebElement timeTypeName =wd.findElements(By.className("android.widget.RadioButton")).get(1);
                timeTypeName.click();
                //click on comment and add comment
                WebElement comment=wd.findElements(By.className("android.widget.EditText")).get(0);
                comment.click();
                comment.sendKeys("Comment");
                wd.navigate().back();
                //Click on "Hours" button
                WebElement hours=wd.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[6]/android.widget.Button[1]"));
                hours.click();
                List<WebElement> adjustHours = wd.findElements(By.className("android.widget.ImageButton"));
                adjustHours.get(2).click();
                adjustHours.get(2).click();
                adjustHours.get(4).click();
                WebElement clickOK=wd.findElements(By.className("android.widget.Button")).get(0);
                clickOK.click();
                WebElement clickSave=wd.findElements(By.className("android.widget.Button")).get(1);
                clickSave.click();
                WebElement clickOnCustomerName=wd.findElement(By.name("1090/"));
                clickOnCustomerName.isDisplayed();
                Reporter.log("Time Report Added Successfully", true);
            } catch (Exception e) {
                System.out.println(e);
                Failure("Unsuccessful in adding time report");
            }
		}

		public void EditTimeReport()
		{
            try {
                Reporter.log("Edit Time Report", true);
                //Click on Day tab
                WebElement dayTab=wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.view.View[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.TabHost[1]/android.widget.LinearLayout[1]/android.widget.TabWidget[1]/android.widget.RelativeLayout[3]/android.widget.TextView[1]"));
                dayTab.click();
                //Value hard coded
                WebElement clickOnCustomerName=wd.findElement(By.name("1090/"));
                clickOnCustomerName.click();
                //Edit comment and save
                WebElement editComment=wd.findElements(By.className("android.widget.EditText")).get(0);
                editComment.clear();
                editComment.sendKeys("Edited comment");
                wd.navigate().back();
                //Click on "Hours" button and edit hours
                WebElement editHours=wd.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[5]/android.widget.Button[1]"));
                editHours.click();
                List<WebElement> editAdjustedHours = wd.findElements(By.className("android.widget.ImageButton"));
                editAdjustedHours.get(2).click();
                editAdjustedHours.get(2).click();
                editAdjustedHours.get(4).click();
                WebElement clickOnOk=wd.findElements(By.className("android.widget.Button")).get(0);
                clickOnOk.click();
                WebElement saveButtonClick=wd.findElements(By.className("android.widget.Button")).get(1);
                saveButtonClick.click();
                Reporter.log("Time Report Edited Successfully", true);
            } catch (Exception e) {
                System.out.println(e);
                Failure("Unsuccessful edition of time report");
            }
		}
		
//		public void DeleteTimeReport()
//		{
//			driver.findElement(By.name("1090/")).click();
//			   keyEventTest() 
//		    driver.sendKeyEvent(AndroidKeyCode.HOME);
		
	//	driver.sendKeyEvent(AndroidKeyCode.HOME);
//			//trying to get the option menu but the code is not completed
//			driver.findElement(By.xpath("//android.view.View[1]/android.widget.TextView[1]")).click();
//			driver.findElement(By.className("android.widget.Button")).click();
//		}

        public void MissingTime()
        {
            try {
                Reporter.log("Missing Time", true);
//                WebElement weekTab=wd.findElement(By.name("Week"));
//                weekTab.click();
//                WebElement monthTab=wd.findElement(By.name("Month"));
//                monthTab.click();
                WebElement clickOnMenuBar = wd.findElements(By.className("android.widget.ImageView")).get(0);
                clickOnMenuBar.click();
                WebElement missingHours=wd.findElement(By.name("Missing Time"));
                missingHours.click();
                WebDriverWait wait=new WebDriverWait(wd, 240);
                WebElement missingTime = wd.findElements(By.className("android.widget.ImageView")).get(1);
                wait.until(ExpectedConditions.elementToBeClickable(missingTime));
                missingTime.click();
                WebElement clickOnOptions=wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.view.View[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.ImageView[1]"));
                clickOnOptions.click();
                WebElement submitMissingTime = wd.findElements(By.className("android.widget.TextView")).get(1);
                submitMissingTime.click();
                WebElement submitPartOfWeek = wd.findElement(By.name("Submit part of week"));
                submitPartOfWeek.click();
                WebElement reportInclusive = wd.findElement(By.className("android.widget.ImageView"));
                reportInclusive.click();
                WebElement selectDay = wd.findElement(By.className("android.widget.CheckedTextView"));
                selectDay.click();
                WebElement submit = wd.findElement(By.className("android.widget.Button"));
                submit.click();
                Thread.sleep(3000);
                if (submit.isDisplayed())
                {
                    WebElement cancel=wd.findElements(By.className("android.widget.Button")).get(1);
                    cancel.click();
                }
                Reporter.log("Missing Time Tested Successfully",true);
                Thread.sleep(3000);
            } catch (Exception e) {
                System.out.println(e);
                Failure("Unsuccessful test of missing time report");
            }
        }

        public void Settings()
        {
            try {
                Reporter.log("App Settings", true);
                WebElement clickOnOptions=wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.view.View[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.ImageView[1]"));
                clickOnOptions.click();
                WebElement settings = wd.findElements(By.className("android.widget.TextView")).get(4);
                settings.click();
                WebElement privateComment=wd.findElements(By.className("android.widget.CheckBox")).get(1);
                privateComment.click();
                WebElement adjustedHours=wd.findElements(By.className("android.widget.CheckBox")).get(2);
                adjustedHours.click();
                WebElement priceDeviation=wd.findElements(By.className("android.widget.CheckBox")).get(3);
                priceDeviation.click();
                WebElement viewPhoneCalendar=wd.findElement(By.xpath("//android.widget.RelativeLayout[5]/android.widget.ImageView[1]"));
                viewPhoneCalendar.click();
                WebElement calendars=wd.findElements(By.className("android.widget.CheckedTextView")).get(0);
                calendars.click();
                wd.navigate().back();
                WebElement searchCalendarEvents=wd.findElement(By.xpath("//android.widget.RelativeLayout[6]/android.widget.ImageView[1]"));
                searchCalendarEvents.click();
                WebElement title=wd.findElements(By.className("android.widget.RadioButton")).get(0);
                title.click();
                TouchActions action=new TouchActions(wd);
                action.scroll(0,100);
                action.build();
                action.perform();
                WebElement numericRepresentation=wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.widget.ImageView[1]"));
                numericRepresentation.click();
                WebElement yes=wd.findElements(By.className("android.widget.RadioButton")).get(0);
                yes.click();
                WebElement timeReportRepresentation=wd.findElement(By.xpath("//android.widget.RelativeLayout[2]/android.widget.ImageView[1]"));
                timeReportRepresentation.click();
                WebElement row1Customer=wd.findElements(By.className("android.widget.ImageView")).get(0);
                row1Customer.click();
                WebElement customer=wd.findElements(By.className("android.widget.RadioButton")).get(0);
                customer.click();
                wd.navigate().back();
                WebElement missingTimeRepresetation=wd.findElement(By.xpath("//android.widget.RelativeLayout[3]/android.widget.ImageView[1]"));
                missingTimeRepresetation.click();
                WebElement viewSomeMonthsBack=wd.findElements(By.className("android.widget.RadioButton")).get(0);
                viewSomeMonthsBack.click();
                WebElement noticeToProjectManager=wd.findElement(By.xpath("//android.widget.RelativeLayout[4]/android.widget.ImageView[1]"));
                noticeToProjectManager.click();
                WebElement saveAndExit=wd.findElement(By.name("Save and Exit"));
                saveAndExit.click();
                wd.navigate().back();
                Reporter.log("Settings done Successfully", true);
            } catch (Exception e) {
                System.out.println(e);
                Failure("Unsuccessful test of app settings");
            }
        }

		public void Logout()
		{
            try {
                Reporter.log("Logout", true);
                //Click on month view
                WebElement clickOnMenuBar = wd.findElements(By.className("android.widget.ImageView")).get(0);
                clickOnMenuBar.click();
                //Click on logout button
                WebElement clickOnLogoutButton = wd.findElements(By.xpath("//android.widget.RelativeLayout[1]/android.view.View[2]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.RelativeLayout[6]/android.widget.TextView[1]")).get(0);
                clickOnLogoutButton.click();
                AcceptAlert();
                Reporter.log("Logout Successful",true);
            } catch(Exception e) {
                System.out.println(e);
                Failure("Unsuccessful logout");
            }
		}

        public void AcceptAlert()
        {
            WebElement okButton=wd.findElement(By.className("android.widget.Button"));
            okButton.click();
        }

        public void Failure(String failureMsg)
        {
            Reporter.log(failureMsg, true);
        }
}
