package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

// Extend BaseTest to connect Cucumber to our existing TestNG browser setup
public class Hooks extends BaseTest {

    // Runs before each Cucumber scenario
    @Before
    public void startBrowserEngine() {
        // Call the TestNG setup to open the browser
        setUp();
    }

    // Runs after each Cucumber scenario
    @After
    public void closeBrowserEngine() {
        tearDown();
    }
}