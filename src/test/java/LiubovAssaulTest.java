import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class LiubovAssaulTest extends BaseTest {

    public static final String URL = "https://www.saucedemo.com/";
    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";

    @Test
    public void testAddToCartOneItemFromInventoryPage() {
        getDriver().get(URL);

        getDriver().findElement(By.id("user-name")).sendKeys(USERNAME);
        getDriver().findElement(By.id("password")).sendKeys(PASSWORD);
        getDriver().findElement(By.id("login-button")).click();

        getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        getDriver().findElement(By.id("remove-sauce-labs-backpack")).isDisplayed();

        String actualResult = getDriver().findElement(By.cssSelector(".shopping_cart_badge")).getText();

        Assert.assertEquals(actualResult, "1");
    }

    @Test
    public void testAddToCartOneItemFromItemPage() {
        getDriver().get(URL);

        getDriver().findElement(By.id("user-name")).sendKeys(USERNAME);
        getDriver().findElement(By.id("password")).sendKeys(PASSWORD);
        getDriver().findElement(By.id("login-button")).click();

        getDriver().findElement(
                By.xpath("//div[@class='inventory_item_name' and text()='Sauce Labs Backpack']")
        ).click();
        getDriver().findElement(By.id("back-to-products")).isDisplayed();

        getDriver().findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        getDriver().findElement(By.id("remove-sauce-labs-backpack")).isDisplayed();

        String actualResult = getDriver().findElement(By.cssSelector(".shopping_cart_badge")).getText();

        Assert.assertEquals(actualResult, "1");
    }

    @Test
    public void testRemoveItemFromCart() {
        getDriver().get(URL);

        getDriver().findElement(By.id("user-name")).sendKeys(USERNAME);
        getDriver().findElement(By.id("password")).sendKeys(PASSWORD);
        getDriver().findElement(By.id("login-button")).click();

        getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        getDriver().findElement(By.xpath("//span[@class ='shopping_cart_badge'][text()='1']")).isDisplayed();

        getDriver().findElement(By.id("remove-sauce-labs-backpack")).click();

        String actualResult = getDriver().findElement(By.cssSelector(".shopping_cart_link")).getText();

        Assert.assertEquals(actualResult, "");
    }
}
