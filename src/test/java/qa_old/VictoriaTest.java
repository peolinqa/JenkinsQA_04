package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class VictoriaTest extends BaseTest {
    @Test
    public void CourseraTest() {
        String SearchBoxXpath = "//div[@class='react-autosuggest__container']//input";
        String search = "//button[2][@aria-label=\"Submit Search\"]//div[@class=\"magnifier-wrapper\"]";

        getDriver().get("https://www.coursera.org/");
        getDriver().findElement(By.xpath(SearchBoxXpath)).sendKeys("Test automation");;
        getDriver().findElement(By.xpath(search)).click();
        Assert.assertEquals(getDriver().findElement(By.xpath(SearchBoxXpath)).getAttribute("value"), "Test automation");
    }
}



