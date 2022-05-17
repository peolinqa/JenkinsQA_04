import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class KorotushakTest extends BaseTest {
    @Test
    public void testLogIn() throws InterruptedException {

        getDriver().get("https://cloud.swivl.com/login");

        Thread.sleep(1000);

        WebElement emailField = getDriver().findElement(By.id("username"));
        WebElement passwordField = getDriver().findElement(By.id("password"));
        WebElement signInButton = getDriver().findElement(By.id("_submit"));

        emailField.sendKeys("korotushak@gmail.com");
        passwordField.sendKeys("testPassword2022");

        signInButton.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://cloud.swivl.com/dashboard");

    }

}
