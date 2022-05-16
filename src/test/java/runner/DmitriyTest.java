package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DmitriyTest extends BaseTest {
    @Test
    public void testDmitriyRudoi () throws InterruptedException {
        getDriver().get("https://www.toyota.com/");

        WebElement searchButton =
                getDriver().findElement(By.xpath("//*[@id=\"tcom-header\"]/div[2]/nav/ul/li[4]/button"));
        Thread.sleep(1000);
        searchButton.click();
        Thread.sleep(1000);

        WebElement searchBox = getDriver().findElement(By.name("keyword"));
        searchBox.sendKeys("2022 Corolla");

        WebElement goButton =
                getDriver().findElement(By.xpath("//*[@id=\"tcom-header\"]/" +
                        "div[3]/div/div[2]/div/div[2]/div/form/button"));
        Thread.sleep(1000);
        goButton.click();

        Thread.sleep(1000);

        WebElement toyotaButton =
                getDriver().findElement(By.xpath("//*[@id=\"tcom-header\"]/div[2]/nav/ul/li[1]/a[1]"));
        Thread.sleep(1000);
        toyotaButton.click();
        String main =
                getDriver().findElement(By.xpath("/html/body/div[1]/div/div[2]/div/" +
                        "section[1]/div/div/div[1]/div[1]/h2")).getText();

        Assert.assertEquals(main, "Explore All Vehicles");
        Thread.sleep(1000);

        getDriver().close();


    }

}
