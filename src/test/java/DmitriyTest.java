import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class DmitriyTest extends BaseTest {

    @Ignore
    @Test
    public void testDmitriyRudoi() throws InterruptedException {
        getDriver().get("https://www.toyota.com/");

        WebElement searchButton = getDriver().findElement(
                By.xpath("//*[@id='tcom-header']/div[2]/nav/ul/li[4]/button"));
        Thread.sleep(500);
        searchButton.click();

        WebElement searchBox = getDriver().findElement(By.name("keyword"));
        searchBox.sendKeys("2022 Corolla");

        WebElement goButton = getDriver().findElement(
                By.xpath("//*[@id='tcom-header']/div[3]/div/div[2]/div/div[2]/div/form/button"));
        goButton.click();

        WebElement toyotaButton = getDriver().findElement(
                By.xpath("//*[@id='tcom-header']/div[2]/nav/ul/li[1]/a[1]"));
        toyotaButton.click();

        String expectedExploreAllVehicles = getDriver().findElement(By.xpath(
                "/html/body/div[1]/div/div[2]/div/section[1]/div/div/div[1]/div[1]/h2")).getText();

        Assert.assertEquals(expectedExploreAllVehicles, "Explore All Vehicles");
    }

}
