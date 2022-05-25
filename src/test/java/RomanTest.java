import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class RomanTest extends BaseTest {

    private static final String URL = "https://www.saucedemo.com/";

    @Test
    public void testRomanTErrorMessageForLockedUser() {
        getDriver().get(URL);

        getDriver().findElement(By.id("user-name")).sendKeys("locked_out_user");

        getDriver().findElement(By.id("password")).sendKeys("secret_sauce");

        getDriver().findElement(By.id("login-button")).click();

        String actualResult = getDriver().findElement(
                By.xpath("//div[@class='error-message-container error']"))
                .getText();

        Assert.assertEquals(actualResult, "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void testRomanTTotalPriceInCart() {
        getDriver().get(URL);

        getDriver().findElement(By.id("user-name")).sendKeys("standard_user");

        getDriver().findElement(By.id("password")).sendKeys("secret_sauce");

        getDriver().findElement(By.id("login-button")).click();

        getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        getDriver().findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();

        getDriver().findElement(By.id("shopping_cart_container")).click();

        getDriver().findElement(By.id("checkout")).click();

        getDriver().findElement(By.id("first-name")).sendKeys("Roman");
        getDriver().findElement(By.id("last-name")).sendKeys("Roman");
        getDriver().findElement(By.id("postal-code")).sendKeys("11111");
        getDriver().findElement(By.id("continue")).click();

        WebElement totalPrice = getDriver().findElement(By.xpath("//div[@class='summary_total_label']"));

        Assert.assertEquals(totalPrice.getText(), "Total: $49.66");
    }
}