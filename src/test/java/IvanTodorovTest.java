import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class IvanTodorovTest extends BaseTest {

    @Test
    public void testErrorLigInWikipedia() {
        String expectedResult = "The supplied credentials could not be authenticated.";

        getDriver().get("https://en.wikipedia.org/wiki/Main_Page");

        WebElement pageLogIn = getDriver().findElement(By.xpath("//*[@id=\"pt-login\"]/a/span"));
        pageLogIn.click();

        WebElement fieldUsername = getDriver().findElement(By.xpath("//*[@id=\"wpName1\"]"));
        WebElement fieldPassword = getDriver().findElement(By.xpath("//*[@id=\"wpPassword1\"]"));

        fieldUsername.sendKeys("   ");
        fieldPassword.sendKeys("   ");

        WebElement buttonLogIn = getDriver().findElement(By.xpath("//*[@id=\"wpLoginAttempt\"]"));
        buttonLogIn.click();

        WebElement errorOfLogIn = getDriver().findElement(By.xpath("//*[@id=\"userloginForm\"]/form/div[1]"));
        String actualResult = errorOfLogIn.getText();

        Assert.assertEquals(actualResult,expectedResult);
    }

}