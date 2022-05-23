import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class SafronovaOlgaTest extends BaseTest {

    @Test
    public void Test () {

        getDriver().get("https://en.wikipedia.org/wiki/Main_Page");

        WebElement searchInput = getDriver().findElement(By.xpath("//input[@id='searchInput']"));
        searchInput.sendKeys("QA tester");

        searchInput.sendKeys(Keys.ENTER);

        WebElement searchResult = getDriver().findElement(By.xpath("//h1[@id='firstHeading']"));

        String searchResultText = searchResult.getText();

        Assert.assertEquals(searchResultText, "Search results");

    }

    @Test
    public  void SafOlgaTest ()  {

        getDriver().get("http://www.99-bottles-of-beer.net/");

        String expectedResult = "Welcome to 99 Bottles of Beer";


        WebElement menuBrowseLanguages = getDriver().findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='menu']/li/a[@href='/abc.html']")

        );
        menuBrowseLanguages.click();


        WebElement menuStart = getDriver().findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='menu']/li/a[@href='/']")

        );
        menuStart.click();


        WebElement h2 =getDriver().findElement(By.xpath("//body/div[@id='wrap']/div[@id='main']/h2"));

        String actualResult = h2.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
