import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.concurrent.TimeUnit;

public class MarinaTest extends BaseTest {

    @Test
    public void testSearchInputHasKeyAfterSearch() {
        String searchKey = "corolla";
        WebDriver webDriver = getDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.get("https://www.toyota.com/");

        WebElement searchBox = webDriver.findElement(By.xpath("//button[@class='search']"));
        searchBox.click();

        WebElement searchField = webDriver.findElement(By.id("search"));
        searchField.sendKeys(searchKey);

        WebElement searchGoButton = webDriver.findElement(By.xpath("//button[@type='submit']"));
        searchGoButton.click();

        webDriver.switchTo().frame(webDriver.findElement(By.xpath("//*[@id=\"vendor-iframe\"]/iframe")));
        String actualSearchInputText = webDriver.findElement(By.id("search-input")).getAttribute("value");
        Assert.assertEquals(actualSearchInputText, searchKey);
    }
}