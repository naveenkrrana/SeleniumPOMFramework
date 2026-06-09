package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import base.BaseTest;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

// Hooks into TestNG execution events to automatically generate our test reports
public class TestListener implements ITestListener {

    private final ExtentReports extent = ReportManager.getInstance();
    // ThreadLocal prevents report logs from mixing up during parallel execution
    private final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        // Append data provider parameters (like username) to the report name to distinguish test iterations
        if (result.getParameters().length > 0) {
            testName = testName + " [" + result.getParameters()[0] + "]";
        }

        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Executed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed");
        test.get().log(Status.FAIL, result.getThrowable());

        try {
            // Take a screenshot only if this was a UI test with an active WebDriver
            Object testClass = result.getInstance();
            if (testClass instanceof BaseTest) {
                BaseTest failedTestClass = (BaseTest) testClass;
                WebDriver driver = failedTestClass.getDriver();

                TakesScreenshot ts = (TakesScreenshot) driver;
                String base64Screenshot = ts.getScreenshotAs(OutputType.BASE64);
                test.get().addScreenCaptureFromBase64String(base64Screenshot);
            } else {
                // Fallback for API tests where a browser screenshot doesn't exist
                test.get().info("API Execution Failed - No UI screenshot available.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        // Write the final report data out to the HTML file
        extent.flush();
    }
}