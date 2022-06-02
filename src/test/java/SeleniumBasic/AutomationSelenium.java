package SeleniumBasic;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class AutomationSelenium {
	
	 public ExtentSparkReporter htmlReporter;
	 public ExtentReports extent;
	 public ExtentTest test;
	 
	 public void startReport() {
		 htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
		 
		 extent = new ExtentReports();
	     extent.attachReporter(htmlReporter);
	     
	     // Passing General information
		  extent.setSystemInfo("Host name", "localhost");
		  extent.setSystemInfo("Environemnt", "QA");
		  extent.setSystemInfo("user", "Neeraj");
		  
		  // configuration items to change the look and feel
		  htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
		  htmlReporter.config().setReportName("Functional Testing"); // Name of the report
		  htmlReporter.config().setTheme(Theme.DARK);
		  
	 }
	 @Test
	 public void testCase1() {
	        test = extent.createTest("Test Case 1", "PASSED test case");
	        Assert.assertTrue(true);
	    }
	    
	    @Test
	    public void testCase2() {
	        test = extent.createTest("Test Case 2", "PASSED test case");
	        Assert.assertTrue(true);
	    }
	    
	    @Test
	    public void testCase3() {
	        test = extent.createTest("Test Case 3", "PASSED test case");
	        Assert.assertTrue(true);
	    }
	     
	    @Test
	    public void testCase4() {
	        test = extent.createTest("Test Case 4", "PASSED test case");
	        Assert.assertTrue(false);
	    }
	     
	    @Test
	    public void testCase5() {
	        test = extent.createTest("Test Case 5", "SKIPPED test case");
	        throw new SkipException("Skipping this test with exception");
	    }
	    
	    @Test(enabled=false)
		public void testCase6(){
				test = extent.createTest("Test Case 6", "I'm Not Ready, please don't execute me");
			}
	   
	    @AfterMethod
	    public void getResult(ITestResult result) {
	        if(result.getStatus() == ITestResult.FAILURE) {
	            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
	            test.fail(result.getThrowable());
	        }
	        else if(result.getStatus() == ITestResult.SUCCESS) {
	            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
	        }
	        else {
	            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
	            test.skip(result.getThrowable());
	        }
	    }
	     
	    @AfterTest
	    public void tearDown() {
	    	//to write or update test information to reporter
	        extent.flush();
	    }

}
