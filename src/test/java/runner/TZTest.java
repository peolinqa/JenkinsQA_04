package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TZTest extends BaseTest {

    @Test

    public void tatsianaZarankinaFirstTest() throws InterruptedException {
        getDriver().get("https://www.reddit.com/");
        WebElement searchButton = getDriver().findElement(By.xpath("//*[@id=\"SearchDropdown\"]/form/label/div/i"));
        searchButton.click();

        WebElement searchBox = getDriver().findElement(By.xpath("//*[@id=\"header-search-bar\"]"));
        searchBox.sendKeys("Java");
        searchButton = getDriver().findElement(By.xpath("//*[@id=\"SearchDropdownContent\"]/a[6]/i"));
        searchButton.click();
        Thread.sleep(1000);
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.reddit.com/search/?q=Java");

    }
}
