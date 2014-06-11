package se.copernicus.Android;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Settings {
	public static void exicom_S4(AppiumDriver wd) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium-version", "1.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.3");
		capabilities.setCapability("deviceName", "S4");
		capabilities.setCapability("app", "/Users/martenrex/Documents/Exicom/copernicus/App-test/CopernicusMobile/CTimeSheet_v2.0.0.apk");
		capabilities.setCapability("appPackage", "se.copernicus");
		capabilities.setCapability("appActivity", ".SplashScreenPage");
		wd = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	public static void exicom_iPad(AppiumDriver wd) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(CapabilityType.VERSION, "7.1");
		capabilities.setCapability(CapabilityType.PLATFORM, "iOS");
		capabilities.setCapability("app", "./cTimeSheetDevice.app");
		capabilities.setCapability("BundleID", "se.exicom.timereport");
		capabilities.setCapability("UDID", "09151608b517ec9b844aaaba7a40869256b1a2ce");
		wd= new AppiumDriver(new URL("http://192.168.1.84:4723/wd/hub"), capabilities);
		wd.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
	}
	
	public static void exicom_nexus4_sim(AppiumDriver wd) throws Exception{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium-version", "1.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4");
		capabilities.setCapability("deviceName", "S4");
		capabilities.setCapability("app", "/Users/martenrex/Documents/Exicom/copernicus/App-test/CopernicusMobile/CTimeSheet_v2.0.0.apk");
		capabilities.setCapability("appPackage", "se.copernicus");
		capabilities.setCapability("appActivity", ".SplashScreenPage");
		wd = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

}
