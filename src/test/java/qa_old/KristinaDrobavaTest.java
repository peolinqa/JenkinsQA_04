package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class KristinaDrobavaTest extends BaseTest {

    @Test
    public void yahooKristinaDrobavaTest() throws InterruptedException {

        getDriver().get("https://www.yahoo.com");

        WebElement searchBox = getDriver().findElement(By.name("p"));
        WebElement searchButton = getDriver().findElement(By.xpath("//input[@id='ybar-search']"));

        searchBox.sendKeys("Kim Kardashian");

        Thread.sleep(3000);
        searchButton.click();

        searchBox = getDriver().findElement(By.name("p"));

        Assert.assertEquals(searchBox.getAttribute("value"), "Kim Kardashian");
    }
}
