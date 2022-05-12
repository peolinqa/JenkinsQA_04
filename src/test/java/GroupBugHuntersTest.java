import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class GroupBugHuntersTest extends BaseTest {

    @Test
    public void testOksanaBakatova()  {

        getDriver().get("https://www.etsy.com");
        WebElement searchBox = getDriver().findElement(By.xpath("//input[@name='search_query']"));
        WebElement searchButton = getDriver().findElement(By.xpath("//button[@type ='submit']"));
        searchBox.sendKeys("soap flower bouquet");
        searchButton.click();
        searchBox = getDriver().findElement(By.xpath("//input[@name='search_query']"));
        Assert.assertEquals(searchBox.getAttribute("value"), "soap flower bouquet");

    }
}
