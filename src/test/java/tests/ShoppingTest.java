package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;

public class ShoppingTest extends BaseTest {

    @Test
    public void testAddBackpackToCart() {
        // Load starting URL from properties file
        getDriver().get(ConfigReader.getProperty("url"));

        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());

        // Log in using default credentials from config
        loginPage.loginToApplication(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        inventoryPage.addBackpackToCart();

        String itemsInCart = inventoryPage.getCartItemCount();
        System.out.println("Items currently in cart: " + itemsInCart);

        // Verify the cart badge updates to 1 after adding a single item
        Assert.assertEquals(itemsInCart, "1", "The cart badge did not update correctly!");
    }
}