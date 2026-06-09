package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends BasePage {

    private final By backpackAddToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void addBackpackToCart() {
        clickElement(backpackAddToCartButton);
    }

    public void clickCartIcon() {
        clickElement(cartIcon);
    }

    public String getCartItemCount() {
        // Wait for the badge to render before reading the text
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }

    public boolean isInventoryDisplayed() {
        // Check if the main container is visible to confirm the page loaded
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container"))).isDisplayed();
    }
}