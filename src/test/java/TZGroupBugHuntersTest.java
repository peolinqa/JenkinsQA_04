
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;


public class TZGroupBugHuntersTest extends BaseTest {
    @Ignore
    @Test

    public void tatsianaZarankinaFirstTest() throws InterruptedException {
        getDriver().get("https://www.reddit.com/");


        WebElement searchBox = getDriver().findElement(By.xpath("//*[@id=\"header-search-bar\"]"));
        searchBox.sendKeys("Java");
        WebElement searchButton = getDriver().findElement(By.xpath("//*[@id=\"SearchDropdownContent\"]/a[6]/i"));
        searchButton.click();
        Thread.sleep(1000);
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.reddit.com/search/?q=Java");

    }
}
