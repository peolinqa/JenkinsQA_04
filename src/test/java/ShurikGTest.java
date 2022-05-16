import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class ShurikGTest extends BaseTest {

    @Test
    public void test99Bottles() {
        String url = "http://www.99-bottles-of-beer.net/";
        String expectedResult = "Welcome to 99 Bottles of Beer";

        getDriver().get(url);


        WebElement menuBrowsLanguges = getDriver().findElement(
                By.xpath("(//a[@href='/abc.html'])[1]"));
        menuBrowsLanguges.click();

        WebElement menuStart = getDriver().findElement(
                By.xpath("(//a[@href='/'])[1]"));
        menuStart.click();

        WebElement h2 = getDriver().findElement(
                By.xpath("(//h2)[2]"));

        String actualResult = h2.getText();

        Assert.assertEquals(actualResult, expectedResult);

        getDriver().quit();
    }
}
