import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NNinaTest extends BaseTest {
    @Test
    public void testUsedCarSearchName() throws InterruptedException {
        getDriver().get("https://www.car.com/");
        String expectedTitle = "Car.com - We Do the Research, You Do the Driving";
        String title = getDriver().getTitle();

        Assert.assertEquals(title, expectedTitle);

        WebElement newCarSearchButton = getDriver().findElement(By.xpath("//button[contains(text(),'New Cars')]"));
        newCarSearchButton.click();

        Thread.sleep(1000);

        WebElement usedCarsSection = getDriver().findElement(By.id("used"));
        usedCarsSection.click();

        String actualSearchNameH1 = getDriver().findElement(By.xpath("//h1[contains(text(),'Search Used Cars')]")).getText();
        String expectedSearchNameH1 = "Search Used Cars";

        Assert.assertEquals(actualSearchNameH1, expectedSearchNameH1);
    }
}
