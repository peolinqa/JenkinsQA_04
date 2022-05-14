import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Iterator;
import java.util.List;

public class SergeyTest extends BaseTest {

    @Ignore
    @Test
    public void testFirstSelenium() throws InterruptedException {
        getDriver().get("https://google.com");

        WebElement searchBox = getDriver().findElement(By.name("q"));
        WebElement searchButton = getDriver().findElement(By.name("btnK"));

        searchBox.sendKeys("Selenium");

        Thread.sleep(1000);

        searchButton.click();

        searchBox = getDriver().findElement(By.name("q"));

        Assert.assertEquals(searchBox.getAttribute("value"), "Selenium");
    }
}
