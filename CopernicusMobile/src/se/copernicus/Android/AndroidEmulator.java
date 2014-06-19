package se.copernicus.Android;

import io.appium.java_client.AppiumDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AndroidEmulator {
	AppiumDriver wd=null;
		
		@BeforeMethod
		public void SetUp() throws Exception {
			Settings.exicom_S4(wd);
		}
		
		@AfterMethod
		public void TearDown() {
			wd.quit();
		}
		
		@Test
		public void Login() throws Exception {
			//Login
			wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
			wd.findElement(By.className("android.widget.ImageView")).click();
			wd.findElement(By.className("android.widget.ImageView")).click();
			List<WebElement> editText=wd.findElements(By.className("android.widget.EditText"));
			editText.get(0).sendKeys("10");
			wd.navigate().back();
	
			//Require Field Validation method
			RequiredFieldValidation();
			editText.get(2).sendKeys("192.168.1.109:7070");
									
			//continue login with incorrect data value
			VerifyIncorrectData();
			     		
     		//Edit User Details method
    		EditUserLoginDetails();
			wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")).click();
     		editText.get(0).sendKeys("password");
			List<WebElement> Button=wd.findElements(By.className("android.widget.Button"));
			Button.get(0).click();
			Button.get(0).click();
			
			//Add Time Report
			AddTimeReport();
			
			//Edit TimeReport
			EditTimeReport();
			
			//DeleteTimeReport
			//DeleteTimeReport();
			
			//Logout
			Logout();
		}
		
		public void RequiredFieldValidation()
		{
			List<WebElement> saveButton=wd.findElements(By.className("android.widget.Button"));
			saveButton.get(0).click();
//			List<WebElement> infoMissing=driver.findElements(By.className("android.widget.TextView"));
//			String eMsg=infoMissing.get(1).getText();
//			Assert.assertEquals("User, Company and Address are required fields", eMsg);
			List<WebElement> okClick=wd.findElements(By.className("android.widget.Button"));
			okClick.get(0).click();
		}
		
		public void VerifyIncorrectData()
		{
			List<WebElement> editText=wd.findElements(By.className("android.widget.EditText"));
			editText.get(1).sendKeys("123");
			wd.navigate().back();
			List<WebElement> saveButton=wd.findElements(By.className("android.widget.Button"));
			saveButton.get(0).click();
			wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")).click();
     		editText.get(0).sendKeys("password");
			List<WebElement> ButtonClick=wd.findElements(By.className("android.widget.Button"));
			ButtonClick.get(0).click();
			ButtonClick.get(0).click();
			WebDriverWait wait=new WebDriverWait(wd, 120);
			List<WebElement> okButton=wd.findElements(By.className("android.widget.Button"));
			wait.until(ExpectedConditions.visibilityOfAllElements(okButton));
//			List<WebElement> infoRequired=driver.findElements(By.className("android.widget.TextView"));
//			String eMsg=infoRequired.get(1).getText();
//			Assert.assertEquals("Login failed. Check that the username, company and password is correct and then try again.", eMsg);
			okButton.get(0).click();
		}
		
		public void EditUserLoginDetails()
		{
			//Code to change user detail and verify warning message
			wd.findElement(By.className("android.widget.ImageView")).click();
     		wd.findElement(By.className("android.widget.ImageView")).click();
			List<WebElement> editText=wd.findElements(By.className("android.widget.EditText"));
			editText.get(1).clear();
			editText.get(1).sendKeys("1000");
			wd.navigate().back();
			List<WebElement> saveButton=wd.findElements(By.className("android.widget.Button"));
			saveButton.get(0).click();
//     		List<WebElement> modUserDetail=driver.findElements(By.className("android.widget.TextView"));
//     		String eMsg=modUserDetail.get(1).getText();
//			Assert.assertEquals("Do you want to modify user detail", eMsg);
     		List<WebElement> okClick=wd.findElements(By.className("android.widget.Button"));
     		okClick.get(0).click();
   		}
		public void AddTimeReport()
		{
			WebDriverWait wait=new WebDriverWait(wd, 240);
			//click on "+" symbol to add time report
			List<WebElement> addTimeReport=wd.findElements(By.xpath("//android.widget.RelativeLayout[1]/android.view.View[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.TabHost[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.ImageView[1]"));
			wait.until(ExpectedConditions.visibilityOfAllElements(addTimeReport));
			addTimeReport.get(0).click();
			//click on "customer,Project and Activity" text
			wd.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")).click();
			List<WebElement> radioButton=wd.findElements(By.className("android.widget.RadioButton"));
			radioButton.get(0).click();
			List<WebElement> imageView = wd.findElements(By.className("android.widget.ImageView"));
			imageView.get(0).click();
			radioButton.get(1).click();
			imageView.get(1).click();
			radioButton.get(1).click();
			List<WebElement> editText=wd.findElements(By.className("android.widget.EditText"));
			editText.get(0).click();
			editText.get(0).sendKeys("Comment");
			wd.navigate().back();
			//Click on "Hours" button
			wd.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[6]/android.widget.Button[1]")).click();
			List<WebElement> reportTime = wd.findElements(By.className("android.widget.ImageButton"));
			reportTime.get(2).click();
			reportTime.get(2).click();
			reportTime.get(4).click();
			List<WebElement> Buttons=wd.findElements(By.className("android.widget.Button"));
			Buttons.get(0).click();
			Buttons.get(1).click();
		}

		public void EditTimeReport()
		{
			//Click on Day tab
			wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.view.View[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.TabHost[1]/android.widget.LinearLayout[1]/android.widget.TabWidget[1]/android.widget.RelativeLayout[3]/android.widget.TextView[1]")).click();
			//Value hard coded
			wd.findElement(By.name("1090/")).click();
			List<WebElement> editText=wd.findElements(By.className("android.widget.EditText"));
			editText.get(0).clear();
			editText.get(0).sendKeys("Edited comment");
			wd.navigate().back();
			//Click on "Hours" button and edit hours
			wd.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[5]/android.widget.Button[1]")).click();
			List<WebElement> reportTime = wd.findElements(By.className("android.widget.ImageButton"));
			reportTime.get(2).click();
			reportTime.get(2).click();
			reportTime.get(4).click();
			List<WebElement> Buttons=wd.findElements(By.className("android.widget.Button"));
			Buttons.get(0).click();
			Buttons.get(1).click();
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
		public void Logout()
		{
			//Click on month view
			wd.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.view.View[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.TabHost[1]/android.widget.LinearLayout[1]/android.widget.TabWidget[1]/android.widget.RelativeLayout[1]/android.widget.ImageView[1]")).click();
			List<WebElement> logoutLink=wd.findElements(By.className("android.widget.ImageView"));
			logoutLink.get(0).click();
			//Click on logout button
			List<WebElement> textView = wd.findElements(By.xpath("//android.widget.RelativeLayout[1]/android.view.View[2]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.RelativeLayout[6]/android.widget.TextView[1]"));                               
			String txt=textView.get(0).getText();
			Reporter.log(txt, true);
			textView.size();
			textView.get(0).click();
			List<WebElement> okButton=wd.findElements(By.className("android.widget.Button"));
			okButton.get(0).click();
		}
} 
