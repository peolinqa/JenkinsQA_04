package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

@Ignore
public class StanLTest extends BaseTest {

    @Test
    public void testSite() {

        getDriver().get("https://mamcupy.com/");
        getDriver().findElement(By.id("title-search-input2")).sendKeys("кружка\n");
        List<WebElement> actualResult = getDriver().findElements(By.xpath("//span[contains(text(), 'Кружка')]"));
        for (WebElement webElement : actualResult) {
            Assert.assertTrue(webElement.getAttribute("textContent").contains("Кружка"));
        }
    }
}
