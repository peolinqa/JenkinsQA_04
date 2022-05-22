import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NNinaTest extends BaseTest {
    @Test
    public void testUsedCarSearchPageH1() throws InterruptedException {
        getDriver().get("https://www.car.com/");

        getDriver().findElement(By.xpath("//button[contains(text(),'New Cars')]")).click();

        Thread.sleep(1000);

        WebElement usedCarsSection = getDriver().findElement(By.id("used"));
        usedCarsSection.click();

        String actualSearchNameH1 = getDriver().findElement(By.xpath("//h1[contains(text(),'Search Used Cars')]")).getText();

        Assert.assertEquals(actualSearchNameH1, "Search Used Cars");
    }
}
