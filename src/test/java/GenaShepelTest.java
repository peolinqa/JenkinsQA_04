import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class GenaShepelTest extends BaseTest {

    @Test
    public void genaShepelFirstTest() throws InterruptedException {

        getDriver().get("https://www.indeed.com/");

        getDriver().findElement(By.id("text-input-what")).sendKeys("QA");

        getDriver().findElement(By.className("yosegi-InlineWhatWhere-primaryButton")).click();

        Assert.assertEquals(getDriver().findElement(By.id("text-input-what")).getAttribute("value"), "QA");

    }
    @Test
    public void genaShepelSecondtTest() throws InterruptedException {

        getDriver().get("https://google.com");

        WebElement searchBox = getDriver().findElement(By.name("q"));
        searchBox.sendKeys("QA");

        WebElement searchButton = getDriver().findElement(By.name("btnK"));
        searchButton.click();

        searchBox = getDriver().findElement(By.name("q"));

        Assert.assertEquals(searchBox.getAttribute("value"), "QA");
    }

    @Test
    public void testMainMenuOfStartPage() throws  InterruptedException{

        String[] expectedResultArr = {
                "http://www.99-bottles-of-beer.net/",
                "http://www.99-bottles-of-beer.net/abc.html",
                "http://www.99-bottles-of-beer.net/search.html",
                "http://www.99-bottles-of-beer.net/toplist.html",
                "http://www.99-bottles-of-beer.net/guestbookv2.html",
                "http://www.99-bottles-of-beer.net/submitnewlanguage.html"
        };

        String[] pathArr = {
                "//*[@id='menu']/li/a[@href='/']",
                "//*[@id='menu']/li/a[@href='/abc.html']",
                "//*[@id='menu']/li/a[@href='/search.html']",
                "//*[@id='menu']/li/a[@href='/toplist.html']",
                "//*[@id='menu']/li/a[@href='/guestbookv2.html']",
                "//*[@id='menu']/li/a[@href='/submitnewlanguage.html']"
        };

        for(int i = 0; i < pathArr.length; i++){
            getDriver().get(expectedResultArr[i]);

            for(int j = 0; j < pathArr.length; j++) {
                getDriver().findElement(By.xpath(pathArr[j])).click();

                Assert.assertEquals(expectedResultArr[j], getDriver().getCurrentUrl());
            }
        }
    }

}

