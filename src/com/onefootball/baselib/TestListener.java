package com.onefootball.baselib;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener extends SolventSelenium implements ITestListener {
	//WebDriver driver=null;
	
    @Override
    public void onTestFailure(ITestResult result) {
    	System.out.println("***** Error "+result.getName()+" test has failed *****");
    	String methodName=result.getName().toString().trim();
    	takeScreenShot(methodName);
    }
    
    
    public void onTestStart(ITestResult result) {
    	
    	Reporter.log("Test is Started", true);
    }
  
    public void onTestSuccess(ITestResult result) { 
    	
    	Reporter.log("Woohooo Test is succeeded", true);
    	driver.quit();
    }

    public void onTestSkipped(ITestResult result) {   }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

    public void onStart(ITestContext context) {   }
    
	public void onFinish(ITestContext context) {
		
		Reporter.log("Woohooo Test is finished", true);
		driver.quit();
		
	}
  
}  