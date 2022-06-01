package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import static java.sql.DriverManager.getDriver;

@Ignore
public class SergeyTest extends BaseTest {

    @Test
    public void testFirstSelenium() {
        Assert.assertTrue(getDriver().findElement(By.id("side-panel")).isDisplayed());
    }

}
