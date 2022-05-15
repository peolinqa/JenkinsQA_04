import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class GenaShepelTest extends BaseTest {

    @Test
    public void genaShepelFirstTest() throws InterruptedException {

        getDriver().get("https://www.indeed.com/");

        WebElement searchButton = getDriver().findElement(By.className("yosegi-InlineWhatWhere-primaryButton"));
        WebElement searchBox = getDriver().findElement(By.id("text-input-what"));
//
        searchBox.sendKeys("QA");
        //Thread.sleep(2000);

        searchButton.click();

        searchBox = getDriver().findElement(By.id("text-input-what"));

        Assert.assertEquals(searchBox.getAttribute("value"), "QA");

        //searchButton = getDriver().findElement(By.className("yosegi-InlineWhatWhere-primaryButton"));


    }
    @Test
    public void genaShepelSecondtTest() throws InterruptedException {

        getDriver().get("https://google.com");

        WebElement searchBox = getDriver().findElement(By.name("q"));
        WebElement searchButton = getDriver().findElement(By.name("btnK"));
//
        searchBox.sendKeys("QA");
        //Thread.sleep(2000);

        searchButton.click();

        searchBox = getDriver().findElement(By.name("q"));

        Assert.assertEquals(searchBox.getAttribute("value"), "QA");

        //searchButton = getDriver().findElement(By.className("yosegi-InlineWhatWhere-primaryButton"));


    }

    @Test
    public void testMainMenuOfStartPage() throws  InterruptedException{

        String startMenu = "http://www.99-bottles-of-beer.net/";
        String browseLanguagesMenu = "http://www.99-bottles-of-beer.net/abc.html";
        String serchLanguagesMenu = "http://www.99-bottles-of-beer.net/search.html";
        String topListMenu = "http://www.99-bottles-of-beer.net/toplist.html";
        String guestBookMenu = "http://www.99-bottles-of-beer.net/guestbookv2.html";
        String submitNewLanguages = "http://www.99-bottles-of-beer.net/submitnewlanguage.html";


        String[] expectedResultArr = {
                startMenu,
                browseLanguagesMenu,
                serchLanguagesMenu,
                topListMenu,
                guestBookMenu,
                submitNewLanguages
        };

        String pathStartMenu = "//*[@id='menu']/li/a[@href='/']";
        String pathBrowseLanguagesMenu = "//*[@id='menu']/li/a[@href='/abc.html']";
        String pathSerchLanguagesMenu = "//*[@id='menu']/li/a[@href='/search.html']";
        String pathTopListMenu = "//*[@id='menu']/li/a[@href='/toplist.html']";
        String pathGuestBookMenu = "//*[@id='menu']/li/a[@href='/guestbookv2.html']";
        String pathSubmitNewLanguages = "//*[@id='menu']/li/a[@href='/submitnewlanguage.html']";

        String[] pathArr = {
                pathStartMenu,
                pathBrowseLanguagesMenu,
                pathSerchLanguagesMenu,
                pathTopListMenu,
                pathGuestBookMenu,
                pathSubmitNewLanguages
        };


        for(int i = 0; i < pathArr.length; i++){
            getDriver().get(expectedResultArr[i]);
            //Thread.sleep(3000);
            for(int j = 0; j < pathArr.length; j++) {
                WebElement nextPage = getDriver().findElement(By.xpath(pathArr[j]));
                nextPage.click();
                String actualResult = getDriver().getCurrentUrl();
                String expectedResult = expectedResultArr[j];
                Assert.assertEquals(expectedResult, actualResult);
                //Thread.sleep(3000);
            }
        }
        getDriver().quit();
    }

}

