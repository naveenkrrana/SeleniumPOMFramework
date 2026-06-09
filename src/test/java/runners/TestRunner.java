package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

// Configure Cucumber to map feature files to step definitions
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepDefinitions",
        plugin = {
                "pretty"
                //"html:target/cucumber-reports/cucumber-report.html"
        },
        monochrome = true // Clean up console output
)
// Extend this class to run Cucumber scenarios through the TestNG engine
public class TestRunner extends AbstractTestNGCucumberTests {

    // Enable parallel execution for Cucumber scenarios
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}