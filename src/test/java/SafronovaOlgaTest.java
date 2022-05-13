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

        WebDriver driver = getDriver();

        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        WebElement searchInput = driver.findElement(By.xpath("//input[@id='searchInput']"));
        searchInput.sendKeys("QA tester");

        searchInput.sendKeys(Keys.ENTER);

        WebElement searchResult = driver.findElement(By.xpath("//h1[@id='firstHeading']"));

        String searchResultText = searchResult.getText();

        Assert.assertEquals(searchResultText, "Search results");

        driver.quit();
    }
}
