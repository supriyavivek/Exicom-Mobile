package se.copernicus.Android;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Settings {
	RemoteWebDriver wd;
	
	public static void exicom_S4(AppiumDriver wd) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium-version", "1.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.3");
		capabilities.setCapability("deviceName", "S4");
		capabilities.setCapability("app", "./CTimeSheet_v2.0.0.apk");
		capabilities.setCapability("appPackage", "se.copernicus");
		capabilities.setCapability("appActivity", ".SplashScreenPage");
		wd = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	public static void exicom_iPad(WebDriver wd) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(CapabilityType.VERSION, "7.1");
		capabilities.setCapability(CapabilityType.PLATFORM, "iOS");
		//capabilities.setCapability("app", "./cTimeSheetSimulator.app");
		capabilities.setCapability("app", "./cTimeSheetDevice.app");
		capabilities.setCapability("BundleID", "se.exicom.timereport");
		capabilities.setCapability("UDID", "ccc088386c2a7955090405df231f385f5e1e5320");
		wd= new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
	}
	
	public static void exicom_nexus4_sim(AppiumDriver wd) throws Exception{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium-version", "1.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4");
		capabilities.setCapability("deviceName", "S4");
		capabilities.setCapability("app", "./CTimeSheet_v2.0.0.apk");
		capabilities.setCapability("appPackage", "se.copernicus");
		capabilities.setCapability("appActivity", ".SplashScreenPage");
		wd = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

}
