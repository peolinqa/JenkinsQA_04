import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MarinaSAlawarTest extends BaseTest {
    @Test
    public void testAlawar () throws InterruptedException {

        getDriver().get("https://alawar.com");

        WebElement searchBox = getDriver().findElement(By.name("q"));
        WebElement searchButton = getDriver().findElement(By.className("b-search__submit"));

        searchBox.sendKeys("COOKING ACADEMY");

        Thread.sleep(1000);
        searchButton.click();

        WebElement d = getDriver().findElement(By.xpath("//h1[contains(text(), 'Found 3 games')]"));
        Assert.assertEquals(d.getText(),"FOUND 3 GAMES");

    }

}
