import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class VictoriaPaTest extends BaseTest {

        @Test
        public void testItemInCard() throws InterruptedException {
            getDriver().get("https://www.saucedemo.com/");

            getDriver().findElement(By.id("user-name")).sendKeys("standard_user");
            getDriver().findElement(By.id("password")).sendKeys("secret_sauce");
            getDriver().findElement(By.id("login-button")).click();
            getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            getDriver().findElement(By.xpath("//span[@class='shopping_cart_badge']")).click();

            Assert.assertTrue(getDriver().getCurrentUrl().contains("https://www.saucedemo.com/cart.html"));
        }
    }

