import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class LiubovAssaulTest extends BaseTest {

    @Test
    public void addToCartOneItem () {

        getDriver().get("https://www.saucedemo.com/");

        String title = getDriver().getTitle();
        Assert.assertEquals (title, "Swag Labs");

        WebElement userName = getDriver().findElement(By.id("user-name"));
        WebElement password = getDriver().findElement(By.id("password"));
        WebElement logButton = getDriver().findElement(By.id("login-button"));

        userName.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        logButton.click();

        String menuTitle = getDriver().findElement(By.xpath("//span[@class = 'title']")).getText();
        Assert.assertEquals(menuTitle, "PRODUCTS");

        getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        getDriver().findElement(By.id("remove-sauce-labs-backpack")).isDisplayed();

        String itemQuantity = getDriver().findElement(By.cssSelector(".shopping_cart_badge")).getText();
        Assert.assertEquals(itemQuantity, "1");

    }

}
