package se.copernicus.Android;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AndroidDeviceTest
{
    WebDriver wd=null;

    @BeforeClass
    public void SetUp () throws Exception {
        String appdirectory = System.getProperty("user.dir")+"/src/test/resources";
        File appDir = new File(appdirectory);
        File app=new File(appDir, "CTimeSheet_v2.0.4.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "se.copernicus");
        capabilities.setCapability("appActivity", ".SplashScreenPage");
        capabilities.setCapability("app", app.getAbsolutePath());
        wd= new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        ImplicitWait(wd,1,TimeUnit.MINUTES);

        Reporter.log("App launched",true);
        Thread.sleep(3000);
    }

    @AfterClass
    public void TearDown()
    {
       // wd.quit();
    }

    @Test
    public void RequiredFieldValidation()
    {
        ImplicitWait(wd, 1, TimeUnit.MINUTES);
        WebElement clickUserName = wd.findElement(By.id("se.copernicus:id/imageview_logginpage_viewusernames"));
        clickUserName.click();
        WebElement addNewUser = wd.findElement(By.id("se.copernicus:id/displayuserdetails_button_newuser"));
        addNewUser.click();
        WebElement userName = wd.findElement(By.id("se.copernicus:id/edittext_adduserpage_username"));
        userName.sendKeys("10");
        GoBack(wd);
        Save();
        AcceptAlert();
        Reporter.log("Require Field Validation Successful", true);
    }

    @Test (dependsOnMethods = {"RequiredFieldValidation"})
    public void VerifyIncorrectData()
    {
        ImplicitWait(wd, 1, TimeUnit.MINUTES);
        WebElement incorrectCompanyId = wd.findElements(By.className("android.widget.EditText")).get(1);
        Assert.assertTrue(incorrectCompanyId.isDisplayed(), "Company textbox field is not displayed");
        incorrectCompanyId.sendKeys("123");
        WebElement address = wd.findElements(By.className("android.widget.EditText")).get(2);
        address.click();
        address.sendKeys("192.168.1.109:7070");
        GoBack(wd);
        Save();
        WebElement passwordLink = wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]"));
        passwordLink.click();
        WebElement password = wd.findElement(By.className("android.widget.EditText"));
        password.sendKeys("WrongPassword");
        ClickOk(wd);
        WebElement login = wd.findElement(By.className("android.widget.Button"));
        login.click();
        WebElement okClick=wd.findElement(By.name("OK"));
        okClick.click();
        Reporter.log("Verification of Incorrect Data Successful", true);
    }

    @Test (dependsOnMethods = {"VerifyIncorrectData"})
    public void EditUserDetailsAndLogin() throws Exception
    {
        //Code to change user detail and verify warning message
        ImplicitWait(wd, 1, TimeUnit.MINUTES);
        WebElement clickUserName=wd.findElement(By.className("android.widget.ImageView"));
        clickUserName.click();
        WebElement clickExistingUser =wd.findElement(By.className("android.widget.ImageView"));
        clickExistingUser.click();
        WebElement correctCompanyId =wd.findElements(By.className("android.widget.EditText")).get(1);
        Thread.sleep(2000);
        correctCompanyId.click();
        WebElement selectAll=wd.findElement(By.className("android.widget.TextView"));
        selectAll.click();
        correctCompanyId.clear();
        correctCompanyId.sendKeys("1000");
        GoBack(wd);
        Save();
        AcceptAlert();
        WebElement usrName=wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[2]"));
        String Name=usrName.getText();
        Assert.assertEquals(Name, "1000", "User name is not correct");
        WebElement passwordLink=wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]"));
        passwordLink.click();
        WebElement password=wd.findElement(By.className("android.widget.EditText"));
        password.sendKeys("password");
        ClickOk(wd);
        WebElement login = wd.findElement(By.className("android.widget.Button"));
        login.click();
        Thread.sleep(3000);
        Assert.assertTrue(login.isDisplayed(), "Login is failed");

        Reporter.log("Edit User Details and Login Successful", true);
    }

    @Test (dependsOnMethods = {"EditUserDetailsAndLogin"})
    public void AddTimeReport() throws Exception
    {
        ImplicitWait(wd, 1, TimeUnit.MINUTES);
        Thread.sleep(3000);
        //click on "+" symbol to add time report
        WebElement addTimeReport=wd.findElement(By.id("se.copernicus:id/imageview_daypage_addtimereport_month_view"));
        Assert.assertTrue(addTimeReport.isDisplayed(),"Add time report option is not enabled");
        ExplicitWait(wd, 180, addTimeReport);
        //click on "customer,Project and Activity"
        WebElement clickOnCustomerProjAndActivity=wd.findElement(By.name("Customer, Project and Activity"));
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
        GoBack(wd);
        //Click on "Hours" button
        WebElement hours=wd.findElement(By.name("Hours"));
        hours.click();
        List<WebElement> adjustHours = wd.findElements(By.className("android.widget.ImageButton"));
        adjustHours.get(2).click();
        adjustHours.get(2).click();
        adjustHours.get(4).click();
        WebElement okClick=wd.findElement(By.name("OK"));
        okClick.click();
        Thread.sleep(3000);
        Save();
        WebElement clickOnCustomerName=wd.findElement(By.name("1090/"));
        clickOnCustomerName.isDisplayed();
        Reporter.log("Time Report Added Successfully", true);
    }

    @Test (dependsOnMethods = {"EditUserDetailsAndLogin"})
    public void EditTimeReport() throws Exception
    {
        ImplicitWait(wd, 1, TimeUnit.MINUTES);
        //Click on Day tab
        WebElement dayTab=wd.findElement(By.name("Day"));
        dayTab.click();
        //Value hard coded
        WebElement clickOnCustomerName=wd.findElement(By.name("1090/"));
        Assert.assertTrue(clickOnCustomerName.isDisplayed(),"Time report is not added");
        clickOnCustomerName.click();
        //Edit comment and save
        WebElement editComment=wd.findElements(By.className("android.widget.EditText")).get(0);
        Thread.sleep(2000);
        editComment.clear();
        editComment.sendKeys("Edited comment");
        GoBack(wd);
        //Click on "Hours" button and edit hours
        WebElement editHours=wd.findElement(By.name("Hours"));
        editHours.click();
        List<WebElement> editAdjustedHours = wd.findElements(By.className("android.widget.ImageButton"));
        editAdjustedHours.get(2).click();
        editAdjustedHours.get(2).click();
        editAdjustedHours.get(4).click();
        WebElement okClick=wd.findElement(By.name("OK"));
        okClick.click();
        Thread.sleep(3000);
        Save();
        Reporter.log("Time Report Edited Successfully", true);
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

    @Test (dependsOnMethods = {"EditTimeReport"})
    public void MissingTime() throws InterruptedException
    {
        ImplicitWait(wd, 1, TimeUnit.MINUTES);
        WebElement clickOnMenuBar = wd.findElements(By.className("android.widget.ImageView")).get(0);
        clickOnMenuBar.click();
        WebElement missingTimeTab=wd.findElement(By.name("Missing Time"));
        missingTimeTab.click();
        WebElement missingTime = wd.findElement(By.xpath("//android.view.View[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.ImageView[1]"));
        missingTime.click();
        WebElement clickOnOptions=wd.findElement(By.xpath("//android.view.View[1]/android.widget.RelativeLayout[1]"));
        clickOnOptions.click();
        WebElement submitMissingTime = wd.findElement(By.name("Submit"));
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
    }

    @Test (dependsOnMethods = {"EditTimeReport"})
    public void Settings()
    {
        ImplicitWait(wd, 1, TimeUnit.MINUTES);
        WebElement clickOnOptions=wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.view.View[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.ImageView[1]"));
        clickOnOptions.click();
        WebElement settings = wd.findElement(By.name("Settings"));
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
        GoBack(wd);
        WebElement searchCalendarEvents=wd.findElement(By.xpath("//android.widget.RelativeLayout[6]/android.widget.ImageView[1]"));
        searchCalendarEvents.click();
        WebElement title=wd.findElements(By.className("android.widget.RadioButton")).get(0);
        title.click();
//                TouchActions action=new TouchActions(wd);
//                action.scroll(0,100);
//                action.build();
//                action.perform();
//                WebElement numericRepresentation=wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.widget.ImageView[1]"));
//                numericRepresentation.click();
//                WebElement yes=wd.findElements(By.className("android.widget.RadioButton")).get(0);
//                yes.click();
//                WebElement timeReportRepresentation=wd.findElement(By.xpath("//android.widget.RelativeLayout[2]/android.widget.ImageView[1]"));
//                timeReportRepresentation.click();
//                WebElement row1Customer=wd.findElements(By.className("android.widget.ImageView")).get(0);
//                row1Customer.click();
//                WebElement customer=wd.findElements(By.className("android.widget.RadioButton")).get(0);
//                customer.click();
//                GoBack(wd);
//                WebElement missingTimeRepresetation=wd.findElement(By.xpath("//android.widget.RelativeLayout[3]/android.widget.ImageView[1]"));
//                missingTimeRepresetation.click();
//                WebElement viewSomeMonthsBack=wd.findElements(By.className("android.widget.RadioButton")).get(0);
//                viewSomeMonthsBack.click();
//                WebElement noticeToProjectManager=wd.findElement(By.xpath("//android.widget.RelativeLayout[4]/android.widget.ImageView[1]"));
//                noticeToProjectManager.click();
//                WebElement saveAndExit=wd.findElement(By.name("Save and Exit"));
//                saveAndExit.click();
        GoBack(wd);
        Reporter.log("Settings done Successfully", true);
    }

    @Test (dependsOnMethods = {"Settings"})
    public void CalendarEvent() throws InterruptedException
    {
        ImplicitWait(wd, 1, TimeUnit.MINUTES);
        WebElement enableCalendarEvent=wd.findElement(By.className("android.widget.ToggleButton"));
        enableCalendarEvent.click();
        WebElement DayTab=wd.findElement(By.name("Day"));
        DayTab.click();
        WebElement clickOnOptions=wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.view.View[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.ImageView[1]"));
        ExplicitWait(wd, 180, clickOnOptions);
        WebElement today = wd.findElement(By.name("Today"));
        today.click();
        Thread.sleep(3000);
        WebElement newEvent=wd.findElement(By.name("New event"));
        Assert.assertTrue(newEvent.isDisplayed(), "Calendar event is not displayed");
        newEvent.click();
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
        //click on private comments and add comments
        WebElement privateComment=wd.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]"));
        privateComment.sendKeys("Private Comment");
        GoBack(wd);
        //Click on "Hours" button
        WebElement hours=wd.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[4]/android.widget.Button[1]"));
        hours.click();
        List<WebElement> adjustHours = wd.findElements(By.className("android.widget.ImageButton"));
        adjustHours.get(2).click();
        adjustHours.get(2).click();
        adjustHours.get(4).click();
        ClickOk(wd);
        //            //click on price deviation and enter value
        //            WebElement priceDeviation=wd.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[3]/android.widget.EditText[1]"));
        //            priceDeviation.sendKeys("5");
        //            wd.navigate().back();
        //Save time report with calendar event
        Save();
        Reporter.log("Time Report Successfully added for calendar event",true);
    }

    @Test (dependsOnMethods = {"CalendarEvent"})
    public void ContactEmployees() throws InterruptedException
    {
        ImplicitWait(wd, 1, TimeUnit.MINUTES);
        WebElement clickOnMenuBar = wd.findElements(By.className("android.widget.ImageView")).get(0);
        clickOnMenuBar.click();
        WebElement contactEmployees = wd.findElement(By.name("Contact Employees"));
        Assert.assertTrue(contactEmployees.isDisplayed(), "Contact employees is not displayed");
        contactEmployees.click();
        Thread.sleep(3000);
//                WebElement clickOnEmployeeName=wd.findElement(By.name("Gunnar Sjöberg"));
//                clickOnEmployeeName.click();
//                WebElement saveToContact=wd.findElement(By.name("Save to Contact"));
//                saveToContact.click();
//                Thread.sleep(3000);
        WebElement departmentsTab=wd.findElement(By.name("Departments"));
        departmentsTab.click();
        Thread.sleep(3000);
        Reporter.log("Contact Employee tested successfully", true);
    }

    @Test (dependsOnMethods = {"ContactEmployees"})
    public void Logout()
    {
        ImplicitWait(wd, 1, TimeUnit.MINUTES);
        //Click on month view
        WebElement clickOnMenuBar = wd.findElements(By.className("android.widget.ImageView")).get(0);
        clickOnMenuBar.click();
        //Click on logout button
        WebElement clickOnLogoutButton = wd.findElements(By.xpath("//android.widget.RelativeLayout[1]/android.view.View[2]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.RelativeLayout[6]/android.widget.TextView[1]")).get(0);
        Assert.assertTrue(clickOnLogoutButton.isDisplayed(), "Logout button is not displayed");
        clickOnLogoutButton.click();
        AcceptAlert();
        Reporter.log("Logout Successful",true);
    }

    public void AcceptAlert()
    {
        WebElement okButton=wd.findElement(By.id("android:id/button1"));
        Assert.assertTrue(okButton.isDisplayed(), "Ok button is not displayed");
        okButton.click();
    }

    public void Save()
    {
        WebElement saveButton = wd.findElement(By.id("se.copernicus:id/button_adduser_save"));
        Assert.assertTrue(saveButton.isDisplayed(),"Save button is not displayed");
        saveButton.click();
    }

    public void ExplicitWait(WebDriver wd, long seconds, WebElement WebEle)
    {
        WebDriverWait wait = new WebDriverWait(wd, seconds);
        wait.until(ExpectedConditions.elementToBeClickable(WebEle));
        WebEle.click();
    }

    public void ImplicitWait(WebDriver wd, long time, TimeUnit minutes)
    {
        wd.manage().timeouts().implicitlyWait(time, minutes);
    }

    public void GoBack(WebDriver wd)
    {
        wd.navigate().back();
    }

    public void ClickOk(WebDriver wd)
    {
       ((JavascriptExecutor)wd).executeScript("mobile: tap", new HashedMap() {{ put("tapCount", 1); put("touchCount", 1); put("duration", 0.5); put("x", 246); put("y", 519); }});

    }
}
