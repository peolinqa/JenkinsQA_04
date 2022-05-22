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

    @Test
    public void testVeranikaMalazhavaya() {

        getDriver().get("https://www.99-bottles-of-beer.net/");

        getDriver().findElement(
                By.xpath("//ul[@id='menu']/li/a[@href='/search.html']")).click();
        WebElement searchBox = getDriver().findElement(
                By.xpath("//form[@id='searchlanguages']/p/input[@name='search']"));
        searchBox.sendKeys("java");
        getDriver().findElement(By.xpath("//input[@name='submitsearch']")).click();
        searchBox = getDriver().findElement(By.name("search"));

        Assert.assertEquals(searchBox.getAttribute("value"), "java");
    }
}
