import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class OlgaZTest extends BaseTest {
    @Test
    public void testOlgaZ() throws InterruptedException {
        getDriver().get("https://chaconne.ru/");

        WebElement searchBox = getDriver().findElement(By.name("q"));
        WebElement searchButton = getDriver().findElement(By.className("btn"));
        searchBox.sendKeys("Мастер и Маргарита");

        Thread.sleep(1000);

        searchButton.click();

        searchBox = getDriver().findElement(By.name("q"));
        Assert.assertEquals(searchBox.getAttribute("value"), "Мастер и Маргарита");
    }
}
