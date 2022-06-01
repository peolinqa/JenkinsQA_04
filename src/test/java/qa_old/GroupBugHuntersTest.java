package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class GroupBugHuntersTest extends BaseTest {

    @Ignore
    @Test
    public void testOksanaBakatova() throws InterruptedException {
        getDriver().get("https://www.etsy.com");

        WebElement searchBox = getDriver().findElement(By.xpath("//input[@name='search_query']"));
        searchBox.sendKeys("soap flower bouquet");
        Thread.sleep(2000);

        getDriver().findElement(By.xpath("//button[@type ='submit']")).click();

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
