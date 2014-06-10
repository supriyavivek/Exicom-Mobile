package se.copernicus.Android;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.net.URL;

import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.internal.seleniumemulation.KeyEvent;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AndroidSimulator {
	RemoteWebDriver driver=null;
		
		@BeforeMethod
		public void SetUp() throws Exception {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("appium-version", "1.0");
			capabilities.setCapability("platformName", "jellybean");
			capabilities.setCapability("platformVersion", "4.2");
			capabilities.setCapability("app", "//CopernicusMobileWorkspace/CTimeSheet_v1.9.6_test.apk");
			capabilities.setCapability("appPackage", "se.copernicus");
			capabilities.setCapability("appActivity", ".SplashScreenPage");
			driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);     
		}
		
		@AfterMethod
		public void TearDown() {
			driver.quit();
		}
		
		@Test
		public void MartensTest() throws Exception{
			Login();
			AddTimeReport();
			DeleteTimeReport();
			Logout();
			
		}
		
		@Test
		public void Login() throws Exception {
			//Login
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
			//Click user
			driver.findElement(By.className("android.widget.ImageView")).click();
			//Click new user
			driver.findElement(By.className("android.widget.ImageView")).click();
			//Find textboxes
			List<WebElement> formList=driver.findElements(By.className("android.widget.EditText"));
			//write in textbox #1 (user) !!Already got focus!!
			formList.get(0).sendKeys("600");
			
			//Require Field Validation method
			RequiredFieldValidation();
			
			//Click textbox  #2 (company) to get focus
			formList.get(1).click();	
			//Send write to textbox 2#
			formList.get(1).sendKeys("utb18");
			
			//Click textbox  #3 (url) to get focus
			formList.get(2).click();
			//Send write to textbox 2#
			formList.get(2).sendKeys("mobiletest.exicom.se");
			
			//Get bottom bar
			List<WebElement> bottomBar=driver.findElements(By.className("android.widget.Button"));
			//Click save
			bottomBar.get(0).click();
     		
			//Click password
			driver.findElement(By.xpath("//android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")).click();
     		
			//write to textbox(password) !!Already foucs!!
			formList.get(0).sendKeys("600");
			
			//Get alertButtons
			List<WebElement> popupButtons=driver.findElements(By.className("android.widget.Button"));
			
			//Click OK
			popupButtons.get(0).click();
			
			//Get bottom bar
			List<WebElement> loginBottomBar=driver.findElements(By.className("android.widget.Button"));
			//Click Login
			loginBottomBar.get(0).click();
			
			//Add Time Report
			AddTimeReport();
			
			//Edit TimeReport
			EditTimeReport();
			
			//DeleteTimeReport
			DeleteTimeReport();
			
			//Logout
			Logout();
		}
		
		public void RequiredFieldValidation()
		{
			List<WebElement> saveButton=driver.findElements(By.className("android.widget.Button"));
			saveButton.get(0).click();
			List<WebElement> infoMissing=driver.findElements(By.className("android.widget.TextView"));
			String eMsg=infoMissing.get(1).getText();
			Assert.assertEquals("User, Company and Address are required fields", eMsg);
			List<WebElement> okClick=driver.findElements(By.className("android.widget.Button"));
			okClick.get(0).click();
		}
		
		public void IncorrectUrl()
		{
			List<WebElement> user=driver.findElements(By.className("android.widget.EditText"));
			user.get(2).sendKeys("192.168.1.109:7070");
			driver.navigate().back();
			List<WebElement> saveButton=driver.findElements(By.className("android.widget.Button"));
			saveButton.get(0).click();
		}
		
		public void EditUserLoginDetails()
		{
			//Code to change url and verify warning message
			List<WebElement> editText=driver.findElements(By.className("android.widget.EditText"));
			editText.get(2).click();
			editText.get(2).click();
			editText.get(2).clear();
			editText.get(2).sendKeys("192.168.1.109");
			driver.navigate().back();
			List<WebElement> saveButton=driver.findElements(By.className("android.widget.Button"));
			saveButton.get(0).click();
     		List<WebElement> modUserDetail=driver.findElements(By.className("android.widget.TextView"));
     		String eMsg=modUserDetail.get(1).getText();
			Assert.assertEquals("Do you want to modify user detail", eMsg);
     		List<WebElement> okClick=driver.findElements(By.className("android.widget.Button"));
     		okClick.get(0).click();
     		saveButton.get(0).click();
		}
		
		//Adding a time report on the XX with XXh
		public void AddTimeReport()
		{
		List<WebElement> week = driver.findElements(By.className("android.widget.Button"));
		week.get(24).click();
		WebDriverWait wait=new WebDriverWait(driver, 240);
		//click on "+" symbol to add time report
		List<WebElement> addTimeReport=driver.findElements(By.xpath("//android.widget.RelativeLayout[1]/android.view.View[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.TabHost[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.ImageView[1]"));
		wait.until(ExpectedConditions.visibilityOfAllElements(addTimeReport));
		addTimeReport.get(0).click();
		//click on "customer,Project and Activity" text
		driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")).click();
		List<WebElement> radioButton=driver.findElements(By.className("android.widget.RadioButton"));
		radioButton.get(0).click();
		List<WebElement> imageView = driver.findElements(By.className("android.widget.ImageView"));
		imageView.get(0).click();
		radioButton.get(1).click();
		imageView.get(1).click();
		radioButton.get(1).click();
		List<WebElement> editText=driver.findElements(By.className("android.widget.EditText"));
		editText.get(0).click();
		editText.get(0).sendKeys("Comment");
		driver.navigate().back();
		//Click on "Hours" button
		driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[6]/android.widget.Button[1]")).click();
		List<WebElement> reportTime = driver.findElements(By.className("android.widget.ImageButton"));
		reportTime.get(2).click();
		reportTime.get(2).click();
		reportTime.get(4).click();
		List<WebElement> Buttons=driver.findElements(By.className("android.widget.Button"));
		Buttons.get(0).click();
		Buttons.get(1).click();
		}

		public void EditTimeReport()
		{
			driver.findElement(By.name("1090/")).click();
			List<WebElement> editText=driver.findElements(By.className("android.widget.EditText"));
			editText.get(0).click();
			editText.get(0).sendKeys("Edited comment");
			driver.navigate().back();
			//Click on "Hours" button and edit hours
			driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[5]/android.widget.Button[1]")).click();
			List<WebElement> reportTime = driver.findElements(By.className("android.widget.ImageButton"));
			reportTime.get(2).click();
			reportTime.get(2).click();
			reportTime.get(4).click();
			List<WebElement> Buttons=driver.findElements(By.className("android.widget.Button"));
			Buttons.get(0).click();
			Buttons.get(1).click();
		}
		
		public void DeleteTimeReport()
		{
			driver.findElement(By.name("1090/")).click();
			//driver.getKeyboard().sendKeys(AndroidKeyCode.MENUsdfdsf);
			//AppiumDriver.sendKeyEvent(AndroidKeyCode.MENU);
			driver.findElement(By.xpath("//android.view.View[1]/android.widget.TextView[1]")).click();
			driver.findElement(By.className("android.widget.Button")).click();
		}
		
		public void Logout()
		{
			List<WebElement> logoutLink=driver.findElements(By.className("android.widget.ImageView"));
			logoutLink.get(0).click();
			//Click on logout button
			List<WebElement> textView = driver.findElements(By.xpath("//android.widget.RelativeLayout[1]/android.view.View[2]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.RelativeLayout[6]/android.widget.TextView[1]"));
			textView.size();
			textView.get(0).click();
			List<WebElement> okButton=driver.findElements(By.className("android.widget.Button"));
			okButton.get(0).click();
		}
}