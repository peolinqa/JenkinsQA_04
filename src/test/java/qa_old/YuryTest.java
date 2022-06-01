package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class YuryTest extends BaseTest {

    @Test
    public void testYuryTseluyko() {
        getDriver().get("https://www.saucedemo.com/");

        WebElement usernameBox = getDriver().findElement(By.id("user-name"));
        usernameBox.sendKeys("standard_user");

        WebElement passwordBox = getDriver().findElement(By.id("password"));
        passwordBox.sendKeys("secret_sauce");

        WebElement loginButton = getDriver().findElement(By.id("login-button"));
        loginButton.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
}
