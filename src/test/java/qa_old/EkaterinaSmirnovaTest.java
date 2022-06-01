package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class EkaterinaSmirnovaTest extends BaseTest {
    @Test
    public void testCheckIfButtonRedirectCorrectPage() {
        getDriver().get("https://www.irvinecompany.com/");

        getDriver().findElement(By.xpath("//a[@title = 'Apartment Homes']")).click();
        WebElement headerText = getDriver().findElement(By.xpath("//h1[text() = 'Apartment Homes']"));

        Assert.assertTrue(headerText.isDisplayed());
    }
}
