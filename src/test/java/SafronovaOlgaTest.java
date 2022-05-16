import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class SafronovaOlgaTest extends BaseTest {

    @Test
    public void Test () {

        WebDriver driver = getDriver();

        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        WebElement searchInput = driver.findElement(By.xpath("//input[@id='searchInput']"));
        searchInput.sendKeys("QA tester");

        searchInput.sendKeys(Keys.ENTER);

        WebElement searchResult = driver.findElement(By.xpath("//h1[@id='firstHeading']"));

        String searchResultText = searchResult.getText();

        Assert.assertEquals(searchResultText, "Search results");

    }

    @Test
    public  void SafOlgaTest ()  {

        WebDriver driver = getDriver();

        String url = "http://www.99-bottles-of-beer.net/";
        String expectedResult = "Welcome to 99 Bottles of Beer";

        driver.get(url);

        WebElement menuBrowseLanguages = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='menu']/li/a[@href='/abc.html']")

        );
        menuBrowseLanguages.click();


        WebElement menuStart = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='menu']/li/a[@href='/']")

        );
        menuStart.click();


        WebElement h2 = driver.findElement(By.xpath("//body/div[@id='wrap']/div[@id='main']/h2"));

        String actualResult = h2.getText();

        Assert.assertEquals(actualResult, expectedResult);


    }
}
