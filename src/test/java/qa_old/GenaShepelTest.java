package qa_old;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class GenaShepelTest extends BaseTest {

    @Test
    public void genaShepelFirstTest(){

        getDriver().get("https://www.indeed.com/");
        getDriver().findElement(By.id("text-input-what")).sendKeys("QA");
        getDriver().findElement(By.className("yosegi-InlineWhatWhere-primaryButton")).click();

        Assert.assertEquals(getDriver().findElement(By.id("text-input-what")).getAttribute("value"), "QA");
    }

    @Test
    public void testMainMenuOfStartPage(){

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

