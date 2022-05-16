import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class YuryTest extends BaseTest {

    @Test
    public void yuryTseluykoTest() {
        getDriver().get("https://www.saucedemo.com/");

        WebElement usernameBox = getDriver().findElement(By.id("user-name"));
        WebElement passwordBox = getDriver().findElement(By.id("password"));
        WebElement loginButton = getDriver().findElement(By.id("login-button"));

        usernameBox.sendKeys("standard_user");
        passwordBox.sendKeys("secret_sauce");
        loginButton.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
}
