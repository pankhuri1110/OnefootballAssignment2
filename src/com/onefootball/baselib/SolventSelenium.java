package com.onefootball.baselib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;


public class SolventSelenium {
	
	public static AndroidDriver driver;
	
	private static String appPath = null;

	private static String appName = null;

	private static String platformVersion = null;

	private static String deviceName = null;

	private static String platformName = null;

	private static String appPackage = null;

	private static String appActivity = null;

	private static String appiumServer = null;
	
	private static String screenshotPath = null; 
	
	public enum LocatorType { 
	    ID("ID"), TEXT("TEXT"), DESCRIPTION("DESCRIPTION"), CLASS("CLASS"), PACKAGE("PACKAGE"); 
	    
		private String type; 
	    private LocatorType(String type) { 
	            this.type = type; 
	        } 
	        
	    @Override 
	    public String toString(){ 
	            return type; 
	        } 
	    }

	public static void loadProperties(String filepath) {
		try {
		     
			System.out.println("Loading Properties");
		    FileReader reader;
		    Properties prop;
		
			reader = new FileReader(filepath);
		
			prop=new Properties();  
	        prop.load(reader);  
	        appPath = prop.getProperty("appPath");
	        appName = prop.getProperty("appName");
	        platformVersion = prop.getProperty("platformVersion");
	        deviceName = prop.getProperty("deviceName");
	        platformName = prop.getProperty("platformName");
	        appPackage = prop.getProperty("appPackage");
	        appActivity = prop.getProperty("appActivity");
	        appiumServer= prop.getProperty("appiumServer");
	        screenshotPath = prop.getProperty("screenshotPath");
	    } 
	      
	    catch (IOException e) {
			e.printStackTrace();
		}  
	  	
	}
	
	
	public static WebDriver launchApp() throws IOException {
		 System.out.println("Initializing Appium");
		 File app = new File(appPath, appName);
		 
		 DesiredCapabilities capability = new DesiredCapabilities();
		
		 capability.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
		 
		 capability.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		 
		 capability.setCapability(MobileCapabilityType.DEVICE_NAME,deviceName);
		 
		 capability.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		
		 capability.setCapability("app-package", appPackage);
		 
		 capability.setCapability("app-activity", appActivity);
		
		 driver = new AndroidDriver(new URL(appiumServer), capability);
		 driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		 
		 return driver;
	}
	

	public MobileElement getElement(String locator, LocatorType type) {
		
		if (type.toString().equals("ID")) {
			System.out.println("Finding Element with Resource id: " + locator);
			return (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"" + locator + "\")");
			
		}
		else if (type.toString().equals("TEXT")) {
			System.out.println("Finding Element with text: " + locator);
			return (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().text(\"" + locator + "\")");
		}
		else if (type.toString().equals("CLASS")) {
			System.out.println("Finding Element with class: " + locator);
			return (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().className(\"" + locator + "\")");
		}
		else if (type.toString().equals("DESCRIPTION")) {
			System.out.println("Finding Element with content-description: " + locator);
			return (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().description(\"" + locator + "\")");
		}
		else if (type.toString().equals("PACKAGE")) {
			System.out.println("Finding Element with classname: " + locator);
			return (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().packageName(\"" + locator + "\")");
		}
		else {
			System.out.println("Locator type not supported");
			return null;
		}
	}
	
	public MobileElement getElementContainsText(String locator) {
		return (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + locator + "\")");
	}


	public List<MobileElement> getChildElementListWithClassname(String resourceID, String classname, int index) {

		return (List<MobileElement>) driver.findElementsByAndroidUIAutomator("new UiSelector().resourceId(\"" + resourceID + "\").childSelector(new UiSelector().className(\"" + classname + "\")).childSelector(new UiSelector().className(\"" + classname + "\")).childSelector(new UiSelector().index(" + index + "))");
		
	}
	
	public MobileElement scrollToElement(String text){
        return (MobileElement) driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
                        + ").scrollIntoView("
                        + "new UiSelector().textContains(\""+text+"\"))");
    }
	
	public MobileElement scrollToEnd(int maxSwipes){
        return (MobileElement) driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector())"
                        + ".scrollToEnd("+maxSwipes+"))");
 
	}
	
	public void takeScreenShot(String methodName) {
		
   	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
				FileUtils.copyFile(scrFile, new File(screenshotPath+methodName+".png"));
				System.out.println("***Placed screen shot in "+ screenshotPath +" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
   }

}
