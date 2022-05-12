import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AntonSadchikovTest extends BaseTest {

    @Test
    public void testDiceSelenium() throws InterruptedException {
        getDriver().get("https://dice.com");

        WebElement searchBox = getDriver().findElement(By.id("typeaheadInput"));
        WebElement searchButton = getDriver().findElement(By.id("submitSearch-button"));

        searchBox.sendKeys("test");

        searchButton.click();

        Thread.sleep(2000);

        searchBox = getDriver().findElement(By.id("typeaheadInput"));

        Assert.assertEquals(searchBox.getAttribute("value"), "test");

    }
}