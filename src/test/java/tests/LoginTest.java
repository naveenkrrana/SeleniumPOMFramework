package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExcelReader;

public class LoginTest extends BaseTest {

    // Pull test data from our Excel file to run data-driven tests
    @DataProvider(name = "sauceUsers", parallel = true)
    public Object[][] getLoginData() {
        // Build the file path dynamically so it works on any machine
        String excelFilePath = System.getProperty("user.dir") + "/src/test/resources/LoginData.xlsx";
        return ExcelReader.getExcelData(excelFilePath, "Sheet1");
    }

    // TestNG matches the Excel columns directly to these method parameters
    @Test(dataProvider = "sauceUsers")
    public void testMultipleLogins(String username, String password) {
        System.out.println("Attempting login with user: " + username);

        getDriver().get(ConfigReader.getProperty("url"));
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.loginToApplication(username, password);

        // Verify the login worked by checking if the inventory UI actually rendered
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        Assert.assertTrue(inventoryPage.isInventoryDisplayed(), "Login failed!");
    }
}