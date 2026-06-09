package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Set the output path for the generated HTML report
            String reportPath = System.getProperty("user.dir") + "/target/ExtentReport.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

            // Configure the browser tab title and main report header
            spark.config().setDocumentTitle("SauceDemo Automation Report");
            spark.config().setReportName("UI E2E Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // Inject test environment details into the main report dashboard
            extent.setSystemInfo("Environment", "QA Sandbox");
            extent.setSystemInfo("Tester", "Naveen Kumar Rana");
        }
        return extent;
    }
}